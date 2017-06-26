package it.polimi.ingsw.ps06.model.effects;

/**
* Classe per la gestione di effetti che rimangono attivi nel tempo ed effetti senza una categorizzazione precisa
*
* @author  ps06
* @version 1.0
* @since   2017-05-11
*/
public abstract class EffectsBonusMalus extends Effect {
	
	protected int value;
	
	/**
	* Costruttore della classe Effetti BonusMalus.
	* 
	* @param	value	modificatore Bonus/Malus dell'effetto
	* 
	*/
	public EffectsBonusMalus(int value) {
		this.value = value;
	}
}
