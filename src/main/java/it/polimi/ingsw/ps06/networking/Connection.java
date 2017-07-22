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
import it.polimi.ingsw.ps06.networking.messages.MessageEvent;
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


	@Override
	public void run() {
		try 
		{
			SocketServer.getInstance().rednezvous(this);

			while(isActive()) receive();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	/**
	 * Costruttore della classe
	 * 
	 * @param 	socket					Socket del Client di riferimento
	 * 
	 * @throws 	UnknownHostException	se l'host indicato non corrisponde ad un Server pronto all'accettazione di Client
	 * @throws 	IOException				se l'istanza degli stream in/out genera eccezioni non gestite
	 */
	public Connection(Socket socket) throws UnknownHostException, IOException {
		this.socket = socket;

		this.out = new ObjectOutputStream(socket.getOutputStream());
		this.in = new ObjectInputStream(socket.getInputStream());

		this.guestString = "Guest" + (new Random()).nextInt(9999);
	}

	/**
	 * Getter per un identificativo univoco della connessione
	 * 	
	 * @return	identificativo univoco della connessione corrente
	 */
	public SocketAddress getID() {
		return this.socket.getRemoteSocketAddress();
	}

	/**
	 * Getter per lo username associato alla connessione attuale
	 * 
	 * @return	lo username della conessione
	 */
	public String getUsername() {
		if (associatedUser == null)
			return guestString;

		return associatedUser.getUsername();
	}

	/**
	 * Setter per lo username associato alla connessione attuale
	 * 
	 * @param	username	lo username della conessione da settare
	 */
	public void setUsername(String username) {
		if (associatedUser == null)
			guestString = username;

		this.associatedUser.setUsername(username);
	}

	/**
	 * Getter per l'indirizzo di rete della connessione
	 * 
	 * @return	coordinate di rete della connessione attuale
	 */
	public String getInetAddress() {
		return socket.getInetAddress().toString();
	}

	/**
	 * Getter per lo User associato alla connessione
	 * 
	 * @return	l'untenza associata alla connessione
	 */
	public User getAssociatedUser() {
		return associatedUser;
	}

	/**
	 * Setter lo User associato alla connessione
	 * 
	 * @param	associatedUser		utenza da associare alla connessione
	 */
	public void setAssociatedUser(User associatedUser) {
		this.associatedUser = associatedUser;
	}

	/**
	 * Getter per il parametro active
	 * 
	 * @return	true	se il parametro è settato a true
	 */
	private synchronized boolean isActive(){
		return active;
	}

	/**
	 * Setter per il player associato alla connessione
	 * 
	 * @param	player	giocatore da associare alla connessione
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * Getter per il player associato alla connesione
	 * 
	 * @return	il giocatore associato alla connesione
	 */
	public Player getPlayer() {
		return this.player;
	}

	/**
	 * Metodo per la ricezione di messaggi in entrata sullo stream in
	 * 
	 * @throws ClassNotFoundException	se la lettura del messaggio non è un tipo atteso
	 * @throws IOException				se la lettura sullo stream in genera eccezioni non gestite
	 */
	public void receive() throws ClassNotFoundException, IOException {
		Message m = (Message) in.readObject();

		System.out.println("[ SERVER ] Message received from " + getInetAddress() + " (" + getUsername() + "): " + m +"\n");

		if ( m instanceof MessageEvent ) {

			((MessageEvent) m).accept(new MessageParser( this ));
			return;
		}

		((Message) m).accept(new MessageParser(this));
	}

	/**
	 * Metodo per l'invio di messaggi sullo stream out
	 * 
	 * @param	message			messaggio da inviare al Client
	 * 
	 * @throws	IOException		se la lettura dello stream causa errori
	 */
	private void send(Message message) throws IOException {		
		out.writeObject(message);
		out.flush();

		System.out.println("[ SERVER ] Message sent to " + getInetAddress() + " (" + getUsername() + "): " + message +"\n");
	}


	/**
	 * Gestione Asincrona dell'invio di un messaggio al Client tramite Thread secondario
	 * 
	 * @param	message		messaggio da inviare al Client
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


	/**
	 * Metodo per la chiusura della connessione a seguito di una disconnessione del Client
	 */
	public synchronized void closeConnection() {		
		try {
			SocketServer.getInstance().deregisterConnection(this);
			socket.close();

			System.out.println("[ SERVER ] The Client " + getInetAddress() + " (" + getUsername() + "): has closed the connection \n");

		} catch (IOException e) {
		}
		active = false;
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
