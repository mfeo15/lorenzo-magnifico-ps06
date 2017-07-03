package it.polimi.ingsw.ps06.model.bonus_malus;

import java.util.ArrayList;

import it.polimi.ingsw.ps06.model.Types.MaterialsKind;

public class BonusMalusResources extends BonusMalus {

	public ArrayList<MaterialsKind> kinds;
	
	public BonusMalusResources(int value, ArrayList<MaterialsKind> kinds) {
		super(value);
		
		this.kinds = kinds;
	}
	
	public ArrayList<MaterialsKind> getMaterialksKind() {
		return kinds;
	}
	
	public void setMaterialksKind(ArrayList<MaterialsKind> kinds) {
		this.kinds = kinds;
	}
}
