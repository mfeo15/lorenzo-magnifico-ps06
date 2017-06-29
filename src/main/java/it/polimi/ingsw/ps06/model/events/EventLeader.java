package it.polimi.ingsw.ps06.model.events;

import it.polimi.ingsw.ps06.model.Types.Action;
import it.polimi.ingsw.ps06.model.Types.ColorPalette;

public abstract class EventLeader extends View2Control {
	
	private int leaderCode;

	public EventLeader(int leaderCode) {
		this.leaderCode = leaderCode;
	}
	
	public int getCode() {
		return leaderCode;
	}
	
	public void setCode(int leaderCode) {
		this.leaderCode=leaderCode;
	}
}
