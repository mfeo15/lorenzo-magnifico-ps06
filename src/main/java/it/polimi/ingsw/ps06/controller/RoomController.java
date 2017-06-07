package it.polimi.ingsw.ps06.controller;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps06.Client;
import it.polimi.ingsw.ps06.model.messages.Client2Server;
import it.polimi.ingsw.ps06.model.messages.Message;
import it.polimi.ingsw.ps06.model.messages.MessageConnection;
import it.polimi.ingsw.ps06.model.messages.MessageConnectionStart;
import it.polimi.ingsw.ps06.model.messages.MessageParser;
import it.polimi.ingsw.ps06.model.messages.StoryBoard;
import it.polimi.ingsw.ps06.view.Room;

public class RoomController extends Observable implements Observer {
	
	private Room theView;
	private Client theModel;
	
	private ArrayList<String> players;
	
	public RoomController(Client model, Room view) {
		this.theView = view;
		this.theModel = model;
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
		if (!( arg instanceof Message))
			return;
		
		//Smista le comunicazioni provenienti dal Client
		if ( o.getClass().isInstance(theModel) ) {
			handleModelFlux( (Message) arg );
			return;
		}
		
		//Smista le comunicazioni provenienti dalla View
		if ( o.getClass().isInstance(theView) ) {
			handleViewFlux( (Message) arg );
			return;
		}
	}
	
	private void handleModelFlux(Message m) {
		
		if (m instanceof MessageConnection) {
			
			if (m instanceof Client2Server) {
				//This is a normal CLIENT -> SERVER message (needs to be sent)
				notifyChangement(m);
			}
			else
			{
				//This is a normal SERVER -> CLIENT message (just received, needs to be parsed)
				MessageParser parser = new MessageParser();
				players = ((MessageConnection) m).accept(parser);
				
				theView.clearPlayers();
				for (String s : players) {
					theView.setPlayer(s, players.indexOf(s));
				}
			}
		}
	}
	
	private void handleViewFlux(Message m) {
		
		if ( m instanceof StoryBoard) 
		{
			//Let the controller handle this, it's just a StoryBoard Event (new View)
			MessageParser parser = new MessageParser();
			((StoryBoard) m).accept(parser);
		} 
		else 
		{
			//Event to let the model handle
			notifyChangement(m);
		}
	}
}