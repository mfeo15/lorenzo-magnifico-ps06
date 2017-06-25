package it.polimi.ingsw.ps06.networking.messages;

import java.util.ArrayList;

import it.polimi.ingsw.ps06.model.Resources;

public class MessagePersonalBoardStatus extends Server2Client {

	private Resources warehouse;
	
	private ArrayList<Integer> territory;
	private ArrayList<Integer> building;
	private ArrayList<Integer> character;
	private ArrayList<Integer> venture;
	
	
	public MessagePersonalBoardStatus(Resources warehouse, ArrayList<Integer> territory, 
			ArrayList<Integer> building, ArrayList<Integer> character, ArrayList<Integer> venture) {
		
		this.warehouse = warehouse;
		
		this.territory = territory;
		this.building = building;
		this.character = character;
		this.venture = venture;
	}
	
	public Resources getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Resources warehouse) {
		this.warehouse = warehouse;
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
