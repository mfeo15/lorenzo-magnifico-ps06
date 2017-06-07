package it.polimi.ingsw.ps06.model.messages;

import java.util.ArrayList;

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
	
	public String visit(MessageUser userMessage);
	public ArrayList<String> visit(MessageWaitingRoomConnections waitingConnections);
	
	public void visit(MessageConnectionStart startConnection);
}