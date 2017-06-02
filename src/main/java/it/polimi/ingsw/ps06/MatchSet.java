package it.polimi.ingsw.ps06;

import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Map;

public class MatchSet {

	private Map<SocketAddress, Connection> participants;
	
	public MatchSet() {
		
	}
	
	public void add(Connection c) {
		participants.put(c.ID(), c);
	}
	
	public void add(ArrayList<Connection> a) {
		a.forEach( c -> participants.put(c.ID(), c) );
	}
	
	public void add(Map<SocketAddress, Connection> m) {
		participants.putAll(m);
	}
	
	public void remove() {
		
	}
}
