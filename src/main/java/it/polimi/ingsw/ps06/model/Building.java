package it.polimi.ingsw.ps06.model;

/**
* Classe per la gestione delle carte di tipo edificio
*
* @author  ps06
* @version 1.1
* @since   2017-05-11
*/
public class Building extends DevelopementCard {
	
	private int dice_requirement;
	private Effect production_effect;
	private Resources requirement;
	
	public Building(){
		
	}
	
	/**
	* Metodo per controllare che il valore del familiare sia sufficiente per avere bonus
	* @return 	Boolean
	**/
	
	public boolean check_dice(Dice dice){
		if( dice.getValue() >= dice_requirement)
			return true;
		else
			return false;
	}
	
	/**
	* Metodo per la gestione dell'azione di produzione per quanto riguarda la singola carta
	* 
	* @param 	player			Giocatore interessato
	* @return 					Nothing
	*/
	
	public void activateProduction(Player player){
		production_effect.activate(player);
		return;
	}
}
