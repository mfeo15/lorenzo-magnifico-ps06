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
	 * Costruttore della classe
	 * 
	 * @param code				codice relativo al tipo di BonusTile creata
	 * @param harvest_bonus		insieme di risorse bonus in caso di raccolto
	 * @param production_bonus	insieme di risorse bonus in caso di produzione
	 * 
	 */
	public BonusTile(int code, Resources harvest_bonus, Resources production_bonus) {
		
		this.code = code;
		this.harvest_bonus = harvest_bonus;
		this.production_bonus = production_bonus;
	}
	
	/**
	 * Getter del codice della BonusTile
	 * 
	 * @return	codice della BonusTiles
	 * 
	 */
	public int getCode() {
		return code;
	}

	/**
	 * Setter del codice della BonusTile
	 * 
	 * @param code	codice della BonusTile da asseggnare
	 * 
	 */
	public void setCode(int code) {
		this.code = code;
	}
	
	/**
	 * Getter della risorsa associata all'azione di raccolto
	 * 
	 * @return	risorsa associata al raccolto
	 * 
	 */
	public Resources getHarvest_bonus() {
		return harvest_bonus;
	}

	/**
	 * Setter della risorsa associata all'azione di raccolto
	 * 
	 * @param	harvest_bonus	risorsa raccolto da settare
	 * 
	 */
	public void setHarvest_bonus(Resources harvest_bonus) {
		this.harvest_bonus = harvest_bonus;
	}
	
	/**
	 * Getter della risorsa associata all'azione di produzione
	 * 
	 * @return	risorsa associata alla produzione
	 * 
	 */
	public Resources getProduction_bonus() {
		return production_bonus;
	}

	/**
	 * Setter della risorsa associata all'azione di produzione
	 * 
	 * @param	harvest_bonus	risorsa produzione da settare
	 * 
	 */
	public void setProduction_bonus(Resources production_bonus) {
		this.production_bonus = production_bonus;
	}
	
	/**
	* Metodo per controllare che il valore del familiare sia sufficiente per avere il bonus
	* 
	* @return	true	se il valore del dado soddisfa il requisito
	* 
	**/
	public boolean check_dice(Dice dice){
		if( dice.getValue() >= dice_required)
			return true;
		else
			return false;
	}
	
	/**
	* Metodo che attiva i bonus produzione e li assegna al giocatore
	* 
	*/
	public void activate_productionBonus(Player player) {
		player.getPersonalBoard().addResource(production_bonus);
	}

	/**
	* Metodo che attiva i bonus produzione e li assegna al giocatore
	* 
	*/
	public void activate_harvestBonus(Player player) {
		player.getPersonalBoard().addResource(harvest_bonus);
	}
	
}
