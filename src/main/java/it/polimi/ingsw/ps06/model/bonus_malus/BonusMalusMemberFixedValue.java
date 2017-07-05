package it.polimi.ingsw.ps06.model.bonus_malus;

import java.util.ArrayList;

import it.polimi.ingsw.ps06.model.Types;
import it.polimi.ingsw.ps06.model.Types.ColorPalette;

public class BonusMalusMemberFixedValue extends BonusMalus {

	private ArrayList<ColorPalette> membersColor;
	
	public BonusMalusMemberFixedValue(int value, ArrayList<ColorPalette> membersColor) {
		super(value);
		this.membersColor = membersColor;
	}
	
	public ArrayList<ColorPalette> getMemberColors() {
		return membersColor;
	}
	
	public void setMemberColors(ArrayList<ColorPalette> membersColor) {
		this.membersColor = membersColor;
	}
}
