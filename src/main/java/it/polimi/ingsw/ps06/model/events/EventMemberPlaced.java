package it.polimi.ingsw.ps06.model.events;

import it.polimi.ingsw.ps06.model.Types.Action;
import it.polimi.ingsw.ps06.model.Types.ColorPalette;

public class EventMemberPlaced extends EventMember {
	
	private Action chosenAction;
	private ColorPalette color;
	
	public EventMemberPlaced(Action chosenAction, ColorPalette color) {
		this.chosenAction = chosenAction;
		this.color = color;
	}
	
	public Action getAction() {
		return chosenAction;
	}
	
	public ColorPalette getColor() {
		return color;
	}
	
	public void setAction(Action chosenAction) {
		this.chosenAction=chosenAction;
	}
	
	public void setColor(ColorPalette color) {
		this.color=color;
	}
	
	@Override
	public void accept(EventVisitor visitor) {
		visitor.visit(this);
		
	}
	
}
