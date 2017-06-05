package it.polimi.ingsw.ps06.model;

import it.polimi.ingsw.ps06.view.GUI.Board;

public class StoryBoard2Board extends Event {

	public Board view;
	public Game model;
	
	public StoryBoard2Board(Board view) {
		this.view = view;
		this.model = new Game(4);
	}
	
	@Override
	public void accept(EventVisitor visitor) {
		visitor.visit(this);
	}
}
