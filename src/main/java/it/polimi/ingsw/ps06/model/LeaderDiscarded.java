package it.polimi.ingsw.ps06.model;

import it.polimi.ingsw.ps06.model.Types.Action;

public class LeaderDiscarded extends Event {
	
	public LeaderDiscarded() {
		
	}

	@Override
	public void accept(EventVisitor v) {
		v.visit(this);
	}
}
