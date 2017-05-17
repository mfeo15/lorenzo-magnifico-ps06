package it.polimi.ingsw.ps06.model;

import java.util.ArrayList;

import it.polimi.ingsw.ps06.model.Types.Action;

/**
* Classe per la gestione delle torri
*
* @author  ps06
* @version 1.1
* @since   2017-05-10
*/

public class Towers implements PlaceSpace {
	private ArrayList<Territory> territories;
	private ArrayList<Building> buildings;
	private ArrayList<Character> characters;
	private ArrayList<Venture> ventures;
	
	private ArrayList<FamilyMember> memberSpaces;
	private ArrayList<DevelopementCard> deck;
	
	private static final int CARTE_TORRE=16;
	EffectsActive attivi; // da modificare in listener
	int deckIndex=0;
	
	/**
	* Metodo per il piazzamento di un familiare su di una delle torri, include controlli di posizionamento
	*
	* @param 	member			Familiare che si vuole piazzare
	* @param 	chosenAction 	Codice dell'azione da eseguire
	* @return 					Nothing
	*/
	
	@Override
	public void placeMember(FamilyMember member, Action chosenAction) {
		boolean multi = attivi.getMulti();
		int towerIndex = Action.valueOf("TOWER_GREEN_1").ordinal();
		int actionIndex = chosenAction.ordinal();
		int relativeIndex = actionIndex - towerIndex;
		
		
		if(memberSpaces.get(relativeIndex) == null || multi){
			memberSpaces.add(relativeIndex, member); 
			getCard(chosenAction);
		} else throw new IllegalStateException("La torre non è accessibile.");
	
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
	
	/**
	* Metodo per il posizionamento delle 16 carte sulle varie torri
	*
	* @param 	Unused
	* @return 	Nothing
	*/
	public DevelopementCard getCard(Action chosenAction){
		
		int position = chosenAction.ordinal();
		DevelopementCard card = deck.get((position + deckIndex)-1);
		return card;
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
	
}
