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

/**
* Classe per la gestione delle singole connessioni al Server.
* Implementazione dell'interfaccia Runnable per la gestione tramite Threads
*
* @author  ps06
* @version 1.0
* @since   2017-06-03 
*/
public class Connection implements Runnable {
	
	private Socket socket;
	
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
	}
	
	private synchronized boolean isActive(){
		return active;
	}
	
	@Override
	public void run() {
		try {
			SocketServer.getInstance().rednezvous(this);
			
			String read;
			while(isActive()){
				//read = in.nextLine();				
				//System.out.println("Client said: " + read);
				
				//out.println("I'm a Computer! I'am a Computery Guy!");
			}			
		} catch ( NoSuchElementException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			close();
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

	public String ID() {
		return socket.getInetAddress().toString();
	}
}
