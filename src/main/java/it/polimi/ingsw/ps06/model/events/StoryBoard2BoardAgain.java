package it.polimi.ingsw.ps06.model.events;

import it.polimi.ingsw.ps06.controller.BoardController;
import it.polimi.ingsw.ps06.view.Board;

public class StoryBoard2BoardAgain extends StoryBoard {

	private transient Board view;
	
	public StoryBoard2BoardAgain(Board view) {
		this.view = view;
	}
	
	public Board getView() {
		return view;
	}
	
	public void setView(Board view) {
		this.view = view;
	}
	
	@Override
	public void accept(EventVisitor visitor) {
		visitor.visit(this);
	}
}
