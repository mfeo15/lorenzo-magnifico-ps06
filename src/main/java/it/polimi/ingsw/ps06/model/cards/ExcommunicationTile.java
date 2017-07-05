package it.polimi.ingsw.ps06.model.cards;

/**
* Classe per gestione delle tessere scomunica
*
* @author  ps06
* @version 1.0
* @since   2017-05-10
*/
public class ExcommunicationTile extends Card {
	protected int period=1;
	
	public void setPeriod(int period){
		this.period=period;
	}
	
	public int getPeriod(){
		return period;
	}
	
	private int period;
	
	/**
	 * Setter per il periodo della tessera
	 * 
	 * @param period	periodo associato alla tessera
	 */
	public void setPeriod(int period) {
		this.period = period;
	}
	
	/**
	 * Getter per il periodo della tessera
	 * 
	 * @return	periodo associato alla tessera
	 */
	public int getPeriod() {
		return this.period;
	}
}
