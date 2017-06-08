package it.polimi.ingsw.ps06.view;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import it.polimi.ingsw.ps06.controller.MenuController;
import it.polimi.ingsw.ps06.model.events.EventClose;
import it.polimi.ingsw.ps06.model.events.StoryBoard2Room;

public class MenuCLI extends Observable implements Menu {
	
	private Scanner input;
	
	public MenuCLI(Scanner input) {
		
		this.input = input;
		
		show();
	}
	
	public Scanner getScanner() {
		return input;
	}
	
	public void addNewControllerObserver(MenuController controller) {
		addObserver(controller);
	}
	
	public void show() {
		
		System.out.println();
		System.out.println(" --- Lorenzo il Magnifio --- ");
		System.out.println();
		System.out.println(" 1) Nuova Partita");
		System.out.println(" 2) Esci");
		System.out.println();
		System.out.print(" > ");
		
		while(true) readInput();
	}
	
	public void readInput() {
		int i = Integer.parseInt(input.nextLine());
		
		if(i==1) startGame();
		if(i==2) notifyExit();
	}
	
	public void showErrorMessage(String messageError) {
		System.out.println();
		System.out.print(messageError);
	}

	@Override
	public void addNewObserver(Observer o) {
		addObserver(o);
		
	}

	@Override
	public void startGame() {
		setChanged();
		StoryBoard2Room storyBoard;
		storyBoard = new StoryBoard2Room(new RoomCLI(input));
		notifyObservers(storyBoard);
		
	}


	@Override
	public void notifyExit() {
		setChanged();
		EventClose close = new EventClose();
		notifyObservers(close);
		
	}
	
	public static void main(String[] args) throws IOException
    {   
        MenuCLI a = new MenuCLI(new Scanner(System.in));
        a.show();
    }  
}
