package it.polimi.ingsw.ps06.model;

import static it.polimi.ingsw.ps06.model.Types.ColorPalette;

import java.util.Random;

/**
* Classe per la gestione dei dadi
*
* @author  ps06
* @version 1.0
* @since   2017-05-10 
*/
public class Dice {

	private int value;
	private ColorPalette color;
	
	/**
	* Costruttore della classe
	* @param 	color 		Colore associato al dado
	*/
	public Dice(ColorPalette color)
	{
		this.color = color;
	}
	
	/**
	 * Getter per il valore attuale del dado
	 * 
	 * @return	il valore attuale del dado
	 * 
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * Metodo invocato per generare un nuovo valore random
	 * 
	 */
	public void roll() {
		value = 1 + (new Random()).nextInt(6);
	}
	
}
