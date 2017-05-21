package it.polimi.ingsw.ps06.model;

/**
* Classe per la gestione delle carte di tipo impresa
*
* @author  ps06
* @version 1.1
* @since   2017-05-11
*/
public class Venture extends DevelopementCard {
	private EffectsResources effect;


	/**
	* Metodo per la gestione degli effetti finali di aggiunta punti vittoria
	* 
	* @param 	player			Giocatore interessato
	* @return 					Nothing
	*/
	
	public void finalEffect(Player player){	
		this.activateEffect(player);
	}
	
	/**
	* Metodo per l'attivazione dell'effetto
	* 
	* @param 	player			Giocatore interessato
	* 
	* @return 					Nothing
	*/
	
	public void activateEffect(Player player){
		effect.activate(player);
	}
	
}
