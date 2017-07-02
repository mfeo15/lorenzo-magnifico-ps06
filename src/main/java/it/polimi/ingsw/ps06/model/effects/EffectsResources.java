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
	* @param	bonus	Risorsa bonus che l'effetto procura al Player quando attivato
	* 
	*/
	public EffectsResources(Resources bonus) {
		this.bonus = bonus;
	}
	
	public Resources getBonus() {
		return this.bonus;
	}
	
	/**
	* Implementazione del metodo astratto activate().
	* Dato un Player su cui attivare l'effetto, il metodo aggiunge al Warehouse il bonus
	* richiamando i metodi corretti che il Player stesso fornisce
	* 
	* @param	p	Player sul quale attivare l'effetto
	* 
	*/
	@Override
	public void activate(Player p) {
		
		p.getPersonalBoard().addResource(bonus);
	}
}
