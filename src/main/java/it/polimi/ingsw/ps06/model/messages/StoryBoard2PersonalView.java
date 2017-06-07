package it.polimi.ingsw.ps06.model.messages;

import it.polimi.ingsw.ps06.view.PersonalView;

public class StoryBoard2PersonalView extends StoryBoard{
	private PersonalView view;
	
	public StoryBoard2PersonalView(PersonalView view) {
		this.view = view;
	}
	
	public PersonalView getView() {
		return view;
	}
	
	public void setView(PersonalView view) {
		this.view = view;
	}
	
	@Override
	public void accept(MessageVisitor visitor) {
		visitor.visit(this);
	}
}
