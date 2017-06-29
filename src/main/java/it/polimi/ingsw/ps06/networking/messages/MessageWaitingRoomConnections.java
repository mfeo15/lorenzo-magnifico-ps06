package it.polimi.ingsw.ps06.networking.messages;

import java.util.ArrayList;

public class MessageWaitingRoomConnections extends Server2Client {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3225347956617979294L;
	
	private ArrayList<String> waitingConnections;
	
	public MessageWaitingRoomConnections(ArrayList<String> a) {
		this.waitingConnections = a;
	}
	
	public ArrayList<String> getWaitingConnections() {
		return waitingConnections;
	}
	
	public void setWaitingConnections(ArrayList<String> a) {
		this.waitingConnections = a;
	}
	
	@Override
	public void accept(MessageVisitor visitor) {
		visitor.visit(this);
	}

}
