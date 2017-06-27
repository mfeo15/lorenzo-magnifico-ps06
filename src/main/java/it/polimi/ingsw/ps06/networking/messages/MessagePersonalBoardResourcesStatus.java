package it.polimi.ingsw.ps06.networking.messages;

import java.util.EnumMap;

import it.polimi.ingsw.ps06.model.Types.ColorPalette;
import it.polimi.ingsw.ps06.model.Types.MaterialsKind;
import it.polimi.ingsw.ps06.model.Types.PointsKind;

public class MessagePersonalBoardResourcesStatus extends Server2Client {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4539908542621428281L;
	
	private EnumMap<MaterialsKind, Integer> materialsValues;
	private EnumMap<PointsKind, Integer> pointsValues;
	
	public MessagePersonalBoardResourcesStatus() {
		
		this.materialsValues = new EnumMap<MaterialsKind, Integer>(MaterialsKind.class);
		this.pointsValues = new EnumMap<PointsKind, Integer>(PointsKind.class);
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

	@Override
	public void accept(MessageVisitor visitor) {
		visitor.visit(this);
	}

}
