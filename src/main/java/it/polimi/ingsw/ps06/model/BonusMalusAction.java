package it.polimi.ingsw.ps06.model;

import it.polimi.ingsw.ps06.model.Types.Action;

public class BonusMalusAction extends BonusMalus {

	private Action action;
	
	public BonusMalusAction(int value, Action action) {
		super(value);
		this.action = action;
	}
	
	public Action getAction() {
		return action;
	}
}
