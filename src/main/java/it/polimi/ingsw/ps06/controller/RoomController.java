package it.polimi.ingsw.ps06.controller;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps06.Client;
import it.polimi.ingsw.ps06.Message;
import it.polimi.ingsw.ps06.model.Event;
import it.polimi.ingsw.ps06.model.EventParser;
import it.polimi.ingsw.ps06.view.GUI.Room;

public class RoomController implements Observer {
	
	private Room theView;
	
	private ArrayList<String> players;
	
	public RoomController(Room roomView) {
		this.theView = roomView;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (!(arg instanceof Event && arg instanceof Message))
			return;
		
		//Smista le comunicazioni provenienti dal Client
		if ( o.getClass().isInstance(Client.getInstance()) ) {
	
			theView.clearPlayers();
			
			Message m = (Message) arg;
			players = m.getMessage();
			
			for (String s : players) {
				theView.setPlayer(s, players.indexOf(s));
			}
		}
		
		//Smista le comunicazioni provenienti dalla View
		if ( o.getClass().isInstance(theView) ) {
			
			EventParser parser = new EventParser();
			((Event) arg).accept(parser);
		}
	}
}