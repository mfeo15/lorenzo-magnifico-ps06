package it.polimi.ingsw.ps06.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import it.polimi.ingsw.ps06.controller.BoardController;
import it.polimi.ingsw.ps06.model.DevelopementCard;
import it.polimi.ingsw.ps06.model.Types.Action;

public class BoardCLI extends Observable implements Board {
	
	private Scanner input;
	
	public BoardCLI(Scanner input) {
		
		this.input = input;
	}
	
	public void addNewControllerObserver(BoardController controller) {
		addObserver(controller);
	}
	
	public void printTowers(ArrayList<DevelopementCard> t) {
		printTowerASCII();
		
	}
	
	public void show() {
		printTowerASCII();
		printTowerASCII();
		printTowerASCII();
		printTowerASCII();
	}
	
	
	private void printTowerASCII() {
		System.out.println("      |>>>");
		System.out.println("      |");
		System.out.println(" |;|_|;|_|;|");
		System.out.println(" \\.    .  /");
		System.out.println("  \\:  .  /");
		System.out.println("   ||:   |");
		System.out.println("   ||:.  |");
		System.out.println("   ||:  .|");
		System.out.println("   ||:   |");
		System.out.println("   ||: , |");
		System.out.println("   ||:   |");
		System.out.println("   ||: . |");
		System.out.println("  _||_   |");
		System.out.println("-~    ~`--");
	}
	
	public void printPoints() {
		
	}

	@Override
	public void setHarvestIndex(int value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setProductionIndex(int value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCouncilIndex(int value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPeriodRound(int period, int round) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPlayerColor(String s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPlayerName(String s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCurrentPlayerName(String s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setActionLog(String s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDices(int black, int white, int orange) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setExcommunicationTiles(int code1, int code2, int code3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCards(int[] cards) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void activateLeaders() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLeaders(int code1, int code2, int code3, int code4) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPersonalResources(int coin, int wood, int stone, int servant) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean checkActionLegality() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void startTimer() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyExit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyAction(Action chosenAction, int memberIndex) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyLeaderDiscard(int index) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyLeaderPlacement(int index) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyTimesUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addNewObserver(Observer o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startGame() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPlayerNumber(int number) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyLeaderActivation(int index) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addMember(Action chosenAction, int memberIndex, int playerIndex) throws IOException {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
