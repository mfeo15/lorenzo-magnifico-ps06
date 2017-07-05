package it.polimi.ingsw.ps06.model.effects;

import it.polimi.ingsw.ps06.model.Player;
import it.polimi.ingsw.ps06.model.Resources;
import it.polimi.ingsw.ps06.model.Types;
import it.polimi.ingsw.ps06.model.Types.PointsKind;

/**
* Classe per la gestione di effetti che comportano una modifica alle risorse di un player
* in relazione al numero di Points di una tipo accumulati.
*
* @author  ps06
* @version 1.0
* @since   2017-05-25
*/
public class EffectsResourcesByPoint extends EffectsResources {
	
	private PointsKind pointsKind;
	private int weight = 1;

	/**
	 * Costruttore della classe Effetto per assegnazione di risorse in base al numero di Punti accumulati.
	 * 
	 * @param	bonus		risorsa bonus che l'effetto procura al Player quando attivato
	 * @param	pointsKind	tipo di punteggio per il quale consegnare il bonus di risorsa
	 */
	public EffectsResourcesByPoint(Resources bonus, PointsKind pointsKind) {
		super(bonus);
		this.pointsKind = pointsKind;
	}
	
	/**
	 * Costruttore della classe Effetto per assegnazione di risorse in base al numero di Punti accumulati.
	 * 
	 * @param	bonus		risorsa bonus che l'effetto procura al Player quando attivato
	 * @param	pointsKind	tipo di punteggio per il quale consegnare il bonus di risorsa
	 * @param	weight		peso della quantificazione del punteggio
	 */
	public EffectsResourcesByPoint(Resources bonus, PointsKind pointsKind, int weight) {
		this(bonus, pointsKind);
		this.weight = weight;
	}
	
	/**
	* <p>Implementazione del metodo astratto activate().</p>
	* <p>Dato un Player su cui attivare l'effetto, il metodo quanitifica un certo punteggio
	* accumulato per il quale poi verrà consegnato un corrispettivo bonus ripetuto. </p>
	* 
	* @param	p	Player sul quale attivare l'effetto
	*/
	@Override
	public void activate(Player p) {

		int pointsAmountOwned = p.getPersonalBoard().getAmount(pointsKind) / weight;
		
		for (int i=0; i < pointsAmountOwned; i++) {
			p.getPersonalBoard().addResource(bonus);
		}
	}

}
