package it.polimi.ingsw.ps06;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Connection implements Runnable {
	
	private Socket socket;
	private SocketServer connectedToServer;
	
	/*
	private ObjectOutputStream out;
	private ObjectInputStream in;
	*/
	
	private Scanner in;
	private PrintStream out;
	
	private boolean active = true;
	
	public Connection(Socket socket, SocketServer connectedToServer) throws UnknownHostException, IOException {
		this.socket = socket;
		this.connectedToServer = connectedToServer;
	}
	
	private synchronized boolean isActive(){
		return active;
	}
	
	@Override
	public void run() {
		try {
			/*
			this.out = new ObjectOutputStream(socket.getOutputStream());
			this.in = new ObjectInputStream(socket.getInputStream());
			*/
			
			this.in = new Scanner(socket.getInputStream());
			this.out = new PrintStream(socket.getOutputStream());
			
			connectedToServer.rednezvous(this);
			
			String read;
			while(isActive()){
				read = in.nextLine();				
				System.out.println("Client said: " + read);
				
				out.println("I'm a Computer! I'am a Computery Guy!");
			}			
		} catch ( NoSuchElementException e) {
			System.err.println("Errore!");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			close();
		}		
	}
	
	public void send(String message) {
		//out.println(message);
		//out.flush();
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
