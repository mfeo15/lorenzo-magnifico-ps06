package it.polimi.ingsw.ps06.networking.messages;

import java.util.ArrayList;
import java.util.EnumMap;

import it.polimi.ingsw.ps06.model.Resources;
import it.polimi.ingsw.ps06.model.Types.MaterialsKind;
import it.polimi.ingsw.ps06.model.Types.PointsKind;

public class MessagePersonalBoardStatus extends Server2Client {

	private EnumMap<MaterialsKind, Integer> materialsValues;
	private EnumMap<PointsKind, Integer> pointsValues;
	
	private ArrayList<Integer> territory;
	private ArrayList<Integer> building;
	private ArrayList<Integer> character;
	private ArrayList<Integer> venture;
	
	
	public MessagePersonalBoardStatus(ArrayList<Integer> territory, 
			ArrayList<Integer> building, ArrayList<Integer> character, ArrayList<Integer> venture) {
		
		this.materialsValues = new EnumMap<MaterialsKind, Integer>(MaterialsKind.class);
		this.pointsValues = new EnumMap<PointsKind, Integer>(PointsKind.class);
		
		this.territory = territory;
		this.building = building;
		this.character = character;
		this.venture = venture;
	}
	
	public void setResourceValue(MaterialsKind kind, int value) {
		this.materialsValues.put(kind, value);
	}
	
	public void setResourceValue(PointsKind kind, int value) {
		this.pointsValues.put(kind, value);
	}
	
	public int getResourceValue(MaterialsKind kind) {
		return this.materialsValues.get(kind);
	}
	
	public int getResourceValue(PointsKind kind) {
		return this.pointsValues.get(kind);
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
