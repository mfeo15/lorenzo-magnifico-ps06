package it.polimi.ingsw.ps06.model.events;

import it.polimi.ingsw.ps06.model.Types.ColorPalette;

public abstract class EventMember extends View2Control {
	
	private ColorPalette color;
	
	public EventMember(ColorPalette color) {
		this.color = color;
	}
	
	public ColorPalette getColor() {
		return color;
	}
	
	public void setColor(ColorPalette color) {
		this.color=color;
	}

}
