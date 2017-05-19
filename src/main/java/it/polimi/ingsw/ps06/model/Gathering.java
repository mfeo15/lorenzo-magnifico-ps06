package it.polimi.ingsw.ps06.model;

import it.polimi.ingsw.ps06.model.Types.Action;

/**
* Classe per la gestione delle azioni di produzione/raccolto
*
* @author  ps06
* @version 1.0
* @since   2017-05-16
*/


public class Gathering extends Actions{
	private int value;
	Action chosenAction;
	
	/**
	* Costruttore della classe
	*
	* @param 	value			Valore dell'azione Produzione/raccolto
	* @param 	chosenAction	Codice dell'azione da eseguire	
	* @return 	
	*/
	public Gathering (Action chosenAction, int value){
		this.chosenAction=chosenAction;
		this.value=value;
		
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
