package it.polimi.ingsw.ps06.model.effects;

import it.polimi.ingsw.ps06.model.Player;
import it.polimi.ingsw.ps06.model.Types.ActionCategory;
import it.polimi.ingsw.ps06.model.bonus_malus.BonusMalusAction;

public class EffectsBonusMalusActions extends EffectsBonusMalus {
	
	private ActionCategory actionCategory;
	
	public EffectsBonusMalusActions(int value, ActionCategory actionCategory) {
		super(value);
		this.actionCategory = actionCategory;
	}

	@Override
	public void activate(Player p) {
		BonusMalusAction bm = new BonusMalusAction(this.value, this.actionCategory);
		p.getBonusMalusCollection().add( bm );
	}	
}