package it.polimi.ingsw.ps06.controller;

import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps06.Client;
import it.polimi.ingsw.ps06.model.messages.EventMessage;
import it.polimi.ingsw.ps06.model.messages.Message;
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
		if (!(arg instanceof Message))
			return;
		
		//Smista le comunicazioni provenienti dal Client
		if ( o.getClass().isInstance(theModel) ) {
			
		}			
		
		//Smista le comunicazioni provenienti dalla View
		if ( o.getClass().isInstance(theView) ) {	
			
			if ( arg instanceof StoryBoard) 
			{
				//Let the controller handle this, it's just a StoryBoard Event (new View)
				MessageParser parser = new MessageParser();
				((StoryBoard) arg).accept(parser);
			} 
			else 
			{
				//Event to let the model handle
				notifyChangement(arg);
			}
		}
	}
}