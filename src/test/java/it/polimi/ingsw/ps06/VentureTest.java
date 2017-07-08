package it.polimi.ingsw.ps06;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.ps06.model.Resources;
import it.polimi.ingsw.ps06.model.Types.MaterialsKind;
import it.polimi.ingsw.ps06.model.Types.PointsKind;
import it.polimi.ingsw.ps06.model.cards.developement.Venture;


/**
 * @author Aaron
 *
 */

public class VentureTest {

	private Venture testC;

	@Before
	public void setup(){
		testC = new Venture();
	}

	@Test
	public void testSetCost() {

		Resources cost = new Resources();
		cost.setResourceValue(MaterialsKind.COIN, 4);
		cost.setResourceValue(MaterialsKind.SERVANT,3);
		cost.setResourceValue(PointsKind.FAITH_POINTS, 2);
		cost.setResourceValue(PointsKind.MILITARY_POINTS, 2);

		testC.setCost(cost);

		assertEquals(testC.getCosts().get(0).getResourceValue(MaterialsKind.COIN), 4);
		assertEquals(testC.getCosts().get(0).getResourceValue(MaterialsKind.SERVANT), 3);
		assertEquals(testC.getCosts().get(0).getResourceValue(PointsKind.FAITH_POINTS), 2);
		assertEquals(testC.getCosts().get(0).getResourceValue(PointsKind.MILITARY_POINTS), 2);	
	}

	@Test
	public void testSetMilRequirement() {

		testC.setMilRequirement(11);
		assertEquals(testC.getMilitaryRequirement(), 11);

		testC.setMilRequirement(-7);
		assertEquals(testC.getMilitaryRequirement(), 0);
	}

	@Test
	public void testIsMilRequirement() {
		testC.setMilRequirement(11);
		assertTrue( testC.isMilRequirement() );

		testC.setMilRequirement(0);
		assertFalse( testC.isMilRequirement() );
	}
}
