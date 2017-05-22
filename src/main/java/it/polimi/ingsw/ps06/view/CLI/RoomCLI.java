package it.polimi.ingsw.ps06.view.CLI;

import java.util.Observable;
import java.util.Scanner;

import it.polimi.ingsw.ps06.controller.RoomControllerCLI;

public class RoomCLI extends Observable {
	
	private Scanner input;
	
	public RoomCLI(Scanner input) {
		
		this.input = input;
	}
	
	public Scanner getScanner() {
		return input;
	}
	
	public void addNewControllerObserver(RoomControllerCLI controller) {
		addObserver(controller);
	}
	
	public void show() {
		System.out.println("ROOM");
		
		//readInput();
	}
	
	public void readInput() {
		int i = Integer.parseInt(input.nextLine());
		
		setChanged();
		notifyObservers(i);
	}
}
