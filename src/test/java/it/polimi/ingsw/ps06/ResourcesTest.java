package it.polimi.ingsw.ps06;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.ps06.model.Resources;
import it.polimi.ingsw.ps06.model.Types.MaterialsKind;
import it.polimi.ingsw.ps06.model.Types.PointsKind;

public class ResourcesTest {
	
	private Resources r;
	
	private int coin_value;
	private int servant_value;
	private int stone_value;
	private int wood_value;
	private int faith_value;
	private int military_value;
	private int victory_value;
	
	@Before
    public void setup() {

		this.r = new Resources();
		this.r.setResourceValue(MaterialsKind.COIN, 1);
		this.r.setResourceValue(MaterialsKind.SERVANT, 2);
		this.r.setResourceValue(MaterialsKind.STONE, 3);
		this.r.setResourceValue(MaterialsKind.WOOD, 4);
		this.r.setResourceValue(PointsKind.FAITH_POINTS, 5);
		this.r.setResourceValue(PointsKind.MILITARY_POINTS, 6);
		this.r.setResourceValue(PointsKind.VICTORY_POINTS, 7);
		
		this.coin_value = r.getResourceValue(MaterialsKind.COIN);
		this.servant_value = r.getResourceValue(MaterialsKind.SERVANT);
		this.stone_value = r.getResourceValue(MaterialsKind.STONE);
		this.wood_value = r.getResourceValue(MaterialsKind.WOOD);
		this.faith_value = r.getResourceValue(PointsKind.FAITH_POINTS);
		this.military_value = r.getResourceValue(PointsKind.MILITARY_POINTS);
		this.victory_value = r.getResourceValue(PointsKind.VICTORY_POINTS);
    }

	@Test
	public void testSetResourceValue() {
		
		Resources resource2Set = new Resources();
		resource2Set.setResourceValue(MaterialsKind.COIN, 1);
		resource2Set.setResourceValue(MaterialsKind.SERVANT, 2);
		resource2Set.setResourceValue(MaterialsKind.STONE, 3);
		resource2Set.setResourceValue(MaterialsKind.WOOD, 4);
		
		assertEquals("The method setResourceValue() is not working for COIN", resource2Set.getResourceValue(MaterialsKind.COIN), 1);
		assertEquals("The method setResourceValue() is not working for SERVANT", resource2Set.getResourceValue(MaterialsKind.SERVANT), 2);
		assertEquals("The method setResourceValue() is not working for STONE", resource2Set.getResourceValue(MaterialsKind.STONE), 3);
		assertEquals("The method setResourceValue() is not working for WOOD", resource2Set.getResourceValue(MaterialsKind.WOOD), 4);
		
		resource2Set.setResourceValue(PointsKind.FAITH_POINTS, 5);
		resource2Set.setResourceValue(PointsKind.MILITARY_POINTS, 6);
		resource2Set.setResourceValue(PointsKind.VICTORY_POINTS, 7);
		
		assertEquals("The method setResourceValue() is not working for FAITH_POINTS", resource2Set.getResourceValue(PointsKind.FAITH_POINTS), 5);
		assertEquals("The method setResourceValue() is not working for MILITARY_POINTS", resource2Set.getResourceValue(PointsKind.MILITARY_POINTS), 6);
		assertEquals("The method setResourceValue() is not working for VICTORY_POINTS", resource2Set.getResourceValue(PointsKind.VICTORY_POINTS), 7);
	}
	
	@Test
	public void testClearResources() {
		
		r.clearResources();
		
		assertEquals("The method clearResources() is not working for COIN", r.getResourceValue(MaterialsKind.COIN), 0);
		assertEquals("The method clearResources() is not working for SERVANT", r.getResourceValue(MaterialsKind.SERVANT), 0);
		assertEquals("The method clearResources() is not working for STONE", r.getResourceValue(MaterialsKind.STONE), 0);
		assertEquals("The method clearResources() is not working for WOOD", r.getResourceValue(MaterialsKind.WOOD), 0);
		assertEquals("The method clearResources() is not working for FAITH_POINTS", r.getResourceValue(PointsKind.FAITH_POINTS), 0);
		assertEquals("The method clearResources() is not working for MILITARY_POINTS", r.getResourceValue(PointsKind.MILITARY_POINTS), 0);
		assertEquals("The method clearResources() is not working for VICTORY_POINTS", r.getResourceValue(PointsKind.VICTORY_POINTS), 0);
	}
	
	@Test
	public void testIncreaseResourceValue() {
		
		r.increaseResourceValue(MaterialsKind.COIN, 5);
		assertEquals( r.getResourceValue(MaterialsKind.COIN), coin_value + 5 );
		
		r.increaseResourceValue(PointsKind.FAITH_POINTS, 8);
		assertEquals( r.getResourceValue(PointsKind.FAITH_POINTS), faith_value + 8 );
	}
	
	@Test
	public void testDecreaseResourceValue() {
		
		r.decreaseResourceValue(MaterialsKind.WOOD, 2);
		assertEquals( r.getResourceValue(MaterialsKind.WOOD), wood_value - 2 );
		
		r.decreaseResourceValue(PointsKind.MILITARY_POINTS, 5);
		assertEquals( r.getResourceValue(PointsKind.MILITARY_POINTS), military_value - 5 );
		
		assertTrue( r.decreaseResourceValue(PointsKind.VICTORY_POINTS, r.getResourceValue(PointsKind.VICTORY_POINTS) - 1  ) );
		
		assertFalse( r.decreaseResourceValue(MaterialsKind.SERVANT, r.getResourceValue(MaterialsKind.SERVANT) + 1  ) );
	}
	
	@Test
	public void testAdd() {
		
		int addendum_coin_value = 5;
		int addendum_servant_value = 7;
		int addendum_stone_value = 2;
		int addendum_wood_value = 0;
		int addendum_faith_value = 3;
		int addendum_military_value = 10;
		int addendum_victory_value = 2;
		
		Resources addendum = new Resources();
		addendum.setResourceValue(MaterialsKind.COIN, addendum_coin_value);
		addendum.setResourceValue(MaterialsKind.SERVANT, addendum_servant_value);
		addendum.setResourceValue(MaterialsKind.STONE, addendum_stone_value);
		addendum.setResourceValue(MaterialsKind.WOOD, addendum_wood_value);
		addendum.setResourceValue(PointsKind.FAITH_POINTS, addendum_faith_value);
		addendum.setResourceValue(PointsKind.MILITARY_POINTS, addendum_military_value);
		addendum.setResourceValue(PointsKind.VICTORY_POINTS, addendum_victory_value);
		
		r.add(addendum);
		
		assertEquals( r.getResourceValue(MaterialsKind.COIN), coin_value + addendum_coin_value );
		assertEquals( r.getResourceValue(MaterialsKind.SERVANT), servant_value + addendum_servant_value );
		assertEquals( r.getResourceValue(MaterialsKind.STONE), stone_value + addendum_stone_value );
		assertEquals( r.getResourceValue(MaterialsKind.WOOD), wood_value + addendum_wood_value );
		
		assertEquals( r.getResourceValue(PointsKind.FAITH_POINTS), faith_value + addendum_faith_value );
		assertEquals( r.getResourceValue(PointsKind.MILITARY_POINTS), military_value + addendum_military_value );
		assertEquals( r.getResourceValue(PointsKind.VICTORY_POINTS), victory_value + addendum_victory_value );
	}
	
	@Test
	public void testIsBiggerThan() {
		
		Resources bigger = new Resources();
		bigger.setResourceValue(MaterialsKind.COIN, 10);
		bigger.setResourceValue(MaterialsKind.SERVANT, 28);
		bigger.setResourceValue(MaterialsKind.STONE, 4);
		bigger.setResourceValue(MaterialsKind.WOOD, 7);
		bigger.setResourceValue(PointsKind.FAITH_POINTS, 6);
		bigger.setResourceValue(PointsKind.MILITARY_POINTS, 18);
		bigger.setResourceValue(PointsKind.VICTORY_POINTS, 11);
		assertFalse( r.isBiggerThan(bigger));
		
		Resources biggetExceptForStone = new Resources();
		biggetExceptForStone.setResourceValue(MaterialsKind.COIN, 10);
		biggetExceptForStone.setResourceValue(MaterialsKind.SERVANT, 28);
		biggetExceptForStone.setResourceValue(MaterialsKind.STONE, 1);
		biggetExceptForStone.setResourceValue(MaterialsKind.WOOD, 7);
		biggetExceptForStone.setResourceValue(PointsKind.FAITH_POINTS, 6);
		biggetExceptForStone.setResourceValue(PointsKind.MILITARY_POINTS, 18);
		biggetExceptForStone.setResourceValue(PointsKind.VICTORY_POINTS, 11);
		assertFalse( r.isBiggerThan(biggetExceptForStone));
		
		Resources smaller = new Resources();
		smaller.setResourceValue(MaterialsKind.COIN, 0);
		smaller.setResourceValue(MaterialsKind.SERVANT, 1);
		smaller.setResourceValue(MaterialsKind.STONE, 1);
		smaller.setResourceValue(MaterialsKind.WOOD, 2);
		smaller.setResourceValue(PointsKind.FAITH_POINTS, 3);
		smaller.setResourceValue(PointsKind.MILITARY_POINTS, 3);
		smaller.setResourceValue(PointsKind.VICTORY_POINTS, 3);
		assertTrue( r.isBiggerThan(smaller));
		
		Resources smallerExceptForWood = new Resources();
		smallerExceptForWood.setResourceValue(MaterialsKind.COIN, 0);
		smallerExceptForWood.setResourceValue(MaterialsKind.SERVANT, 1);
		smallerExceptForWood.setResourceValue(MaterialsKind.STONE, 1);
		smallerExceptForWood.setResourceValue(MaterialsKind.WOOD, 12);
		smallerExceptForWood.setResourceValue(PointsKind.FAITH_POINTS, 3);
		smallerExceptForWood.setResourceValue(PointsKind.MILITARY_POINTS, 3);
		smallerExceptForWood.setResourceValue(PointsKind.VICTORY_POINTS, 3);
		assertFalse( r.isBiggerThan(smallerExceptForWood));
	}
}
