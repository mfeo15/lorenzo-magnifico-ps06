package it.polimi.ingsw.ps06.controller;

import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps06.Client;
import it.polimi.ingsw.ps06.Message;
import it.polimi.ingsw.ps06.model.Event;
import it.polimi.ingsw.ps06.model.EventParser;
import it.polimi.ingsw.ps06.view.GUI.Room;

public class RoomController implements Observer {
	
	private Room theView;
	private Client theClient;
	
	public RoomController(Room roomView) {
		this.theView = roomView;
	}

	@Override
	public void update(Observable o, Object arg) {

		//Smista le comunicazioni provenienti dal Client
		if ( o.getClass().isInstance(Client.getInstance()) ) {
	
			theView.clearPlayers();
			
			Message m = (Message) arg;
			
			for (String s : m.getMessage()) {
				theView.setPlayer(s, m.getMessage().indexOf(s));
			}
		}
		
		//Smista le comunicazioni provenienti dalla View
		if ( o.getClass().isInstance(theView) ) {
			
			EventParser parser = new EventParser();
			((Event) arg).accept(parser);
		}
	}
}