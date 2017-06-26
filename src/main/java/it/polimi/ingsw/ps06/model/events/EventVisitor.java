package it.polimi.ingsw.ps06.model.events;

import it.polimi.ingsw.ps06.model.events.EventClose;
import it.polimi.ingsw.ps06.model.events.EventLeaderActivated;
import it.polimi.ingsw.ps06.model.events.EventLeaderDiscarded;
import it.polimi.ingsw.ps06.model.events.EventLeaderPlayed;
import it.polimi.ingsw.ps06.model.events.EventMemberPlaced;
import it.polimi.ingsw.ps06.model.events.StoryBoard2Board;
import it.polimi.ingsw.ps06.model.events.StoryBoard2Room;

public interface EventVisitor {

	public void visit(EventClose eventClose);
	
	public void visit(StoryBoard2Room storyboard);
	public void visit(StoryBoard2Board storyboard);
	
	public void visit(RoomHasLoaded roomHasLoaded);
	public void visit(BoardHasLoaded boardHasLoaded);
	
	public void visit(EventMemberPlaced memberPlaced);
	public void visit(EventLeaderDiscarded leaderDiscarded);
	public void visit(EventLeaderActivated leaderActivated);
	public void visit(EventLeaderPlayed leaderPlayed);
}