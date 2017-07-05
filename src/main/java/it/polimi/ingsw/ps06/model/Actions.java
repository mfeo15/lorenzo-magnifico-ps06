package it.polimi.ingsw.ps06.model;

import java.util.Observable;

/**
* Classe astratta per la gestione delle azioni
*
* @author  ps06
* @version 1.0
* @since   2017-05-16
*/
public abstract class Actions {
	
	protected int servants;
	
	/**
	 * Costruttore della classe
	 * 
	 * @param	servants	servitori impiegati a completare l'azione
	 */
	public Actions(int servants) {
		this.servants = servants;
	}

	/**
	 * Metodo atratto di attivazione dell'azione
	 *
	 */
	public abstract void activate();
}
