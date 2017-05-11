package it.polimi.ingsw.ps06.model;

/**
* Classe per la gestione degli spazi produzione e raccolto
*
* @author  ps06
* @version 1.0
* @since   2017-05-10
*/

public class HarvestProduction implements ActionSpace {

	/**
	* Metodo per il piazzamento di un familiare su di una zona Produzione o Raccolto, include controllo di posizionamento
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
	* Metodo per impostare quali degli spazi sono accessibili in base al numero di giocatori
	*
	* @author  ps06
	* @version 1.0
	* @since   2017-05-11 
	*/
	public static void setSpaces(int giocatori){
		
	}
	
	/**
	* Metodo per avviare il processo di Raccolto o di Produzione
	*
	* @author  ps06
	* @version 1.0
	* @since   2017-05-11 
	*/
	public static void startGathering(Player player, Action chosenAction){
		//chiama risorse
	}
	
	/**
	* Metodo per ripulire i familiari allocati in questa zona
	*
	* @author  ps06
	* @version 1.0
	* @since   2017-05-11 
	*/
	public static void cleanZone(){
		
	}
	
}
