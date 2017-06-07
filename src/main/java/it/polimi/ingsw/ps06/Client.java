package it.polimi.ingsw.ps06;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps06.model.messages.Message;

public class Client extends Observable implements Observer {
	
	private static Client instance = null;

	private Socket socket;
	
	private String host;
	private int	port;
	
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	public static Client getInstance() {
		if (instance == null)
			try {
				instance = new Client();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return instance;
	}
	
	private Client() throws UnknownHostException, IOException {
		
		this.out = new ObjectOutputStream(socket.getOutputStream());
		this.in = new ObjectInputStream(socket.getInputStream());
	}
	
	public void setupParameters(String host, int port) {
		this.host = host;
		this.port = port;
	}
	
	public void receive() throws ClassNotFoundException, IOException {
		Message m;
		m = (Message) in.readObject();;
		notifyChangement(m);
	}
	
	
	private void send(Message message) throws IOException{
		
		out.writeObject(message);
		
		System.out.println("Client sent: "+ message);
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

	
	
	public void start() {
		
		try {
			this.socket = new Socket(host, port);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		new Thread(new Runnable() {			
			@Override
			public void run() 
			{
				while (true) {

					try {
						receive();
					} catch (ClassNotFoundException | IOException e) {
						e.printStackTrace();
					}
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

	/* MVC - SERVER IS MODEL AND SO IS AN OBSERVER OBJECT */
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}
