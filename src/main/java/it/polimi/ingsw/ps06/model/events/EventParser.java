package it.polimi.ingsw.ps06.model.events;

import java.io.IOException;

import it.polimi.ingsw.ps06.controller.BoardController;
import it.polimi.ingsw.ps06.controller.RoomController;
import it.polimi.ingsw.ps06.model.Game;
import it.polimi.ingsw.ps06.networking.Client;
import it.polimi.ingsw.ps06.networking.Connection;
import it.polimi.ingsw.ps06.networking.SocketServer;
import it.polimi.ingsw.ps06.networking.messages.MessageBoardReady;
import it.polimi.ingsw.ps06.networking.messages.MessageDisconnect;


/**
 * Classe implementativa di EventVisitor, parsa l'evento da visitare seguendo
 * le direttive del Visitor Pattern Design
 * 
 * @author ps06
 * @since	2017-06-10
 */
public class EventParser implements EventVisitor {
	
	private Object theSupporter;
	
	/**
	 * Costruttore di default della classe
	 */
	public EventParser() {
		
	}
	
	/**
	 * Costruttore della classe con parametro di supporto alle gestione delle attività
	 * 
	 * @param	theSupporter	oggetto di supporto alla visita
	 */
	public EventParser(Object theSupporter) {
		this.theSupporter = theSupporter;
	}
	
	@Override
	public void visit(EventClose event) {
		Client.getInstance().asyncSend( new MessageDisconnect() );
		System.exit(0);
	}

	@Override
	public void visit(StoryBoard2Room event) {
		
		Client.getInstance().deleteAllObservers();
		
		RoomController controller = new RoomController(Client.getInstance() ,event.getView());
		
		controller.addNewObserver(Client.getInstance());
		event.getView().addNewObserver(controller);
		Client.getInstance().addNewObserver(controller);
		try {event.getView().show();} catch (IOException e) {e.printStackTrace();}
	}

	@Override
	public void visit(StoryBoard2Board event) {
		
		Client.getInstance().deleteAllObservers();
		
		BoardController controller = new BoardController(Client.getInstance(), event.getView());
		
		controller.addNewObserver(Client.getInstance());
		event.getView().addNewObserver(controller);
		Client.getInstance().addNewObserver(controller);
		try {event.getView().show();} catch (IOException e) {e.printStackTrace();}	
	}

	@Override
	public void visit(EventMemberPlaced event) 
	{
		Connection c = ((Connection) theSupporter);
 		
		Game game = SocketServer.getInstance().retrieveMatch(c).getGame();

		if (game.getCurrentPlayer().equals(c.getPlayer())) {
			
			if (event.getClass() == EventMemberPlacedWithPrivilege.class) {
				game.doMemberPlacement(c.getPlayer(), 
										event.getAction(), 
										event.getColor(), 
										event.getServantsBonus(), 
										((EventMemberPlacedWithPrivilege) event).getPrivilege()
									  );
				return;
			}
			
			if (event.getClass() == EventMemberPlacedWithDoublePrivilege.class) {
				game.doMemberPlacement(c.getPlayer(), 
								event.getAction(), 
								event.getColor(), 
								event.getServantsBonus(), 
								((EventMemberPlacedWithDoublePrivilege) event).getPrivilege(),
								((EventMemberPlacedWithDoublePrivilege) event).getSecondPrivilegeDifferentFromTheFirstOne()
							);
				return;
			}
			
			game.doMemberPlacement(c.getPlayer(), event.getAction(), event.getColor(), event.getServantsBonus());
		}
	}

	@Override
	public void visit(EventLeaderDiscarded event) {

		Connection c = ((Connection) theSupporter);
		c.getPlayer().doLeaderDiscarding( event.getCode() );
	}

	@Override
	public void visit(EventLeaderActivated event) 
	{
		Connection c = ((Connection) theSupporter);
		c.getPlayer().doLeaderActivating( event.getCode() );
	}

	@Override
	public void visit(EventLeaderPlayed event) 
	{
		Connection c = ((Connection) theSupporter);
		c.getPlayer().doLeaderPlaying( event.getCode() );
	}

	@Override
	public void visit(RoomHasLoaded event) {
		
        try {
			Client.getInstance().init();
			(new Thread(Client.getInstance())).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void visit(BoardHasLoaded event) {
		MessageBoardReady br = new MessageBoardReady();
		Client.getInstance().asyncSend(br);
	}
}
