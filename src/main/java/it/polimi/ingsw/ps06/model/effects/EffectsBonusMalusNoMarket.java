package it.polimi.ingsw.ps06.model.effects;

import it.polimi.ingsw.ps06.model.Player;
import it.polimi.ingsw.ps06.model.bonus_malus.BonusMalusNoMarket;

public class EffectsBonusMalusNoMarket extends EffectsBonusMalus {
	
	public EffectsBonusMalusNoMarket() {
		super(-1);
	}
	
	@Override
	public void activate(Player p) {
		BonusMalusNoMarket bm = new BonusMalusNoMarket();
		p.getBonusMalusCollection().add( bm );
	}	
}
