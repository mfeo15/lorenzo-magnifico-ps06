package it.polimi.ingsw.ps06.view;

import java.io.IOException;
import java.util.Observer;

public interface PersonalView {
	
	void setResources (int coin, int wood, int stone, int servant, int victory, int military, int faith);
	
	void setTerritoryCard(int id, int index);
	
	void setBuildingCard(int id, int index);
	
	void addNewObserver(Observer o);
	
	void show() throws IOException;
	
	void hasLoaded();
	
	void notifyExit();

}
