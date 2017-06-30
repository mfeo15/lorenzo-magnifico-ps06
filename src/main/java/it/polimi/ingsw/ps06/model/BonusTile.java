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
	public BonusTile(int code, Resources harvest_bonus, Resources production_bonus) {
		
		this.code = code;
		this.harvest_bonus = harvest_bonus;
		this.production_bonus = production_bonus;
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	public Resources getHarvest_bonus() {
		return harvest_bonus;
	}

	public void setHarvest_bonus(Resources harvest_bonus) {
		this.harvest_bonus = harvest_bonus;
	}

	public Resources getProduction_bonus() {
		return production_bonus;
	}

	public void setProduction_bonus(Resources production_bonus) {
		this.production_bonus = production_bonus;
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
		player.getPersonalBoard().addResource(production_bonus);
		return;
		}

	/**
	* Metodo che attiva i bonus raccolto e li da al giocatore
	* 
	* @return 	Nothing
	*/
	public void activate_harvestBonus(Player player){
		player.getPersonalBoard().addResource(harvest_bonus);
		return;
	}
	
}
