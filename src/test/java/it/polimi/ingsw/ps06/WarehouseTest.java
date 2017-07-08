package it.polimi.ingsw.ps06;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.ps06.model.Resources;
import it.polimi.ingsw.ps06.model.Types.MaterialsKind;
import it.polimi.ingsw.ps06.model.Types.PointsKind;
import it.polimi.ingsw.ps06.model.Warehouse;

public class WarehouseTest {
	
	private int initial_wood = 2;
	private int initial_stone = 2;
	private int initial_servant = 3;

	@Test
	public void testInitialSetup() {
		Warehouse w = new Warehouse();
		
		assertEquals(initial_stone, w.getResourceValue(MaterialsKind.STONE));
		assertEquals(initial_wood, w.getResourceValue(MaterialsKind.WOOD));
		assertEquals(initial_servant, w.getResourceValue(MaterialsKind.SERVANT));
	}
	
	@Test
	public void testIncreaseMaterials() {
		Warehouse w = new Warehouse();
		
		int increase_material = 5;
		w.increaseResourceValue(MaterialsKind.SERVANT, increase_material);
		assertEquals(initial_servant + increase_material, w.getResourceValue(MaterialsKind.SERVANT));
		
		int increase_point = 2;
		w.increaseResourceValue(PointsKind.FAITH_POINTS, increase_point);
		assertEquals(increase_point, w.getResourceValue(PointsKind.FAITH_POINTS));
	}
	
	@Test
	public void testDecreaseResourceValue() {
		Warehouse w = new Warehouse();
		w.increaseResourceValue(PointsKind.FAITH_POINTS, 5);
		
		int decrease_material = 2;
		w.decreaseResourceValue(MaterialsKind.SERVANT, decrease_material);
		assertEquals(initial_servant - decrease_material, w.getResourceValue(MaterialsKind.SERVANT));
		
		int decrease_point = 4;
		w.decreaseResourceValue(PointsKind.FAITH_POINTS, decrease_point);
		assertEquals( 5 - decrease_point, w.getResourceValue(PointsKind.FAITH_POINTS));
	}
	
	@Test
	public void testAddResources() {
		Warehouse w = new Warehouse();
		
		Resources r = new Resources();
		r.setResourceValue(MaterialsKind.STONE, 4);
		r.setResourceValue(PointsKind.VICTORY_POINTS, 2);
		
		w.addResources(r);
		
		assertEquals( initial_stone + r.getResourceValue(MaterialsKind.STONE), w.getResourceValue(MaterialsKind.STONE));
		assertEquals( r.getResourceValue(PointsKind.VICTORY_POINTS), w.getResourceValue(PointsKind.VICTORY_POINTS));
	}
	
	@Test
	public void testReduceRes() {
		Warehouse w = new Warehouse();
		
		Resources r = new Resources();
		r.setResourceValue(MaterialsKind.STONE, 1);
		
		w.reduceRes(r);
		
		assertEquals( initial_stone - r.getResourceValue(MaterialsKind.STONE), w.getResourceValue(MaterialsKind.STONE));
	}
}
