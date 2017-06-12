package it.polimi.ingsw.ps06.model;

import java.util.ArrayList;

import it.polimi.ingsw.ps06.model.Types.Action;
import it.polimi.ingsw.ps06.model.Types.MaterialsKind;
import it.polimi.ingsw.ps06.model.Types.PointsKind;

/**
* Classe per la gestione dello spazio di mercato
*
* @author  ps06
* @version 1.2
* @since   2017-05-10 
*/

public class Market implements PlaceSpace {
	private ArrayList<FamilyMember> memberSpaces;
	private ArrayList<Effect> bonus;
	private Effect e;
	private EffectsActive attivi; // da modificare in listener
	private int openedWindows;
	private boolean check;
	
	/**
	* Metodo per il piazzamento di un familiare nel mercato, include controlli di posizionamento
	*
	* @param 	member			Familiare che si vuole piazzare
	* @param 	chosenAction 	Codice dell'azione da eseguire
	* @return 					Nothing
	*/
	@Override
	public void placeMember(FamilyMember member, Action chosenAction) throws IllegalArgumentException {
		boolean multi = true; //attivi.getMulti();
		int marketIndex = Action.valueOf("MARKET_1").ordinal();
		int actionIndex = chosenAction.ordinal();
		int relativeIndex = actionIndex - marketIndex;
		int errorCode = 0;
		
		// Gestire carta scomunica del mercato if(EffectsActive.checkNoMarket == true) handle();
		
		// Gestione condizione di selezione sbagliata
		if ( (relativeIndex > openedWindows) || (member.getValue()<1) ) {errorCode=1; handle(errorCode);}
		
		else{
			
			
			// Gestione condizione base in cui il campo è vuoto
			if(memberSpaces.get(relativeIndex) == null){
				memberSpaces.add(relativeIndex, member);
				giveBonus(member.getPlayer(), relativeIndex);
					
			} 
			else  {
					
				// Gestione regola del colore in caso di bonus attivo di azione multipla
				if( multi && (member.getPlayer() != memberSpaces.get(relativeIndex).getPlayer()) ){
							
					// Solo i familiari non neutri contano al fine di non ripetere familiari dello stesso colore nello stesso spazio
					if(member.getPlayer()!=null) { memberSpaces.add(relativeIndex, member);}
					giveBonus(member.getPlayer(), relativeIndex);
					
				} else {errorCode=2; handle(errorCode);}
				
			}
		}
	}
	
	/**
	* Costruttore del mercato. Si occupa di impostarlo in maniera adeguata
	*
	* @param 	numberPlayers	Numero di giocatori della partita
	* @return 	Nothing
	*/
	public Market(int numberPlayers) {
		
		//attivi = new EffectsActive();
		bonus = new ArrayList();
		setSpaces(numberPlayers);
		initBonus(numberPlayers);
		
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
	* Metodo per rendere disponibli solo il numero di finestre di cui si hanno bisogno, a seconda del numero di giocatori
	*
	* @param 	numberPlayers	Numero di giocatori della partita
	* @return 	Nothing
	*/
	private void setSpaces(int numberPlayers) {
		// basta restringere le azioni!
		
		switch (numberPlayers) {
		case 2:
		case 3:
			openedWindows = 2;
			break;
		case 4:
			openedWindows = 4;
			break;
		case 5:
			openedWindows = 5;
			break;
		default:
			throw new IllegalArgumentException("Il numero di giocatori non è accettato");
		}
	}
	
	/**
	* Metodo per inizializzare i bonus del mercato
	*
	* @param 	numberPlayers	Numero di giocatori della partita
	* @return 	Nothing
	*/
	private void initBonus(int numberPlayers){
		
		//Creazione del primo bonus del market
		bonus.add(new EffectsResources(new Resources(MaterialsKind.COIN,5)));
		//Creazione del secondo bonus del market
		bonus.add(new EffectsResources(new Resources(MaterialsKind.SERVANT,5)));
		
		if(numberPlayers>3) { 

			//Creazione del terzo bonus del market
			Resources r = new Resources(MaterialsKind.COIN,2);
			r.setResourceValue(PointsKind.MILITARY_POINTS, 3);
			bonus.add(new EffectsResources(r));
			
			//Creazione del quarto bonus del market
			//bonus.add(new EffectsActions(new Privilege())); //privilegi
			
			if(numberPlayers>4) { 
				//Creazione del quinto bonus del market
				r.clearResources();
				r.setResourceValue(PointsKind.FAITH_POINTS,2);
				r.setResourceValue(PointsKind.MILITARY_POINTS, 1);
				bonus.add(new EffectsResources(r));
			}
		}	
	}
	
	/**
	* Metodo per assegnare le risorse ai giocatori
	*
	* @param 	player		Giocatore a cui dare il bonus
	* @return 	Nothing
	*/
	private void giveBonus(Player player, int index){
		e = bonus.get(index);
		e.activate(player);
		if(index==3) e.activate(player);
	}
	
	/**
	* Metodo per rimuovere i familiari dagli spazi
	*
	* @return 	Nothing
	*/
	public void cleanMarket() {
		memberSpaces.clear();
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
	* Metodo per ritornare l'arraylist di bonus
	*
	* @return 	memberSpaces	ArrayList bonus
	*/
	public ArrayList<Effect> getBonuses(){
		return bonus;
	}
	
	
}
