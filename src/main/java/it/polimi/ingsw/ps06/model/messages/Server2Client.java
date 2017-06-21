package it.polimi.ingsw.ps06.model.messages;

import java.net.SocketAddress;

public abstract class Server2Client extends Message{

	private int recipientPlayerID;

	public int getRecipient() {
		return recipientPlayerID;
	}

	public void setRecipient(int recipientPlayerID) {
		this.recipientPlayerID = recipientPlayerID;
	}
	 
}
