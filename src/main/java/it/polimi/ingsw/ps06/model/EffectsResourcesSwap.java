package it.polimi.ingsw.ps06.model;

/**
* Classe per la gestione di effetti che comportano una modifica alle risorse di un player
* per la quale è richiesto uno scambio di risorse in imput prima di ottenerne il benificio.
*
* @author  ps06
* @version 1.0
* @since   2017-05-25
*/
public class EffectsResourcesSwap extends EffectsResources {

	private Resources input;
	
	/**
	* Costruttore della classe Effetto per lo swap di risorse.
	* 
	* @param	bonus	Risorsa bonus che l'effetto procura al Player quando attivato
	* @param	input	Risorsa input che l'effetto sottrae al Player per consegnare il bonus
	* 
	*/
	public EffectsResourcesSwap(Resources bonus, Resources input) {
		super(bonus);
		this.input = input;
	}
	
	/**
	* Implementazione del metodo astratto activate().
	* Dato un Player su cui attivare l'effetto, il metodo aggiunge al Warehouse il bonus
	* richiamando i metodi corretti che il Player stesso fornisce.
	* Prima di assegnare il bonus, rimuove il costo per l'attivazione dello Swap sempre richiamando
	* i metodi di accesso al Warehouse del Player passato.
	* 
	* @param	p	Player sul quale attivare l'effetto
	* 
	*/
	@Override
	public void activate(Player p) {
		
		//p.getPersonalBoard().remove...
		
		p.getPersonalBoard().increaseResources(bonus);
	}
}
