package it.polimi.ingsw.ps06.model.events;

import java.io.IOException;

import it.polimi.ingsw.ps06.controller.BoardController;
import it.polimi.ingsw.ps06.controller.RoomController;
import it.polimi.ingsw.ps06.model.Game;
import it.polimi.ingsw.ps06.networking.Client;
import it.polimi.ingsw.ps06.networking.Connection;
import it.polimi.ingsw.ps06.networking.SocketServer;
import it.polimi.ingsw.ps06.networking.messages.BoardReady;
import it.polimi.ingsw.ps06.networking.messages.MessageDisconnect;

public class EventParser implements EventVisitor {
	
	private Object theModel;
	
	public EventParser() {
		
	}
	
	public EventParser(Object model) {
		this.theModel = model;
	}
	
	@Override
	public void visit(EventClose eventClose) {
		Client.getInstance().asyncSend( new MessageDisconnect() );
		System.exit(0);
	}

	@Override
	public void visit(StoryBoard2Room storyboard) {
		
		Client.getInstance().deleteAllObservers();
		
		RoomController controller = new RoomController(Client.getInstance() ,storyboard.getView());
		
		controller.addNewObserver(Client.getInstance());
		storyboard.getView().addNewObserver(controller);
		Client.getInstance().addNewObserver(controller);
		try {storyboard.getView().show();} catch (IOException e) {e.printStackTrace();}
	}

	@Override
	public void visit(StoryBoard2Board storyboard) {
		
		Client.getInstance().deleteAllObservers();
		
		BoardController controller = new BoardController(Client.getInstance(), storyboard.getView());
		
		controller.addNewObserver(Client.getInstance());
		storyboard.getView().addNewObserver(controller);
		Client.getInstance().addNewObserver(controller);
		try {storyboard.getView().show();} catch (IOException e) {e.printStackTrace();}	
	}

	@Override
	public void visit(EventMemberPlaced memberPlaced) {
		Connection c = ((Connection) theModel);
 		
		Game game = SocketServer.getInstance().retrieveMatch(c).getGame();

		if (game.getCurrentPlayer().equals(c.getPlayer())) {
			
			if (memberPlaced instanceof EventMemberPlacedWithPrivilege)
				game.doMemberPlacement(c.getPlayer(), 
										memberPlaced.getAction(), 
										memberPlaced.getColor(), 
										memberPlaced.getServantsBonus(), 
										((EventMemberPlacedWithPrivilege) memberPlaced).getPrivilege());
			else
				game.doMemberPlacement(c.getPlayer(), memberPlaced.getAction(), memberPlaced.getColor(), memberPlaced.getServantsBonus());
		}
	}

	@Override
	public void visit(EventLeaderDiscarded leaderDiscarded) {

		Connection c = ((Connection) theModel);
		c.getPlayer().getLeader( leaderDiscarded.getCode() ).discardLeader();
	}

	@Override
	public void visit(EventLeaderActivated leaderActivated) {
		Connection c = ((Connection) theModel);
		
		c.getPlayer().getLeader( leaderActivated.getCode() ).activateLeader();
	}

	@Override
	public void visit(EventLeaderPlayed leaderPlayed) {
		Connection c = ((Connection) theModel);
		
		c.getPlayer().getLeader( leaderPlayed.getCode() ).playLeader();
	}

	@Override
	public void visit(RoomHasLoaded roomHasLoaded) {
		
        try {
			Client.getInstance().init();
			(new Thread(Client.getInstance())).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void visit(BoardHasLoaded boardHasLoaded) {
		BoardReady br = new BoardReady();
		Client.getInstance().asyncSend(br);
	}
}
