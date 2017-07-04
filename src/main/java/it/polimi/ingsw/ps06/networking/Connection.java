package it.polimi.ingsw.ps06.networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import it.polimi.ingsw.ps06.model.Player;
import it.polimi.ingsw.ps06.networking.messages.Broadcast;
import it.polimi.ingsw.ps06.networking.messages.EventMessage;
import it.polimi.ingsw.ps06.networking.messages.Message;
import it.polimi.ingsw.ps06.networking.messages.MessageParser;
import it.polimi.ingsw.ps06.networking.messages.Server2Client;

/**
* Classe per la gestione delle singole connessioni al Server.
* Implementazione dell'interfaccia Runnable per la gestione tramite Threads
*
* @author  ps06
* @version 1.0
* @since   2017-06-03 
*/
public class Connection implements Runnable, Observer {
	
	private Socket socket;
	
	private Player player;
	
	private User associatedUser;
	
	private String guestString;
	
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	private boolean active = true;
	
	/**
	 * Costruttore della classe
	 * 
	 * @param socket				Socket del Client di riferimento
	 * @param connectedToServer		Server al quale le connessioni fanno riferimento
	 * 
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public Connection(Socket socket) throws UnknownHostException, IOException {
		this.socket = socket;
		
		this.out = new ObjectOutputStream(socket.getOutputStream());
		this.in = new ObjectInputStream(socket.getInputStream());
		
		this.guestString = "Guest" + (new Random()).nextInt(9999);
	}
	
	public SocketAddress getID() {
		return this.socket.getRemoteSocketAddress();
	}
	
	private synchronized boolean isActive(){
		return active;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	@Override
	public void run() {
		try {
			SocketServer.getInstance().rednezvous(this);
			
			while(isActive())
				receive();
						
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public void receive() throws ClassNotFoundException, IOException {
		Message m = (Message) in.readObject();
		
		System.out.println("[ SERVER ] Message received from " + getInetAddress() + " (" + getUsername() + "): " + m +"\n");
		
		if ( m instanceof EventMessage ) {
			
			((EventMessage) m).accept(new MessageParser( this ));
			return;
		}
			
		((Message) m).accept(new MessageParser(this));
	}
	
	/**
	 * Invio di un particolare Messaggio
	 * 
	 * @param message	Messaggio da inviare al Client
	 */
	private void send(Message message) throws IOException {		
		out.writeObject(message);
		out.flush();
		
		System.out.println("[ SERVER ] Message sent to " + getInetAddress() + " (" + getUsername() + "): " + message +"\n");
	}
	
	
	/**
	 * Gestione Asincrona dell'invio di un messaggio al Client
	 * 
	 * @param message	Messaggio da inviare al Client
	 */
	public void asyncSend(final Message message) {
		new Thread(new Runnable() {			
			@Override
			public void run() {
				try {
					send(message);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
		
		try { Thread.sleep(300); } catch (InterruptedException e1) { e1.printStackTrace(); } 
	}
	
	
	public synchronized void closeConnection() {		
		try {
			SocketServer.getInstance().deregisterConnection(this);
			socket.close();
			
			System.out.println("[ SERVER ] The Client " + getInetAddress() + " (" + getUsername() + "): has closed the connection \n");
			
		} catch (IOException e) {
		}
		active = false;
	}

	public String getUsername() {
		if (associatedUser == null)
			return guestString;
		
		return associatedUser.getUsername();
	}
	
	public void setUsername(String username) {
		if (associatedUser == null)
			guestString = username;
		
		this.associatedUser.setUsername(username);
	}
	
	public String getInetAddress() {
		return socket.getInetAddress().toString();
	}

	public User getAssociatedUser() {
		return associatedUser;
	}
	
	public void setAssociatedUser(User associatedUser) {
		this.associatedUser = associatedUser;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		
		if (!(arg instanceof Message))
			return;
		
		if (arg instanceof Server2Client)
			if (arg instanceof Broadcast) {
				asyncSend((Message) arg);
			} else {
				if ( ((Server2Client) arg).getRecipient() == this.getPlayer().getID() )
					asyncSend((Server2Client) arg);
			}
	}
}
