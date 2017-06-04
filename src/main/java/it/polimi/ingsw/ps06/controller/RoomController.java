package it.polimi.ingsw.ps06.controller;

import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps06.model.Event;
import it.polimi.ingsw.ps06.view.GUI.Room;

public class RoomController implements Observer {
	
	private Room theView;
	
	public RoomController(Room roomView) {
		this.theView = roomView;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (!(arg instanceof Event))
			return;
		
	}
}
