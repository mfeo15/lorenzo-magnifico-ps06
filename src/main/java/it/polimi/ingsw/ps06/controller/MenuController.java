package it.polimi.ingsw.ps06.controller;

import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps06.model.events.Event;
import it.polimi.ingsw.ps06.model.events.EventParser;
import it.polimi.ingsw.ps06.networking.Client;
import it.polimi.ingsw.ps06.view.Menu;

public class MenuController extends Observable implements Observer {
	
	private Menu theView;
	private Client theClient;
	
	public MenuController(Menu menuView) {
		this.theView = menuView;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if (!(arg instanceof Event))
			return;
		
		//Smista le comunicazioni provenienti dal Client
		if ( o.getClass().isInstance(theClient) ) {
			
		}
				
		//Smista le comunicazioni provenienti dalla View
		if ( o.getClass().isInstance(theView) ) {
					
			EventParser parser = new EventParser();
			((Event) arg).accept(parser);
		}
	}
}
