package it.polimi.ingsw.ps06.model.messages;

public interface MessageVisitable {

	public void accept(MessageVisitor visitor);
}
