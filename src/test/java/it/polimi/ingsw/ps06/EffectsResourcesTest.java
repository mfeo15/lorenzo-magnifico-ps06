package it.polimi.ingsw.ps06;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.ps06.model.Player;
import it.polimi.ingsw.ps06.model.Resources;
import it.polimi.ingsw.ps06.model.Types.MaterialsKind;
import it.polimi.ingsw.ps06.model.Types.PointsKind;
import it.polimi.ingsw.ps06.model.effects.EffectsResources;

public class EffectsResourcesTest {
	
	private EffectsResources e;
	private Player p;
	
	private int player_coin  = 5;
	private int player_faith = 0;
	
	private int bonus_coin  = 3;
	private int bonus_faith = 1;

	@Before
	public void setUp()  
	{
		Resources bonus = new Resources();
		bonus.setResourceValue(MaterialsKind.COIN, bonus_coin);
		bonus.setResourceValue(PointsKind.FAITH_POINTS, bonus_faith);
		
		e = new EffectsResources(bonus);
		
		p = new Player(0);
		p.getPersonalBoard().addResource(MaterialsKind.COIN, player_coin);
		p.getPersonalBoard().addResource(PointsKind.FAITH_POINTS, player_faith);
	}

	@Test
	public void testActivate() {
		e.activate(p);
		
		assertEquals( player_coin + bonus_coin, p.getPersonalBoard().getAmount(MaterialsKind.COIN) );
		assertEquals( player_faith + bonus_faith, p.getPersonalBoard().getAmount(PointsKind.FAITH_POINTS) );
	}
}
