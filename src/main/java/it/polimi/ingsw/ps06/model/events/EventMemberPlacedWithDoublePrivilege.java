package it.polimi.ingsw.ps06.model.events;

import it.polimi.ingsw.ps06.model.Types.Action;
import it.polimi.ingsw.ps06.model.Types.ColorPalette;
import it.polimi.ingsw.ps06.model.Types.CouncilPrivilege;

public class EventMemberPlacedWithDoublePrivilege extends EventMemberPlacedWithPrivilege {
	
	private CouncilPrivilege secondPrivilegeDifferentFromTheFirstOne;

	public EventMemberPlacedWithDoublePrivilege(Action chosenAction, ColorPalette color, int servantsBonus, 
							CouncilPrivilege privilege, CouncilPrivilege secondPrivilegeDifferentFromTheFirstOne) {
		super(chosenAction, color, servantsBonus, privilege);
		
		this.secondPrivilegeDifferentFromTheFirstOne = secondPrivilegeDifferentFromTheFirstOne;
	}
	
	public CouncilPrivilege getSecondPrivilegeDifferentFromTheFirstOne() {
		return secondPrivilegeDifferentFromTheFirstOne;
	}

	public void setSecondPrivilegeDifferentFromTheFirstOne(CouncilPrivilege secondPrivilegeDifferentFromTheFirstOne) {
		this.secondPrivilegeDifferentFromTheFirstOne = secondPrivilegeDifferentFromTheFirstOne;
	}
	
	@Override
	public void accept(EventVisitor visitor) {
		visitor.visit(this);
	}
}
