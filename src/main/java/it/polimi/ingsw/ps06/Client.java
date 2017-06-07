package it.polimi.ingsw.ps06;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps06.model.messages.Client2Server;
import it.polimi.ingsw.ps06.model.messages.Message;

public class Client extends Observable implements Runnable, Observer {
	
	private static Client instance = null;

	private Socket socket;
	
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
	
	public void receive() throws ClassNotFoundException, IOException {
		Message m;
		m = (Message) in.readObject();;
		notifyChangement(m);
	}
	
	
	private void send(Message message) throws IOException{
		
		out.writeObject(message);
		out.flush();
		
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
		
		Client.getInstance().asyncSend((Message) arg);
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
