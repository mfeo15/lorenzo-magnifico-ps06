package it.polimi.ingsw.ps06.model.events;

import it.polimi.ingsw.ps06.model.Types.Action;
import it.polimi.ingsw.ps06.model.Types.ColorPalette;

public class EventMemberPlaced extends EventMember {
	
	private Action chosenAction;
	private ColorPalette color;
	private int servantsBonus;
	
	public EventMemberPlaced(Action chosenAction, ColorPalette color, int servantsBonus) {
		this.chosenAction = chosenAction;
		this.color = color;
		this.servantsBonus = servantsBonus;
	}
	
	public Action getAction() {
		return chosenAction;
	}
	
	public ColorPalette getColor() {
		return color;
	}
	
	public int getServantsBonus() {
		return servantsBonus;
	}
	
	public void setAction(Action chosenAction) {
		this.chosenAction=chosenAction;
	}
	
	public void setColor(ColorPalette color) {
		this.color=color;
	}
	
	public void setServantsBonus(int servantsBonus) {
		this.servantsBonus = servantsBonus;
	}
	
	@Override
	public void accept(EventVisitor visitor) {
		visitor.visit(this);
		
	}
	
}
