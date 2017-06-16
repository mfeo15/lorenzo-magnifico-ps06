package it.polimi.ingsw.ps06.model.messages;

import java.net.SocketAddress;

public abstract class Server2Client extends Message{

	private SocketAddress recipient;

	public SocketAddress getRecipient() {
		return recipient;
	}

	public void setRecipient(SocketAddress recipient) {
		this.recipient = recipient;
	}
	 
}
