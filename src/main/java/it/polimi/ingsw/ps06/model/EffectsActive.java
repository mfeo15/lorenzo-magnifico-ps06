package it.polimi.ingsw.ps06.model;

import static it.polimi.ingsw.ps06.model.Types.Action;
import java.util.Observable;
import java.util.Observer;

/**
* Classe per la gestione di effetti che rimangono attivi nel tempo ed effetti senza una categorizzazione precisa
*
* @author  ps06
* @version 1.0
* @since   2017-05-11
*/
public abstract class EffectsActive extends Effect implements Observer {
	
	protected Player owner;
	protected Observable toStalk;
	
	/**
	* Costruttore della classe Effetti Attivi.
	* 
	* @param	toStalk		Clase per la quale mettersi successivamente in ascolto
	* 
	*/
	public EffectsActive(Observable toStalk) {
		this.toStalk = toStalk;
	}
	
	@Override
	public void activate(Player p) {	
		owner = p;
		toStalk.addObserver(this);
	}
}
