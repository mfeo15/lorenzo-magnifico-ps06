package it.polimi.ingsw.ps06.model.messages;

public interface MessageVisitor {
	
	void visit(EventMessage event);
	
	void visit(MessagePlayerID messageID);
	void visit(MessageCurrentPlayer currentPlayer);
	void visit(MessageCurrentPlayerOrder currentPlayerOrder);
	
	void visit(MessageUser userMessage);
	void visit(MessageWaitingRoomConnections waitingConnections);
	void visit(MessagePlayingConnections playingConnections);
	
	void visit(MessageGameStart gameStart);
	void visit(MessageGameHasStarted hasStarted);
	void visit(MessageGameStatus gameStat);
	void visit(MessageConnectionStart startConnection);
	
	void visit(MessageVaticanReport vaticanRep);
	
	void visit(BoardReady br);
	void visit(MessageBoardSetupDice boardSetupDice);
	void visit(MessageBoardSetupDevCards boardSetupDevCards);
	void visit(MessageBoardMemberHasBeenPlaced newMember);
	
	void visit(MessageLeaderCards leaderCards);
	
	void visit(MessagePersonalBoardStatus pbStatus);
	void visit(MessageObtainPersonalBoardStatus obtainPbStatus);
	void visit(MessagePersonalBoardResourcesStatus resStatus);
	
	void visit(MessageModel2ViewNotification notification);
}