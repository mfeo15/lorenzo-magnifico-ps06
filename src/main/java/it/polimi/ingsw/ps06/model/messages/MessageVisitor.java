package it.polimi.ingsw.ps06.model.messages;

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

public interface MessageVisitor {
	
	public void visit(MessageUser userMessage);
	public void visit(MessageWaitingRoomConnections waitingConnections);
	
	public void visit(MessageGameStart gameStart);
	public void visit(MessageGameHasStarted hasStarted);
	public void visit(MessageConnectionStart startConnection);
	
	public void visit(MessageBoardSetupDice boardSetupDice);
	
	public void visit(BoardReady br);
}