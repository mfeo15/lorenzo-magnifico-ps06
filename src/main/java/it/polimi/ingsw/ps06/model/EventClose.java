package it.polimi.ingsw.ps06.model;

public class EventClose extends Event {

	@Override
	public void accept(EventVisitor v) {
		v.visit(this);
	}

}
