package it.polimi.ingsw.ps06.model.messages;

import java.util.EnumMap;
import it.polimi.ingsw.ps06.model.Types.ColorPalette;

public class MessageBoardSetupDice extends Server2Client {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6736709067770491733L;
	
	private EnumMap<ColorPalette, Integer> diceValues;
	
	public MessageBoardSetupDice(int black, int white, int orange) {
		
		this.diceValues = new EnumMap<ColorPalette, Integer>(ColorPalette.class);
		
		this.diceValues.put(ColorPalette.DICE_BLACK, black);
		this.diceValues.put(ColorPalette.DICE_WHITE, white);
		this.diceValues.put(ColorPalette.DICE_ORANGE, orange);
	}
	
	public void setBlackDiceValue(int v) {
		this.diceValues.put(ColorPalette.DICE_BLACK, v);
	}
	
	public void setWhiteDiceValue(int v) {
		this.diceValues.put(ColorPalette.DICE_WHITE, v);
	}
	
	public void setOrangekDiceValue(int v) {
		this.diceValues.put(ColorPalette.DICE_ORANGE, v);
	}
	
	public int getBlackDiceValue() {
		return this.diceValues.get(ColorPalette.DICE_BLACK);
	}
	
	public int getWhiteDiceValue() {
		return this.diceValues.get(ColorPalette.DICE_WHITE);
	}
	
	public int getOrangeDiceValue() {
		return this.diceValues.get(ColorPalette.DICE_ORANGE);
	}
	
	public EnumMap<ColorPalette, Integer> getDiceValues() {
		return this.diceValues;
	}
	
	@Override
	public void accept(MessageVisitor visitor) {
		visitor.visit(this);
	}
}
