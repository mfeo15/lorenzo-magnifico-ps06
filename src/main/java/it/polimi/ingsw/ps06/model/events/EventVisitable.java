package it.polimi.ingsw.ps06.model.events;

public interface EventVisitable {

	public void accept(EventVisitor visitor);
}
