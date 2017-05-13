package it.polimi.ingsw.ps06.model;

import static it.polimi.ingsw.ps06.model.Types.Action;

import java.util.ArrayList;

/**
* Classe per la gestione del palazzo del consiglio
*
* @author  ps06
* @version 1.0
* @since   2017-05-10
*/

public class CouncilPalace implements ActionSpace {
	private ArrayList<FamilyMember> familiari;
	
	
	/**
	* Metodo per il piazzamento di un familiare nel palazzo del consiglio
	*
	* @param 	member			Familiare che si vuole piazzare
	* @param 	chosenAction 	Codice dell'azione da eseguire
	* @return 					Nothing
	*/
	@Override
	public void placeMember(FamilyMember member, Action chosenAction) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	* Costruttore del palazzo del consiglio
	*
	* @param 	numberPlayers	Numero di giocatori della partita
	* @return 	Nothing
	*/
	public void CouncilPalace(){
		
	}
	
	/**
	* Metodo per valutare l'ordine dei giocatori per la prossima fase di gioco
	*
	* @return 	familiari	array di familiari prima della pulizia
	*/
	public ArrayList<FamilyMember> checkOrder(){
		return familiari;
	}
	
	/**
	* Metodo per assegnare le risorse che sono state scelte dal giocatore
	*
	* @param 	player			Giocatore a cui dare le risorse
	* @param 	chosenAction	Codice dell'azione da eseguire	
	* @return 					Nothing
	*/
	public void giveResources(Player player, Action chosenAction){
		
	}
	
	/**
	* Metodo per ripulire i familiari che sono stati allocati in questa zona, metodo da utilizzare DOPO averne valutato l'ordine
	*
	* @param 	Unused
	* @return 	Nothing
	*/
	public void cleanPalace(){
		
	}
}
