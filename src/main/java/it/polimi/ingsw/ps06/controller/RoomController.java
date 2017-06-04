package it.polimi.ingsw.ps06.controller;

import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps06.Client;
import it.polimi.ingsw.ps06.Connection;
import it.polimi.ingsw.ps06.Message;
import it.polimi.ingsw.ps06.view.CLI.RoomCLI;

public class RoomController implements Observer {
	
	private RoomCLI theView;
	private Client theClient;
	
	public RoomController(Client client, RoomCLI roomView) {
		this.theView = roomView;
		this.theClient = client;
		
		theClient.addObserver(this);
	}
	
	public RoomController(Client c) {
		this.theClient = c;
		
		//theClient.addNewObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
		if ( o.getClass().isInstance(theClient) ) {
			
			Message m = (Message) arg;
			
			System.out.println("Controller received: " + m.getMessage() );
		}
		
		if ( o.getClass().isInstance(theView) ) {
			
		}
	}
}