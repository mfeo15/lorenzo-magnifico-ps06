package it.polimi.ingsw.ps06.model.effects;

import java.util.ArrayList;

import it.polimi.ingsw.ps06.model.Player;
import it.polimi.ingsw.ps06.model.Types.ActionCategory;
import it.polimi.ingsw.ps06.model.Types.ColorPalette;
import it.polimi.ingsw.ps06.model.bonus_malus.BonusMalusAction;
import it.polimi.ingsw.ps06.model.bonus_malus.BonusMalusMember;

public class EffectsBonusMalusMember extends EffectsBonusMalus {
	
	private ArrayList<ColorPalette> membersColor;
	
	public EffectsBonusMalusMember(int value, ArrayList<ColorPalette> membersColor) {
		super(value);
		this.membersColor = membersColor;
	}

	@Override
	public void activate(Player p) {
		BonusMalusMember bm = new BonusMalusMember(this.value, this.membersColor);
		p.getBonusMalusCollection().add( bm );
	}	
}