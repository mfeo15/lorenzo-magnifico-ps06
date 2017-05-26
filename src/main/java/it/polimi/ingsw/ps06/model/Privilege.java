package it.polimi.ingsw.ps06.model;

import it.polimi.ingsw.ps06.model.Types.CouncilPrivilege;

/**
* Classe per la gestione delle azioni di scelta privilegi
*
* @author  ps06
* @version 1.0
* @since   2017-05-16
*/
public class Privilege extends Actions{
	private CouncilPrivilege chosen;
	
	/**
	* Costruttore
	* 	
	*/
	public Privilege(){
		askChoice();
	}
	
	/**
	* Metodo per chiedere la scelta all'utente
	*
	* @return 	
	*/
	public void askChoice(){
		//listener
		//if(...) chosen=BONUS_!;
		
	}
	
	/**
	* Metodo di attivazione dell'azione
	*
	* @param 	p		Giocatore a cui attivare l'azione
	* @return 	
	*/
	@Override
	public void activate(Player p) {
		// TODO Auto-generated method stub
		
	}

}
