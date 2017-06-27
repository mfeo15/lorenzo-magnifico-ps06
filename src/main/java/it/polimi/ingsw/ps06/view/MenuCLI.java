package it.polimi.ingsw.ps06.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import it.polimi.ingsw.ps06.controller.MenuController;
import it.polimi.ingsw.ps06.model.events.EventClose;
import it.polimi.ingsw.ps06.model.events.StoryBoard2Room;


public class MenuCLI extends Observable implements Menu {
	
	private BufferedReader input;
	private boolean cond = true;
	
	public MenuCLI(BufferedReader input) {
		
		this.input = input;
	}

	public BufferedReader getScanner() {
		return input;
	}
	
	public void show() {
		
		System.out.println("\n\n\n\n");
		
		
		System.out.println("   ▄█        ▄██████▄     ▄████████    ▄████████ ███▄▄▄▄    ▄███████▄   ▄██████▄  ");     
		System.out.println("  ███       ███    ███   ███    ███   ███    ███ ███▀▀▀██▄ ██▀     ▄██ ███    ███ ");    
		System.out.println("  ███       ███    ███   ███    ███   ███    █▀  ███   ███       ▄███▀ ███    ███ ");     
		System.out.println("  ███       ███    ███  ▄███▄▄▄▄██▀  ▄███▄▄▄     ███   ███  ▀█▀▄███▀▄▄ ███    ███ ");     
		System.out.println("  ███       ███    ███ ▀▀███▀▀▀▀▀   ▀▀███▀▀▀     ███   ███   ▄███▀   ▀ ███    ███ ");     
		System.out.println("  ███       ███    ███ ▀███████████   ███    █▄  ███   ███ ▄███▀       ███    ███ ");     
		System.out.println("  ███▌    ▄ ███    ███   ███    ███   ███    ███ ███   ███ ███▄     ▄█ ███    ███ ");     
		System.out.println("  █████▄▄██  ▀██████▀    ███    ███   ██████████  ▀█   █▀   ▀████████▀  ▀██████▀  ");     
		System.out.println("  ▀                      ███    ███                                               ");

		
		System.out.println("\t\t\t    ▄█   ▄█              ▄▄▄▄███▄▄▄▄      ▄████████    ▄██████▄  ███▄▄▄▄    ▄█     ▄████████  ▄█   ▄████████  ▄██████▄  ");     
		System.out.println("\t\t\t   ███  ███            ▄██▀▀▀███▀▀▀██▄   ███    ███   ███    ███ ███▀▀▀██▄ ███    ███    ███ ███  ███    ███ ███    ███ ");    
		System.out.println("\t\t\t   ███▌ ███            ███   ███   ███   ███    ███   ███    █▀  ███   ███ ███▌   ███    █▀  ███▌ ███    █▀  ███    ███ ");     
		System.out.println("\t\t\t   ███▌ ███            ███   ███   ███   ███    ███  ▄███        ███   ███ ███▌  ▄███▄▄▄     ███▌ ███        ███    ███ ");     
		System.out.println("\t\t\t   ███▌ ███            ███   ███   ███ ▀███████████ ▀▀███ ████▄  ███   ███ ███▌ ▀▀███▀▀▀     ███▌ ███        ███    ███ ");     
		System.out.println("\t\t\t   ███  ███            ███   ███   ███   ███    ███   ███    ███ ███   ███ ███    ███        ███  ███    █▄  ███    ███ ");     
		System.out.println("\t\t\t   ███  ███▌    ▄      ███   ███   ███   ███    ███   ███    ███ ███   ███ ███    ███        ███  ███    ███ ███    ███ ");     
		System.out.println("\t\t\t   █▀   █████▄▄██       ▀█   ███   █▀    ███    █▀    ████████▀   ▀█   █▀  █▀     ███        █▀   ████████▀   ▀██████▀  ");     
		System.out.println("\t\t\t        ▀          																									  ");
		
		System.out.println(" █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ ");

		System.out.println();
		System.out.println(" 1) Nuova Partita");
		System.out.println(" 2) Esci");
		System.out.println();
		System.out.print(" > ");

		while (cond)
			try {
				readInput();
			} catch (NumberFormatException | IOException e) {
				e.printStackTrace();
			}
	}
	
	public void readInput() throws NumberFormatException, IOException {
		
		int i = Integer.parseInt(input.readLine());

		switch (i) {
			case 1: startGame(); break;
			case 2: notifyExit(); break;
			default: System.out.println("ERRORE");
		}
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
		cond=false;
		setChanged();
		StoryBoard2Room storyBoard = new StoryBoard2Room(new RoomCLI(input));
		notifyObservers(storyBoard);
	}


	@Override
	public void notifyExit() {
		setChanged();
		EventClose close = new EventClose();
		notifyObservers(close);
	} 
	
	public static void main(String[] args) {
		MenuCLI m = new MenuCLI( new BufferedReader(new InputStreamReader(System.in)));
		m.show();
	}
}
