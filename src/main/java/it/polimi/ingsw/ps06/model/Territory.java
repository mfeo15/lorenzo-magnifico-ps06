package it.polimi.ingsw.ps06.model;

import it.polimi.ingsw.ps06.model.EffectsResources;
import it.polimi.ingsw.ps06.model.EffectsActions;


/**
* Classe per la gestione delle carte di tipo territorio
*
* @author  ps06
* @version 1.1
* @since   2017-05-11
*/
public class Territory extends DevelopementCard {
	
	private Effect harvest_effect;
	private int dice_requirement;

	/**
	* costruttore della plancia giocatore, alloca gli arraylist per le carte che si 
	* aggiungono man mano, un warehouse contenitore delle risorse e una tessera bonus personale
	*
	* @param 	none
	* 
	* @return 	Nothing
	*/
	
	//ancora da implementare la lettura da file
	public Territory(){
		code = 2;
		//code = x;
		//dice_requirement = n;
		//harvest_effect = new //tipo di effetto
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
	* Metodo per la gestione dell'azione di raccolto per quanto riguarda la singola carta
	* 
	* @param 	player			Giocatore interessato
	* @return 					Nothing
	*/
	
	public void activateHarvest(Player player){
		harvest_effect.activate(player);
		return;
	}
}
