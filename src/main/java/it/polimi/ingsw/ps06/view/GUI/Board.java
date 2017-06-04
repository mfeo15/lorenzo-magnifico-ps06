package it.polimi.ingsw.ps06.view.GUI;

import it.polimi.ingsw.ps06.model.Types.Action;

public interface Board {
	
	public void setHarvestIndex(int value);
	
	public void setProductionIndex(int value);
	
	public void setCouncilIndex(int value);
	
	public void setPeriodRound(int period, int round);
	
	public void setPlayerColor(String s);
	
	public void setPlayerName(String s);
	
	public void setCurrentPlayerName(String s);
	
	public void setActionLog(String s);
	
	public void setDices(int black, int white, int orange);
	
	public void setExcommunicationTiles(int code1, int code2, int code3);
	
	public void activateLeaders();
	
	public void setLeaders(int code1, int code2, int code3, int code4);
	
	public void setPersonalResources(int coin, int wood, int stone, int servant);
	
	public boolean checkActionLegality();
	
	public void startTimer();
	
	public void notifyExit();
	
	public void notifyAction(Action chosenAction, int memberIndex);
	
	public void notifyLeaderDiscard(int index);
	
	public void notifyLeaderPlacement(int index);
	
	public void notifyTimesUp();
	
}
