package it.polimi.ingsw.ps06;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.ps06.model.CardAcquisition;
import it.polimi.ingsw.ps06.model.Player;
import it.polimi.ingsw.ps06.model.Resources;
import it.polimi.ingsw.ps06.model.Types.MaterialsKind;
import it.polimi.ingsw.ps06.model.Types.PointsKind;
import it.polimi.ingsw.ps06.model.cards.developement.Building;
import it.polimi.ingsw.ps06.model.cards.developement.Character;
import it.polimi.ingsw.ps06.model.cards.developement.Territory;
import it.polimi.ingsw.ps06.model.cards.developement.Venture;

public class CardAcquisitionTest {
	
	private CardAcquisition cardBuy;
	
	private boolean extraCost;
	
	private Player player;
	private Territory t;
	private Building b;
	private Character c;
	private Venture v;
	
	private int player_initial_servants;
	private final int player_initial_coins = 2;
	private final int player_initial_military = 20;
	private final int building_coin_cost = 5;
	private final int charachter_coin_cost = 3;
	private final int venture_coin_cost = 1;
	
	@Before
	public void setup() {
		
		//Standard first player with Coin:5 Stone:2 Wood:2 Servant:3 and Military:20
		player = new Player(0);
		Resources r = new Resources(MaterialsKind.COIN, player_initial_coins);
		r.setResourceValue(PointsKind.MILITARY_POINTS, player_initial_military);
		player.getPersonalBoard().addResource(r);
		player_initial_servants = player.getPersonalBoard().getInventory().getResourceValue(MaterialsKind.SERVANT);
		
		t = new Territory();
		
		b = new Building();
		b.setCost( new Resources(MaterialsKind.COIN, building_coin_cost) );
		
		c = new Character();
		c.setCost(new Resources(MaterialsKind.COIN, charachter_coin_cost) ); 
		
		v = new Venture();
		v.setRequirement(new Resources(MaterialsKind.COIN, venture_coin_cost) );
	}

	@Test
	public void testCheckCosts() {
		
		//Owning 2 coins i want to buy a free territory, can I? -> TRUE
		cardBuy = new CardAcquisition(t, player, 0);
		extraCost = false;
		assertTrue( cardBuy.checkCosts(extraCost) );
		
		//Owning 2 coins i want to buy a free territory (but i have an extra cost of 3 coins), can I? -> FALSE
		cardBuy = new CardAcquisition(t, player, 0);
		extraCost = true;
		assertFalse( cardBuy.checkCosts(extraCost) );
		
		//Owning 2 coins i want to buy a 5 coin building, can I? -> FALSE
		cardBuy = new CardAcquisition(b, player, 0);
		extraCost = false;
		assertFalse( cardBuy.checkCosts(extraCost) );
		
		//Owning 2 coins i want to buy a 1 coin venture, can I? -> TRUE
		cardBuy = new CardAcquisition(v, player, 0);
		extraCost = false;
		assertTrue( cardBuy.checkCosts(extraCost) );
		
		//Owning 2 coins ... what if NULL is passed as a card, can I? -> FALSE
		cardBuy = new CardAcquisition(null, player, 0);
		extraCost = false;
		assertFalse( cardBuy.checkCosts(extraCost) );
	}
	
	@Test
	public void testCheckRequirements() {
		
		//There is no requirements for cards other than Ventures, all TRUE here
		cardBuy = new CardAcquisition(t, player, 0);
		assertTrue( cardBuy.checkRequirements() );
		cardBuy = new CardAcquisition(b, player, 0);
		assertTrue( cardBuy.checkRequirements() );
		cardBuy = new CardAcquisition(c, player, 0);
		assertTrue( cardBuy.checkRequirements() );
		
		//Owning 20 Military Points i want a Venture with no conditions -> TRUE
		cardBuy = new CardAcquisition(v, player, 0);
		assertTrue( cardBuy.checkRequirements() );
		
		//Owning 20 Military Points i want a Venture with military at least 15 -> TRUE
		cardBuy = new CardAcquisition(v, player, 0);
		v.setMilRequirement(15);
		assertTrue( cardBuy.checkRequirements() );
		
		//Owning 20 Military Points i want a Venture with military at least 30 -> FALSE
		cardBuy = new CardAcquisition(v, player, 0);
		v.setMilRequirement(30);
		assertFalse( cardBuy.checkRequirements() );
	}
	
	@Test
	public void testActivate() {
		
		//I get my first territory card -> size must be of 1
		cardBuy = new CardAcquisition(t, player, 0);
		cardBuy.checkCosts( extraCost = false );
		cardBuy.checkRequirements();
		cardBuy.activate();
		assertEquals( player.getPersonalBoard().getTerritories().size(), 1 );
		
		//I get my second territory card -> size must be of 2
		cardBuy = new CardAcquisition(t, player, 0);
		cardBuy.checkCosts( extraCost = false );
		cardBuy.checkRequirements();
		cardBuy.activate();
		assertEquals( player.getPersonalBoard().getTerritories().size(), 2 );
		
		//I get my first character card using 2 servants -> size must be of 1
		int servants = 2;
		cardBuy = new CardAcquisition(c, player, servants);
		cardBuy.checkCosts( extraCost = false );
		cardBuy.checkRequirements();
		cardBuy.activate();
		assertEquals( player.getPersonalBoard().getCharacters().size(), 1 );
		assertEquals( player.getPersonalBoard().getInventory().getResourceValue(MaterialsKind.SERVANT), player_initial_servants - servants );
		
		//I get my second venture card, now i have to pay the extra cost -> size must be of 1 && coins decreased of 1
		cardBuy = new CardAcquisition(v, player, 0);
		v.setMilRequirement(15);
		cardBuy.checkCosts( extraCost = false );
		cardBuy.checkRequirements();
		cardBuy.activate();
		assertEquals( player.getPersonalBoard().getVentures().size(), 1 );
		assertEquals( player.getPersonalBoard().getInventory().getResourceValue(MaterialsKind.COIN), 
																		player_initial_coins - venture_coin_cost );
	}

}
