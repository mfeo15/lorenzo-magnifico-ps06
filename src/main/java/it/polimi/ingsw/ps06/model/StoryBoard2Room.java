package it.polimi.ingsw.ps06.model;

import it.polimi.ingsw.ps06.view.GUI.Room;

public class StoryBoard2Room extends Event {

	private Room view;
	
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