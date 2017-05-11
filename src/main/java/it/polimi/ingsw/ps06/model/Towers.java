package it.polimi.ingsw.ps06.model;

import java.util.ArrayList;

/**
* Classe per la gestione delle torri
*
* @author  ps06
* @version 1.0
* @since   2017-05-10
*/

public class Towers implements ActionSpace {

	private ArrayList<Territory> territories;
	private ArrayList<Building> buildings;
	private ArrayList<Character> characters;
	private ArrayList<Venture> ventures;
	
	
	/**
	* Metodo per il piazzamento di un familiare si di una delle torri, include controlli di posizionamento
	*
	* @author  ps06
	* @version 1.0
	* @since   2017-05-11 
	*/
	
	@Override
	public void placeMember(FamilyMember member, Action chosenAction) {
		// TODO Auto-generated method stub
	}
	
	/**
	* Metodo per il posizionamento delle 16 carte sulle varie torri
	*
	* @author  ps06
	* @version 1.0
	* @since   2017-05-11 
	*/
	public static void setCards(){
		
	}
	
	/**
	* Metodo per verificare i costi dovuti al posizionamento di un familiare sulla torre
	*
	* @author  ps06
	* @version 1.0
	* @since   2017-05-11 
	*/
	public static void checkCosts(Player player, Action chosenAction){
		//chiama risorse
	}
	
	/**
	* Metodo per attribuire la carta al giocatore che l'ha scelta con successo
	*
	* @author  ps06
	* @version 1.0
	* @since   2017-05-11 
	*/
	public static void giveCard(Player player, Action chosenAction){
		
	}
	
	/**
	* Metodo per togliere le carte rimaste sulla torre e i familiari utilizzati
	*
	* @author  ps06
	* @version 1.0
	* @since   2017-05-11 
	*/
	public static void cleanTowers(){
		
	}
	
}
