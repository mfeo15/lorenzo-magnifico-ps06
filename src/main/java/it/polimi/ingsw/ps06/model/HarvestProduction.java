package it.polimi.ingsw.ps06.model;

import java.util.ArrayList;
import it.polimi.ingsw.ps06.model.Types.Action;

/**
* Classe per la gestione degli spazi produzione e raccolto
*
* @author  ps06
* @version 1.3
* @since   2017-05-10
*/

public class HarvestProduction implements PlaceSpace {
	private ArrayList<FamilyMember> harvestSpaces1;
	private ArrayList<FamilyMember> harvestSpaces2;
	private ArrayList<FamilyMember> productionSpaces1;
	private ArrayList<FamilyMember> productionSpaces2;
	private EffectsActive attivi;
	private Gathering gathering;
	private int numberPlayers;
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
		boolean condition; //Regola del colore
		
		// Gestione condizioni della PRIMA casella di Harvest
		if(chosenAction==Action.HARVEST_1 && member.getValue()> 1){
			
			if( (harvestSpaces1.get(0) == null) || (harvestSpaces1.get(0) != null && harvestSpaces1.get(1) == null && numberPlayers>4) ){
				harvestSpaces1.add(member); 
				startGathering(member.getValue(), chosenAction, member.getPlayer());
			} 
			else{
			
				// Gestione attributo azione multipla del leader
				if(multi){
					ArrayList<FamilyMember> allHarvest = new ArrayList<FamilyMember>(harvestSpaces1);
					allHarvest.addAll(harvestSpaces2);
					
					// Iterazione arrayList di raccolto nel caso l'effetto multi è attivo
					condition = true;
					for(FamilyMember family : allHarvest){
						if(member.getPlayer() == family.getPlayer()){
							condition=false;
						}
					}
					
					// Caso in cui il familiare sia neutro
					if(member.getPlayer()==null) condition=true;
				
					// Aggiunta e produzione nel caso la regola del colore sia rispettata
					if(condition){
						harvestSpaces1.add(member); 
						startGathering(member.getValue(), chosenAction, member.getPlayer());
						allHarvest.clear();
					}
					
				}
			}
		}
		
		// Gestione condizioni della SECONDA casella di Harvest
		if(chosenAction==Action.HARVEST_2 && member.getValue()> 4){
			if(numberPlayers>2){
				ArrayList<FamilyMember> allHarvest = new ArrayList<FamilyMember>(harvestSpaces1);
				allHarvest.addAll(harvestSpaces2);
				
				// Iterazione arrayList di raccolto
				condition = true;
				for(FamilyMember family : allHarvest){
					if(member.getPlayer() == family.getPlayer()){
						condition=false;
					}
				}
			
				// Caso in cui il familiare sia neutro
				if(member.getPlayer()==null) condition=true;
				
				// Aggiunta e produzione nel caso la regola del colore sia rispettata
				if(condition){
					harvestSpaces2.add(member); 
					startGathering(member.getValue() -3, chosenAction, member.getPlayer());
					allHarvest.clear();
				}
				
			} else handle(errorCode);
		}
		
		
		
		// Gestione condizioni della PRIMA casella di Produzione
		if(chosenAction==Action.PRODUCTION_1 && member.getValue()> 1){
			
			if( (productionSpaces1.get(0) == null) || (productionSpaces1.get(0) != null && productionSpaces1.get(1) == null && numberPlayers>4) ){
				productionSpaces1.add(member);
				startGathering(member.getValue(), chosenAction, member.getPlayer());
			} 
			else{
			
				// Gestione attributo azione multipla del leader
				if(multi){
					
					ArrayList<FamilyMember> allProduction = new ArrayList<FamilyMember>(productionSpaces1);
					allProduction.addAll(productionSpaces2);
					
					// Iterazione arrayList di produzione nel caso l'effetto multi è attivo
					condition = true;
					for(FamilyMember family : allProduction){
						if(member.getPlayer() == family.getPlayer()){
							condition=false;
						}
					}
					
					// Caso in cui il familiare sia neutro
					if(member.getPlayer()==null) condition=true;
					
					// Aggiunta e produzione nel caso la regola del colore sia rispettata
					if(condition){
						productionSpaces1.add(member); 
						startGathering(member.getValue(), chosenAction, member.getPlayer());
						allProduction.clear();
					}
					
				}
			}
		}
		
		// Gestione condizioni della SECONDA casella di Produzione
		if(chosenAction==Action.PRODUCTION_2 && member.getValue()> 4){
			if(numberPlayers>2){
				
				ArrayList<FamilyMember> allProduction = new ArrayList<FamilyMember>(productionSpaces1);
				allProduction.addAll(productionSpaces2);
				
				// Iterazione arrayList di raccolto
				condition = true;
				for(FamilyMember family : allProduction){
					if(member.getPlayer() == family.getPlayer()){
						condition=false;
					}
				}
			
				// Caso in cui il familiare sia neutro
				if(member.getPlayer()==null) condition=true;
				
				// Aggiunta e produzione nel caso la regola del colore sia rispettata
				if(condition){
					productionSpaces2.add(member); 
					startGathering(member.getValue() - 3, chosenAction, member.getPlayer());
					allProduction.clear();
				}
				
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
		this.numberPlayers=numberPlayers;
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
	private void startGathering(int value, Action chosenAction, Player player){
		gathering = new Gathering(chosenAction,value);
		gathering.activate(player);
		
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
	
	/**
	* Metodo per ritornare l'arraylist di familiari nella casella 1 del raccolto
	*
	* @return 	memberSpaces	ArrayList familiari
	*/
	public ArrayList<FamilyMember> getHarvestSpace1(){
		return harvestSpaces1;
	}
	
	/**
	* Metodo per ritornare l'arraylist di familiari nella casella 2 del raccolto
	*
	* @return 	memberSpaces	ArrayList familiari
	*/
	public ArrayList<FamilyMember> getHarvestSpace2(){
		return harvestSpaces2;
	}
	
	/**
	* Metodo per ritornare l'arraylist di familiari nella casella 1 della produzine
	*
	* @return 	memberSpaces	ArrayList bonus
	*/
	public ArrayList<FamilyMember> getProductionSpace1(){
		return productionSpaces1;
	}
	
	/**
	* Metodo per ritornare l'arraylist di familiari nella casella 2 della produzine
	*
	* @return 	memberSpaces	ArrayList bonus
	*/
	public ArrayList<FamilyMember> getProductionSpace2(){
		return productionSpaces1;
	}
	
}
