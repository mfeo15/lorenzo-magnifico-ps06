package it.polimi.ingsw.ps06.model.effects;

import java.util.ArrayList;

import it.polimi.ingsw.ps06.model.Player;
import it.polimi.ingsw.ps06.model.Types.MaterialsKind;
import it.polimi.ingsw.ps06.model.bonus_malus.BonusMalusResources;

public class EffectsBonusMalusResources extends EffectsBonusMalus {
	
	private ArrayList<MaterialsKind> kinds;
	
	public EffectsBonusMalusResources(int value, ArrayList<MaterialsKind> kinds) {
		super(value);
		this.kinds = kinds;
	}

	@Override
	public void activate(Player p) {
		BonusMalusResources bm = new BonusMalusResources(this.value, this.kinds);
		p.getBonusMalusCollection().add( bm );
	}	
}