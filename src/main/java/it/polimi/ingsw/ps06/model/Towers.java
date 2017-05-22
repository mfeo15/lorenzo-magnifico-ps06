package it.polimi.ingsw.ps06.model;

import java.util.ArrayList;

import it.polimi.ingsw.ps06.model.Types.Action;

/**
* Classe per la gestione delle torri
*
* @author  ps06
* @version 1.2
* @since   2017-05-10
*/

public class Towers implements PlaceSpace {
	private ArrayList<Territory> territories;
	private ArrayList<Building> buildings;
	private ArrayList<Character> characters;
	private ArrayList<Venture> ventures;
	
	private ArrayList<FamilyMember> memberSpaces;
	private ArrayList<DevelopementCard> deck;
	private CardAcquisition acquire;
	
	private static final int CARTE_TORRE=16;
	private EffectsActive attivi; // da modificare in listener
	private int deckIndex=0;
	private int tower=0;
	private int range1=0, range2=0;
	private int errorCode=0;
	
	/**
	* Metodo per il piazzamento di un familiare su di una delle torri, include controlli di posizionamento
	*
	* @param 	member			Familiare che si vuole piazzare
	* @param 	chosenAction 	Codice dell'azione da eseguire
	* @return 					Nothing
	*/
	
	@Override
	public void placeMember(FamilyMember member, Action chosenAction) {
		int towerIndex = Action.valueOf("TOWER_GREEN_1").ordinal();
		int actionIndex = chosenAction.ordinal();
		int relativeIndex = actionIndex - towerIndex;
		int arrayIndex;
		boolean condition; // Regola del colore
		
		
		if(memberSpaces.get(relativeIndex) == null){
			
			checkWhichTower(chosenAction);
			arrayIndex=range1;
			condition=true;
			
			while(arrayIndex<range2){
				if(member.getPlayer() == memberSpaces.get(arrayIndex).getPlayer()){
					condition = false;
				}
			}
			
			// Caso in cui il familiare sia neutro
			if(member.getPlayer()==null) condition=true;
			
			if(!condition)handle(errorCode);
			else {
				boolean satisfied1 = acquire.checkCosts(member.getPlayer(), chosenAction);
				boolean satisfied2 = acquire.checkRequirements(member.getPlayer(), chosenAction);
				
				if(satisfied1 && satisfied2){
					memberSpaces.add(relativeIndex, member); 
					getCard(member.getPlayer(), chosenAction);
				}
			}
			
		} else handle(errorCode);
	}
	
	/**
	* Costruttore delle torri. Si occupa di disporre le carte
	*
	* @param 	deck		Mazzo di carte mischiato
	* @return 	Nothing
	*/
	public Towers(ArrayList<DevelopementCard> deck) {
		this.deck=deck;
	}
	
	private void checkWhichTower(Action chosenAction){
		
		switch(chosenAction){
			case TOWER_GREEN_1:
			case TOWER_GREEN_2:
			case TOWER_GREEN_3:
			case TOWER_GREEN_4:
				tower=1;
				range1=0;
				range2=3;
				break;
			case TOWER_BLUE_1:
			case TOWER_BLUE_2:
			case TOWER_BLUE_3:
			case TOWER_BLUE_4:
				tower=2;
				range1=4;
				range2=7;
				break;
			case TOWER_YELLOW_1:
			case TOWER_YELLOW_2:
			case TOWER_YELLOW_3:
			case TOWER_YELLOW_4:
				tower=3;
				range1=8;
				range2=11;
				break;
			case TOWER_PURPLE_1:
			case TOWER_PURPLE_2:
			case TOWER_PURPLE_3:
			case TOWER_PURPLE_4:
				tower=4;
				range1=12;
				range2=15;
				break;
		}
			
	}
	
	/**
	* Metodo per il posizionamento delle 16 carte sulle varie torri
	*
	* @param 	Unused
	* @return 	Nothing
	*/
	private void getCard(Player player, Action chosenAction){
		
		int position = chosenAction.ordinal();
		DevelopementCard card = deck.get((position + deckIndex)-1);
		acquire = new CardAcquisition(card);
		acquire.activate(player);
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
		deckIndex = deckIndex + CARTE_TORRE;
		memberSpaces.clear();
	}
	
	/**
	* Metodo per ritornare l'arraylist di territori
	*
	* @return 	territories	ArrayList territori
	*/
	public ArrayList<Territory> getTerritories(){
		for(int i=0; i<4; i++){
			territories.add((Territory)deck.get(i));
		}
		return territories;
	}
	
	/**
	* Metodo per ritornare l'arraylist di familiari
	*
	* @return 	memberSpaces	ArrayList familiari
	*/
	public ArrayList<FamilyMember> getFamilyList(){
		return memberSpaces;
	}
	
	/**
	* Metodo per ritornare l'arraylist di personaggi
	*
	* @return 	characters	ArrayList personaggi
	*/
	public ArrayList<Character> getCharacters(){
		for(int i=4; i<8; i++){
			characters.add((Character)deck.get(i));
		}
		return characters;
	}
	
	/**
	* Metodo per ritornare l'arraylist di edifici
	*
	* @return 	buildings	ArrayList edifici
	*/
	public ArrayList<Building> getBuildings(){
		for(int i=8; i<12; i++){
			buildings.add((Building)deck.get(i));
		}
		return buildings;
	}
	
	/**
	* Metodo per ritornare l'arraylist di carte impresa
	*
	* @return 	territories	ArrayList imprese
	*/
	public ArrayList<Venture> getVentures(){
		for(int i=12; i<16; i++){
			ventures.add((Venture)deck.get(i));
		}
		return ventures;
	}
	
}
