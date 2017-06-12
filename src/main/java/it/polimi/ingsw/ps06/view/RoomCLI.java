package it.polimi.ingsw.ps06.view;

import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import it.polimi.ingsw.ps06.controller.RoomController;
import it.polimi.ingsw.ps06.model.events.BoardHasLoaded;
import it.polimi.ingsw.ps06.model.events.EventClose;
import it.polimi.ingsw.ps06.model.events.RoomHasLoaded;
import it.polimi.ingsw.ps06.model.messages.MessageUser;
import it.polimi.ingsw.ps06.model.events.StoryBoard2Board;
import it.polimi.ingsw.ps06.model.events.StoryBoard2Room;

public class RoomCLI extends Observable implements Room {
	
	private Scanner input;
	
	public RoomCLI(Scanner input) {
		
		this.input = input;
	}
	
	public Scanner getScanner() {
		return input;
	}
	
	public void addNewControllerObserver(RoomController controller) {
		addObserver(controller);
	}
	
	public void show() {
		System.out.println();
		System.out.println("----- THE ROOM -----");
		System.out.println();
		System.out.println(" Inserisci il comando. Scrivi \"?\" per più informazioni");
		System.out.println();
		System.out.print(" > ");
		
		hasLoaded();
		while(true) readInput();
	}
	
	public void readInput() {
		String s = input.nextLine();
		
		if(("start").equals(s)) startGame();
		if(("?").equals(s)) giveInfo();
		if(("exit").equals(s)) notifyExit();
		if(("login").equals(s)){
			System.out.print(" > ");
			String s1 = String.valueOf(input.nextLine());
			System.out.print(" > ");
			String s2 = String.valueOf(input.nextLine());
			giveCredentials(s1,s2);
		}
	}

	public void giveInfo() {
		System.out.println("----- LISTA COMANDI -----");
		System.out.println();
		System.out.println(" Usa il comando \"start\" per partire");
		System.out.println(" Usa il comando \"exit\" per uscire");
		System.out.println(" Usa il comando \"login Username Password\" per fare il login");
		System.out.println();
		System.out.print(" > ");

		while(true) readInput();
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
		MessageUser userMessage;
		
		userMessage = new MessageUser(username);
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
		//(new BoardCLI(input)).show();  
		
		setChanged();
		StoryBoard2Board storyBoard;
		storyBoard = new StoryBoard2Board(new BoardCLI(input));
		notifyObservers(storyBoard);
		
	}


	@Override
	public void notifyExit() {
		setChanged();
		EventClose close = new EventClose();
		notifyObservers(close);
		
	}

	@Override
	public void hasStarted() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hasLoaded() {
		setChanged();
		RoomHasLoaded roomLoaded = new RoomHasLoaded();
		notifyObservers(roomLoaded);
		
	}
	
	
	
	
	
}
