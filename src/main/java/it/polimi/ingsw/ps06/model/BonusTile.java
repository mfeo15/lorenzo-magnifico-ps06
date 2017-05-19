package it.polimi.ingsw.ps06.model;


/**
 * classe per la definizione e gestione della tessera bonus personale
 * 
 * @author ps06
 * @version 1.0
 * @since 13-05-2017
 */

public class BonusTile {
	
	private Resources harvest_bonus;
	private Resources production_bonus;
	private int dice_required;

	/**
	* Metodo per controllare che il valore del familiare sia sufficiente per avere bonus
	* @return 					Boolean
	**/
	
	public boolean check_dice(){
		if( >= dice_required)
			return true;
		else
			return false;
	}
	
	/**
	* Metodo che attiva i bonus e li da al giocatore
	* @return 					Nothing
	*/
	
	public class activate_bonus(){
		if(this.check_dice){
			
			
		}
	}
	
}
