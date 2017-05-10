package it.polimi.ingsw.ps06.model;

import java.util.ArrayList;

/**
* Class representing a single player engaged in a game battle
*
* @author  ps06
* @version 1.0
* @since   2017-05-10 
*/
public class Player {
	
	private String name;
	private int color;
	
	private ArrayList<Leader> leaders;
	
	private PersonalBoard personalBoard;
	 
	
	public Player(String name, int color) {
		super();
		
		this.name = name;
		this.color = color;
		
		this.personalBoard = new PersonalBoard(new Coins(), new Wood(), new Stone(), new Servants());
	}
}