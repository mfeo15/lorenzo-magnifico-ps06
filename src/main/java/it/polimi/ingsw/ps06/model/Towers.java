package it.polimi.ingsw.ps06.model;

import java.util.ArrayList;
import static it.polimi.ingsw.ps06.model.Types.Action;

/**
* Classe per la gestione delle torri
*
* @author  ps06
* @version 1.0
* @since   2017-05-10
*/

public class Towers implements PlaceSpace {
	private ArrayList<Territory> territories;
	private ArrayList<Building> buildings;
	private ArrayList<Character> characters;
	private ArrayList<Venture> ventures;
	
	/**
	* Metodo per il piazzamento di un familiare su di una delle torri, include controlli di posizionamento
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
	* Costruttore delle torri. Si occupa di disporre le carte
	*
	* @param 	numberPlayers	Numero di giocatori della partita
	* @return 	Nothing
	*/
	public Towers() {
		setCards();
	}
	
	/**
	* Metodo per il posizionamento delle 16 carte sulle varie torri
	*
	* @param 	Unused
	* @return 	Nothing
	*/
	public void setCards(){
		
	}
	
	/**
	* Metodo per verificare i costi dovuti al posizionamento di un familiare sulla torre
	*
	* @param 	player			Giocatore su cui fare la verifica della disponibilità di risorse
	* @param 	chosenAction	Codice dell'azione da eseguire	
	* @return 	
	*/
	public void checkCosts(Player player, Action chosenAction){
		//chiama risorse
	}
	
	/**
	* Metodo per attribuire la carta al giocatore che l'ha scelta con successo
	*
	* @param 	player			Giocatore a cui dare la carta
	* @param 	chosenAction	Codice dell'azione da eseguire	
	* @return 	 
	*/
	public void giveCard(Player player, Action chosenAction){
		
	}
	
	/**
	* Metodo per togliere le carte rimaste sulla torre e i familiari utilizzati
	*
	* @param 	Unused
	* @return 	Nothing
	*/
	public void cleanTowers(){
		
	}
	
}
