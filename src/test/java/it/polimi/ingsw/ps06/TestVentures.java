package it.polimi.ingsw.ps06;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.ps06.model.Resources;
import it.polimi.ingsw.ps06.model.Types.MaterialsKind;
import it.polimi.ingsw.ps06.model.Types.PointsKind;
import it.polimi.ingsw.ps06.model.cards.developement.Venture;


/**
 * @author Aaron
 *
 */

public class TestVentures {
	Venture testC;
	Resources requirement;
	
	public void setup(){
		testC=new Venture();
		Resources requirement = new Resources();
		requirement.setResourceValue(MaterialsKind.COIN, 4);
		requirement.setResourceValue(MaterialsKind.SERVANT,3);
		requirement.setResourceValue(PointsKind.FAITH_POINTS, 2);
		testC.setCost(requirement);
	}
	
	@Test
	public void testSetRequirement() {
		testC.setCost(requirement);
	}

	@Test
	public void testSetMilRequirement() {
		testC.setMilRequirement(11);
		assertEquals(testC.getMilitaryRequirement(), 11);
	}

	@Test
	public void testIsMilRequirement() {
		testC.setMilRequirement(11);
		assertEquals(testC.isMilRequirement(), true);
	}

	@Test
	public void testGetRequirements() {
		assertEquals(testC.getCosts().get(0).getResourceValue(MaterialsKind.COIN), 4);
		assertEquals(testC.getCosts().get(0).getResourceValue(MaterialsKind.SERVANT), 3);
		assertEquals(testC.getCosts().get(0).getResourceValue(PointsKind.FAITH_POINTS), 2);
	}

	@Test
	public void testGetMilitaryRequirement() {
	assertEquals(testC.getMilitaryRequirement(), 11);
	}
}
