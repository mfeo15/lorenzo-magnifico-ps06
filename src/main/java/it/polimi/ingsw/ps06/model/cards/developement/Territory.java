package it.polimi.ingsw.ps06.model.cards.developement;



/**
* Classe per la gestione delle carte di tipo territorio
*
* @author  ps06
* @version 1.1
* @since   2017-05-11
*/
public class Territory extends DevelopementCard {
	
	private int dice_requirement;
	
	/**
	 * Setter per il requisito di dado per eseguire un raccolto
	 * 
	 * @param	dice_requirement	valore del requisito da settare
	 */
	public void setDiceReq(int dice_requirement) {
		this.dice_requirement=dice_requirement;
 	}	
		
	/**
	 * Metodo per controllare che il valore del familiare sia sufficiente 
	 * ad attivare il l'effetto permanente
	 * 
	 * @param	dice	valore del familiare da verificare
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
