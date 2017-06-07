package it.polimi.ingsw.ps06.model.messages;

import java.io.IOException;
import java.util.ArrayList;

import it.polimi.ingsw.ps06.Client;
import it.polimi.ingsw.ps06.controller.BoardController;
import it.polimi.ingsw.ps06.controller.RoomController;
import it.polimi.ingsw.ps06.model.LeaderDiscarded;
import it.polimi.ingsw.ps06.model.MemberPlaced;

public class MessageParser implements MessageVisitor {

	@Override
	public void visit(EventClose eventClose) {
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
	public void visit(MessageConnectionStart startConnection) {
		try {
			Client.getInstance().init();
			(new Thread(Client.getInstance())).start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	@Override
	public String visit(MessageUser userMessage) {
		return userMessage.getUsername();
	}

	@Override
	public ArrayList<String> visit(MessageWaitingRoomConnections waitingConnections) {
		return waitingConnections.getWaitingConnections();
	}
}
