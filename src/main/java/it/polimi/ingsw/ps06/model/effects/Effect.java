package it.polimi.ingsw.ps06.model.effects;

import it.polimi.ingsw.ps06.model.Player;

/**
 * Classe astratta per la definizione degli effetti
 * 
 * @author ps06
 * @version 1.0
 * @since 2017-05-13
 */
public abstract class Effect {
	
	/**
	* Metodo astratto per l'attivazione dell'effeto. Necessita una specifica
	* implementazione per ogni estensione.
	* 
	* @param	p	Player sul quale attivare l'effetto
	*/
	public abstract void activate(Player p);

}
