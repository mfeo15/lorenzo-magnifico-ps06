package it.polimi.ingsw.ps06;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.ps06.model.Resources;
import it.polimi.ingsw.ps06.model.Types.MaterialsKind;
import it.polimi.ingsw.ps06.model.Types.PointsKind;
import it.polimi.ingsw.ps06.model.cards.developement.Character;

/**
 * @author Aaron
 *
 */

public class TestCharacters {
	Character testC;
	Resources requirement;
	
    public void setup() {
    	Resources requirement = new Resources();
		requirement.setResourceValue(MaterialsKind.COIN, 4);
		requirement.setResourceValue(MaterialsKind.SERVANT,3);
		requirement.setResourceValue(PointsKind.FAITH_POINTS, 2);
		requirement.setResourceValue(PointsKind.MILITARY_POINTS, 2);
		testC.setCost(requirement);
    }
    
	@Test
	public void testSetRequirement() {
		testC.setCost(requirement);
		assertEquals(testC.getCost() instanceof Resources, true);
	}

	@Test
	public void testGetRequirement() {
		assertEquals(testC.getCost().getResourceValue(MaterialsKind.COIN), 4);
		assertEquals(testC.getCost().getResourceValue(MaterialsKind.SERVANT), 3);
		assertEquals(testC.getCost().getResourceValue(PointsKind.FAITH_POINTS), 2);
		assertEquals(testC.getCost().getResourceValue(PointsKind.MILITARY_POINTS), 2);
	}

}
