package it.polimi.ingsw.ps06;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.ps06.networking.User;


public class UserTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSetUsername() {
		User u = new User();
		u.setUsername("theTestUsername");
		
		assertEquals("theTestUsername", u.getUsername());
	}
	
	@Test
	public void testSetPassword() {
		User u = new User();
		u.setPassword("theTestPassword");
		
		assertEquals("theTestPassword", u.getPassword());
	}
	
	@Test
	public void testSetGameCounter() {
		User u = new User();
		u.setGameCounter(20);
		
		assertEquals(20, u.getGameCounter());
	}
	
	@Test
	public void testSetWinCounder() {
		User u = new User();
		u.setWinCounder(3);
		
		assertEquals(3, u.getWinCounder());
	}
	
	@Test
	public void testSetMaxScore() {
		User u = new User();
		u.setMaxScore(137);
		
		assertEquals(137, u.getMaxScore());
	}
	
	@Test
	public void testSetSecondPlaceCounter() {
		User u = new User();
		u.setSecondPlaceCounter(8);
		
		assertEquals(8, u.getSecondPlaceCounter());
	}

	@Test
	public void testAuthenticate() {
		User u = new User();
		u.setUsername("correct_username");
		u.setPassword("correct_password");
		
		assertTrue( u.autenticate("correct_username", "correct_password") );
		
		assertFalse( u.autenticate("correct_username", "wrong_password") );
	}
}
