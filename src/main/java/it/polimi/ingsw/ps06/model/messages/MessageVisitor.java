package it.polimi.ingsw.ps06.model.messages;

import it.polimi.ingsw.ps06.model.LeaderDiscarded;
import it.polimi.ingsw.ps06.model.MemberPlaced;

public interface MessageVisitor {

	public void visit(EventClose eventClose);
	
	public void visit(StoryBoard2Room storyboard);
	public void visit(StoryBoard2Board storyboard);
	public void visit(StoryBoard2PersonalView storyboard);
	
	//public void visit(Trouble v);
	public void visit(EventMemberPlaced memberPlaced);
	public void visit(EventLeaderDiscarded leaderDiscarded);
	public void visit(EventLeaderActivated leaderActivated);
	public void visit(EventLeaderPlayed leaderPlayed);
	
	public void visit(MessageUser userMessage);
	
	public void visit(MessageConnectionStart startConnection);
}