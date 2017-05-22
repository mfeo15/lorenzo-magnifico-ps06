package it.polimi.ingsw.ps06.view.CLI;

import java.io.PrintStream;
import java.util.Observable;
import java.util.Scanner;

import it.polimi.ingsw.ps06.controller.MenuControllerCLI;

public class MenuCLI extends Observable {
	
	private Scanner input;
	
	public MenuCLI(Scanner input) {
		
		this.input = input;
	}
	
	public Scanner getScanner() {
		return input;
	}
	
	public void addNewControllerObserver(MenuControllerCLI controller) {
		addObserver(controller);
	}
	
	public void show() {
		System.out.println();
		System.out.println(" --- Lorenzo il Magnifio --- ");
		System.out.println();
		System.out.println(" 1) Nuova Partita");
		System.out.println(" 2) Impostazioni");
		System.out.println(" 3) Credits");
		System.out.println();
		System.out.print(" > ");
		
		readInput();
	}
	
	public void readInput() {
		int i = Integer.parseInt(input.nextLine());
		
		setChanged();
		notifyObservers(i);
	}
}
