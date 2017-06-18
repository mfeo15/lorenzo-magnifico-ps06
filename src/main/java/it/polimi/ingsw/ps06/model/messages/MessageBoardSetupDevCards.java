package it.polimi.ingsw.ps06.model.messages;

import java.util.ArrayList;

import it.polimi.ingsw.ps06.model.Resources;

public class MessageBoardSetupDevCards extends Broadcast {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1599520886249943921L;
	
	private int[] roundCards;
	
	private ArrayList<Integer> territory;
	private ArrayList<Integer> building;
	private ArrayList<Integer> character;
	private ArrayList<Integer> venture;
	
	public MessageBoardSetupDevCards( int[] roundCards) {
		this.roundCards = roundCards;
	}
	
	public MessageBoardSetupDevCards(ArrayList<Integer> territory, 
			ArrayList<Integer> building, ArrayList<Integer> character, ArrayList<Integer> venture) {
		
		this.territory = territory;
		this.building = building;
		this.character = character;
		this.venture = venture;
	}
	
	public int[] getRoundCards() {
		return this.roundCards;
	}

	public ArrayList<Integer> getTerritory() {
		return territory;
	}

	public void setTerritory(ArrayList<Integer> territory) {
		this.territory = territory;
	}

	public ArrayList<Integer> getBuilding() {
		return building;
	}

	public void setBuilding(ArrayList<Integer> building) {
		this.building = building;
	}

	public ArrayList<Integer> getCharacter() {
		return character;
	}

	public void setCharacter(ArrayList<Integer> character) {
		this.character = character;
	}

	public ArrayList<Integer> getVenture() {
		return venture;
	}

	public void setVenture(ArrayList<Integer> venture) {
		this.venture = venture;
	}

	@Override
	public void accept(MessageVisitor visitor) {
		visitor.visit(this);
	}

}
