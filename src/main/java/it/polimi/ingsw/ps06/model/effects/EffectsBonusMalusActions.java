package it.polimi.ingsw.ps06.model.effects;

import it.polimi.ingsw.ps06.model.Player;
import it.polimi.ingsw.ps06.model.Types.ActionCategory;
import it.polimi.ingsw.ps06.model.bonus_malus.BonusMalusAction;

/**
 * Classe per la gestione di effetti che rimangono attivi relativi ad azioni sulla Board
 *
 * @author  ps06
 * @version 1.0
 * @since   2017-05-11
 */
public class EffectsBonusMalusActions extends EffectsBonusMalus {
	
	private ActionCategory actionCategory;
	
	/**
	 * Costruttore della classe Bonus/Malus per azioni della Board
	 * 
	 * @param value				valore del Bonus Malus
	 * @param actionCategory	tipo di azione su cui applicare il Bonus Malus
	 */
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