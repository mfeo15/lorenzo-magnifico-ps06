package it.polimi.ingsw.ps06.controller;

import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps06.view.CLI.MenuCLI;
import it.polimi.ingsw.ps06.view.CLI.RoomCLI;

public class MenuController extends Observable implements Observer {
	
	private Menu theView;
	
	public MenuController(Menu menuView) {
		this.theView = menuView;
		this.theView.addNewControllerObserver(this);
	}
	
	@Override
	public void update(Observable o, Object arg) {

		try {										// <== Exception da gestire, qualcosa non va!
			int i = (int) arg;
			
			switch( i ) {
			case 1:
				RoomCLI viewCLI = new RoomCLI(theView.getScanner());
				RoomController c = new RoomController(viewCLI);
				c.init();
				
				break;
			case 2:
				System.exit(0);
			case 3:
				/* TEST CODE
				BoardCLI viewCLI = new BoardCLI(theView.getScanner());
				Game game = new Game(4);
				BoardControllerCLI c = new BoardControllerCLI(game, viewCLI);
				c.init();
				*/
			default:
				theView.showErrorMessage("Input \"" + arg + "\" non accettato. Inserisci un input corretto > " );
			}
			
		} catch (NumberFormatException e) {
			theView.showErrorMessage("Input \"" + arg + "\" non accettato. Inserisci un input corretto > " );
		}
	}
}
