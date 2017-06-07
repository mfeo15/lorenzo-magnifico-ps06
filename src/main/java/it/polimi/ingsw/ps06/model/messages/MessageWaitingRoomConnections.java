package it.polimi.ingsw.ps06.model.messages;

import java.util.ArrayList;

public class MessageWaitingRoomConnections extends Server2Client {

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
	public ArrayList<String> accept(MessageVisitor visitor) {
		return visitor.visit(this);
	}

}
