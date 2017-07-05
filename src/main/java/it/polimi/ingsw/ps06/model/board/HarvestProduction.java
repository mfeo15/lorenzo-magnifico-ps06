package it.polimi.ingsw.ps06.model.board;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps06.model.FamilyMember;
import it.polimi.ingsw.ps06.model.Gathering;
import it.polimi.ingsw.ps06.model.Types.Action;
import it.polimi.ingsw.ps06.model.effects.EffectsBonusMalus;
import it.polimi.ingsw.ps06.networking.messages.MessageBoardMemberHasBeenPlaced;
import it.polimi.ingsw.ps06.networking.messages.MessageModel2ViewNotification;

/**
 * Classe per la gestione degli spazi produzione e raccolto
 *
 * @author  ps06
 * @version 1.3
 * @since   2017-05-10
 */
public class HarvestProduction extends Observable implements PlaceSpace {
	private ArrayList<FamilyMember> harvestSpaces1;;
	private ArrayList<FamilyMember> harvestSpaces2;
	private ArrayList<FamilyMember> productionSpaces1;
	private ArrayList<FamilyMember> productionSpaces2;
	private EffectsBonusMalus attivi;
	private Gathering gathering;
	private int numberPlayers;
	private int hs2index=0, ps2index=0;
	
	/**
	 * Metodo per il piazzamento di un familiare su di una zona Produzione o Raccolto, include controllo di posizionamento
	 *
	 * @param 	member			Familiare che si vuole piazzare
	 * @param 	chosenAction 	Codice dell'azione da eseguire
	 * @return 					Nothing
	 */
	@Override
	public void placeMember(FamilyMember member, Action chosenAction, int servants) throws IllegalStateException {
		boolean multi = false;
		boolean condition; //Regola del colore
		
		int memberValue = member.getValue() + servants;
		if (member.getPlayer().getBonusMalusCollection().contains(chosenAction.getActionCategory())) 
			memberValue += member.getPlayer().getBonusMalusCollection().getBonusMalus(chosenAction.getActionCategory()).getValue();
		
		// Gestione condizioni della PRIMA casella di Harvest
		if(chosenAction == Action.HARVEST_1) {
			
			if (memberValue < 1) {
				handle(1, member);
				return;
			}
			
			if( (harvestSpaces1.get(0) == null) || (harvestSpaces1.get(0) != null && harvestSpaces1.get(1) == null && numberPlayers>4) ){
				harvestSpaces1.add(0,member); 
				startGathering(chosenAction, member, servants);
			} 
			else {
			
				// Gestione attributo azione multipla del leader
				if(multi){
					ArrayList<FamilyMember> allHarvest = new ArrayList<FamilyMember>(harvestSpaces1);
					allHarvest.addAll(harvestSpaces2);
					
					// Iterazione arrayList di raccolto nel caso l'effetto multi è attivo
					condition = true;
					for(FamilyMember family : allHarvest){
						if(member.getFakePlayer() == family.getFakePlayer()){
							condition=false;
						}
					}
					
					// Caso in cui il familiare sia neutro
					if(member.getFakePlayer()==null) condition=true;
				
					// Aggiunta e produzione nel caso la regola del colore sia rispettata
					if(condition){
						harvestSpaces1.add(1,member); 
						startGathering(chosenAction, member, servants);
						allHarvest.clear();
					}
					
				}
			}
		}
		
		// Gestione condizioni della SECONDA casella di Harvest
		if(chosenAction==Action.HARVEST_2) {
			
			if ((memberValue - 3) < 1) {
				handle(1, member);
				return;
			}
				
			memberValue -= 3;
			if(numberPlayers>2) {
				ArrayList<FamilyMember> allHarvest = new ArrayList<FamilyMember>(harvestSpaces1);
				allHarvest.addAll(harvestSpaces2);
				
				// Iterazione arrayList di raccolto
				condition = true;
				for(FamilyMember family : allHarvest){
					if(member.getFakePlayer() == family.getPlayer()){
						condition=false;
					}
				}
			
				// Caso in cui il familiare sia neutro
				if(member.getFakePlayer()==null) condition=true;
				
				// Aggiunta e produzione nel caso la regola del colore sia rispettata
				if(condition){
					harvestSpaces2.add(hs2index, member); 
					hs2index++;
					startGathering(chosenAction, member, servants);
					allHarvest.clear();
				}
				
			} else handle(-1, member);
		}
		
		
		
		// Gestione condizioni della PRIMA casella di Produzione
		if(chosenAction==Action.PRODUCTION_1) {
			
			if ((memberValue - 3) < 1) {
				handle(1, member);
				return;
			}
			
			if( (productionSpaces1.get(0) == null) || (productionSpaces1.get(0) != null && productionSpaces1.get(1) == null && numberPlayers>4) ){
				productionSpaces1.add(0,member);
				startGathering(chosenAction, member, servants);
			} 
			else{
			
				// Gestione attributo azione multipla del leader
				if(multi) {
					
					ArrayList<FamilyMember> allProduction = new ArrayList<FamilyMember>(productionSpaces1);
					allProduction.addAll(productionSpaces2);
					
					// Iterazione arrayList di produzione nel caso l'effetto multi è attivo
					condition = true;
					for(FamilyMember family : allProduction){
						if(member.getFakePlayer() == family.getFakePlayer()){
							condition=false;
						}
					}
					
					// Caso in cui il familiare sia neutro
					if(member.getFakePlayer()==null) condition=true;
					
					// Aggiunta e produzione nel caso la regola del colore sia rispettata
					if(condition){
						productionSpaces1.add(1,member); 
						startGathering(chosenAction, member, servants);
						allProduction.clear();
					}
					
				}
			}
		}
		
		// Gestione condizioni della SECONDA casella di Produzione
		if(chosenAction==Action.PRODUCTION_2) {
			
			if ((memberValue - 3) < 1) {
				handle(1, member);
				return;
			}
			
			memberValue -= 3;
			if(numberPlayers>2){
				
				ArrayList<FamilyMember> allProduction = new ArrayList<FamilyMember>(productionSpaces1);
				allProduction.addAll(productionSpaces2);
				
				// Iterazione arrayList di raccolto
				condition = true;
				for(FamilyMember family : allProduction){
					if(member.getFakePlayer() == family.getFakePlayer()){
						condition=false;
					}
				}
			
				// Caso in cui il familiare sia neutro
				if(member.getFakePlayer()==null) condition=true;
				
				// Aggiunta e produzione nel caso la regola del colore sia rispettata
				if(condition){
					productionSpaces2.add(ps2index, member); 
					ps2index++;
					startGathering(chosenAction, member, servants);
					allProduction.clear();
				}
				
			} else handle(-1, member);
		}
		
	}
	
	/**
	* Costruttore della zona Produzione e Raccolto. Imposta la zona
	*
	* @param 	numberPlayers	Numero di giocatori della partita
	* @return 	Nothing
	*/
	public HarvestProduction(int numberPlayers) {
		
		harvestSpaces1 = new ArrayList<FamilyMember>();
		harvestSpaces2 = new ArrayList<FamilyMember>();
		productionSpaces1 = new ArrayList<FamilyMember>();
		productionSpaces2 = new ArrayList<FamilyMember>();
		
		this.numberPlayers=numberPlayers;
		
		initializeArrayLists();
	}
	
	public void initializeArrayLists(){
		
		for(int j=0; j<10; j++){
			harvestSpaces1.add(null);
			productionSpaces1.add(null);
			}
		
		for(int j=0; j<20; j++){
			harvestSpaces2.add(null);
			productionSpaces2.add(null);
			}
		
	}
	
	/**
	* Gestisci errori di posizionamento familiare
	*
	* @param	code		codice errore
	* @return 	Nothing
	*/
	private void handle(int code, FamilyMember member){
		
		String notification = "Il giocatore " + member.getPlayer().getColorAssociatedToID() + " ha piazzato un familiare ";
		
		switch(code) {
		case 1: notification += " di valore non sufficiente per l'azione";
			break;
		default: notification = "UNKNOWN ERROR ON HARVEST&PRODUCTION";
		}
		
		MessageModel2ViewNotification m = new MessageModel2ViewNotification(notification);
		notifyChangement(m);
	}
	
	/**
	* Metodo per avviare il processo di Raccolto o di Produzione
	*
	* @param 	player			Giocatore che effettua Produzione o Raccolto
	* @param 	chosenAction	Codice dell'azione da eseguire	
	* @return 	
	*/
	private void startGathering(Action chosenAction, FamilyMember member, int servantsUsed) {

		gathering = new Gathering(chosenAction, member, servantsUsed);
		gathering.activate();
		
		//Tell the view what happened
		MessageBoardMemberHasBeenPlaced newMember = new MessageBoardMemberHasBeenPlaced(chosenAction, member.getColor(), member.getPlayer().getID() );
		notifyChangement(newMember);
		
		//Tell the model what happened
		Integer i = 1;
		notifyChangement(i);
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
		
		initializeArrayLists();
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
	
	
	public void notifyChangement(Object o) {
		setChanged();
		notifyObservers(o);
	}
	
	public void addNewObserver(Observer obs) {
		addObserver(obs);
	}
	
	public void deleteAnObserver(Observer obs) {
		deleteObserver(obs);
	}
}
