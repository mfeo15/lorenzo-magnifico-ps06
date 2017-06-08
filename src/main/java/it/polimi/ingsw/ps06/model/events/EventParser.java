package it.polimi.ingsw.ps06.model.events;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps06.Client;
import it.polimi.ingsw.ps06.Connection;
import it.polimi.ingsw.ps06.controller.BoardController;
import it.polimi.ingsw.ps06.controller.RoomController;
import it.polimi.ingsw.ps06.model.Types.ColorPalette;
import it.polimi.ingsw.ps06.model.events.EventClose;
import it.polimi.ingsw.ps06.model.events.EventLeaderActivated;
import it.polimi.ingsw.ps06.model.events.EventLeaderDiscarded;
import it.polimi.ingsw.ps06.model.events.EventLeaderPlayed;
import it.polimi.ingsw.ps06.model.events.EventMemberPlaced;
import it.polimi.ingsw.ps06.model.events.StoryBoard2Board;
import it.polimi.ingsw.ps06.model.events.StoryBoard2PersonalView;
import it.polimi.ingsw.ps06.model.events.StoryBoard2Room;

public class EventParser implements EventVisitor {
	
	@Override
	public void visit(EventClose eventClose) {
		//Client.getInstance().
		System.exit(0);
	}

	@Override
	public void visit(StoryBoard2Room storyboard) {
		
		RoomController controller = new RoomController(Client.getInstance() ,storyboard.getView());
		
		Client.getInstance().addNewObserver(controller);
		controller.addNewObserver(Client.getInstance());
		storyboard.getView().addNewObserver(controller);
		try {storyboard.getView().show();} catch (IOException e) {e.printStackTrace();}
	}

	@Override
	public void visit(StoryBoard2Board storyboard) {
		
		BoardController controller = new BoardController(Client.getInstance(), storyboard.getView());
		
		Client.getInstance().addNewObserver(controller);
		controller.addNewObserver(Client.getInstance());
		storyboard.getView().addNewObserver(controller);
		try {storyboard.getView().show();} catch (IOException e) {e.printStackTrace();}	
	}

	@Override
	public void visit(StoryBoard2PersonalView storyboard) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(EventMemberPlaced memberPlaced) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(EventLeaderDiscarded leaderDiscarded) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(EventLeaderActivated leaderActivated) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(EventLeaderPlayed leaderPlayed) {
		// TODO Auto-generated method stub
		
	}
}
