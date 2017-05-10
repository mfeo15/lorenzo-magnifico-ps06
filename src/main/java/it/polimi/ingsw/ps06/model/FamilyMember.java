package it.polimi.ingsw.ps06.model;

public class FamilyMember {
	
	private int rappresentativeDice;
	private int value;
	
	
	/**
	   * This is the default constructor of the class. The Family Member instanced is by default neutral
	   * @return 	Nothing.
	   */
	public FamilyMember()
	{
		super();
		this.rappresentativeDice = ColorPalette.EMPTY;
	}
	
	/**
	   * This is the constructor of the class
	   * @param 	rappresentativeDice 	During the instance of a Family Member it has to be associated to a particular dice.
	   * 									Values accepted comes from the ColorPalette interface.
	   * @return 	Nothing.
	   */
	public FamilyMember(int rappresentativeDice) throws IllegalArgumentException
	{
		super();
		
		if (rappresentativeDice != ColorPalette.DICE_BLACK && rappresentativeDice != ColorPalette.DICE_ORANGE && rappresentativeDice != ColorPalette.DICE_WHITE)
			throw new IllegalArgumentException();
		
		this.rappresentativeDice = rappresentativeDice;
	}
	
	

}
