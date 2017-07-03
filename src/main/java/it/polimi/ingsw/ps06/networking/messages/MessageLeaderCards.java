package it.polimi.ingsw.ps06.networking.messages;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps06.model.Types.LeaderStates;

public class MessageLeaderCards extends Server2Client {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1599520886249943921L;
	
	private ArrayList<Integer> leaderCards;
	
	public MessageLeaderCards( ArrayList<Integer> leaderCards) {
		this.leaderCards = leaderCards;
	}
	
	public ArrayList<Integer> getLeaderCards() {
		return this.leaderCards;
	}
	
	public void setLeaderCards( ArrayList<Integer> leaderCards ) {
		this.leaderCards = leaderCards;
	}

	@Override
	public void accept(MessageVisitor visitor) {
		visitor.visit(this);
	}

}
