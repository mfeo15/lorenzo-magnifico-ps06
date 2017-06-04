package it.polimi.ingsw.ps06.controller;

import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps06.model.Event;
import it.polimi.ingsw.ps06.model.EventParser;
import it.polimi.ingsw.ps06.view.GUI.Menu;
import it.polimi.ingsw.ps06.view.GUI.MenuCLI;

public class MenuController extends Observable implements Observer {
	
	private Menu theView;
	
	public MenuController(Menu menuView) {
		this.theView = menuView;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if (!(arg instanceof Event))
			return;
		
		EventParser parser = new EventParser();
		((Event) arg).accept(parser);
	}
}
