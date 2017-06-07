package it.polimi.ingsw.ps06.view;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Scanner;

import it.polimi.ingsw.ps06.controller.BoardControllerCLI;
import it.polimi.ingsw.ps06.model.DevelopementCard;

public class BoardCLI extends Observable {
	
	private Scanner input;
	
	public BoardCLI(Scanner input) {
		
		this.input = input;
	}
	
	public void addNewControllerObserver(BoardControllerCLI controller) {
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
}
