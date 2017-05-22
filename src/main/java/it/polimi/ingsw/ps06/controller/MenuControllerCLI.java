package it.polimi.ingsw.ps06.controller;

import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps06.view.CLI.MenuCLI;
import it.polimi.ingsw.ps06.view.CLI.RoomCLI;

public class MenuControllerCLI implements MenuController, Observer {
	
	private MenuCLI theView;
	
	public MenuControllerCLI(MenuCLI theView) {
		this.theView = theView;
		this.theView.addNewControllerObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		int i = (int) arg;
		switch(i) {
		case 1:
			
			RoomCLI viewCLI = new RoomCLI(theView.getScanner());
			RoomControllerCLI c = new RoomControllerCLI(viewCLI);
			c.init();
			
			break;
		case 2:
			/* TEST CODE
			BoardCLI viewCLI = new BoardCLI(theView.getScanner());
			Game game = new Game(4);
			BoardControllerCLI c = new BoardControllerCLI(game, viewCLI);
			c.init();
			*/
		case 3:
		default:
		}
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		theView.show();
	}
}
