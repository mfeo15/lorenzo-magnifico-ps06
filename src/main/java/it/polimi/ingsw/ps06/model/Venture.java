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

	
	/**Metodo per aggiungere i requisiti delle carte impresa
	 * 
	 * @param res	requisito di tipo risorsa per prendere la carta
	 */
	public void setRequirement(Resources res){
		requirement.add(res);
		return;
	}

	/**Metodo che restituisce i	requisiti delle carte impresa
	 * 
	 * @param res	requisito di tipo risorsa per prendere la carta
	 */
	
	public ArrayList<Resources> getRequirements(){
		return requirement;
	}
}
