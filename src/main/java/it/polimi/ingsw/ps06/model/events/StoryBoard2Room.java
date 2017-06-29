package it.polimi.ingsw.ps06.model.events;

import it.polimi.ingsw.ps06.view.Room;

public class StoryBoard2Room extends StoryBoard {

	private transient Room view;
	
	public StoryBoard2Room(Room view) {
		this.view = view;
	}
	
	public Room getView() {
		return view;
	}
	
	public void setView(Room view) {
		this.view = view;
	}
	
	@Override
	public void accept(EventVisitor visitor) {
		visitor.visit(this);
	}
}