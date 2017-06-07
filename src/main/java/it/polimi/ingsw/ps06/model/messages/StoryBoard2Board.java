package it.polimi.ingsw.ps06.model.messages;

import it.polimi.ingsw.ps06.view.Board;

public class StoryBoard2Board extends StoryBoard {

	private Board view;
	
	public StoryBoard2Board(Board view) {
		this.view = view;
		//this.model = new Game(4);
	}
	
	public Board getView() {
		return view;
	}
	
	public void setView(Board view) {
		this.view = view;
	}
	
	@Override
	public void accept(MessageVisitor visitor) {
		visitor.visit(this);
	}
}
