package it.polimi.ingsw.ps06.model.effects;

import it.polimi.ingsw.ps06.model.BonusMalusAction;
import it.polimi.ingsw.ps06.model.Player;
import it.polimi.ingsw.ps06.model.Types.ActionCategory;

public class EffectsBonusMalusActions extends EffectsBonusMalus {
	
	private ActionCategory actionCategory;
	
	public EffectsBonusMalusActions(int value, ActionCategory actionCategory) {
		super(value);
	}

	@Override
	public void activate(Player p) {
		BonusMalusAction bm = new BonusMalusAction(this.value, this.actionCategory);
		p.getBonusMalusCollection().add( bm );
	}	
}