package it.polimi.ingsw.ps06.model;

import java.util.ArrayList;
/**
* Classe per la gestione delle carte di tipo impresa
*
* @author  ps06
* @version 1.1
* @since   2017-05-11
*/
public class Venture extends DevelopementCard {
	
	private ArrayList<Resources> requirement;
	private int MilitaryRequirement = -1;
	
	/**Metodo per aggiungere i requisiti delle carte impresa
	 * 
	 * @param res	requisito di tipo risorsa per prendere la carta
	 */
	
	public void setRequirement(Resources res) {
		requirement=new ArrayList<Resources>();
		requirement.add(res);
		return;
	}
	
	/**Metodo per aggiungere il requisito dei punti militari
	 * 
	 * @param i	requisito di tipo intero per i punti militari
	 * @return nothing
	 */
	
	public void setMilRequirement(int i) {
		MilitaryRequirement=i;
		return;
	}
	
	/**Metodo per dire se esiste un requisito di punti militari
	 * 
	 * @param none
	 * @return boolean
	 */
	
	public boolean isMilRequirement() {
		return (MilitaryRequirement > 0);
	}

	/**Metodo che restituisce i	requisiti delle carte impresa
	 * 
	 * @param res	requisito di tipo risorsa per prendere la carta
	 */
	
	public ArrayList<Resources> getRequirements() {
		return requirement;
	}
	
	/**Metodo che restituisce il requisito militare della carta
	 * 
	 * @param res	requisito di tipo risorsa per prendere la carta
	 */
	
	public int getMilitaryRequirement() {
		return MilitaryRequirement;
	}
	
}
