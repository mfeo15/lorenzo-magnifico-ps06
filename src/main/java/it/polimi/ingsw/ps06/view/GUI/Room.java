package it.polimi.ingsw.ps06.view.GUI;

public interface Room {
	
	public void setPlayer(String name, int index);
	
	public void giveCredentials(String username, String password);
	
	public boolean checkLogin();
	
	public void startGame();
	
	public void notifyExit();

}
