package it.polimi.ingsw.ps06.model.effects;

import java.util.ArrayList;

import it.polimi.ingsw.ps06.model.Player;
import it.polimi.ingsw.ps06.model.Types.ColorPalette;
import it.polimi.ingsw.ps06.model.bonus_malus.BonusMalusMemberFixedValue;

public class EffectsBonusMalusMemberFixedValue extends EffectsBonusMalus {
	
	private ArrayList<ColorPalette> membersColor;
	
	public EffectsBonusMalusMemberFixedValue(int value, ArrayList<ColorPalette> membersColor) {
		super(value);
		this.membersColor = membersColor;
	}

	@Override
	public void activate(Player p) {
		BonusMalusMemberFixedValue bm = new BonusMalusMemberFixedValue(this.value, this.membersColor);
		p.getBonusMalusCollection().add( bm );
	}	
}