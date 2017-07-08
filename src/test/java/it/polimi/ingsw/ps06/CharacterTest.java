package it.polimi.ingsw.ps06;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.ps06.model.Resources;
import it.polimi.ingsw.ps06.model.Types.MaterialsKind;
import it.polimi.ingsw.ps06.model.Types.PointsKind;
import it.polimi.ingsw.ps06.model.cards.developement.Character;

/**
 * @author Aaron
 *
 */

public class CharacterTest {

	private Character testC;

	@Before
	public void setup() {
		testC = new Character();
	}

	@Test
	public void testSetCost() {

		Resources cost = new Resources();
		cost.setResourceValue(MaterialsKind.COIN, 4);
		cost.setResourceValue(MaterialsKind.SERVANT,3);
		cost.setResourceValue(PointsKind.FAITH_POINTS, 2);
		cost.setResourceValue(PointsKind.MILITARY_POINTS, 2);

		testC.setCost(cost);

		assertEquals(testC.getCost().getResourceValue(MaterialsKind.COIN), 4);
		assertEquals(testC.getCost().getResourceValue(MaterialsKind.SERVANT), 3);
		assertEquals(testC.getCost().getResourceValue(PointsKind.FAITH_POINTS), 2);
		assertEquals(testC.getCost().getResourceValue(PointsKind.MILITARY_POINTS), 2);	
	}
}
