package it.polimi.ingsw.ps06.model.events;

import it.polimi.ingsw.ps06.model.Types.Action;
import it.polimi.ingsw.ps06.model.Types.ColorPalette;
import it.polimi.ingsw.ps06.model.Types.CouncilPrivilege;

public class EventMemberPlacedWithPrivilege extends EventMemberPlaced {
	
	private CouncilPrivilege privilege;

	public EventMemberPlacedWithPrivilege(Action chosenAction, ColorPalette color, int servantsBonus, CouncilPrivilege privilege) {
		
		super(chosenAction, color, servantsBonus);
		
		this.privilege = privilege;
	}
	
	public CouncilPrivilege getPrivilege() {
		return privilege;
	}

	public void setPrivilege(CouncilPrivilege privilege) {
		this.privilege = privilege;
	}
	
	@Override
	public void accept(EventVisitor visitor) {
		visitor.visit(this);
	}
	
}
