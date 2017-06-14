package it.polimi.ingsw.ps06.model.messages;

import java.io.IOException;
import java.util.ArrayList;

import it.polimi.ingsw.ps06.Connection;
import it.polimi.ingsw.ps06.MatchSet;
import it.polimi.ingsw.ps06.SocketServer;
import it.polimi.ingsw.ps06.model.events.EventParser;
import it.polimi.ingsw.ps06.view.Board;
import it.polimi.ingsw.ps06.view.Room;

public class MessageParser implements MessageVisitor {
	
	private Object supporter;
	
	public MessageParser() {
		
	}
	
	public MessageParser(Object supporter) {
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
	public void visit(MessagePlayingConnections playingConnections) {
		Board b = ((Board) supporter);
		
		b.setPlayerNumber( playingConnections.getWaitingConnections().size() );
		
		for (String s : playingConnections.getWaitingConnections())
			b.setPlayersNames(s, playingConnections.getWaitingConnections().indexOf(s));
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
		
		MessagePlayerID messageID = new MessagePlayerID(c.getPlayer().getID());
		c.asyncSend(messageID);
		
		try { Thread.sleep(1000); } catch (InterruptedException e1) { e1.printStackTrace(); } 
		
		try {
			
			MatchSet match = SocketServer.getInstance().retrieveMatch(c);
			
			ArrayList<String> a = new ArrayList<String>();
			match.getAll().forEach(connection -> a.add(connection.getUsername()));		
			MessagePlayingConnections messagePlayingCs = new MessagePlayingConnections( a );
			c.asyncSend(messagePlayingCs);
			
			try { Thread.sleep(1000); } catch (InterruptedException e1) { e1.printStackTrace(); } 
			
			int diceB = match.getGame().getDiceBlack().getValue();
			int diceW = match.getGame().getDiceWhite().getValue();
			int diceO = match.getGame().getDiceOrange().getValue();
			MessageBoardSetupDice messageDice = new MessageBoardSetupDice(diceB, diceW, diceO );
			c.asyncSend(messageDice);
			
			try { Thread.sleep(1000); } catch (InterruptedException e1) { e1.printStackTrace(); } 
			
			MessageCurrentPlayer messageCurrentP = new MessageCurrentPlayer( match.getGame().getCurrentPlayer().getID() );
			c.asyncSend(messageCurrentP);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void visit(EventMessage event) {
		
		event.getEvent().accept( new EventParser(supporter) );
	}

	@Override
	public void visit(MessageBoardAddMember newMember) {
		Board b = ((Board) supporter);
		
		try {
			b.addMember(newMember.getActionExecuted(), newMember.getColor(), newMember.getPlayerIndex());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void visit(MessagePlayerID messageID) {
		Board b = ((Board) supporter);
		b.setOwnerPlayerIndex( messageID.getID() );
	}

	@Override
	public void visit(MessageCurrentPlayer currentPlayer) {
		Board b = ((Board) supporter);
		b.setCurrentPlayerID( currentPlayer.getID() );
	}

	@Override
	public void visit(MessageGameStatus gameStat) {

		System.out.println("PERIOD: " + gameStat.getCurrentPeriod() + " ROUND: " + gameStat.getCurrentRound());
		
		Board b = ((Board) supporter);
		b.setPeriodRound(gameStat.getCurrentPeriod(),gameStat.getCurrentRound());
	}

	@Override
	public void visit(MessageVaticanReport vaticanRep) {
		// TODO Auto-generated method stub
	}
}
