package it.polimi.ingsw.ps06.model.messages;

public interface EventMessageVisitable {

	public void accept(MessageVisitor visitor);
}
