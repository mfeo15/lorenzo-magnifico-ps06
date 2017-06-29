package it.polimi.ingsw.ps06.networking.messages;

public interface MessageVisitable {

	public void accept(MessageVisitor visitor);
}
