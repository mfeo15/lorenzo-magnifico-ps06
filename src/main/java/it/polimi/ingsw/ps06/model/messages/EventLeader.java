package it.polimi.ingsw.ps06.model.messages;

import it.polimi.ingsw.ps06.model.Types.Action;
import it.polimi.ingsw.ps06.model.Types.ColorPalette;

public abstract class EventLeader extends View2Control {
	protected int leaderCode;

	public EventLeader(int leaderCode) {
		this.leaderCode = leaderCode;
	}
	
	public int getColor() {
		return leaderCode;
	}
	
	public void setCode(int leaderCode) {
		this.leaderCode=leaderCode;
	}
}
