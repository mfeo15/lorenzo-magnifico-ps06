package it.polimi.ingsw.ps06.controller;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps06.model.DevelopementCard;
import it.polimi.ingsw.ps06.model.Game;
import it.polimi.ingsw.ps06.view.CLI.BoardCLI;

public class BoardControllerCLI implements BoardController, Observer {
	
	private BoardCLI theView;
	private Game  theGame;
	
	public BoardControllerCLI(Game theGame, BoardCLI theView) {
		this.theGame = theGame;
		
		this.theView = theView;
		theView.addNewControllerObserver(this);
	}
	

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		theView.printTowers(/* model.getBoard().getTower() */ new ArrayList<DevelopementCard>());
	}
}
