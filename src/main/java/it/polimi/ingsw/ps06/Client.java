package it.polimi.ingsw.ps06;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;

import it.polimi.ingsw.ps06.model.messages.Message;

public class Client extends Observable implements Runnable, Observer {
	
	private static Client instance = null;

	private Socket socket;
	
	private Queue<Message> queuedMessages = new LinkedList<Message>();
	
	private String host;
	private int	port;
	
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	public static Client getInstance() {
		if (instance == null)
				instance = new Client();
		
		return instance;
	}
	
	public void init() throws UnknownHostException, IOException {
		this.socket = new Socket(host, port);
		
		this.out = new ObjectOutputStream(socket.getOutputStream());
		this.in = new ObjectInputStream(socket.getInputStream());
	}
	
	public void setupParameters(String host, int port) {
		this.host = host;
		this.port = port;
	}
	
	private void receive() throws ClassNotFoundException, IOException {
		Message m;
		m = (Message) in.readObject();;
		
		System.out.println("[ ] Message received : " + m + "\n");
		
		notifyChangement(m);
	}
	
	
	private void send(Message message) throws IOException{
		
		out.writeObject(message);
		out.flush();
		
		System.out.println("[ ] Message sent : " + message + "\n");
	}
	
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
	
	/* MVC - SERVER IS MODEL AND SO IS AN OBSERVABLE OBJECT */
	
	public void notifyChangement(Object o) {
		
		System.out.println("OBSERVERS: " + countObservers() );
		
		if ( countObservers() == 0 ) {
			System.out.println("QUED");
			queuedMessages.add((Message) o);
			return;
		}
		
		setChanged();
		notifyObservers(o);
	}
	
	public void addNewObserver(Observer obs) {
		addObserver(obs);
		
		for (Message m : queuedMessages)
			notifyChangement(m);
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
}
