package it.polimi.ingsw.ps06.controller;

import it.polimi.ingsw.ps06.view.GUI.Room;

public class RoomControllerGUI implements RoomController {

	private RoomGUI theView;
	
	public RoomControllerGUI() {
		theView = new Room();
	}
}