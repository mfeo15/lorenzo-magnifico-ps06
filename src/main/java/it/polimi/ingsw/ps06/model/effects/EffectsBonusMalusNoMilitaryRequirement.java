package it.polimi.ingsw.ps06.model.effects;

import it.polimi.ingsw.ps06.model.Player;
import it.polimi.ingsw.ps06.model.bonus_malus.BonusMalusNoMilitaryRequirement;
import it.polimi.ingsw.ps06.model.bonus_malus.BonusMalusNoTowersEffects;

public class EffectsBonusMalusNoMilitaryRequirement extends EffectsBonusMalus {
	
	public EffectsBonusMalusNoMilitaryRequirement() {
		super(-1);
	}
	
	@Override
	public void activate(Player p) {
		BonusMalusNoMilitaryRequirement bm = new BonusMalusNoMilitaryRequirement();
		p.getBonusMalusCollection().add( bm );
	}	
}
