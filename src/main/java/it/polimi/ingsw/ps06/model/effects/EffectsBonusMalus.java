package it.polimi.ingsw.ps06.model.effects;

/**
 * Classe astratta per la gestione di effetti che rimangono attivi nel tempo
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
	*/
	public EffectsBonusMalus(int value) {
		this.value = value;
	}
}
