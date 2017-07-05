package it.polimi.ingsw.ps06.model.effects;

import it.polimi.ingsw.ps06.model.Player;
import it.polimi.ingsw.ps06.model.bonus_malus.BonusMalusDoubleMaterialsFromDevCards;

/**
 * Classe per la gestione dell'effetto "ottieni doppio materials quando prendi una DevCard"
 *
 * @author  ps06
 * @version 1.0
 * @since   2017-05-11
 */
public class EffectsBonusMalusDoubleMaterialsFromDevCards extends EffectsBonusMalus {
	
	/**
	 * Costruttore della classe
	 */
	public EffectsBonusMalusDoubleMaterialsFromDevCards() {
		super(-1);
	}
	
	@Override
	public void activate(Player p) {
		BonusMalusDoubleMaterialsFromDevCards bm = new BonusMalusDoubleMaterialsFromDevCards();
		p.getBonusMalusCollection().add( bm );
	}	
}
