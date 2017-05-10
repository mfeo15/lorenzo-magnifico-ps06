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
	private ArrayList<Territory> territories;
	private ArrayList<Building> buildings;
	private ArrayList<Character> characters;
	private ArrayList<Venture> ventures;
	
	
	
	public ArrayList<Territory> getTerritories() {
		return territories;
	}
	public void setTerritories(ArrayList<Territory> territories) {
		this.territories = territories;
	}
	public ArrayList<Building> getBuildings() {
		return buildings;
	}
	public void setBuildings(ArrayList<Building> buildings) {
		this.buildings = buildings;
	}
	public ArrayList<Character> getCharacters() {
		return characters;
	}
	public void setCharacters(ArrayList<Character> characters) {
		this.characters = characters;
	}
	public ArrayList<Venture> getVentures() {
		return ventures;
	}
	public void setVentures(ArrayList<Venture> ventures) {
		this.ventures = ventures;
	}
	
}
