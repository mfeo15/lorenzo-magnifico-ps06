package it.polimi.ingsw.ps06.view;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import it.polimi.ingsw.ps06.controller.PersonalViewController;
import it.polimi.ingsw.ps06.model.Types.Action;
import it.polimi.ingsw.ps06.model.events.EventClose;
import it.polimi.ingsw.ps06.networking.messages.MessageObtainPersonalBoardStatus;

public class PersonalViewCLI extends Observable implements PersonalView {

	private Scanner input;
	private int playerCode;
	
	public PersonalViewCLI(Scanner input, int playerCode) {
		
		this.input = input;
	}
	
	public Scanner getScanner() {
		return input;
	}
	
	public void addNewControllerObserver(PersonalViewController controller) {
		addObserver(controller);
	}
	
	public void show() {
		System.out.println("--- PERSONAL VIEW ---");
		System.out.println("Digita \"exit\" per uscire");
		
		while(true) readInput();
	}
	
	public void readInput() {
		String s = input.nextLine();
		
		if(("exit").equals(s)) notifyExit();
	}
	
	@Override
	public void setResources(int coin, int wood, int stone, int servant, int victory, int military, int faith) {
		System.out.println("--> Il giocatore ha coin: "+coin+" wood: "+wood+" stone: "+stone+" servant: "+servant+" victory: "+victory+" military: "+military+" faith: "+faith);
		
	}

	@Override
	public void addNewObserver(Observer o) {
		addObserver(o);
		
	}

	@Override
	public void setTerritoryCard(int id, int index) {
		System.out.println("--> Il giocatore ha la carta con ID: "+id+" è la sua carta territorio numero "+index);
		
	}

	@Override
	public void setBuildingCard(int id, int index) {
		System.out.println("--> Il giocatore ha la carta con ID: "+id+" è la sua carta edificio numero "+index);
		
	}

	@Override
	public void notifyExit() {
		setChanged();
		EventClose close = new EventClose();
		notifyObservers(close);
		
	}

	@Override
	public void hasLoaded() {
		setChanged();
		MessageObtainPersonalBoardStatus obtainPbStatus = new MessageObtainPersonalBoardStatus(playerCode); 
		notifyObservers(obtainPbStatus);
		
	}

	@Override
	public Board getBackgroundView() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
