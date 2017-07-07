package it.polimi.ingsw.ps06;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
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

public class DevelopementCardTest {

	private DevelopementCard devCard;

	@Before
	public void setup() {
		devCard = new DevelopementCard();
	}

	@Test
	public void testSetPeriod() {
		devCard.setPeriod(2);
		assertEquals("Wrong period setted", 2, devCard.getPeriod());
	}

	@Test
	public void testSetEffectEffect() {
		Resources reseff = new Resources(PointsKind.MILITARY_POINTS, 2);
		EffectsResources effect = new EffectsResources(reseff);

		devCard.addNewEffect(effect);

		assertEquals(devCard.getInstantEffects().size(), 1);
	}

	@Test
	public void testGetPeriod() {
		devCard.setPeriod(2);
		assertEquals(devCard.getPeriod(), 2);
	}

	@Test
	public void testSetCode() {
		devCard.setCode(21);
		assertEquals("Wrong code setted", devCard.getCode(), 21);	
	}

	@Test
	public void testSetTitle() {
		devCard.setTitle("test");
		assertEquals("Wrong title setted", devCard.getTitle(), "test");
	}

	@Test
	public void testGetTitle() {
		devCard.setTitle("test");
		assertEquals( devCard.getTitle(), "test");
	}

	@Test
	public void testSetPermEffect() {
		Resources reseff= new Resources(PointsKind.MILITARY_POINTS, 2);
		EffectsResources effect=new EffectsResources(reseff);

		devCard.addNewEffect(effect);

		assertEquals(devCard.getInstantEffects().size(), 1);
	}

	@Test
	public void testGetCode() {
		devCard.setCode(21);
		assertEquals(devCard.getCode(), 21);
	}

}
