package it.polimi.ingsw.ps06.model.messages;

import java.io.IOException;

import it.polimi.ingsw.ps06.Client;
import it.polimi.ingsw.ps06.Connection;
import it.polimi.ingsw.ps06.SocketServer;
import it.polimi.ingsw.ps06.view.Board;
import it.polimi.ingsw.ps06.view.Room;

public class MessageParser implements MessageVisitor {
	
	private Object supporter;
	
	public MessageParser() {
		
	}
	
	public MessageParser(Object supporter/*, Class<T> supporterClass*/) {
		this.supporter = supporter;
	}
	
	@Override
	public void visit(MessageConnectionStart startConnection) {
		
	}

	@Override
	public void visit(MessageUser userMessage) {
		Connection c = ((Connection) supporter);
		c.setUsername(userMessage.getUsername());
		
		try {
			SocketServer.getInstance().sendWaitingConnectionsStats();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void visit(MessageWaitingRoomConnections waitingConnections) {
		Room r = ((Room) supporter);
		
		for (String s : waitingConnections.getWaitingConnections())
			r.setPlayer(s, waitingConnections.getWaitingConnections().indexOf(s));
	}
	
	@Override
	public void visit(MessageGameHasStarted hasStarted) {
		Room r = ((Room) supporter);
		r.hasStarted();
	}
	
	@Override
	public void visit(MessageGameStart gameStart) {
		try {
			SocketServer.getInstance().startNewGame();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void visit(MessageBoardSetupDice boardSetupDice) {
		Board b = ((Board) supporter);
		b.setDices(boardSetupDice.getBlackDiceValue(), boardSetupDice.getWhiteDiceValue(), boardSetupDice.getOrangeDiceValue());
	}

	@Override
	public void visit(BoardReady br) {
		Connection c = ((Connection) supporter);
		
		try {
			SocketServer.getInstance().retrieveGame(c).setupRound();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
