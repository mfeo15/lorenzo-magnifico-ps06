package it.polimi.ingsw.ps06.model;

public interface EventVisitable {

	public void accept(EventVisitor visitor);
}
