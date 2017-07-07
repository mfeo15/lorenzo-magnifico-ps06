package it.polimi.ingsw.ps06;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.ps06.model.Dice;
import it.polimi.ingsw.ps06.model.Types.ColorPalette;

public class DiceTest {
	
	private int min_value = 1;
	private int max_value = 6;
	
	private Dice d;

	@Before
	public void setUp() {
		d = new Dice(ColorPalette.DICE_BLACK);
	}

	@Test
	public void testRoll() {
		for (int i=0; i<100; i++) {
			d.roll();
			int value = d.getValue();
			assertTrue("Valore del dado fuori dal range: " + value, min_value <= value && value <= max_value);
		}
	}

}
