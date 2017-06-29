package it.polimi.ingsw.ps06;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.ps06.model.FamilyMember;
import it.polimi.ingsw.ps06.model.Player;
import it.polimi.ingsw.ps06.model.Privilege;
import it.polimi.ingsw.ps06.model.Resources;
import it.polimi.ingsw.ps06.model.Types.CouncilPrivilege;
import it.polimi.ingsw.ps06.model.Types.MaterialsKind;
import it.polimi.ingsw.ps06.model.Types.PointsKind;

public class PrivilegeTest {
	
	private FamilyMember member;
	private final int default_member_value = 4;
	
	private int initial_player_coins;
	private int initial_player_stone;
	private int initial_player_wood;
	private int initial_player_servants;
	private int initial_player_military;
	
	@Before
	public void setup() {
		
		//Standard first player with Coin:5 Stone:2 Wood:2 Servant:3 and Military:20
		Player player = new Player(0);
		Resources r = new Resources(MaterialsKind.COIN, 5);
		player.getPersonalBoard().addResource(r);
		
		initial_player_coins = player.getPersonalBoard().getAmount(MaterialsKind.COIN);
		initial_player_stone = player.getPersonalBoard().getAmount(MaterialsKind.STONE);
		initial_player_wood = player.getPersonalBoard().getAmount(MaterialsKind.WOOD);
		initial_player_servants = player.getPersonalBoard().getAmount(MaterialsKind.SERVANT);
		initial_player_military = player.getPersonalBoard().getAmount(PointsKind.MILITARY_POINTS);
		
		member = new FamilyMember( player );
		member.setValue(default_member_value);
	}

	@Test
	public void testActivate() {
		
		Privilege p;
		
		//Get the bonus_1 (1 wood and 1 stone) -> player will have +1 coin, +1 wood, +1 stone
		p = new Privilege(0, member, CouncilPrivilege.BONUS_1);
		p.activate();
		assertEquals( member.getPlayer().getPersonalBoard().getAmount(MaterialsKind.COIN), initial_player_coins += 1 );
		assertEquals( member.getPlayer().getPersonalBoard().getAmount(MaterialsKind.STONE), initial_player_stone += 1 );
		assertEquals( member.getPlayer().getPersonalBoard().getAmount(MaterialsKind.WOOD), initial_player_wood += 1 );
		
		//Get the bonus_4 (2 military) -> player will have +1 coin, +2 military
		p = new Privilege(0, member, CouncilPrivilege.BONUS_4);
		p.activate();
		assertEquals( member.getPlayer().getPersonalBoard().getAmount(MaterialsKind.COIN), initial_player_coins += 1 );
		assertEquals( member.getPlayer().getPersonalBoard().getAmount(PointsKind.MILITARY_POINTS), initial_player_military += 2 );
		
		//Get the bonus_2 (2 servants) while using 2 servants to do so -> player will have +1 coin, +1 servants
		int servantsUsed = 2;
		p = new Privilege(servantsUsed, member, CouncilPrivilege.BONUS_2);
		p.activate();
		assertEquals( member.getPlayer().getPersonalBoard().getAmount(MaterialsKind.COIN), initial_player_coins += 1 );
		assertEquals( member.getPlayer().getPersonalBoard().getAmount(MaterialsKind.SERVANT), initial_player_servants - servantsUsed + 2 );
	}

}
