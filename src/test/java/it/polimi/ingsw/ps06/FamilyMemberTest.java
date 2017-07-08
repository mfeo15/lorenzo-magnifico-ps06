package it.polimi.ingsw.ps06;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.ps06.model.FamilyMember;
import it.polimi.ingsw.ps06.model.Player;
import it.polimi.ingsw.ps06.model.Types.ColorPalette;
import it.polimi.ingsw.ps06.model.bonus_malus.BonusMalusMember;

public class FamilyMemberTest {
	
	@Test
	public void testGetValue() {
		Player p = new Player(0);
		
		FamilyMember m = new FamilyMember(p, ColorPalette.DICE_BLACK);
		m.setValue(2);
		
		assertEquals(2, m.getValue());
		
		ArrayList<ColorPalette> colors = new ArrayList<ColorPalette>();
		colors.add(ColorPalette.DICE_BLACK);
		p.getBonusMalusCollection().add( new BonusMalusMember(2, colors) );
		
		assertEquals(2+2, m.getValue());
	}

	@Test
	public void testGetPlayer() {
		Player p = new Player(0);
		
		FamilyMember m_colored = new FamilyMember(p, ColorPalette.DICE_BLACK);
		FamilyMember m_uncolored = new FamilyMember(p);
		
		assertEquals(p, m_colored.getPlayer());
		assertEquals(p, m_uncolored.getPlayer());
	}
	
	@Test
	public void testGetFakePlayer() {
		Player p = new Player(0);
		
		FamilyMember m_colored = new FamilyMember(p, ColorPalette.DICE_BLACK);
		FamilyMember m_uncolored = new FamilyMember(p);
		
		assertEquals(p, m_colored.getFakePlayer());
		assertNull( m_uncolored.getFakePlayer() );
	}
}
