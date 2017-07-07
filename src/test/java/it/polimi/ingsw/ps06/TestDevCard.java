package it.polimi.ingsw.ps06;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import it.polimi.ingsw.ps06.model.Resources;
import it.polimi.ingsw.ps06.model.Types.PointsKind;
import it.polimi.ingsw.ps06.model.cards.developement.DevelopementCard;
import it.polimi.ingsw.ps06.model.effects.Effect;
import it.polimi.ingsw.ps06.model.effects.EffectsResources;

/**
 * @author Aaron
 *
 */

public class TestDevCard {
	DevelopementCard testC;

	@Test
	public void testSetPeriod() {
		testC.setPeriod(2);
		assertEquals("Wrong period setted", testC.getPeriod(), 2);
		}

	@Test
	public void testSetEffectEffect() {
		Resources reseff= new Resources(PointsKind.MILITARY_POINTS, 2);
		EffectsResources effect=new EffectsResources(reseff);
		testC.addNewEffect(effect);
		ArrayList<Effect> instant_effect = testC.getInstantEffects();
		assertEquals(instant_effect.size(), 1);
		}

	@Test
	public void testGetPeriod() {
		assertEquals(testC.getPeriod(), 2);
	}

	@Test
	public void testSetCode() {
		testC.setCode(21);
		assertEquals("Wrong code setted", testC.getCode(), 21);	}

	@Test
	public void testSetTitle() {
		testC.setTitle("test");
		assertEquals("Wrong title setted", testC.getTitle(), "test");
		}

	@Test
	public void testGetTitle() {
		assertEquals( testC.getTitle(), "test");
	}

	@Test
	public void testSetPermEffect() {
		Resources reseff= new Resources(PointsKind.MILITARY_POINTS, 2);
		EffectsResources effect=new EffectsResources(reseff);
		testC.addNewEffect(effect);
		ArrayList<Effect> instant_effect = testC.getInstantEffects();
		assertEquals(instant_effect.size(), 1);
		}

	@Test
	public void testGetCode() {
		assertEquals(testC.getCode(), 21);
	}

}
