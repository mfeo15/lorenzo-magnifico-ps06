package it.polimi.ingsw.ps06.networking.messages;

import java.io.IOException;
import java.util.ArrayList;

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

/**
 * Classe implementativa di MessageVisitor, parsa il messaggio da visitare seguendo
 * le direttive del Visitor Pattern Design
 * 
 * @author ps06
 * @since	2017-06-10
 */
public class MessageParser implements MessageVisitor {

	private Object theSupporter;

	/**
	 * Costruttore di default della classe
	 */
	public MessageParser() 
	{

	}

	/**
	 * Costruttore della classe con parametro di supporto alle gestione delle attività
	 * 
	 * @param	theSupporter	oggetto di supporto alla visita
	 */
	public MessageParser(Object theSupporter) 
	{
		this.theSupporter = theSupporter;
	}

	@Override
	public void visit(MessageUser message) 
	{
		Connection c = ((Connection) theSupporter);
		ParserUsers users = new ParserUsers("resources/XML/Users.xml");

		if (users.contains( message.getUsername() )) 
		{
			User u = users.getUser( message.getUsername() );

			if (u.autenticate(message.getUsername(), message.getPassword())) 
			{	
				MessageUserHasLogged usrLogged = new MessageUserHasLogged(u.getUsername(), 
						u.getGameCounter(), 
						u.getWinCounder(), 
						u.getSecondPlaceCounter(),
						u.getMaxScore());

				c.asyncSend(usrLogged);

				c.setAssociatedUser(u);
			}
		}

		SocketServer.getInstance().sendWaitingConnectionsStats();
	}

	@Override
	public void visit(MessageWaitingRoomConnections message) 
	{
		Room r = ((Room) theSupporter);

		for (String s : message.getWaitingConnections())
			r.setPlayer(s, message.getWaitingConnections().indexOf(s));
	}

	@Override
	public void visit(MessagePlayingConnections message) 
	{
		Board b = ((Board) theSupporter);

		b.setPlayerNumber( message.getWaitingConnections().size() );

		for (String s : message.getWaitingConnections())
			b.setPlayersNames(s, message.getWaitingConnections().indexOf(s));
	}

	@Override
	public void visit(MessageGameHasStarted message) 
	{
		Room r = ((Room) theSupporter);
		r.hasStarted();
	}

	@Override
	public void visit(MessageGameStart message) 
	{
		SocketServer.getInstance().startNewGame();
	}

	@Override
	public void visit(MessageBoardSetupDice message) 
	{
		Board b = ((Board) theSupporter);
		b.setDices(message.getBlackDiceValue(), message.getWhiteDiceValue(), message.getOrangeDiceValue());
	}

	@Override
	public synchronized void visit(MessageBoardReady message) 
	{
		Connection c = ((Connection) theSupporter);
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
	public void visit(MessageEvent message) 
	{
		message.getEvent().accept( new EventParser(theSupporter) );
	}

	@Override
	public void visit(MessageBoardMemberHasBeenPlaced message) 
	{
		Board b = ((Board) theSupporter);

		try {
			b.addMember(message.getActionExecuted(), message.getColor(), message.getPlayerIndex());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void visit(MessagePlayerID message) 
	{
		Board b = ((Board) theSupporter);
		b.setOwnerPlayerIndex( message.getID() );
	}

	@Override
	public void visit(MessageCurrentPlayer message) 
	{
		Board b = ((Board) theSupporter);
		b.setCurrentPlayerID( message.getID() );
	}

	@Override
	public void visit(MessageGameStatus message) 
	{	
		Board b = ((Board) theSupporter);
		b.setPeriodRound(message.getCurrentPeriod(),message.getCurrentRound());
	}

	@Override
	public void visit(MessageVaticanReport message) 
	{
		Board b = ((Board) theSupporter);

		for (int excommunicatedPlayer : message.getExcommunicatedPlayers())
			b.excommunicate(message.getPeriod(), excommunicatedPlayer);
	}

	@Override
	public void visit(MessageModel2ViewNotification message) 
	{
		Board b = ((Board) theSupporter);
		b.showErrorLog( message.getNotification() );
	}

	@Override
	public void visit(MessagePersonalBoardStatus message) 
	{
		Board b = ((Board) theSupporter);

		try {
			b.setBonusTilePersonalView( message.getBonusTileCode() );
		} catch (IOException e) {
			e.printStackTrace();
		}

		b.setResourcesPersonalView(message.getResourceValue(MaterialsKind.COIN), 
				message.getResourceValue(MaterialsKind.WOOD), 
				message.getResourceValue(MaterialsKind.STONE), 
				message.getResourceValue(MaterialsKind.SERVANT), 
				message.getResourceValue(PointsKind.VICTORY_POINTS), 
				message.getResourceValue(PointsKind.MILITARY_POINTS), 
				message.getResourceValue(PointsKind.FAITH_POINTS));

		for (int i : message.getBuilding()) 
			b.setBuildingCardPersonalView(i, message.getBuilding().indexOf(i));

		for (int i : message.getTerritory()) 
			b.setTerritoryCardPersonalView(i, message.getTerritory().indexOf(i));
	}

	@Override
	public void visit(MessageObtainPersonalBoardStatus message) 
	{
		Connection connection = ((Connection) theSupporter);
		Game game = SocketServer.getInstance().retrieveMatch(connection).getGame();

		Player player =  game.getPlayer( message.getPlayer() );

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
	public void visit(MessagePersonalBoardResourcesStatus message) 
	{
		Board b = ((Board) theSupporter);

		int coin = message.getResourceValue(MaterialsKind.COIN);
		int wood = message.getResourceValue(MaterialsKind.WOOD);
		int stone = message.getResourceValue(MaterialsKind.STONE);
		int servant = message.getResourceValue(MaterialsKind.SERVANT);
		int faith = message.getResourceValue(PointsKind.FAITH_POINTS);
		int victory = message.getResourceValue(PointsKind.VICTORY_POINTS);
		int military = message.getResourceValue(PointsKind.MILITARY_POINTS);

		b.setPersonalResources(coin, wood, stone, servant, victory, military, faith);
	}

	@Override
	public void visit(MessageBoardSetupDevCards message) 
	{
		Board b = ((Board) theSupporter);
		b.setCards( message.getRoundCards() );
	}

	@Override
	public void visit(MessageBoardSetupExcomCards message) 
	{
		Board b = ((Board) theSupporter);
		b.setExcommunicationTiles( message.getPeriodOne(), message.getPeriodTwo(), message.getPeriodThree() );
	}

	@Override
	public void visit(MessageCurrentPlayerOrder message) 
	{
		Board b = ((Board) theSupporter);
		
		int[] playerOrder = new int[ message.getPlayerOrder().size() ];
		for(int i=0; i < message.getPlayerOrder().size(); i++) playerOrder[i] = message.getPlayerOrder().get(i);
		b.setOrder( playerOrder );
	}

	@Override
	public void visit(MessageLeaderCards message) 
	{
		Board b = ((Board) theSupporter);

		b.setLeaders(message.getLeaderCards().get(0), 
				message.getLeaderCards().get(1), 
				message.getLeaderCards().get(2), 
				message.getLeaderCards().get(3)
				);
	}

	@Override
	public void visit(MessageDisconnect message) 
	{
		Connection connection = ((Connection) theSupporter);
		Game game = SocketServer.getInstance().retrieveMatch(connection).getGame();
		Player disconnecting_player = connection.getPlayer();
		
		connection.closeConnection();
		
		if (game == null || disconnecting_player == null)
			return;
		
		disconnecting_player.deactivate();
		
		if (game.getPlayerList().size() == 2)
			game.end(disconnecting_player.getID());
		else 
		{
			if ( game.getCurrentPlayer().equals(disconnecting_player) ) {
				game.advanceCurrentPlayer();
			}
		}
	}

	@Override
	public void visit(MessagePlayerPassed message) 
	{
		Connection connection = ((Connection) theSupporter);	
		SocketServer.getInstance().retrieveMatch(connection).getGame().advanceCurrentPlayer();
	}

	@Override
	public void visit(MessageBoardSetupTimeoutAction message) 
	{
		Board b = ((Board) theSupporter);	
		b.setTimer( message.getTimeout() );
	}

	@Override
	public void visit(MessageLeaderHasBeenPlayed message) 
	{
		Board b = ((Board) theSupporter);
		b.playLeader( message.getCode() );
	}

	@Override
	public void visit(MessageLeaderHasBeenActivated message) 
	{
		Board b = ((Board) theSupporter);
		b.activateLeader( message.getCode() );
	}

	@Override
	public void visit(MessageLeaderHasBeenDiscarded message) 
	{
		Board b = ((Board) theSupporter);
		b.discardLeader( message.getCode() );
	}

	@Override
	public void visit(MessageGameHasEnded message) 
	{
		Board b = ((Board) theSupporter);
		b.gameHasEnded( message.getWinnerPlayerCode() );
	}

	@Override
	public void visit(MessageUserHasLogged message) 
	{
		Room r = ((Room) theSupporter);
		r.userHasLoggedIn( message.getUsername(), 
				message.getGameCounter(), 
				message.getWinCounder(), 
				message.getSecondPlaceCounter(), 
				message.getMaxScore() 
				);
	}

	@Override
	public void visit(MessageTelegram message) 
	{
		Connection connection = ((Connection) theSupporter);

		if ( message.getTelegram() != null ) {

			MessageTelegramHasBeenSent telegram = new MessageTelegramHasBeenSent( connection.getPlayer().getID(), message.getTelegram() );
			SocketServer.getInstance().sendToPlayingConnections(connection, telegram);
		}
	}

	@Override
	public void visit( MessageTelegramHasBeenSent message) {
		Board b = ((Board) theSupporter);
		b.addChatText( message.getPlayer() , message.getTelegram());
	}
}
