package it.polimi.ingsw.ps06.model;


/**
 * classe per la definizione e gestione della tessera bonus personale
 * 
 * @author ps06
 * @version 1.1
 * @since 13-05-2017
 */

public class BonusTile {
	
	private int code;
	private Resources harvest_bonus;
	private Resources production_bonus;
	private int dice_required;

	/**
	 * costruttore della classe tabella bonus personale
	 * 
	 * @return 	nothing
	**/
	
	public BonusTile(){
		//da file dovrà ottenere le info da inserire
		harvest_bonus = new Resources();
		production_bonus = new Resources();
		//harvest_bonus.add(valori);  valori da ottenere dal file
		//production_bonus.add(valori);  valori da ottenere dal file
		//this.dice_required=numero; valore da ottenere dal file	
	}
	
	
	/**
	* Metodo per controllare che il valore del familiare sia sufficiente per avere bonus
	* @return 	Boolean
	**/
	
	public boolean check_dice(Dice dice){
		if( dice.getValue() >= dice_required)
			return true;
		else
			return false;
	}
	
	/**
	* Metodo che attiva i bonus produzione e li da al giocatore
	* 
	* @return 	Nothing
	*/
	
	public void activate_productionBonus(Player player){
		player.getPersonalBoard().increaseResources(production_bonus);
		return;
		}

	/**
	* Metodo che attiva i bonus raccolto e li da al giocatore
	* 
	* @return 	Nothing
	*/
	
	public void activate_harvestBonus(Player player){
		player.getPersonalBoard().increaseResources(harvest_bonus);
		return;
	}
	
}
