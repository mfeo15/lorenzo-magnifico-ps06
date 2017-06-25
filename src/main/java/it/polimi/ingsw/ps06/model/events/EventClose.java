package it.polimi.ingsw.ps06.model.events;

import it.polimi.ingsw.ps06.networking.messages.MessageVisitor;

public class EventClose extends StoryBoard {

	@Override
	public void accept(EventVisitor v) {
		v.visit(this);
	}

}
