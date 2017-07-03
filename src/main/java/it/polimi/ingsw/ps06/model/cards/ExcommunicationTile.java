package it.polimi.ingsw.ps06.model.cards;

/**
* Classe per gestione delle tessere scomunica
*
* @author  ps06
* @version 1.0
* @since   2017-05-10
*/

public class ExcommunicationTile extends Card {
	
	private int period;
	
	public void setPeriod(int period) {
		this.period = period;
	}
	
	public int getPeriod() {
		return this.period;
	}
}
