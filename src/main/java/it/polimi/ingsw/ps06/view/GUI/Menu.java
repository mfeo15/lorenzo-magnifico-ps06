package it.polimi.ingsw.ps06.view.GUI;

import java.io.IOException;
import java.util.Observer;

public interface Menu {

	void addNewObserver(Observer o);
	
	void startGame();
	
	void notifyExit();
	
	void show() throws IOException;

}
