package it.polimi.ingsw.ps06.model.effects;

import it.polimi.ingsw.ps06.model.Player;
import it.polimi.ingsw.ps06.model.bonus_malus.BonusMalusAction;
import it.polimi.ingsw.ps06.model.bonus_malus.BonusMalusNoTowersEffects;

/**
 * Classe per la gestione dell'effetto "nessun bonus istantaneo del posizionamento nelle torri"
 *
 * @author  ps06
 * @version 1.0
 * @since   2017-05-11
 */
public class EffectsBonusMalusNoTowersEffects extends EffectsBonusMalus {
	
	/**
	 * Costruttore della classe
	 */
	public EffectsBonusMalusNoTowersEffects() {
		super(-1);
	}
	
	@Override
	public void activate(Player p) {
		BonusMalusNoTowersEffects bm = new BonusMalusNoTowersEffects();
		p.getBonusMalusCollection().add( bm );
	}	
}
