package it.polimi.ingsw.ps06.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import it.polimi.ingsw.ps06.controller.RoomController;
import it.polimi.ingsw.ps06.model.events.BoardHasLoaded;
import it.polimi.ingsw.ps06.model.events.EventClose;
import it.polimi.ingsw.ps06.model.events.RoomHasLoaded;
import it.polimi.ingsw.ps06.model.messages.MessageGameStart;
import it.polimi.ingsw.ps06.model.messages.MessageUser;
import it.polimi.ingsw.ps06.model.events.StoryBoard2Board;
import it.polimi.ingsw.ps06.model.events.StoryBoard2Room;

public class RoomCLI extends Observable implements Room {
	
	private BufferedReader input;
	
	private Thread reader;
	
	private boolean reading = true;
	
	
	public RoomCLI(BufferedReader input) {
		
		this.input = input;
	}
	
	public BufferedReader getScanner() {
		return input;
	}
	
	public void addNewControllerObserver(RoomController controller) {
		addObserver(controller);
	}
	
	public void readThreaded() {
		reader = new Thread(new Runnable() {			
			@Override
			public void run() {

				while (reading) {

					String s;
					//while ( !(s = input.nextLine()).equals("exit") ) read(s);
					try {
						/*while ( !(s = input.readLine()).equalsIgnoreCase("exit") )*/ read( input.readLine() );

						//notifyExit();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		});
		
		reader.start();
	}
	
	public void read(String s) throws IOException {
		
		if (s == null)
			return;
		
		if( ("start").equals(s) ) {
			startGame();
			return;
		}
		
		if( ("?").equals(s) ) {
			giveInfo();
			return;
		}
		
		if( ("login").equals(s) ) {
			System.out.print(" Username > ");
			String s1 = input.readLine();
			System.out.print(" Password > ");
			String s2 = input.readLine();
			giveCredentials(s1,s2);
			return;
		}
	}
	
	public void show() {
		System.out.println();
		System.out.println("----- THE ROOM -----");
		System.out.println();
		System.out.println(" Inserisci il comando. Scrivi \"?\" per più informazioni");
		System.out.println();
		System.out.print(" > ");
		
		hasLoaded();
	}

	public void giveInfo() {
		System.out.println("----- LISTA COMANDI -----");
		System.out.println();
		System.out.println(" Usa il comando \"start\" per partire");
		System.out.println(" Usa il comando \"exit\" per uscire");
		System.out.println(" Usa il comando \"login\" per fare il login");
		System.out.println();
		System.out.print(" > ");
	}
	
	@Override
	public void setPlayer(String name, int index) {
		System.out.println("--> "+name+" Si è connesso alla stanza!");
		System.out.println();
		System.out.print(" > ");
	}

	@Override
	public void clearPlayers() {
		System.out.println("--> La stanza è ora vuota!");
		System.out.println();
		System.out.print(" > ");
	}

	@Override
	public void giveCredentials(String username, String password) {
		System.out.println("--> Login Effettuato!");
		System.out.println();
		System.out.print(" > ");
		
		setChanged();
		MessageUser userMessage = new MessageUser(username);
		notifyObservers(userMessage);
	}

	@Override
	public boolean checkLogin() {
		return false;
	}

	@Override
	public void addNewObserver(Observer o) {
		addObserver(o);
		
	}

	@Override
	public void startGame() {
		
		setChanged();		
		MessageGameStart gameStart;
		gameStart = new MessageGameStart();
		notifyObservers(gameStart);
		
	}


	@Override
	public void notifyExit() {
		
		reading = false;
		
		setChanged();
		EventClose close = new EventClose();
		notifyObservers(close);
		
	}

	@Override
	public void hasStarted() {
		
		reading = false;
		
		setChanged();
		StoryBoard2Board storyBoard = new StoryBoard2Board(new BoardCLI(input));
		notifyObservers(storyBoard);
	}

	@Override
	public void hasLoaded() {
		
		readThreaded();
		
		setChanged();
		RoomHasLoaded roomLoaded = new RoomHasLoaded();
		notifyObservers(roomLoaded);
	}
	
	
	
	
	
}
