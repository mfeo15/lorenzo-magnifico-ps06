package it.polimi.ingsw.ps06.model;

import java.util.ArrayList;
import it.polimi.ingsw.ps06.model.Types.Action;

/**
* Classe per la gestione degli spazi produzione e raccolto
*
* @author  ps06
* @version 1.1
* @since   2017-05-10
*/

public class HarvestProduction implements PlaceSpace {
	private ArrayList<FamilyMember> harvestSpaces1;
	private ArrayList<FamilyMember> harvestSpaces2;
	private ArrayList<FamilyMember> productionSpaces1;
	private ArrayList<FamilyMember> productionSpaces2;
	EffectsActive attivi;
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
		boolean multi = attivi.getMulti();
		int errorCode = 0;
		
		// Gestione condizioni della PRIMA casella di Harvest
		if(chosenAction==Action.HARVEST_1 && member.getValue()> 1){
			
			if( (harvestSpaces1.get(0) == null) || (harvestSpaces1.get(0) != null && harvestSpaces1.get(1) == null && numberPlayers>4) ){
				harvestSpaces1.add(member); 
				startGathering(member.getValue(), chosenAction);
			} 
			
			// Gestione attributo azione multipla del leader
			if(multi){
				for(FamilyMember family : harvestSpaces1)
					if(member.getPlayer() != family.getPlayer()){
						harvestSpaces1.add(member); 
						startGathering(member.getValue(), chosenAction);
					}
				
			}
		}
		
		// Gestione condizioni della SECONDA casella di Harvest
		if(chosenAction==Action.HARVEST_2 && member.getValue()> 4){
			if(numberPlayers>2){
				harvestSpaces2.add(member);
				startGathering(member.getValue() - 3, chosenAction);
			}
		}
		
		
		
		// Gestione condizioni della PRIMA casella di Produzione
		if(chosenAction==Action.PRODUCTION_1 && member.getValue()> 1){
			
			if( (productionSpaces1.get(0) == null) || (productionSpaces1.get(0) != null && productionSpaces1.get(1) == null && numberPlayers>4) ){
				productionSpaces1.add(member);
				startGathering(member.getValue(), chosenAction);
			} 
			
			// Gestione attributo azione multipla del leader
			if(multi){
				for(FamilyMember family : productionSpaces1)
					if(member.getPlayer() != family.getPlayer()){
						productionSpaces1.add(member);
						startGathering(member.getValue(), chosenAction);
					}
				
			}
		}
		
		// Gestione condizioni della SECONDA casella di Produzione
		if(chosenAction==Action.PRODUCTION_2 && member.getValue()> 4){
			if(numberPlayers>2){
				productionSpaces2.add(member);
				startGathering(member.getValue() - 3, chosenAction);
			} else handle(errorCode);
		}
		
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
	* Gestisci errori di posizionamento familiare
	*
	* @param	code		codice errore
	* @return 	Nothing
	*/
	private void handle(int code){
		
	}
	
	/**
	* Metodo per avviare il processo di Raccolto o di Produzione
	*
	* @param 	player			Giocatore che effettua Produzione o Raccolto
	* @param 	chosenAction	Codice dell'azione da eseguire	
	* @return 	
	*/
	public void startGathering(int value, Action chosenAction){

		//PersonalBoard.startGathering();
	}
	
	/**
	* Metodo per ripulire i familiari allocati in queste zone
	*
	* @param 	Unused
	* @return 	Nothing
	*/
	public void cleanZone(){
		harvestSpaces1.clear();
		harvestSpaces2.clear();
		productionSpaces1.clear();
		productionSpaces2.clear();
	}
	
}
