package it.polimi.ingsw.ps06.view.GUI;

import java.io.IOException;
import java.util.Observer;

import it.polimi.ingsw.ps06.model.Types.Action;

public interface Board {
	
	void setHarvestIndex(int value);
	
	void setProductionIndex(int value);
	
	void setCouncilIndex(int value);
	
	void setPeriodRound(int period, int round);
	
	void setPlayerColor(String s);
	
	void setPlayerName(String s);
	
	void setCurrentPlayerName(String s);
	
	void setActionLog(String s);
	
	void setDices(int black, int white, int orange);
	
	void setExcommunicationTiles(int code1, int code2, int code3);
	
	void activateLeaders();
	
	void setLeaders(int code1, int code2, int code3, int code4);
	
	void setPersonalResources(int coin, int wood, int stone, int servant);
	
	boolean checkActionLegality();
	
	void startTimer();
	
	void notifyExit();
	
	void notifyAction(Action chosenAction, int memberIndex);
	
	void notifyLeaderDiscard(int index);
	
	void notifyLeaderPlacement(int index);
	
	void notifyTimesUp();
	
	void show() throws IOException;
	
	void addNewObserver(Observer o);
	
}
