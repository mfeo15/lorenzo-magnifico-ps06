package it.polimi.ingsw.ps06.model.events;

import it.polimi.ingsw.ps06.view.Board;

public class RoomHasLoaded extends StoryBoard {
	
	@Override
	public void accept(EventVisitor visitor) {
		visitor.visit(this);
	}
}
