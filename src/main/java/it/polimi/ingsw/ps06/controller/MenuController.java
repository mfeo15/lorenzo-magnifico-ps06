package it.polimi.ingsw.ps06.controller;

import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps06.model.events.Event;
import it.polimi.ingsw.ps06.model.events.EventParser;
import it.polimi.ingsw.ps06.view.Menu;

/**
 * Controller associato alla View Menu
 *
 * @author  ps06
 * @version 1.1
 * @since   2017-05-23
 */
public class MenuController extends Observable implements Observer {

	private Menu theView;

	/**
	 * Costruttore della classe
	 * 
	 * @param	menuView	View associata al Controller
	 */
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
