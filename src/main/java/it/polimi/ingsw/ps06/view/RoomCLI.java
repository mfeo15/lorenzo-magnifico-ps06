package it.polimi.ingsw.ps06.view;

import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import it.polimi.ingsw.ps06.controller.RoomController;
import it.polimi.ingsw.ps06.model.messages.EventClose;
import it.polimi.ingsw.ps06.model.messages.MessageUser;
import it.polimi.ingsw.ps06.model.messages.StoryBoard2Board;
import it.polimi.ingsw.ps06.model.messages.StoryBoard2Room;

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
		System.out.println("----- THE ROOM -----");
		System.out.println();
		System.out.println(" Inserisci il comando. Scrivi \"?\" per più informazioni");

		while(true) readInput();
	}
	
	public void readInput() {
		String s = String.valueOf(input.nextLine());
		
		if(s=="start") startGame();
		if(s=="?") giveInfo();
		if(s=="exit") notifyExit();
		if(s=="login"){
			System.out.println(" > ");
			String s1 = String.valueOf(input.nextLine());
			System.out.println(" > ");
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

		while(true) readInput();
	}
	
	@Override
	public void setPlayer(String name, int index) {
		System.out.println("--> "+name+" Si è connesso alla stanza!");
		
	}

	@Override
	public void clearPlayers() {
		System.out.println("--> La stanza è ora vuota!");
		
	}

	@Override
	public void giveCredentials(String username, String password) {
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
	
	
}
