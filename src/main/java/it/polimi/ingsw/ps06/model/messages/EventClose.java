package it.polimi.ingsw.ps06.model.messages;

public class EventClose extends EventMessage {

	@Override
	public void accept(MessageVisitor v) {
		v.visit(this);
	}

}
