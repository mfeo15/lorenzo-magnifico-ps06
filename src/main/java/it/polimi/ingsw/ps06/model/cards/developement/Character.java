package it.polimi.ingsw.ps06.model.cards.developement;

import it.polimi.ingsw.ps06.model.Resources;

/**
* Classe per la gestione delle carte di tipo personaggio
*
* @author  ps06
* @version 1.0
* @since   2017-05-11
*/
public class Character extends DevelopementCard {
	private Resources requirement;
	
	
	/**Metodo per settare i requisiti della carta
	 * 
	 * @param	requirement		requisiti della carta
	 * @return	nothing
	 */
	 
	public void setRequirement(Resources requirement){
		this.requirement=requirement;
	 	return;
	}	
	 
	/**
	* Metodo per la gestione degli effetti per quanto riguarda la singola carta
	* 
	* @param 	player			Giocatore interessato
	* @return 					Nothing
	*/
	
	public Resources getRequirement(){
		return requirement;
	}

}
