/** 
 * 
 */
package it.polimi.ingsw.ps06;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.ps06.model.FamilyMember;
import it.polimi.ingsw.ps06.model.Player;
import it.polimi.ingsw.ps06.model.Types.ColorPalette;

/**
 * @author Luca
 *
 */
public class FamilyMemberTest {

	/**
	 * Test method for {@link it.polimi.ingsw.ps06.model.FamilyMember#FamilyMember(it.polimi.ingsw.ps06.model.Player)}.
	 */
	@Test
	public void testFamilyMemberPlayer() {
		Player p = new Player(2);
		FamilyMember m = new FamilyMember(p);
		
		assertTrue( m.getValue() == 0);
		m.setValue(2);
		assertTrue( m.getValue() == 2);
		
		assertTrue( m.getColor() == ColorPalette.UNCOLORED);
		m.setColor(ColorPalette.DICE_BLACK);
		assertEquals( ColorPalette.DICE_BLACK, m.getColor() );
		
	}

	/**
	 * Test method for {@link it.polimi.ingsw.ps06.model.FamilyMember#FamilyMember(it.polimi.ingsw.ps06.model.Player, it.polimi.ingsw.ps06.model.Types.ColorPalette)}.
	 */
	@Test
	public void testFamilyMemberPlayerColorPalette() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link it.polimi.ingsw.ps06.model.FamilyMember#setValue(int)}.
	 */
	@Test
	public void testSetValue() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link it.polimi.ingsw.ps06.model.FamilyMember#getValue()}.
	 */
	@Test
	public void testGetValue() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link it.polimi.ingsw.ps06.model.FamilyMember#setColor(it.polimi.ingsw.ps06.model.Types.ColorPalette)}.
	 */
	@Test
	public void testSetColor() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link it.polimi.ingsw.ps06.model.FamilyMember#getColor()}.
	 */
	@Test
	public void testGetColor() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link it.polimi.ingsw.ps06.model.FamilyMember#getColorString()}.
	 */
	@Test
	public void testGetColorString() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link it.polimi.ingsw.ps06.model.FamilyMember#getPlayer()}.
	 */
	@Test
	public void testGetPlayer() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link it.polimi.ingsw.ps06.model.FamilyMember#getFakePlayer()}.
	 */
	@Test
	public void testGetFakePlayer() {
		fail("Not yet implemented");
	}

}
