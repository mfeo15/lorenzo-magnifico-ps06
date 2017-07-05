package it.polimi.ingsw.ps06.model.effects;

import it.polimi.ingsw.ps06.model.Player;
import it.polimi.ingsw.ps06.model.bonus_malus.BonusMalusNoMilitaryRequirement;
import it.polimi.ingsw.ps06.model.bonus_malus.BonusMalusNoTowersEffects;

/**
 * Classe per la gestione dell'effetto "Nessun requisito militare richiesto per le carte territorio"
 *
 * @author  ps06
 * @version 1.0
 * @since   2017-05-11
 */
public class EffectsBonusMalusNoMilitaryRequirement extends EffectsBonusMalus {
	
	/**
	 * Costruttore della classe
	 */
	public EffectsBonusMalusNoMilitaryRequirement() {
		super(-1);
	}
	
	@Override
	public void activate(Player p) {
		BonusMalusNoMilitaryRequirement bm = new BonusMalusNoMilitaryRequirement();
		p.getBonusMalusCollection().add( bm );
	}	
}
