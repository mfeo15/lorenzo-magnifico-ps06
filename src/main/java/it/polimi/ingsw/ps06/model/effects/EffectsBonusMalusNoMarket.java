package it.polimi.ingsw.ps06.model.effects;

import it.polimi.ingsw.ps06.model.Player;
import it.polimi.ingsw.ps06.model.bonus_malus.BonusMalusNoMarket;

/**
 * Classe per la gestione dell'effetto "vietato utilizzare il mercato"
 *
 * @author  ps06
 * @version 1.0
 * @since   2017-05-11
 */
public class EffectsBonusMalusNoMarket extends EffectsBonusMalus {
	
	/**
	 * Costruttore della classe
	 */
	public EffectsBonusMalusNoMarket() {
		super(-1);
	}
	
	@Override
	public void activate(Player p) {
		BonusMalusNoMarket bm = new BonusMalusNoMarket();
		p.getBonusMalusCollection().add( bm );
	}	
}
