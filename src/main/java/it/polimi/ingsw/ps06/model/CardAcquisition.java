package it.polimi.ingsw.ps06.model;

import it.polimi.ingsw.ps06.model.Types.Action;

/**
* Classe per la gestione delle azioni che comportano un acquisizione di una carta
*
* @author  ps06
* @version 1.0
* @since   2017-05-16
*/
public class CardAcquisition {
	private DevelopementCard card;
	
	public CardAcquisition(){
		
	}

	/**
	* Metodo per verificare i costi dovuti all'azione che comporta l'acquisizione di una carta
	*
	* @param 	player			Giocatore su cui fare la verifica della disponibilità di risorse
	* @param 	chosenAction	Codice dell'azione da eseguire	
	* @return 	
	*/
	public void checkCosts(Player player, Action chosenAction){
		//chiama risorse
	}
	
	/**
	* Metodo per verificare i costi dovuti all'azione che comporta l'acquisizione di una carta
	*
	* @param 	player			Giocatore su cui fare la verifica della disponibilità di risorse
	* @param 	chosenAction	Codice dell'azione da eseguire	
	* @return 	
	*/
	public void checkRequirements(Player player, Action chosenAction){
		//chiama risorse
	}
}
