package it.polimi.ingsw.ps06.model.effects;

import it.polimi.ingsw.ps06.model.Player;
import it.polimi.ingsw.ps06.model.Resources;

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
	 * @param	bonus	risorsa bonus che l'effetto procura al Player quando attivato
	 * @param	input	risorsa input che l'effetto sottrae al Player per consegnare il bonus
	 */
	public EffectsResourcesSwap(Resources bonus, Resources input) {
		super(bonus);
		this.input = input;
	}

	/**
	 * <p>Implementazione del metodo astratto activate().</p>
	 * <p>Dato un Player su cui attivare l'effetto, il metodo aggiunge al Warehouse il bonus
	 * richiamando i metodi corretti che il Player stesso fornisce.</p>
	 * <p>Prima di assegnare il bonus, rimuove il costo per l'attivazione dello Swap sempre richiamando
	 * i metodi di accesso al Warehouse del Player passato.</p>
	 * 
	 * @param	p	Player sul quale attivare l'effetto 
	 */
	@Override
	public void activate(Player p) {

		if (p.getPersonalBoard().getInventory().isBiggerThan(input)) 
		{
			p.getPersonalBoard().reduceResource(input);
			p.getPersonalBoard().addResource(bonus);
		}
	}
}
