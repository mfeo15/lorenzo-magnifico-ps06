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
	public void visit(MessageUser userMessage) 
	{
		Connection c = ((Connection) theSupporter);
		ParserUsers users = new ParserUsers("resources/XML/Users.xml");

		if (users.contains( userMessage.getUsername() )) 
		{
			User u = users.getUser( userMessage.getUsername() );

			if (u.autenticate(userMessage.getUsername(), userMessage.getPassword())) 
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
	public void visit(MessageWaitingRoomConnections waitingConnections) 
	{
		Room r = ((Room) theSupporter);

		for (String s : waitingConnections.getWaitingConnections())
			r.setPlayer(s, waitingConnections.getWaitingConnections().indexOf(s));
	}

	@Override
	public void visit(MessagePlayingConnections playingConnections) 
	{
		Board b = ((Board) theSupporter);

		b.setPlayerNumber( playingConnections.getWaitingConnections().size() );

		for (String s : playingConnections.getWaitingConnections())
			b.setPlayersNames(s, playingConnections.getWaitingConnections().indexOf(s));
	}

	@Override
	public void visit(MessageGameHasStarted hasStarted) 
	{
		Room r = ((Room) theSupporter);
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
		Board b = ((Board) theSupporter);
		b.setDices(boardSetupDice.getBlackDiceValue(), boardSetupDice.getWhiteDiceValue(), boardSetupDice.getOrangeDiceValue());
	}

	@Override
	public synchronized void visit(MessageBoardReady br) 
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
	public void visit(MessageEvent event) 
	{
		event.getEvent().accept( new EventParser(theSupporter) );
	}

	@Override
	public void visit(MessageBoardMemberHasBeenPlaced newMember) 
	{
		Board b = ((Board) theSupporter);

		try {
			b.addMember(newMember.getActionExecuted(), newMember.getColor(), newMember.getPlayerIndex());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void visit(MessagePlayerID messageID) 
	{
		Board b = ((Board) theSupporter);
		b.setOwnerPlayerIndex( messageID.getID() );
	}

	@Override
	public void visit(MessageCurrentPlayer currentPlayer) 
	{
		Board b = ((Board) theSupporter);
		b.setCurrentPlayerID( currentPlayer.getID() );
	}

	@Override
	public void visit(MessageGameStatus gameStat) 
	{	
		Board b = ((Board) theSupporter);
		b.setPeriodRound(gameStat.getCurrentPeriod(),gameStat.getCurrentRound());
	}

	@Override
	public void visit(MessageVaticanReport vaticanRep) 
	{
		Board b = ((Board) theSupporter);

		for (int excommunicatedPlayer : vaticanRep.getExcommunicatedPlayers())
			b.excommunicate(vaticanRep.getPeriod(), excommunicatedPlayer);
	}

	@Override
	public void visit(MessageModel2ViewNotification notification) 
	{
		Board b = ((Board) theSupporter);
		b.showErrorLog( notification.getNotification() );
	}

	@Override
	public void visit(MessagePersonalBoardStatus pbStatus) 
	{
		Board b = ((Board) theSupporter);

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
		Connection connection = ((Connection) theSupporter);
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
		Board b = ((Board) theSupporter);

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
		Board b = ((Board) theSupporter);
		b.setCards( boardSetupDevCards.getRoundCards() );
	}

	@Override
	public void visit(MessageBoardSetupExcomCards boardSetupExcomCards) 
	{
		Board b = ((Board) theSupporter);
		b.setExcommunicationTiles(boardSetupExcomCards.getPeriodOne(), 
				boardSetupExcomCards.getPeriodTwo(), 
				boardSetupExcomCards.getPeriodThree()
				);
	}

	@Override
	public void visit(MessageCurrentPlayerOrder currentPlayerOrder) 
	{
		Board b = ((Board) theSupporter);
		
		int[] playerOrder = new int[ currentPlayerOrder.getPlayerOrder().size() ];
		for(int i=0; i < currentPlayerOrder.getPlayerOrder().size(); i++) playerOrder[i] = currentPlayerOrder.getPlayerOrder().get(i);
		b.setOrder( playerOrder );
	}

	@Override
	public void visit(MessageLeaderCards leaderCards) 
	{
		Board b = ((Board) theSupporter);

		b.setLeaders(leaderCards.getLeaderCards().get(0), 
				leaderCards.getLeaderCards().get(1), 
				leaderCards.getLeaderCards().get(2), 
				leaderCards.getLeaderCards().get(3)
				);
	}

	@Override
	public void visit(MessageDisconnect disconnect) 
	{
		Connection connection = ((Connection) theSupporter);	
		connection.closeConnection();
	}

	@Override
	public void visit(MessagePlayerPassed playerPassed) 
	{
		Connection connection = ((Connection) theSupporter);	
		SocketServer.getInstance().retrieveMatch(connection).getGame().advanceCurrentPlayer();
	}

	@Override
	public void visit(MessageBoardSetupTimeoutAction timeoutAction) 
	{
		Board b = ((Board) theSupporter);	
		b.setTimer( timeoutAction.getTimeout() );
	}

	@Override
	public void visit(MessageLeaderHasBeenPlayed leaderPlayed) 
	{
		Board b = ((Board) theSupporter);
		b.playLeader( leaderPlayed.getCode() );
	}

	@Override
	public void visit(MessageLeaderHasBeenActivated leaderActivated) 
	{
		Board b = ((Board) theSupporter);
		b.activateLeader( leaderActivated.getCode() );
	}

	@Override
	public void visit(MessageLeaderHasBeenDiscarded leaderDiscarded) 
	{
		Board b = ((Board) theSupporter);
		b.discardLeader( leaderDiscarded.getCode() );
	}

	@Override
	public void visit(MessageGameHasEnded hasEnded) 
	{
		Board b = ((Board) theSupporter);
		b.gameHasEnded( hasEnded.getWinnerPlayerCode() );
	}

	@Override
	public void visit(MessageUserHasLogged hasLogged) 
	{
		Room r = ((Room) theSupporter);
		r.userHasLoggedIn( hasLogged.getUsername(), 
				hasLogged.getGameCounter(), 
				hasLogged.getWinCounder(), 
				hasLogged.getSecondPlaceCounter(), 
				hasLogged.getMaxScore() 
				);
	}

	@Override
	public void visit(MessageTelegram tel) 
	{
		Connection connection = ((Connection) theSupporter);

		if ( tel.getTelegram() != null ) {

			MessageTelegramHasBeenSent telegram = new MessageTelegramHasBeenSent( connection.getPlayer().getID(), tel.getTelegram() );
			SocketServer.getInstance().sendToPlayingConnections(connection, telegram);
		}
	}

	@Override
	public void visit( MessageTelegramHasBeenSent telegramSent) {
		Board b = ((Board) theSupporter);
		b.addChatText( telegramSent.getPlayer() , telegramSent.getTelegram());
	}
}
