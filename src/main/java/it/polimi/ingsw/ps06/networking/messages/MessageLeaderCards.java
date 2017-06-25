package it.polimi.ingsw.ps06.networking.messages;

import java.util.HashMap;

import it.polimi.ingsw.ps06.model.Types.LeaderStates;

public class MessageLeaderCards extends Server2Client {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1599520886249943921L;
	
	private HashMap<Integer, LeaderStates> leaderCards;
	
	public MessageLeaderCards( HashMap<Integer, LeaderStates> leaderCards) {
		this.leaderCards = leaderCards;
	}
	
	public HashMap<Integer, LeaderStates> getLeaderCards() {
		return this.leaderCards;
	}
	
	public void setLeaderCards( HashMap<Integer, LeaderStates> leaderCards ) {
		this.leaderCards = leaderCards;
	}

	@Override
	public void accept(MessageVisitor visitor) {
		visitor.visit(this);
	}

}
