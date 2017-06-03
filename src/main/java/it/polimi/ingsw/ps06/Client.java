package it.polimi.ingsw.ps06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client implements Runnable {

	private Socket socket;
	
	//private ObjectOutputStream out;
	//private ObjectInputStream in;
	
	private Scanner in;
	private PrintStream out;
	
	private BufferedReader tastiera;
	
	public Client(String host, int port) throws UnknownHostException, IOException {
		this.socket = new Socket(host, port);
		
		//this.out = new ObjectOutputStream(socket.getOutputStream());
		//this.in = new ObjectInputStream(socket.getInputStream());
		
		this.in = new Scanner(socket.getInputStream());
		this.out = new PrintStream(socket.getOutputStream());
		
		this.tastiera = new BufferedReader(new InputStreamReader(System.in));
	}
	
	public void send() throws IOException {
		
		String message = tastiera.readLine();
		
		out.println(message);
		System.out.println("Client sent: "+ message);
		
		System.out.println("Server said: " + in.nextLine());
	}
	
	@Override
	public void run() {
		while (true) {
			try {
			send();
			} catch (IOException e) {}
		}
	}
}
