package it.polimi.ingsw.ps06;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.ps06.model.cards.ExcommunicationTile;

public class ExcommunicationTileTest {
	
	private ExcommunicationTile tile;

	@Before
	public void setUp() throws Exception {
		tile = new ExcommunicationTile();
	}

	@Test
	public void testSetPeriod() {
		tile.setPeriod(3);
		assertEquals(3, tile.getPeriod());
	}
}
