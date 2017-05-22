package it.polimi.ingsw.ps06.model;

import it.polimi.ingsw.ps06.model.Types.Action;

/**
* Classe per la gestione delle azioni che comportano un acquisizione di una carta
*
* @author  ps06
* @version 1.0
* @since   2017-05-16
*/
public class CardAcquisition extends Actions{

	private DevelopementCard card;

	
	public CardAcquisition(DevelopementCard card){
		this.card=card;
	}

	/**
	* Metodo per verificare i costi dovuti all'azione che comporta l'acquisizione di una carta
	*
	* @param 	player			Giocatore su cui fare la verifica della disponibilità di risorse
	* @param 	chosenAction	Codice dell'azione da eseguire	
	* @return 	
	*/
	public boolean checkCosts(Player player, Action chosenAction){
		//chiama risorse
		return true;
	}
	
	/**
	* Metodo per verificare i costi dovuti all'azione che comporta l'acquisizione di una carta
	*
	* @param 	player			Giocatore su cui fare la verifica della disponibilità di risorse
	* @param 	chosenAction	Codice dell'azione da eseguire	
	* @return 	
	*/
	public boolean checkRequirements(Player player, Action chosenAction){
		//chiama risorse
		return true;
	}
	

	/**
	* Metodo per assegnare la carta ad un giocatore
	*
	* @param 	player			Giocatore su cui fare la verifica della disponibilità di risorse
	* @return 	
	*/
	@Override
	public void activate(Player p) {
		// TODO Auto-generated method stub
		
	}

}