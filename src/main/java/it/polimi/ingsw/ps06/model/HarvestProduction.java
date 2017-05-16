package it.polimi.ingsw.ps06.model;

import java.util.ArrayList;
import it.polimi.ingsw.ps06.model.Types.Action;

/**
* Classe per la gestione degli spazi produzione e raccolto
*
* @author  ps06
* @version 1.0
* @since   2017-05-10
*/

public class HarvestProduction implements PlaceSpace {
	private ArrayList<FamilyMember> harvestSpaces;
	private ArrayList<FamilyMember> gatherSpaces;
	int numberPlayers;
	/**
	* Metodo per il piazzamento di un familiare su di una zona Produzione o Raccolto, include controllo di posizionamento
	*
	* @param 	member			Familiare che si vuole piazzare
	* @param 	chosenAction 	Codice dell'azione da eseguire
	* @return 					Nothing
	*/
	@Override
	public void placeMember(FamilyMember member, Action chosenAction) throws IllegalStateException {
		
	//condizioni da rivedere, non è sempre l'indice 1	
		
		if(chosenAction==Action.HARVEST_1){
			if(harvestSpaces.get(0) == null || (harvestSpaces.get(1) == null && numberPlayers>4)){
				harvestSpaces.add(member); 
			} else throw new IllegalStateException();
		}
		if(chosenAction==Action.HARVEST_2) if(numberPlayers>2) harvestSpaces.add(member);
		
		
		if(chosenAction==Action.PRODUCTION_1){
			if(gatherSpaces.get(0) == null || (gatherSpaces.get(1) == null && numberPlayers>4)){
				gatherSpaces.add(member); 
			}else throw new IllegalStateException();
		}
		if(chosenAction==Action.PRODUCTION_2) if(numberPlayers>2) gatherSpaces.add(member);
		
	}
	
	/**
	* Costruttore della zona Produzione e Raccolto. Imposta la zona
	*
	* @param 	numberPlayers	Numero di giocatori della partita
	* @return 	Nothing
	*/
	public HarvestProduction(int numberPlayers) {
		setSpaces(numberPlayers);
		this.numberPlayers=numberPlayers;
	}
	
	/**
	* Metodo per impostare quali degli spazi sono accessibili in base al numero di giocatori
	*
	* @param 	giocatori	Numero di giocatori della partita
	* @return 				Nothing
	*/
	public void setSpaces(int numberPlayers){
		// restringi azioni
		
	}
	
	/**
	* Metodo per avviare il processo di Raccolto o di Produzione
	*
	* @param 	player			Giocatore che effettua Produzione o Raccolto
	* @param 	chosenAction	Codice dell'azione da eseguire	
	* @return 	
	*/
	public void startGathering(Player player, Action chosenAction){
		//PersonalBoard.startGathering();
	}
	
	/**
	* Metodo per ripulire i familiari allocati in queste zone
	*
	* @param 	Unused
	* @return 	Nothing
	*/
	public void cleanZone(){
		harvestSpaces.clear();
		gatherSpaces.clear();
	}
	
}
