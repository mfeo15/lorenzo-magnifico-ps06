package it.polimi.ingsw.ps06.model;

/**
* Classe per la gestione di effetti che comportano una modifica alle risorse di un player
*
* @author  ps06
* @version 1.0
* @since   2017-05-11
*/
public class EffectsResources extends Effect {
	private Resources bonus;
	
	@Override
	public void activate(Player p) {
		
		//TODO add Resources to Player Warehouse ==> Need some setter method
	}
	
	/**
	* Costruttore della classe Effetto per le Risorse.
	* 
	* @param	bonus	Risorsa bonus che l'effetto procura al Player quando attivato
	* 
	*/
	public EffectsResources(Resources bonus) {
		this.bonus = bonus;
	}
}
