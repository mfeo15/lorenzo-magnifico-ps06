package it.polimi.ingsw.ps06.model.effects;

import it.polimi.ingsw.ps06.model.BonusMalusAction;
import it.polimi.ingsw.ps06.model.BonusMalusNoTowersEffects;
import it.polimi.ingsw.ps06.model.Player;

public class EffectsBonusMalusNoTowersEffects extends EffectsBonusMalus {
	
	public EffectsBonusMalusNoTowersEffects() {
		super(-1);
	}
	
	@Override
	public void activate(Player p) {
		BonusMalusNoTowersEffects bm = new BonusMalusNoTowersEffects();
		p.getBonusMalusCollection().add( bm );
	}	
}
