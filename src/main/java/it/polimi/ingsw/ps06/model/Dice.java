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
	* @return 	Nothing.
	*/
	public Dice(ColorPalette color)
	{
		this.color = color;
	}
	
	public int getValue() {
		return value;
	}
	
	public void roll() {
		value = 1 + (new Random()).nextInt(6);
	}
	
}
