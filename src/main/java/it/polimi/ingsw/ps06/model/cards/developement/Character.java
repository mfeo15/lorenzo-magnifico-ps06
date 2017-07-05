package it.polimi.ingsw.ps06.model.cards.developement;

import it.polimi.ingsw.ps06.model.Resources;

/**
* Classe per la gestione delle carte di tipo personaggio
*
* @author  ps06
* @version 1.0
* @since   2017-05-11
*/
public class Character extends DevelopementCard {
	
	private Resources cost;
	
	
	/**
	 * Setter per il costo della carta
	 * 
	 * @param	cost	costo della carta
	 */
	public void setCost(Resources cost) {
		this.cost = cost;
	}	
	 
	/**
	 * Getter per il costo della carta
	 * 
	 * @return	costo della carta
	 */
	public Resources getCost() {
		return cost;
	}

}
