package it.polimi.ingsw.ps06.model;

import it.polimi.ingsw.ps06.model.Types.Action;

public class MemberPlaced extends Event {

	private Action action;
	private FamilyMember member;
	
	public MemberPlaced() {
		
	}
	
	public Action getAction() {
		return action;
	}

	@Override
	public void accept(EventVisitor v) {
		v.visit(this);
	}
}
