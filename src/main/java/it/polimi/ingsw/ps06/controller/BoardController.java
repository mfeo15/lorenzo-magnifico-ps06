package it.polimi.ingsw.ps06.controller;

import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps06.model.events.Event;
import it.polimi.ingsw.ps06.model.events.EventParser;
import it.polimi.ingsw.ps06.model.events.StoryBoard;
import it.polimi.ingsw.ps06.networking.Client;
import it.polimi.ingsw.ps06.networking.messages.Client2Server;
import it.polimi.ingsw.ps06.networking.messages.EventMessage;
import it.polimi.ingsw.ps06.networking.messages.Message;
import it.polimi.ingsw.ps06.networking.messages.MessageParser;
import it.polimi.ingsw.ps06.networking.messages.Server2Client;
import it.polimi.ingsw.ps06.view.Board;

public class BoardController extends Observable implements Observer {
	
	private Board theView;
	private Client theModel;
	
	public BoardController(Client model, Board view) {
		this.theView = view;
		this.theModel = model;
		
		System.out.println("BOARD IS THE BOSS");
	}

	public void addNewObserver(Observer o) {
		addObserver(o);
	}
	
	public void notifyChangement(Message m) {
		setChanged();
		notifyObservers(m);
	} 
	
	@Override
	public void update(Observable o, Object arg) {
		/*
		if (!( arg instanceof Message))
			return;
		*/
		
		//Smista le comunicazioni provenienti dal Client
		if ( o.getClass().isInstance(theModel) ) {
			handleMessage( (Message) arg );
			return;
		}
		
		//Smista le comunicazioni provenienti dalla View
		if ( o.getClass().isInstance(theView) ) {
			
			if (arg instanceof Message) {
				handleMessage( (Message) arg );
				return;
			}
			
			if (arg instanceof Event) {
				handleEvent( (Event) arg );
				return;
			}
		}
	}
	
	private void handleMessage(Message m) {
		
		
		if (m instanceof Server2Client) {
			//This is a normal SERVER -> CLIENT message (time to parse it)
			((Server2Client) m).accept(new MessageParser(theView));
			return;
		}	
		
		if (m instanceof Client2Server) {
			//This is a normal CLIENT -> SERVER message (let someone else handle it)
			notifyChangement(m);
		}	
	}
	
	private void handleEvent(Event e) {
		
		if ( e instanceof StoryBoard) 
		{
			//Let the controller handle this, it's just a StoryBoard Event (new View)
			EventParser parser = new EventParser(this);
			((StoryBoard) e).accept(parser);
		} 
		else 
		{
			//Event to let the model handle
			EventMessage m = new EventMessage(e);
			notifyChangement(m);
		}
	}
}