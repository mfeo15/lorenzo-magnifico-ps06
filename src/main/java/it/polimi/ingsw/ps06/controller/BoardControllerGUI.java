package it.polimi.ingsw.ps06.controller;

import it.polimi.ingsw.ps06.view.GUI.Board;

public class BoardControllerGUI implements MenuController {

	private BoardGUI theView;
	
	public BoardControllerGUI() {
		theView = new BoardCLI();
	}
}