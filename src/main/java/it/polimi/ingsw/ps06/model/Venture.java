package it.polimi.ingsw.ps06.model;

/**
* Classe per la gestione delle carte di tipo impresa
*
* @author  ps06
* @version 1.0
* @since   2017-05-11
*/
public class Venture extends DevelopementCard {
EffectsResources effect;
	/**
	* Metodo per la gestione degli effetti finali di aggiunta punti vittoria
	* 
	* @param 	player			Giocatore interessato
	* @param 	value			Valore in punti vittoria da aggiungere a fine partita
	* @return 					Nothing
	*/
	public void fianlEffect(Player player, int value){
		
		effect.handleFinalEffects(player, value);
	}
}
