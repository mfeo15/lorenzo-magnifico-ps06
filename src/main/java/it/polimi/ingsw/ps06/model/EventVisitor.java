package it.polimi.ingsw.ps06.model;

public interface EventVisitor {

	public void visit(EventClose eventClose);
	
	public void visit(StoryBoard2Room storyboard);
	public void visit(StoryBoard2Board storyboard);
	
	//public void visit(Trouble v);
	public void visit(MemberPlaced memberPlaced);
	public void visit(LeaderDiscarded leaderDiscarded);
}