package it.polimi.ingsw.ps06.controller;

import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps06.view.CLI.RoomCLI;

public class RoomController implements Observer {
	
	private Room theView;
	
	public RoomController(Room roomView) {
		this.theView = roomView;
		this.theView.addNewControllerObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
	}
}
