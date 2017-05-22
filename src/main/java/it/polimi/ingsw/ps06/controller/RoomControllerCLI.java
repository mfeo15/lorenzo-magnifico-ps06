package it.polimi.ingsw.ps06.controller;

import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps06.view.CLI.RoomCLI;

public class RoomControllerCLI implements RoomController, Observer {
	
	private RoomCLI theView;
	
	public RoomControllerCLI(RoomCLI theView) {
		this.theView = theView;
		this.theView.addNewControllerObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
}
