package it.polimi.ingsw.ps06.model.messages;

import java.util.ArrayList;

public class MessagePlayingConnections extends Server2Client {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3664380926756621120L;
	
	private ArrayList<String> playingConnections;
	
	public MessagePlayingConnections(ArrayList<String> a) {
		this.playingConnections = a;
	}
	
	public ArrayList<String> getWaitingConnections() {
		return playingConnections;
	}
	
	public void setWaitingConnections(ArrayList<String> a) {
		this.playingConnections = a;
	}
	
	@Override
	public void accept(MessageVisitor visitor) {
		visitor.visit(this);
	}

}
