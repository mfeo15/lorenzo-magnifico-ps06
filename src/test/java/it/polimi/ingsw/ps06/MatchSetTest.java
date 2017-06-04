package it.polimi.ingsw.ps06;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import org.junit.Before;
import org.junit.Test;

public class MatchSetTest {
	
	private MatchSet matchTest;
	private Connection c1;
	private Connection c2;
	
	@Before
    public void setup() throws UnknownHostException, IOException{
        this.matchTest = new MatchSet();
        
        this.c1 = new Connection( new Socket("192.168.1.10", 6789) );
        this.c2 = new Connection( new Socket("192.168.1.20", 6789) );
    }

	@Test
	public void testAdd() throws Exception {
		matchTest.add(c1);
		matchTest.add(c1);
		
		assertEquals(1, matchTest.size());
	}

}
