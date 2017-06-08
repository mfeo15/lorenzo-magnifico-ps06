package it.polimi.ingsw.ps06.model.events;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps06.model.Types.ColorPalette;
import it.polimi.ingsw.ps06.model.events.EventClose;
import it.polimi.ingsw.ps06.model.events.EventLeaderActivated;
import it.polimi.ingsw.ps06.model.events.EventLeaderDiscarded;
import it.polimi.ingsw.ps06.model.events.EventLeaderPlayed;
import it.polimi.ingsw.ps06.model.events.EventMemberPlaced;
import it.polimi.ingsw.ps06.model.events.StoryBoard2Board;
import it.polimi.ingsw.ps06.model.events.StoryBoard2PersonalView;
import it.polimi.ingsw.ps06.model.events.StoryBoard2Room;

public interface EventVisitor {

	public void visit(EventClose eventClose);
	
	public void visit(StoryBoard2Room storyboard);
	public void visit(StoryBoard2Board storyboard);
	public void visit(StoryBoard2PersonalView storyboard);
	
	public void visit(EventMemberPlaced memberPlaced);
	public void visit(EventLeaderDiscarded leaderDiscarded);
	public void visit(EventLeaderActivated leaderActivated);
	public void visit(EventLeaderPlayed leaderPlayed);
	
	/*
	public String visit(MessageUser userMessage);
	public ArrayList<String> visit(MessageWaitingRoomConnections waitingConnections);
	
	public int visit(MessageGameStart gameStart);
	public int visit(MessageGameHasStarted hasStarted);
	public void visit(MessageConnectionStart startConnection);
	
	public HashMap<ColorPalette, Integer> visit(MessageBoardSetupDice boardSetupDice);
	*/
}