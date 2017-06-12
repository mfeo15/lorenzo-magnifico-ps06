package it.polimi.ingsw.ps06.model.messages;

import it.polimi.ingsw.ps06.model.events.Event;

public class EventMessage extends Message {

	private Event e;
	
	public EventMessage(Event e) {
		this.e = e;
	}
	
	public Event getEvent() {
		return e;
	}
	
	public void setEvent(Event e) {
		this.e = e;
	}
	
	@Override
	public void accept(MessageVisitor visitor) {
		visitor.visit(this);
	}

}
