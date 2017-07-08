package it.polimi.ingsw.ps06.networking.messages;

import it.polimi.ingsw.ps06.model.events.Event;

public class MessageEvent extends Message {

	private Event e;
	
	public MessageEvent(Event e) {
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
