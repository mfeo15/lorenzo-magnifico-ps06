package it.polimi.ingsw.ps06.view;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import it.polimi.ingsw.ps06.controller.PersonalViewController;

public class PersonalViewCLI extends Observable implements PersonalView {

	private Scanner input;
	
	public PersonalViewCLI(Scanner input) {
		
		this.input = input;
	}
	
	public Scanner getScanner() {
		return input;
	}
	
	public void addNewControllerObserver(PersonalViewController controller) {
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
	
	@Override
	public void setResources(int coin, int wood, int stone, int servant, int victory, int military, int faith) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCard(int id, int index) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addNewObserver(Observer o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show(int code) throws IOException {
		// TODO Auto-generated method stub
		
	}
	
	

}
