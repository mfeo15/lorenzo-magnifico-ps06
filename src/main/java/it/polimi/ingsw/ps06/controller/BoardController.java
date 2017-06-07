package it.polimi.ingsw.ps06.controller;

import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps06.Client;
import it.polimi.ingsw.ps06.model.messages.Client2Server;
import it.polimi.ingsw.ps06.model.messages.EventMessage;
import it.polimi.ingsw.ps06.model.messages.Message;
import it.polimi.ingsw.ps06.model.messages.MessageConnection;
import it.polimi.ingsw.ps06.model.messages.MessageParser;
import it.polimi.ingsw.ps06.model.messages.StoryBoard;
import it.polimi.ingsw.ps06.view.Board;

public class BoardController extends Observable implements Observer {
	
	private Board theView;
	private Client theModel;
	
	public BoardController(Client model, Board view) {
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
				Client.getInstance().asyncSend((Client2Server) m);
			}
			else
			{
				//This is a normal SERVER -> CLIENT message (just received, needs to be parsed)
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