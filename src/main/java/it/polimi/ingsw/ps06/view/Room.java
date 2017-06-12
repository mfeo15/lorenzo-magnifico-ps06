package it.polimi.ingsw.ps06.view;

import java.io.IOException;
import java.util.Observer;

public interface Room {
	
	void setPlayer(String name, int index);
	
	void clearPlayers(); 
	
	void giveCredentials(String username, String password);
	
	boolean checkLogin();
	
	void addNewObserver(Observer o);
	
	void startGame();
	
	void notifyExit();
	
	void hasStarted();
	
	void show() throws IOException;
	
	void hasLoaded();

}
