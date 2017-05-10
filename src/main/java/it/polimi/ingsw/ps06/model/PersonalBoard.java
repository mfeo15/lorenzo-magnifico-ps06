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
	private Coins coins;
	private Wood wood;
	private Stone stone;
	private Servants servants;
	
	private ArrayList<Territory> territories;
	private ArrayList<Building> buildings;
	private ArrayList<Character> characters;
	private ArrayList<Venture> ventures;
	
	private BonusTile bonusTile;
	
	private final int MILITARY_REQUIREMENT_THIRD_TERRITORY 	= 3;
	private final int MILITARY_REQUIREMENT_FOURTH_TERRITORY	= 7;
	private final int MILITARY_REQUIREMENT_FIFTH_TERRITORY 	= 12;
	private final int MILITARY_REQUIREMENT_SIXTH_TERRITORY 	= 18;
	
	private final int MAX_NUMBER_TERRITORY 	= 6;
	private final int MAX_NUMBER_BUILDING 	= 6;
	
	
	public PersonalBoard(Coins coins, Wood wood, Stone stone, Servants servants) {
		
		super();
		this.coins = coins;
		this.wood = wood;
		this.stone = stone;
		this.servants = servants;
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

