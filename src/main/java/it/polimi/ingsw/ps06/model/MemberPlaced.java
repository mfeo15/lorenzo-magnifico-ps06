package it.polimi.ingsw.ps06.model;

import it.polimi.ingsw.ps06.model.Types.Action;
import it.polimi.ingsw.ps06.model.messages.EventMessage;
import it.polimi.ingsw.ps06.model.messages.MessageVisitor;

public class MemberPlaced extends EventMessage {

	private Action action;
	private FamilyMember member;
	
	public MemberPlaced() {
		
	}
	
	public Action getAction() {
		return action;
	}

	@Override
	public void accept(MessageVisitor v) {
		v.visit(this);
	}
}
