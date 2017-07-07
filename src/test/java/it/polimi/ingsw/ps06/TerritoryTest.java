package it.polimi.ingsw.ps06;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.ps06.model.cards.developement.Territory;

/**
 * @author Aaron
 *
 */

public class TerritoryTest {

	private Territory testC;

	@Before
	public void setup() {
		testC = new Territory();
	}

	@Test
	public void testCheck_dice() {

		testC.setDiceReq(3);
		assertFalse( testC.check_dice(2) );

		assertTrue( testC.check_dice(6) );

		assertTrue( testC.check_dice(3) );
	}

}
