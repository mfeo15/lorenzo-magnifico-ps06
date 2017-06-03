package it.polimi.ingsw.ps06;

import java.util.ArrayList;

public class MatchSet {

	private final int MAX_SIZE = 4;
	
	private ArrayList<Connection> participants;
	
	public MatchSet() {
		participants = new ArrayList<Connection>();
	}
	
	public void add(Connection c) throws Exception {
		
		if ( contains(c) )
			return;
		
		if ( ! isFull() ) {
			participants.add(c);
		} else {
			throw new Exception("MatchSet is full");
		}
	}
	
	public void add(ArrayList<Connection> a) throws Exception {
		
		/*
		for (Connection c : a)
			if ( contains(c) ) a.remove(c);
		*/
		a.removeIf( c -> contains(c) );
		
		if (a.size() + participants.size() > MAX_SIZE )
			throw new Exception("Paramater array makes MatchSet out of bound");
		
		participants.addAll(a);
	}
	
	
	public int size() {
		return participants.size();
	}
	
	// controlla che l'elemento sia contenuto nell'insieme
	public boolean contains(Connection c) {	
		return participants.contains(c);
	}
	
	public boolean isFull() {
		return ( size() == MAX_SIZE );
	}
	
	public void remove(Connection c) {
		participants.remove(c);
	}

	@Override
	public boolean equals(Object obj) {
		
		return ((MatchSet) obj).participants.equals(this.participants);
	}
}