package it.polimi.ingsw.ps06.model;

import java.util.ArrayList;

/**
* Classe per la modellizzazione della tessera personale
*
* @author  ps06
* @version 1.0
* @since   2017-05-09 
*/
public class PersonalBoard 
{
	private ArrayList<Resources> resources; //<------- modificato dalle varie classi risorse
	
	private ArrayList<Territory> territories;
	private ArrayList<Building> buildings;
	private ArrayList<Character> characters;
	private ArrayList<Venture> ventures;
	
	private BonusTile bonusTile;
	
	private Warehouse status;
	
	/*
	 * implementazione da rivedere di tutte queste costanti !!!
	 * 
	private final int MILITARY_REQUIREMENT_THIRD_TERRITORY 	= 3;
	private final int MILITARY_REQUIREMENT_FOURTH_TERRITORY	= 7;
	private final int MILITARY_REQUIREMENT_FIFTH_TERRITORY 	= 12;
	private final int MILITARY_REQUIREMENT_SIXTH_TERRITORY 	= 18;
	
	private final int MAX_NUMBER_TERRITORY 	= 6;
	private final int MAX_NUMBER_BUILDING 	= 6;
	*/
	
	public PersonalBoard() {
		
		super();
		
		status = new Warehouse();
		bonusTile = new BonusTile();
	}
	
	public void addCard(Territory cardTerritory) 
	{
		
	}

	public void addCard(Character cardCharacter) {
		characters.add(cardCharacter);
	}

	public void addCard(Building cardBuilding) {
	
	}

	public void addCard(Venture cardVenture) {
		ventures.add(cardVenture);
	}
}

