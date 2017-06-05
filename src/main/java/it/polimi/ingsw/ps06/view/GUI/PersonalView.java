package it.polimi.ingsw.ps06.view.GUI;

import java.io.IOException;

public interface PersonalView {
	
	public void setResources (int coin, int wood, int stone, int servant, int victory, int military, int faith);
	
	public void setCard(int id, int index);
	
	void show() throws IOException;

}
