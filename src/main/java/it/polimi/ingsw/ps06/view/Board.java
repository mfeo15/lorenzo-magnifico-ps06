package it.polimi.ingsw.ps06.view;

import java.io.IOException;
import java.util.Observer;

import it.polimi.ingsw.ps06.model.Types.Action;
import it.polimi.ingsw.ps06.model.Types.ColorPalette;

public interface Board {
	
	void setHarvestIndex(int value);
	
	void setProductionIndex(int value);
	
	void setCouncilIndex(int value);
	
	void setPeriodRound(int period, int round);
	
	void setPlayerColor(String s);
	
	void setOwnerPlayerIndex(int index);
	
	void setPlayersNames(String s, int index);
	
	void setCurrentPlayerID(int id);
	
	void setDices(int black, int white, int orange);
	
	void setExcommunicationTiles(int code1, int code2, int code3);
	
	void setCards(int[] cards);
	
	void activateLeaders();
	
	void setLeaders(int code1, int code2, int code3, int code4);
	
	void setPersonalResources(int coin, int wood, int stone, int servant);
	
	void setPlayerNumber(int number);
	
	void addMember(Action chosenAction, ColorPalette color, int playerIndex) throws IOException;
	
	void startTimer();
	
	void notifyExit();
	
	void notifyAction(Action chosenAction, int memberIndex, int servants);
	
	void notifyLeaderDiscard(int index);
	
	void notifyLeaderPlacement(int index);
	
	void notifyLeaderActivation(int index);
	
	void notifyTimesUp();
	
	void show() throws IOException;
	
	void hasLoaded();
	
	void addNewObserver(Observer o);
	
	void showErrorLog(String s);
	
	void startGame(int index);
	
	void setRound();
	
}
