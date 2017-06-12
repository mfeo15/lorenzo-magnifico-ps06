package it.polimi.ingsw.ps06.model.events;

import it.polimi.ingsw.ps06.model.messages.MessageVisitor;

public class EventClose extends Event {

	@Override
	public void accept(EventVisitor v) {
		v.visit(this);
	}

}
