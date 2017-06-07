package it.polimi.ingsw.ps06.controller;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps06.Client;
import it.polimi.ingsw.ps06.Message;
<<<<<<< Updated upstream
import it.polimi.ingsw.ps06.model.Event;
import it.polimi.ingsw.ps06.model.EventParser;
import it.polimi.ingsw.ps06.view.Room;
=======
import it.polimi.ingsw.ps06.model.messages.EventMessage;
import it.polimi.ingsw.ps06.model.messages.MessageParser;
import it.polimi.ingsw.ps06.model.messages.StoryBoard;
import it.polimi.ingsw.ps06.view.GUI.Room;
>>>>>>> Stashed changes

public class RoomController extends Observable implements Observer {
	
	private Room theView;
	
	private ArrayList<String> players;
	
	public RoomController(Room roomView) {
		this.theView = roomView;
	}
	
	public void addNewObserver(Observer o) {
		addObserver(o);
	}
	
	public void notifyChangement(Object o) {
		setChanged();
		notifyObservers(o);
	} 

	@Override
	public void update(Observable o, Object arg) {
		if (!(arg instanceof EventMessage && arg instanceof Message))
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
			
			if ( arg instanceof StoryBoard) {
				MessageParser parser = new MessageParser();
				((StoryBoard) arg).accept(parser);
			} else {
				notifyChangement(arg);
			}
		}
	}
}