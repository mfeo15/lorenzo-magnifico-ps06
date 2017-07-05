package it.polimi.ingsw.ps06.model.events;

public class BoardHasLoaded extends StoryBoard {
	
	@Override
	public void accept(EventVisitor visitor) {
		visitor.visit(this);
	}
}
