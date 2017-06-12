package it.polimi.ingsw.ps06.model;



/**
* Classe per la gestione delle carte di tipo territorio
*
* @author  ps06
* @version 1.1
* @since   2017-05-11
*/
public class Territory extends DevelopementCard {
	
	private int dice_requirement;
	
	
	/**Metodo per settare il requisito di dado
	 * @param	code	periodo della carta
	 * @return	nothing
	 */
 
	public void setDiceReq(int dice_requirement){
		this.dice_requirement=dice_requirement;
		return;
 	}	
		
	
	/**
	* Metodo per controllare che il valore del familiare sia sufficiente per avere bonus
	* @return 	Boolean
	**/
	
	public boolean check_dice(int dice){
		if( dice >= dice_requirement)
			return true;
		else
			return false;
	}
}
