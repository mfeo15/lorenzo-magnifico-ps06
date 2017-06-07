package it.polimi.ingsw.ps06.model.messages;

public interface MessageConnectionVisitable {

	public <T > T accept(MessageVisitor visitor);
}
