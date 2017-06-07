package it.polimi.ingsw.ps06.model.messages;

import it.polimi.ingsw.ps06.model.Types.Action;
import it.polimi.ingsw.ps06.model.Types.ColorPalette;
import it.polimi.ingsw.ps06.view.Board;

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
	public void accept(MessageVisitor visitor) {
		visitor.visit(this);
		
	}
	
}
