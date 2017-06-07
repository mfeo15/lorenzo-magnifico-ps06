package it.polimi.ingsw.ps06.model;

import it.polimi.ingsw.ps06.model.Types.Action;
import it.polimi.ingsw.ps06.model.messages.EventMessage;
import it.polimi.ingsw.ps06.model.messages.MessageVisitor;

public class LeaderDiscarded extends EventMessage {
	
	public LeaderDiscarded() {
		
	}

	@Override
	public void accept(MessageVisitor v) {
		v.visit(this);
	}
}
