package it.polimi.ingsw.ps06;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.ps06.model.Resources;
import it.polimi.ingsw.ps06.model.cards.developement.Territory;
import it.polimi.ingsw.ps06.model.cards.*;

/**
 * @author Aaron
 *
 */

public class TestTerritories {
	Territory testC;
	
	public void setup(){
		testC=new Territory();
	}
	
	@Test
	public void testSetDiceReq() {
		testC.setDiceReq(5);
	}

	@Test
	public void testCheck_dice() {
		assertEquals(testC.check_dice(5), true);
	}

}
