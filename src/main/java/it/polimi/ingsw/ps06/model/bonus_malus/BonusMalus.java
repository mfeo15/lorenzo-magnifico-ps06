package it.polimi.ingsw.ps06.model.bonus_malus;

/**
 * Classe generica per i Bonus Malus delle carte
 *
 * @author  ps06
 * @version 1.1
 * @since   2017-06-26
 */
public class BonusMalus {

	protected int value;
	
	/**
	 * Costruttore della classe
	 * 
	 * @param	value	valore del Bonus Malus
	 */
	public BonusMalus(int value) {
		this.value = value;
	}
	
	/**
	 * Getter per il valore del Bonus Malus
	 * 
	 * @return	il valore del Bonus Malus
	 */
	public int getValue() {
		return value;
	}
 }
