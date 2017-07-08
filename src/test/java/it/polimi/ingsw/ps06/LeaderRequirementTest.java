package it.polimi.ingsw.ps06;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.ps06.model.LeaderRequirement;
import it.polimi.ingsw.ps06.model.Resources;
import it.polimi.ingsw.ps06.model.Types.CardType;
import it.polimi.ingsw.ps06.model.Types.MaterialsKind;
import it.polimi.ingsw.ps06.model.Types.PointsKind;

public class LeaderRequirementTest {
	
	private LeaderRequirement leaderRequirement;
	
	private int coin_value		= 1;
	private int servant_value	= 2;
	private int stone_value		= 3;
	private int wood_value		= 4;
	private int faith_value 	= 5;
	private int military_value 	= 6;
	private int victory_value 	= 7;
	
	private int card_territory_count 	= 5;
	private int card_building_count 	= 5;
	private int card_character_count 	= 5;
	private int card_venture_count 		= 5;
	
	@Before
    public void setup() {

		this.leaderRequirement = new LeaderRequirement();
		this.leaderRequirement.setResourceValue(MaterialsKind.COIN, this.coin_value);
		this.leaderRequirement.setResourceValue(MaterialsKind.SERVANT, this.servant_value);
		this.leaderRequirement.setResourceValue(MaterialsKind.STONE, this.stone_value);
		this.leaderRequirement.setResourceValue(MaterialsKind.WOOD, this.wood_value);
		this.leaderRequirement.setResourceValue(PointsKind.FAITH_POINTS, this.faith_value);
		this.leaderRequirement.setResourceValue(PointsKind.MILITARY_POINTS, this.military_value);
		this.leaderRequirement.setResourceValue(PointsKind.VICTORY_POINTS, this.victory_value);
		
		this.leaderRequirement.setResourceValue(CardType.TERRITORY, this.card_territory_count);
		this.leaderRequirement.setResourceValue(CardType.BUILDING, this.card_building_count);
		this.leaderRequirement.setResourceValue(CardType.CHARACTER, this.card_character_count);
		this.leaderRequirement.setResourceValue(CardType.VENTURE, this.card_venture_count);
		
		this.leaderRequirement.setAnyCardReq(6);
    }

	@Test
	public void testSetResourceValue() {
		
		LeaderRequirement requirement2Set = new LeaderRequirement();
		requirement2Set.setResourceValue(MaterialsKind.COIN, 1);
		requirement2Set.setResourceValue(MaterialsKind.SERVANT, 2);
		requirement2Set.setResourceValue(MaterialsKind.STONE, 3);
		requirement2Set.setResourceValue(MaterialsKind.WOOD, 4);
		
		assertEquals("The method setResourceValue() is not working for COIN", 1, requirement2Set.getResourceValue(MaterialsKind.COIN));
		assertEquals("The method setResourceValue() is not working for SERVANT", 2, requirement2Set.getResourceValue(MaterialsKind.SERVANT));
		assertEquals("The method setResourceValue() is not working for STONE", 3, requirement2Set.getResourceValue(MaterialsKind.STONE));
		assertEquals("The method setResourceValue() is not working for WOOD", 4, requirement2Set.getResourceValue(MaterialsKind.WOOD));
		
		requirement2Set.setResourceValue(PointsKind.FAITH_POINTS, 5);
		requirement2Set.setResourceValue(PointsKind.MILITARY_POINTS, 6);
		requirement2Set.setResourceValue(PointsKind.VICTORY_POINTS, 7);
		
		assertEquals("The method setResourceValue() is not working for FAITH_POINTS", 5, requirement2Set.getResourceValue(PointsKind.FAITH_POINTS));
		assertEquals("The method setResourceValue() is not working for MILITARY_POINTS", 6, requirement2Set.getResourceValue(PointsKind.MILITARY_POINTS));
		assertEquals("The method setResourceValue() is not working for VICTORY_POINTS", 7, requirement2Set.getResourceValue(PointsKind.VICTORY_POINTS));
		
		requirement2Set.setResourceValue(CardType.TERRITORY, 8);
		requirement2Set.setResourceValue(CardType.BUILDING, 9);
		requirement2Set.setResourceValue(CardType.CHARACTER, 10);
		requirement2Set.setResourceValue(CardType.VENTURE, 11);
		
		assertEquals("The method setResourceValue() is not working for TERRITORY", 8, requirement2Set.getResourceValue(CardType.TERRITORY));
		assertEquals("The method setResourceValue() is not working for BUILDING", 9, requirement2Set.getResourceValue(CardType.BUILDING));
		assertEquals("The method setResourceValue() is not working for CHARACTER", 10, requirement2Set.getResourceValue(CardType.CHARACTER));
		assertEquals("The method setResourceValue() is not working for VENTURE", 11, requirement2Set.getResourceValue(CardType.VENTURE));
	}
	
	@Test
	public void testSetAnyCard() {
		LeaderRequirement requirement2Set = new LeaderRequirement();
		
		requirement2Set.setAnyCardReq(25);
		assertEquals("The method setResourceValue() is not working for TERRITORY", 25, requirement2Set.getAnyCardReq());
		
		requirement2Set.setAnyCardReq(-3);
		assertEquals("The method setResourceValue() is not working for TERRITORY", 0, requirement2Set.getAnyCardReq());
	}
	
	@Test
	public void testClearResources() {
		
		leaderRequirement.clearResources();
		
		assertEquals("The method clearResources() is not working for COIN", 0, leaderRequirement.getResourceValue(MaterialsKind.COIN));
		assertEquals("The method clearResources() is not working for SERVANT", 0, leaderRequirement.getResourceValue(MaterialsKind.SERVANT));
		assertEquals("The method clearResources() is not working for STONE", 0, leaderRequirement.getResourceValue(MaterialsKind.STONE));
		assertEquals("The method clearResources() is not working for WOOD", 0, leaderRequirement.getResourceValue(MaterialsKind.WOOD));
		
		assertEquals("The method clearResources() is not working for FAITH_POINTS", 0, leaderRequirement.getResourceValue(PointsKind.FAITH_POINTS));
		assertEquals("The method clearResources() is not working for MILITARY_POINTS", 0, leaderRequirement.getResourceValue(PointsKind.MILITARY_POINTS));
		assertEquals("The method clearResources() is not working for VICTORY_POINTS", 0, leaderRequirement.getResourceValue(PointsKind.VICTORY_POINTS));
		
		assertEquals("The method clearResources() is not working for TERRITORY", 0, leaderRequirement.getResourceValue(CardType.TERRITORY));
		assertEquals("The method clearResources() is not working for BUILDING", 0, leaderRequirement.getResourceValue(CardType.BUILDING));
		assertEquals("The method clearResources() is not working for CHARACTER", 0, leaderRequirement.getResourceValue(CardType.CHARACTER));
		assertEquals("The method clearResources() is not working for VENTURE", 0, leaderRequirement.getResourceValue(CardType.VENTURE));
		
		assertEquals("The method clearResources() is not working for AnyCard", 0, leaderRequirement.getAnyCardReq());
	}
	
	@Test
	public void testIncreaseResourceValue() {
		
		leaderRequirement.increaseResourceValue(MaterialsKind.COIN, 5);
		assertEquals( leaderRequirement.getResourceValue(MaterialsKind.COIN), coin_value + 5 );
		
		leaderRequirement.increaseResourceValue(PointsKind.FAITH_POINTS, 8);
		assertEquals( leaderRequirement.getResourceValue(PointsKind.FAITH_POINTS), faith_value + 8 );
		
		leaderRequirement.increaseResourceValue(CardType.TERRITORY, 3);
		assertEquals( leaderRequirement.getResourceValue(CardType.TERRITORY), card_territory_count + 3 );
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
		int addendum_territory_count = 0;
		int addendum_building_count = 4;
		int addendum_character_count = 7;
		int addendum_venture_count = 1;
		
		LeaderRequirement addendum = new LeaderRequirement();
		addendum.setResourceValue(MaterialsKind.COIN, addendum_coin_value);
		addendum.setResourceValue(MaterialsKind.SERVANT, addendum_servant_value);
		addendum.setResourceValue(MaterialsKind.STONE, addendum_stone_value);
		addendum.setResourceValue(MaterialsKind.WOOD, addendum_wood_value);
		addendum.setResourceValue(PointsKind.FAITH_POINTS, addendum_faith_value);
		addendum.setResourceValue(PointsKind.MILITARY_POINTS, addendum_military_value);
		addendum.setResourceValue(PointsKind.VICTORY_POINTS, addendum_victory_value);
		addendum.setResourceValue(CardType.TERRITORY, addendum_territory_count);
		addendum.setResourceValue(CardType.BUILDING, addendum_building_count);
		addendum.setResourceValue(CardType.CHARACTER, addendum_character_count);
		addendum.setResourceValue(CardType.VENTURE, addendum_venture_count);
		
		leaderRequirement.add(addendum);
		
		assertEquals( leaderRequirement.getResourceValue(MaterialsKind.COIN), coin_value + addendum_coin_value );
		assertEquals( leaderRequirement.getResourceValue(MaterialsKind.SERVANT), servant_value + addendum_servant_value );
		assertEquals( leaderRequirement.getResourceValue(MaterialsKind.STONE), stone_value + addendum_stone_value );
		assertEquals( leaderRequirement.getResourceValue(MaterialsKind.WOOD), wood_value + addendum_wood_value );
		
		assertEquals( leaderRequirement.getResourceValue(PointsKind.FAITH_POINTS), faith_value + addendum_faith_value );
		assertEquals( leaderRequirement.getResourceValue(PointsKind.MILITARY_POINTS), military_value + addendum_military_value );
		assertEquals( leaderRequirement.getResourceValue(PointsKind.VICTORY_POINTS), victory_value + addendum_victory_value );
		
		assertEquals( leaderRequirement.getResourceValue(CardType.TERRITORY), card_territory_count + addendum_territory_count );
		assertEquals( leaderRequirement.getResourceValue(CardType.BUILDING), card_building_count + addendum_building_count );
		assertEquals( leaderRequirement.getResourceValue(CardType.CHARACTER), card_character_count + addendum_character_count );
		assertEquals( leaderRequirement.getResourceValue(CardType.VENTURE), card_venture_count + addendum_venture_count );
	}
	
	@Test
	public void testIsBiggerThan() {
		
		LeaderRequirement bigger = new LeaderRequirement();
		bigger.setResourceValue(MaterialsKind.COIN, 10);
		bigger.setResourceValue(MaterialsKind.SERVANT, 28);
		bigger.setResourceValue(MaterialsKind.STONE, 4);
		bigger.setResourceValue(MaterialsKind.WOOD, 7);
		bigger.setResourceValue(PointsKind.FAITH_POINTS, 6);
		bigger.setResourceValue(PointsKind.MILITARY_POINTS, 18);
		bigger.setResourceValue(PointsKind.VICTORY_POINTS, 11);
		bigger.setResourceValue(PointsKind.VICTORY_POINTS, 11);
		bigger.setResourceValue(CardType.TERRITORY, 8);
		bigger.setResourceValue(CardType.BUILDING, 8);
		bigger.setResourceValue(CardType.CHARACTER, 8);
		bigger.setResourceValue(CardType.VENTURE, 8);
		assertFalse( leaderRequirement.isBiggerThan(bigger));
		
		LeaderRequirement biggetExceptForTerritory = new LeaderRequirement();
		biggetExceptForTerritory.setResourceValue(MaterialsKind.COIN, 10);
		biggetExceptForTerritory.setResourceValue(MaterialsKind.SERVANT, 28);
		biggetExceptForTerritory.setResourceValue(MaterialsKind.STONE, 1);
		biggetExceptForTerritory.setResourceValue(MaterialsKind.WOOD, 7);
		biggetExceptForTerritory.setResourceValue(PointsKind.FAITH_POINTS, 6);
		biggetExceptForTerritory.setResourceValue(PointsKind.MILITARY_POINTS, 18);
		biggetExceptForTerritory.setResourceValue(PointsKind.VICTORY_POINTS, 11);
		biggetExceptForTerritory.setResourceValue(CardType.TERRITORY, 2);
		biggetExceptForTerritory.setResourceValue(CardType.BUILDING, 8);
		biggetExceptForTerritory.setResourceValue(CardType.CHARACTER, 8);
		biggetExceptForTerritory.setResourceValue(CardType.VENTURE, 8);
		assertFalse( leaderRequirement.isBiggerThan(biggetExceptForTerritory));
		
		LeaderRequirement smaller = new LeaderRequirement();
		smaller.setResourceValue(MaterialsKind.COIN, 0);
		smaller.setResourceValue(MaterialsKind.SERVANT, 1);
		smaller.setResourceValue(MaterialsKind.STONE, 1);
		smaller.setResourceValue(MaterialsKind.WOOD, 2);
		smaller.setResourceValue(PointsKind.FAITH_POINTS, 3);
		smaller.setResourceValue(PointsKind.MILITARY_POINTS, 3);
		smaller.setResourceValue(PointsKind.VICTORY_POINTS, 3);
		smaller.setResourceValue(CardType.TERRITORY, 2);
		smaller.setResourceValue(CardType.BUILDING, 2);
		smaller.setResourceValue(CardType.CHARACTER, 2);
		smaller.setResourceValue(CardType.VENTURE, 2);
		assertTrue( leaderRequirement.isBiggerThan(smaller));
		
		LeaderRequirement smallerExceptForAnyCard = new LeaderRequirement();
		smallerExceptForAnyCard.setResourceValue(MaterialsKind.COIN, 0);
		smallerExceptForAnyCard.setResourceValue(MaterialsKind.SERVANT, 1);
		smallerExceptForAnyCard.setResourceValue(MaterialsKind.STONE, 1);
		smallerExceptForAnyCard.setResourceValue(MaterialsKind.WOOD, 12);
		smallerExceptForAnyCard.setResourceValue(PointsKind.FAITH_POINTS, 3);
		smallerExceptForAnyCard.setResourceValue(PointsKind.MILITARY_POINTS, 3);
		smallerExceptForAnyCard.setResourceValue(PointsKind.VICTORY_POINTS, 3);
		smallerExceptForAnyCard.setResourceValue(CardType.TERRITORY, 2);
		smallerExceptForAnyCard.setResourceValue(CardType.BUILDING, 2);
		smallerExceptForAnyCard.setResourceValue(CardType.CHARACTER, 2);
		smallerExceptForAnyCard.setResourceValue(CardType.VENTURE, 2);
		smallerExceptForAnyCard.setAnyCardReq(2);
		assertFalse( leaderRequirement.isBiggerThan(smallerExceptForAnyCard));
	}
}
