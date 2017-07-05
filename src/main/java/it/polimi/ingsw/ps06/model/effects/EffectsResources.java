package it.polimi.ingsw.ps06.model.effects;

import it.polimi.ingsw.ps06.model.Player;
import it.polimi.ingsw.ps06.model.Resources;

/**
 * Classe per la gestione di effetti che comportano una modifica alle risorse di un player
 *
 * @author  ps06
 * @version 1.0
 * @since   2017-05-25
 */
public class EffectsResources extends Effect {
	
	protected Resources bonus;
	
	/**
	* Costruttore della classe Effetto per le Risorse.
	* 
	* @param	bonus	risorsa bonus che l'effetto procura al Player quando attivato
	*/
	public EffectsResources(Resources bonus) {
		this.bonus = bonus;
	}
	
	/**
	 * Getter per il bonus associato all'effetto
	 * 
	 * @return	il bonus risorsa dell'effetto
	 */
	public Resources getBonus() {
		return this.bonus;
	}
	
	/**
	* <p>Implementazione del metodo astratto activate()</p>
	* <p>Dato un Player su cui attivare l'effetto, il metodo assegna alla personal board il contenuto
	* dell'attributo bonus.</p>
	* 
	* @param	p	Player sul quale attivare l'effetto
	*/
	@Override
	public void activate(Player p) 
	{	
		p.getPersonalBoard().addResource(bonus);
	}
}
