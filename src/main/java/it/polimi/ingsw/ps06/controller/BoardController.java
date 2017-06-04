package it.polimi.ingsw.ps06.controller;

import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps06.model.Board;
import it.polimi.ingsw.ps06.model.Event;
import it.polimi.ingsw.ps06.model.EventParser;
import it.polimi.ingsw.ps06.model.Game;
import it.polimi.ingsw.ps06.view.GUI.BoardCLI;

public class BoardController implements Observer {
	
	private Board theView;
	private Game  theModel;
	
	public BoardController(Game game, Board boardView) {
		this.theModel = game;
		this.theView = boardView;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (!(arg instanceof Event))
			return;
		
		EventParser parser = new EventParser();
		((Event) arg).accept(parser);
	}
}