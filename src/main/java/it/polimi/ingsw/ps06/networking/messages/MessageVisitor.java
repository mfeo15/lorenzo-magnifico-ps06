package it.polimi.ingsw.ps06.networking.messages;

public interface MessageVisitor {
	
	/**
	 * 
	 * 
	 * @param message
	 */
	void visit(EventMessage message);
	
	/**
	 * @param message
	 */
	void visit(MessageConnectionStart message);
	
	/**
	 * @param message
	 */
	void visit(MessageDisconnect message);
	
	/**
	 * @param message
	 */
	void visit(MessageTelegram message);
	
	/**
	 * @param telegramSent
	 */
	void visit(MessageTelegramHasBeenSent telegramSent);
	
	/**
	 * @param messageID
	 */
	void visit(MessagePlayerID messageID);
	
	/**
	 * @param currentPlayer
	 */
	void visit(MessageCurrentPlayer currentPlayer);
	
	/**
	 * @param currentPlayerOrder
	 */
	void visit(MessageCurrentPlayerOrder currentPlayerOrder);
	
	void visit(MessageUser userMessage);
	void visit(MessageUserHasLogged hasLogged);
	void visit(MessageWaitingRoomConnections waitingConnections);
	void visit(MessagePlayingConnections playingConnections);
	
	void visit(MessagePlayerPassed playerPassed);
	
	void visit(MessageGameStart gameStart);
	void visit(MessageGameHasStarted hasStarted);
	void visit(MessageGameStatus gameStat);
	void visit(MessageGameHasEnded hasEnded);
	
	void visit(MessageVaticanReport vaticanRep);
	
	void visit(BoardReady br);
	void visit(MessageBoardSetupTimeoutAction timeoutAction);
	void visit(MessageBoardSetupDice boardSetupDice);
	void visit(MessageBoardSetupDevCards boardSetupDevCards);
	void visit(MessageBoardSetupExcomCards boardSetupExcomCards);
	void visit(MessageBoardMemberHasBeenPlaced newMember);
	
	void visit(MessageLeaderCards leaderCards);
	void visit(MessageLeaderHasBeenPlayed leaderPlayed);
	void visit(MessageLeaderHasBeenActivated leaderActivated);
	void visit(MessageLeaderHasBeenDiscarded leaderDiscarded);
	
	void visit(MessagePersonalBoardStatus pbStatus);
	void visit(MessageObtainPersonalBoardStatus obtainPbStatus);
	void visit(MessagePersonalBoardResourcesStatus resStatus);
	
	void visit(MessageModel2ViewNotification notification);
}