package it.polimi.ingsw.ps06.model.cards.developement;

import it.polimi.ingsw.ps06.model.Resources;

/**
* Classe per la gestione delle carte di tipo edificio
*
* @author  ps06
* @version 1.1
* @since   2017-05-11
*/
public class Building extends DevelopementCard {
	
	private int dice_requirement;
	private Resources cost;
	
	/**
	 * Setter per il requisito di dado per eseguire una produzione
	 * 
	 * @param	dice_requirement	valore del requisito da settare
	 */
	public void setDiceReq(int dice_requirement){
		this.dice_requirement=dice_requirement;
		return;
 	}	
	
	/**
	 * Setter per il costo della carta
	 * 
	 * @param	cost	costo della carta
	 */
	public void setCost(Resources cost){
		this.cost = cost;
	}	
	
	/**
	 * Getter per il costo della carta
	 * 
	 * @return	costo della carta
	 */
	public Resources getCost(){
	 	return this.cost;
	}
	
	/**
	 * Metodo per controllare che il valore del familiare sia sufficiente 
	 * ad attivare il l'effetto permanente
	 * 
	 * @param	dice	valore del familiare da confrontare
	 * 
	 * @return	true	se il familiare può attivare l'effetto
	 */
	public boolean check_dice(int dice){
		if( dice >= dice_requirement)
			return true;
		else
			return false;
	}
	
}
