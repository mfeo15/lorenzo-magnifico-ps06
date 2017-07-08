package it.polimi.ingsw.ps06.networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps06.networking.messages.Message;

/**
 * Classe per la gestione dei Client
 *
 * @author  ps06
 * @version 1.2
 * @since   2017-06-02
 */
public class Client extends Observable implements Runnable, Observer {
	
	private static Client instance = null;

	private Socket socket;
	
	private String host;
	private int	port;
	
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	private boolean talkToTheCLI = true;;

	/**
	 * <p>SINGLETON DESIGN PATTERN</p>
	 * <p>Metodo per ottenere l'istanza statica della classe, in modo univoco</p>
	 * 
	 * @return	istanza dell'oggetto Client
	 */
	public static Client getInstance() {
		if (instance == null)
				instance = new Client();
		
		return instance;
	}
	
	@Override
	public void run() {
		
		while (true) {
			try {
				receive();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Metodo di configurazione iniziale, istanziando la connessione Socket ed aprendo
	 * gli stream in e out
	 * 
	 * @throws	UnknownHostException	se l'attributo host e port non corrispondono ad un Server in ascolto	
	 * @throws	IOException				se lo stream in e out non è istanziato con successo
	 */
	public void init() throws UnknownHostException, IOException {
		this.socket = new Socket(host, port);
		
		this.out = new ObjectOutputStream(socket.getOutputStream());
		this.in = new ObjectInputStream(socket.getInputStream());
	}
	
	/**
	 * Metodo per salvare i parametri di connessione
	 * 
	 * @param	host	indirizzo di rete del server
	 * @param 	port	porta di rete del server	
	 */
	public void setupParameters(String host, int port) {
		this.host = host;
		this.port = port;
	}
	
	public boolean canTalkToTheCLI() {
		return talkToTheCLI;
	}

	public void setTalkToTheCLI(boolean talkToTheCLI) {
		this.talkToTheCLI = talkToTheCLI;
	}
	
	/**
	 * Metodo di ricezione sullo stream in ingresso di messaggi spediti dal server.
	 * Si occupa di passare il messaggio al controller corretto.
	 * 
	 * @throws ClassNotFoundException	se la lettura del messaggio non è un tipo atteso
	 * @throws IOException				se la lettura sullo stream in genera eccesioni non gestite
	 */
	private void receive() throws ClassNotFoundException, IOException {
		Message m = (Message) in.readObject();

		if ( canTalkToTheCLI() ) System.out.println("[ CLIENT ] Message received : " + m + "\n");

		notifyChangement(m);
	}
	
	/**
	 * Metodo di invio dei mesaggi sullo stream in uscita
	 * 
	 * @param message		messaggio da spedire al server
	 * 
	 * @throws IOException	se la scrittura sullo stream out genera eccezioni non gestite
	 */
	private void send(Message message) throws IOException {
		
		if (socket == null)
			return;
		
		out.writeObject(message);
		out.flush();
		
		if ( canTalkToTheCLI() ) System.out.println("[ CLIENT ] Message sent : " + message + "\n");
	}
	
	/**
	 * Metodo di invio ASYNC attraverso un Thread secondario
	 * 
	 * @param	message		messaggio da spedire al server
	 */
	public void asyncSend(final Message message){
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
	}
	
	/* MVC - SERVER IS MODEL AND SO IS AN OBSERVABLE OBJECT */
	
	public void notifyChangement(Object o) {
	
		setChanged();
		notifyObservers(o);
	}
	
	public void addNewObserver(Observer obs) {
		addObserver(obs);
	}
	
	public void deleteAnObserver(Observer obs) {
		deleteObserver(obs);
	}
	
	public void deleteAllObservers() {
		deleteObservers();
	}

	/* MVC - SERVER IS MODEL AND SO IS AN OBSERVER OBJECT */
	@Override
	public void update(Observable o, Object arg) {
		
		if (!(arg instanceof Message))
			return;
		
		asyncSend((Message) arg);
	}
}
