package it.polimi.ingsw.ps06.model.messages;

import java.util.ArrayList;

public class MessageCurrentPlayerOrder extends Broadcast {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3664380926756621120L;
	
	private ArrayList<Integer> players;
	
	public MessageCurrentPlayerOrder(ArrayList<Integer> a) {
		this.players = a;
	}
	
	public ArrayList<Integer> getPlayerOrder() {
		return players;
	}
	
	public void setPlayerOrder(ArrayList<Integer> a) {
		this.players = a;
	}
	
	@Override
	public void accept(MessageVisitor visitor) {
		visitor.visit(this);
	}

}
