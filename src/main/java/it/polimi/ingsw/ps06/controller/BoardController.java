package it.polimi.ingsw.ps06.controller;

import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps06.Client;
import it.polimi.ingsw.ps06.Message;
import it.polimi.ingsw.ps06.model.Event;
import it.polimi.ingsw.ps06.model.EventParser;
import it.polimi.ingsw.ps06.view.Board;

public class BoardController implements Observer {
	
	private Board theView;
	
	public BoardController(Board boardView) {
		this.theView = boardView;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (!(arg instanceof Event && arg instanceof Message))
			return;
		
		//Smista le comunicazioni provenienti dal Client
		if ( o.getClass().isInstance(Client.getInstance()) ) {
			
		}			
		
		//Smista le comunicazioni provenienti dalla View
		if ( o.getClass().isInstance(theView) ) {					
		
			EventParser parser = new EventParser();
			((Event) arg).accept(parser);
		}
	}
}