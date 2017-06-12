package it.polimi.ingsw.ps06.model;

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

	/**
	* Costruttore della classe Effetto per assegnazione di risorse in base al numero di Punti accumulati.
	* 
	* @param	bonus		Risorsa bonus che l'effetto procura al Player quando attivato
	* @param	pointsKind	Tipo di punteggio per il quale consegnare il bonus di risorsa
	* 
	*/
	public EffectsResourcesByPoint(Resources bonus, PointsKind pointsKind) {
		super(bonus);
		this.pointsKind = pointsKind;
	}
	
	/**
	* Implementazione del metodo astratto activate().
	* Dato un Player su cui attivare l'effetto, il metodo quanitifica un certo punteggio
	* accumulato per il quale poi verrà consegnato un corrispettivo bonus ripetuto. 
	* 
	* @param	p	Player sul quale attivare l'effetto
	* 
	*/
	@Override
	public void activate(Player p) {

		int pointsAmountOwned = p.getPersonalBoard().getPointsCount(pointsKind);
		
		for (int i=0; i < pointsAmountOwned; i++) {
			p.getPersonalBoard().increaseResources(bonus);
		}
	}

}
