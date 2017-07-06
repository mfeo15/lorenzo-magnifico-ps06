package it.polimi.ingsw.ps06.model.bonus_malus;

import it.polimi.ingsw.ps06.model.Types.ActionCategory;

/**
 * Classe per i Bonus Malus relativi ad una specifica azione di gioco
 *
 * @author  ps06
 * @version 1.1
 * @since   2017-06-26
 */
public class BonusMalusAction extends BonusMalus {

	private ActionCategory actionCategory;
	
	/**
	 * Costruttore della classe
	 * 
	 * @param 	value			valore del Bonus Malus
	 * @param 	actionCategory	tipo di azione su cui applicare il Bonus Malus
	 * 
	 * @see						it.polimi.ingsw.ps06.model.Types
	 */
	public BonusMalusAction(int value, ActionCategory actionCategory) {
		super(value);
		this.actionCategory = actionCategory;
	}
	
	/**
	 * Getter per il tipo di azione
	 * 
	 * @return	il tipo di azione
	 */
	public ActionCategory getActionCategory() {
		return actionCategory;
	}
	
	/**
	 * Setter per il tipo di azione su cui applicare il Bonus Malus
	 * 
	 * @param	actionCategory	tipo di azione da settare
	 * 
	 * @see						it.polimi.ingsw.ps06.model.Types
	 */
	public void setActionCategory(ActionCategory actionCategory) {
		this.actionCategory = actionCategory;
	}
}
