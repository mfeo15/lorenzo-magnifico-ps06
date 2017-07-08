package it.polimi.ingsw.ps06;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.ps06.model.Player;
import it.polimi.ingsw.ps06.model.Resources;
import it.polimi.ingsw.ps06.model.Types.ColorPalette;
import it.polimi.ingsw.ps06.model.Types.MaterialsKind;
import it.polimi.ingsw.ps06.model.Types.PointsKind;
import it.polimi.ingsw.ps06.model.cards.developement.Building;
import it.polimi.ingsw.ps06.model.effects.EffectsResourcesByPoint;

public class EffectsResourcesByPointTest {

	private EffectsResourcesByPoint e;
	private EffectsResourcesByPoint e_weighted;
	private Player p;
	
	private int player_coin  = 5;
	private int player_faith = 0;
	private int player_military = 12;
	
	private int bonus_coin  = 3;
	private int bonus_faith = 1;
	
	private int weight = 2;
	
	@Before
	public void setUp() 
	{
		Resources bonus = new Resources();
		bonus.setResourceValue(MaterialsKind.COIN, bonus_coin);
		bonus.setResourceValue(PointsKind.FAITH_POINTS, bonus_faith);
		
		e = new EffectsResourcesByPoint(bonus, PointsKind.MILITARY_POINTS);
		e_weighted = new EffectsResourcesByPoint(bonus, PointsKind.MILITARY_POINTS, weight);
		
		p = new Player(0);
		p.getPersonalBoard().addResource(MaterialsKind.COIN, player_coin);
		p.getPersonalBoard().addResource(PointsKind.FAITH_POINTS, player_faith);
		p.getPersonalBoard().addResource(PointsKind.MILITARY_POINTS, player_military);
	}

	@Test
	public void test() 
	{
		e.activate(p);
		assertEquals( player_coin + (bonus_coin * player_military), p.getPersonalBoard().getAmount(MaterialsKind.COIN) );
		assertEquals( player_faith + (bonus_faith * player_military), p.getPersonalBoard().getAmount(PointsKind.FAITH_POINTS) );
		
		//Updates values after the first effect, preparing the test for the next one
		player_coin = p.getPersonalBoard().getAmount(MaterialsKind.COIN);
		player_faith = p.getPersonalBoard().getAmount(PointsKind.FAITH_POINTS);
		
		e_weighted.activate(p);
		assertEquals( player_coin + (bonus_coin * (player_military/weight)), p.getPersonalBoard().getAmount(MaterialsKind.COIN) );
		assertEquals( player_faith + (bonus_faith * (player_military/weight)), p.getPersonalBoard().getAmount(PointsKind.FAITH_POINTS) );
	}

}
