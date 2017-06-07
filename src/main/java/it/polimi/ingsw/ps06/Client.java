package it.polimi.ingsw.ps06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

public class Client extends Observable implements Observer, Runnable {
	
	private static Client instance = null;

	private Socket socket;
	
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	public static Client getInstance(String host, int port) throws UnknownHostException, IOException {
		if (instance == null)
			instance = new Client(host, port);
		
		return instance;
	}
	
	public static Client getInstance()  {		
		return instance;
	}
	
	private Client(String host, int port) throws UnknownHostException, IOException {
		this.socket = new Socket(host, port);
		
		this.out = new ObjectOutputStream(socket.getOutputStream());
		this.in = new ObjectInputStream(socket.getInputStream());
	}
	
	public void receive() throws ClassNotFoundException, IOException {
		Message m;
		m = (Message) in.readObject();;
		notifyChangement(m);
	}
	
	/*
	public void send() throws IOException {
		
		String message = tastiera.readLine();
		
		out.println(message);
		System.out.println("Client sent: "+ message);
	}
	*/
	
	public void start() {
		
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

	/* MVC - SERVER IS MODEL AND SO IS AN OBSERVER OBJECT */
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}
