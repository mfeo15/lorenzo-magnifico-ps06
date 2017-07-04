package it.polimi.ingsw.ps06.networking.messages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import it.polimi.ingsw.ps06.model.Game;
import it.polimi.ingsw.ps06.model.PersonalBoard;
import it.polimi.ingsw.ps06.model.Player;
import it.polimi.ingsw.ps06.model.Types.MaterialsKind;
import it.polimi.ingsw.ps06.model.Types.PointsKind;
import it.polimi.ingsw.ps06.model.XMLparser.ParserUsers;
import it.polimi.ingsw.ps06.model.events.EventParser;
import it.polimi.ingsw.ps06.networking.Connection;
import it.polimi.ingsw.ps06.networking.MatchSet;
import it.polimi.ingsw.ps06.networking.SocketServer;
import it.polimi.ingsw.ps06.networking.User;
import it.polimi.ingsw.ps06.view.Board;
import it.polimi.ingsw.ps06.view.Room;

public class MessageParser implements MessageVisitor {
	
	private Object supporter;
	
	public MessageParser() 
	{
		
	}
	
	public MessageParser(Object supporter) 
	{
		this.supporter = supporter;
	}
	
	@Override
	public void visit(MessageConnectionStart startConnection) {
		
	}

	@Override
	public void visit(MessageUser userMessage) 
	{
		Connection c = ((Connection) supporter);
		ParserUsers users = new ParserUsers("resources/XML/users.xml");
		
		if (users.contains( userMessage.getUsername() )) {
			User u = users.getUser( userMessage.getUsername() );
			if (u.autenticate(userMessage.getUsername(), userMessage.getPassword()))
				c.setAssociatedUser(u);
		}
	
		SocketServer.getInstance().sendWaitingConnectionsStats();
	}

	@Override
	public void visit(MessageWaitingRoomConnections waitingConnections) 
	{
		Room r = ((Room) supporter);
		
		for (String s : waitingConnections.getWaitingConnections())
			r.setPlayer(s, waitingConnections.getWaitingConnections().indexOf(s));
	}
	
	@Override
	public void visit(MessagePlayingConnections playingConnections) 
	{
		Board b = ((Board) supporter);
		
		b.setPlayerNumber( playingConnections.getWaitingConnections().size() );
		
		for (String s : playingConnections.getWaitingConnections())
			b.setPlayersNames(s, playingConnections.getWaitingConnections().indexOf(s));
	}
	
	@Override
	public void visit(MessageGameHasStarted hasStarted) 
	{
		Room r = ((Room) supporter);
		r.hasStarted();
	}
	
	@Override
	public void visit(MessageGameStart gameStart) 
	{
		SocketServer.getInstance().startNewGame();
	}

	@Override
	public void visit(MessageBoardSetupDice boardSetupDice) 
	{
		Board b = ((Board) supporter);
		b.setDices(boardSetupDice.getBlackDiceValue(), boardSetupDice.getWhiteDiceValue(), boardSetupDice.getOrangeDiceValue());
	}

	@Override
	public synchronized void visit(BoardReady br) 
	{
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
		
		MessageBoardSetupTimeoutAction timeoutAction = new MessageBoardSetupTimeoutAction( SocketServer.getInstance().getTimeoutAction() );
		SocketServer.getInstance().sendToPlayingConnections(c, timeoutAction);
		
		match.getGame().start();

	}

	@Override
	public void visit(EventMessage event) 
	{
		event.getEvent().accept( new EventParser(supporter) );
	}

	@Override
	public void visit(MessageBoardMemberHasBeenPlaced newMember) 
	{
		Board b = ((Board) supporter);
		
		try {
			b.addMember(newMember.getActionExecuted(), newMember.getColor(), newMember.getPlayerIndex());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void visit(MessagePlayerID messageID) 
	{
		Board b = ((Board) supporter);
		b.setOwnerPlayerIndex( messageID.getID() );
	}

	@Override
	public void visit(MessageCurrentPlayer currentPlayer) 
	{
		Board b = ((Board) supporter);
		b.setCurrentPlayerID( currentPlayer.getID() );
	}

	@Override
	public void visit(MessageGameStatus gameStat) 
	{	
		Board b = ((Board) supporter);
		b.setPeriodRound(gameStat.getCurrentPeriod(),gameStat.getCurrentRound());
	}

	@Override
	public void visit(MessageVaticanReport vaticanRep) 
	{
		Board b = ((Board) supporter);
		
		for (int excommunicatedPlayer : vaticanRep.getExcommunicatedPlayers())
			b.excommunicate(vaticanRep.getPeriod(), excommunicatedPlayer);
	}

	@Override
	public void visit(MessageModel2ViewNotification notification) 
	{
		Board b = ((Board) supporter);
		b.showErrorLog( notification.getNotification() );
	}

	@Override
	public void visit(MessagePersonalBoardStatus pbStatus) 
	{
		Board b = ((Board) supporter);
		
		try {
			b.setBonusTilePersonalView( pbStatus.getBonusTileCode() );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		b.setResourcesPersonalView(pbStatus.getResourceValue(MaterialsKind.COIN), 
										pbStatus.getResourceValue(MaterialsKind.WOOD), 
										pbStatus.getResourceValue(MaterialsKind.STONE), 
										pbStatus.getResourceValue(MaterialsKind.SERVANT), 
										pbStatus.getResourceValue(PointsKind.VICTORY_POINTS), 
										pbStatus.getResourceValue(PointsKind.MILITARY_POINTS), 
										pbStatus.getResourceValue(PointsKind.FAITH_POINTS));
		
		for (int i : pbStatus.getBuilding()) 
			b.setBuildingCardPersonalView(i, pbStatus.getBuilding().indexOf(i));
		
		for (int i : pbStatus.getTerritory()) 
			b.setTerritoryCardPersonalView(i, pbStatus.getTerritory().indexOf(i));
	}

	@Override
	public void visit(MessageObtainPersonalBoardStatus obtainPbStatus) 
	{
		Connection connection = ((Connection) supporter);
		Game game = SocketServer.getInstance().retrieveMatch(connection).getGame();
		
		Player player =  game.getPlayer( obtainPbStatus.getPlayer() );
		
		if ( player != null) {
			PersonalBoard pb = player.getPersonalBoard();
			
			ArrayList<Integer> territoriesCode = new ArrayList<Integer>();
			pb.getTerritories().forEach(t -> territoriesCode.add( t.getCode() ));
			
			ArrayList<Integer> buildingsCode = new ArrayList<Integer>();
			pb.getBuildings().forEach(b -> buildingsCode.add( b.getCode() ));
			
			ArrayList<Integer> charactersCode = new ArrayList<Integer>();
			pb.getCharacters().forEach(c -> charactersCode.add( c.getCode() ));
			
			ArrayList<Integer> venturesCode = new ArrayList<Integer>();
			pb.getVentures().forEach(v -> venturesCode.add( v.getCode() ));
			
			MessagePersonalBoardStatus pbStatus = new MessagePersonalBoardStatus ( 
														pb.getBonusTile().getCode(), 
														territoriesCode, 
														buildingsCode, 
														charactersCode, 
														venturesCode
													);
			for (MaterialsKind m : MaterialsKind.values()) pbStatus.setResourceValue(m, pb.getInventory().getResourceValue(m));
			for (PointsKind p : PointsKind.values()) pbStatus.setResourceValue(p, pb.getInventory().getResourceValue(p));
			connection.asyncSend(pbStatus);
		}
	}

	@Override
	public void visit(MessagePersonalBoardResourcesStatus resStatus) 
	{
		Board b = ((Board) supporter);

		int coin = resStatus.getResourceValue(MaterialsKind.COIN);
		int wood = resStatus.getResourceValue(MaterialsKind.WOOD);
		int stone = resStatus.getResourceValue(MaterialsKind.STONE);
		int servant = resStatus.getResourceValue(MaterialsKind.SERVANT);
		int faith = resStatus.getResourceValue(PointsKind.FAITH_POINTS);
		int victory = resStatus.getResourceValue(PointsKind.VICTORY_POINTS);
		int military = resStatus.getResourceValue(PointsKind.MILITARY_POINTS);
		
		b.setPersonalResources(coin, wood, stone, servant, victory, military, faith);
	}

	@Override
	public void visit(MessageBoardSetupDevCards boardSetupDevCards) 
	{
		Board b = ((Board) supporter);
		b.setCards( boardSetupDevCards.getRoundCards() );
	}
	
	@Override
	public void visit(MessageBoardSetupExcomCards boardSetupExcomCards) {
		Board b = ((Board) supporter);
		b.setExcommunicationTiles(boardSetupExcomCards.getPeriodOne(), 
								  boardSetupExcomCards.getPeriodTwo(), 
								  boardSetupExcomCards.getPeriodThree()
								 );
	}

	@Override
	public void visit(MessageCurrentPlayerOrder currentPlayerOrder) 
	{
		Board b = ((Board) supporter);
		
		int[] playerOrder = new int[ currentPlayerOrder.getPlayerOrder().size() ];
		for(int i=0; i < currentPlayerOrder.getPlayerOrder().size(); i++) playerOrder[i] = currentPlayerOrder.getPlayerOrder().get(i);
		b.setOrder( playerOrder );
	}

	@Override
	public void visit(MessageLeaderCards leaderCards) 
	{
		Board b = ((Board) supporter);
		
		b.setLeaders(leaderCards.getLeaderCards().get(0), 
						leaderCards.getLeaderCards().get(1), 
						leaderCards.getLeaderCards().get(2), 
						leaderCards.getLeaderCards().get(3)
					);
	}

	@Override
	public void visit(MessageDisconnect disconnect) 
	{
		Connection connection = ((Connection) supporter);	
		connection.closeConnection();
	}

	@Override
	public void visit(MessagePlayerPassed playerPassed) 
	{
		Connection connection = ((Connection) supporter);	
		SocketServer.getInstance().retrieveMatch(connection).getGame().advanceCurrentPlayer();
	}

	@Override
	public void visit(MessageBoardSetupTimeoutAction timeoutAction) 
	{
		Board b = ((Board) supporter);	
		b.setTimer( timeoutAction.getTimeout() );
	}

	@Override
	public void visit(MessageLeaderHasBeenPlayed leaderPlayed) 
	{
		Board b = ((Board) supporter);
		b.playLeader( leaderPlayed.getCode() );
	}

	@Override
	public void visit(MessageLeaderHasBeenActivated leaderActivated) 
	{
		Board b = ((Board) supporter);
		b.activateLeader( leaderActivated.getCode() );
	}

	@Override
	public void visit(MessageLeaderHasBeenDiscarded leaderDiscarded) 
	{
		Board b = ((Board) supporter);
		b.discardLeader( leaderDiscarded.getCode() );
	}

	@Override
	public void visit(MessageGameHasEnded hasEnded) {
		// TODO Auto-generated method stub
		System.out.println("THE GAME IS NOW OVER");
		System.out.println("The player_" + hasEnded.getWinnerPlayerCode() + " just won!");
		
		Board b = ((Board) supporter);
		b.gameHasEnded( hasEnded.getWinnerPlayerCode() );
	}
}
