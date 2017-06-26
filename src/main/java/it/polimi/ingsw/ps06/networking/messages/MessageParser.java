package it.polimi.ingsw.ps06.networking.messages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import it.polimi.ingsw.ps06.model.Game;
import it.polimi.ingsw.ps06.model.PersonalBoard;
import it.polimi.ingsw.ps06.model.Player;
import it.polimi.ingsw.ps06.model.Types.MaterialsKind;
import it.polimi.ingsw.ps06.model.Types.PointsKind;
import it.polimi.ingsw.ps06.model.events.EventParser;
import it.polimi.ingsw.ps06.networking.Connection;
import it.polimi.ingsw.ps06.networking.MatchSet;
import it.polimi.ingsw.ps06.networking.SocketServer;
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
	
		SocketServer.getInstance().sendWaitingConnectionsStats();
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
		SocketServer.getInstance().startNewGame();
	}

	@Override
	public void visit(MessageBoardSetupDice boardSetupDice) {
		Board b = ((Board) supporter);
		b.setDices(boardSetupDice.getBlackDiceValue(), boardSetupDice.getWhiteDiceValue(), boardSetupDice.getOrangeDiceValue());
	}

	@Override
	public synchronized void visit(BoardReady br) {
		Connection c = ((Connection) supporter);
		MatchSet match = SocketServer.getInstance().retrieveMatch(c);
		
		MessagePlayerID messageID = new MessagePlayerID(c.getPlayer().getID());
		c.asyncSend(messageID);
		
		SocketServer.getInstance().increaseQueue(match);
		if (!( SocketServer.getInstance().isFullQueue(match) ))
			return;

		SocketServer.getInstance().clearQueue(match);

		ArrayList<String> a = new ArrayList<String>();
		match.getAll().forEach(connection -> a.add(connection.getUsername()));		
		MessagePlayingConnections messagePlayingCs = new MessagePlayingConnections( a );		
		SocketServer.getInstance().sendToPlayingConnections(c, messagePlayingCs);
		
		//match.getAll().forEach(connection -> c.getPlayer().getPersonalBoard().addNewObserver(connection));
		
		try {
			match.getGame().start();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void visit(EventMessage event) {
		
		event.getEvent().accept( new EventParser(supporter) );
	}

	@Override
	public void visit(MessageBoardMemberHasBeenPlaced newMember) {
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
		
		Board b = ((Board) supporter);
		b.setPeriodRound(gameStat.getCurrentPeriod(),gameStat.getCurrentRound());
	}

	@Override
	public void visit(MessageVaticanReport vaticanRep) {
		Board b = ((Board) supporter);
		
		for (int excommunicatedPlayer : vaticanRep.getExcommunicatedPlayers())
			b.excommunicate(vaticanRep.getPeriod(), excommunicatedPlayer);
	}

	@Override
	public void visit(MessageModel2ViewNotification notification) {
		Board b = ((Board) supporter);
		b.showErrorLog( notification.getNotification() );
	}

	@Override
	public void visit(MessagePersonalBoardStatus pbStatus) {
		Board b = ((Board) supporter);
		
		
		
		b.setResourcesPersonalView(pbStatus.getWarehouse().getResourceValue(MaterialsKind.COIN), 
										pbStatus.getWarehouse().getResourceValue(MaterialsKind.WOOD), 
										pbStatus.getWarehouse().getResourceValue(MaterialsKind.STONE), 
										pbStatus.getWarehouse().getResourceValue(MaterialsKind.SERVANT), 
										pbStatus.getWarehouse().getResourceValue(PointsKind.VICTORY_POINTS), 
										pbStatus.getWarehouse().getResourceValue(PointsKind.MILITARY_POINTS), 
										pbStatus.getWarehouse().getResourceValue(PointsKind.FAITH_POINTS));
		
		for (int i : pbStatus.getBuilding()) 
			b.setBuildingCardPersonalView(i, pbStatus.getBuilding().indexOf(i));
		
		for (int i : pbStatus.getTerritory()) 
			b.setTerritoryCardPersonalView(i, pbStatus.getTerritory().indexOf(i));
	}

	@Override
	public void visit(MessageObtainPersonalBoardStatus obtainPbStatus) {
		Connection connection = ((Connection) supporter);
		Game game = SocketServer.getInstance().retrieveMatch(connection).getGame();
		
		Player p =  game.getPlayer( obtainPbStatus.getPlayer() );
		
		if ( p != null) {
			PersonalBoard pb = p.getPersonalBoard();
			
			ArrayList<Integer> territoriesCode = new ArrayList<Integer>();
			pb.getTerritories().forEach(t -> territoriesCode.add( t.getCode() ));
			
			ArrayList<Integer> buildingsCode = new ArrayList<Integer>();
			pb.getBuildings().forEach(b -> buildingsCode.add( b.getCode() ));
			
			ArrayList<Integer> charactersCode = new ArrayList<Integer>();
			pb.getCharacters().forEach(c -> charactersCode.add( c.getCode() ));
			
			ArrayList<Integer> venturesCode = new ArrayList<Integer>();
			pb.getVentures().forEach(v -> venturesCode.add( v.getCode() ));
			
			MessagePersonalBoardStatus pbStatus = 
					new MessagePersonalBoardStatus( pb.getInventory(), territoriesCode, buildingsCode, charactersCode, venturesCode);
			connection.asyncSend(pbStatus);
		}
	}

	@Override
	public void visit(MessagePersonalBoardResourcesStatus resStatus) {
		Board b = ((Board) supporter);
		
		int coin = resStatus.getWarehouse().getResourceValue(MaterialsKind.COIN);
		int wood = resStatus.getWarehouse().getResourceValue(MaterialsKind.WOOD);
		int stone = resStatus.getWarehouse().getResourceValue(MaterialsKind.STONE);
		int servant = resStatus.getWarehouse().getResourceValue(MaterialsKind.SERVANT);
		int victory = resStatus.getWarehouse().getResourceValue(PointsKind.VICTORY_POINTS);
		int military = resStatus.getWarehouse().getResourceValue(PointsKind.MILITARY_POINTS);
		int faith = resStatus.getWarehouse().getResourceValue(PointsKind.FAITH_POINTS);
		
		b.setPersonalResources(coin, wood, stone, servant, victory, military, faith);
	}

	@Override
	public void visit(MessageBoardSetupDevCards boardSetupDevCards) {
		Board b = ((Board) supporter);
		
		b.setCards( boardSetupDevCards.getRoundCards() );
	}

	@Override
	public void visit(MessageCurrentPlayerOrder currentPlayerOrder) {
		Board b = ((Board) supporter);
		
		int[] playerOrder = new int[ currentPlayerOrder.getPlayerOrder().size() ];
		for(int i=0; i < currentPlayerOrder.getPlayerOrder().size(); i++) playerOrder[i] = currentPlayerOrder.getPlayerOrder().get(i);
		b.setOrder( playerOrder );
	}

	@Override
	public void visit(MessageLeaderCards leaderCards) {
		Board b = ((Board) supporter);
		
		ArrayList<Integer> leadersCode = new ArrayList<Integer>();
		leaderCards.getLeaderCards().keySet().forEach( l -> leadersCode.add(l) );
		
		b.setLeaders(leadersCode.get(0), leadersCode.get(1), leadersCode.get(2), leadersCode.get(3));
	}

	@Override
	public void visit(MessageDisconnect disconnect) {
		Connection connection = ((Connection) supporter);
		
		connection.closeConnection();
	}
}
