package it.polimi.ingsw.ps06.controller;

import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps06.Client;
import it.polimi.ingsw.ps06.Message;
<<<<<<< Updated upstream
import it.polimi.ingsw.ps06.model.Event;
import it.polimi.ingsw.ps06.model.EventParser;
import it.polimi.ingsw.ps06.view.Board;
=======
import it.polimi.ingsw.ps06.model.messages.EventMessage;
import it.polimi.ingsw.ps06.model.messages.MessageParser;
import it.polimi.ingsw.ps06.view.GUI.Board;
>>>>>>> Stashed changes

public class BoardController extends Observable implements Observer {
	
	private Board theView;
	
	public BoardController(Board boardView) {
		this.theView = boardView;
	}

	public void addNewObserver(Observer o) {
		addObserver(o);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if (!(arg instanceof EventMessage && arg instanceof Message))
			return;
		
		//Smista le comunicazioni provenienti dal Client
		if ( o.getClass().isInstance(Client.getInstance()) ) {
			
		}			
		
		//Smista le comunicazioni provenienti dalla View
		if ( o.getClass().isInstance(theView) ) {					
		
			MessageParser parser = new MessageParser();
			((EventMessage) arg).accept(parser);
		}
	}
}