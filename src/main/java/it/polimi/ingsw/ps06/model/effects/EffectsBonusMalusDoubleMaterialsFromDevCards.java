package it.polimi.ingsw.ps06.model.effects;

import it.polimi.ingsw.ps06.model.Player;
import it.polimi.ingsw.ps06.model.bonus_malus.BonusMalusDoubleMaterialsFromDevCards;

public class EffectsBonusMalusDoubleMaterialsFromDevCards extends EffectsBonusMalus {
	
	public EffectsBonusMalusDoubleMaterialsFromDevCards() {
		super(-1);
	}
	
	@Override
	public void activate(Player p) {
		BonusMalusDoubleMaterialsFromDevCards bm = new BonusMalusDoubleMaterialsFromDevCards();
		p.getBonusMalusCollection().add( bm );
	}	
}
