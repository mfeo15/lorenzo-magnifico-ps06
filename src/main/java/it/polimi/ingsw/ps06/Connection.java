package it.polimi.ingsw.ps06;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps06.model.messages.EventMessage;
import it.polimi.ingsw.ps06.model.messages.Message;
import it.polimi.ingsw.ps06.model.messages.MessageParser;

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
	
	private String username;
	
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
		
		this.username = "Guest" + (int)(Math.random() * (9999 - 1) + 1);
	}
	
	private synchronized boolean isActive(){
		return active;
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
		
		System.out.println("[ ] Message received from " + getInetAddress() + " (" + getUsername() + "): " + m +"\n");
		
		if ( m instanceof EventMessage ) {
			// THIS IS AN EVENT OF GAME
			return;
		}
		
		if ( m instanceof Message ) {
			
			((Message) m).accept(new MessageParser(this));
		}
	}
	
	/**
	 * Invio di un particolare Messaggio
	 * 
	 * @param message	Messaggio da inviare al Client
	 */
	private void send(Message message) throws IOException {		
		out.writeObject(message);
		out.flush();
		
		System.out.println("[ ] Message sent : " + message + "\n");
	}
	
	
	/**
	 * Gestione Asincrona dell'invio di un messaggio al Client
	 * 
	 * @param message	Messaggio da inviare al Client
	 */
	public void asyncSend(final Message message){
		new Thread(new Runnable() {			
			@Override
			public void run() {
				try {
					send(message);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
		}).start();
	}
	
	
	public synchronized void closeConnection() {		
		//send("Connessione terminata!");
		try {
			socket.close();
		} catch (IOException e) {
		}
		active = false;
	}
	
	private void close() {
		closeConnection();		
		System.out.println("Deregistro il client!");
		//server.deregisterConnection(this);
	}

	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getInetAddress() {
		return socket.getInetAddress().toString();
	}

	
	@Override
	public void update(Observable o, Object arg) {
		
		if (!(arg instanceof Message))
			return;
		
		asyncSend((Message) arg);
	}
}
