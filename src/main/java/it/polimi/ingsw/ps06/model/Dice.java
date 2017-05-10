package it.polimi.ingsw.ps06.model;

/**
* Class modeling the Dice
*
* @author  ps06
* @version 1.0
* @since   2017-05-10 
*/
public class Dice {

	private int value;
	private ColorPalette color;
	
	/**
	   * This is the constructor of the class
	   * @param 	color 		Color associated to the dice
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
		value = (int) ( Math.random() * 6 + 1 );
	}
	
}
