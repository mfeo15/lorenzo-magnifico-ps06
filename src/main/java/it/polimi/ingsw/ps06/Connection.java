package it.polimi.ingsw.ps06;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Connection implements Runnable {
	
	private Socket socket;
	
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	private String name;
	
	private boolean active = true;
	
	public Connection(Socket socket) throws UnknownHostException, IOException {
		this.socket = socket;
		
		this.out = new ObjectOutputStream(socket.getOutputStream());
		this.in = new ObjectInputStream(socket.getInputStream());

	}
	
	public SocketAddress ID() {
		return socket.getRemoteSocketAddress();
	}
	
	private synchronized boolean isActive(){
		return active;
	}
	
	@Override
	public void run() {
		try{
			send("Benvenuto! Chi sei?");
			String read = in.nextLine();
			name = read;
			//server.rednezvous(this, name);
			while(isActive()){
				read = in.nextLine();				
				//notify(read);
			}			
		} catch (IOException | NoSuchElementException e) {
			System.err.println("Errore!");
		}finally{
			close();
		}		
	}
	
	public void send(String message) {
		out.println(message);
		out.flush();
	}
	
	public void asyncSend(final String message){
		new Thread(new Runnable() {			
			@Override
			public void run() {
				send(message);				
			}
		}).start();
	}
	
	public synchronized void closeConnection() {		
		send("Connessione terminata!");
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

}
