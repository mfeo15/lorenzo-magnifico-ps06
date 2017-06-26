package it.polimi.ingsw.ps06.model;

import it.polimi.ingsw.ps06.model.Types.ActionCategory;

public class BonusMalusAction extends BonusMalus {

	private ActionCategory actionCategory;
	
	public BonusMalusAction(int value, ActionCategory actionCategory) {
		super(value);
		this.actionCategory = actionCategory;
	}
	
	public ActionCategory getActionCategory() {
		return actionCategory;
	}
	
	public void setActionCategory(ActionCategory actionCategory) {
		this.actionCategory = actionCategory;
	}
}
