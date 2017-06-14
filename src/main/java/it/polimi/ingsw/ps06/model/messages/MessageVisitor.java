package it.polimi.ingsw.ps06.model.messages;

public interface MessageVisitor {
	
	public void visit(EventMessage event);
	
	public void visit(MessagePlayerID messageID);
	public void visit(MessageCurrentPlayer currentPlayer);
	
	public void visit(MessageUser userMessage);
	public void visit(MessageWaitingRoomConnections waitingConnections);
	public void visit(MessagePlayingConnections playingConnections);
	
	public void visit(MessageGameStart gameStart);
	public void visit(MessageGameHasStarted hasStarted);
	public void visit(MessageGameStatus gameStat);
	public void visit(MessageConnectionStart startConnection);
	
	public void visit(MessageVaticanReport vaticanRep);
	
	public void visit(MessageBoardSetupDice boardSetupDice);
	public void visit(MessageBoardAddMember newMember);
	
	public void visit(BoardReady br);
}