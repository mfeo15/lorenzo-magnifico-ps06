package it.polimi.ingsw.ps06.controller;

import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps06.Client;
<<<<<<< Updated upstream
import it.polimi.ingsw.ps06.model.Event;
import it.polimi.ingsw.ps06.model.EventParser;
import it.polimi.ingsw.ps06.view.Menu;
=======
import it.polimi.ingsw.ps06.model.messages.EventMessage;
import it.polimi.ingsw.ps06.model.messages.MessageParser;
import it.polimi.ingsw.ps06.view.GUI.Menu;
>>>>>>> Stashed changes

public class MenuController extends Observable implements Observer {
	
	private Menu theView;
	private Client theClient;
	
	public MenuController(Menu menuView) {
		this.theView = menuView;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if (!(arg instanceof EventMessage))
			return;
		
		//Smista le comunicazioni provenienti dal Client
		if ( o.getClass().isInstance(theClient) ) {
			
		}
				
		//Smista le comunicazioni provenienti dalla View
		if ( o.getClass().isInstance(theView) ) {
					
			MessageParser parser = new MessageParser();
			((EventMessage) arg).accept(parser);
		}
	}
}
