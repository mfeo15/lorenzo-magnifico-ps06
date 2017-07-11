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
import it.polimi.ingsw.ps06.model.effects.EffectsResourcesByCard;

public class EffectsResourcesByCardTest {

	private EffectsResourcesByCard e;
	private Player p;
	
	private int player_coin  = 5;
	private int player_faith = 0;
	private int player_building = 3;
	
	private int bonus_coin  = 3;
	private int bonus_faith = 1;
	
	@Before
	public void setUp() 
	{
		Resources bonus = new Resources();
		bonus.setResourceValue(MaterialsKind.COIN, bonus_coin);
		bonus.setResourceValue(PointsKind.FAITH_POINTS, bonus_faith);
		
		e = new EffectsResourcesByCard(bonus, ColorPalette.CARD_YELLOW);
		
		p = new Player(0);
		p.getPersonalBoard().addResource(MaterialsKind.COIN, player_coin);
		p.getPersonalBoard().addResource(PointsKind.FAITH_POINTS, player_faith);
		for(int i = 0; i < player_building; i++) {
			p.getPersonalBoard().addCard( new Building() );
		}
	}

	@Test
	public void test() {
		e.activate(p);
		
		assertEquals( player_coin + (bonus_coin * player_building), p.getPersonalBoard().getAmount(MaterialsKind.COIN) );
		assertEquals( player_faith + (bonus_faith * player_building), p.getPersonalBoard().getAmount(PointsKind.FAITH_POINTS) );
	}

}
