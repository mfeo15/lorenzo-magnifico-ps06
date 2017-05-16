package it.polimi.ingsw.ps06.model;

import java.util.ArrayList;

import it.polimi.ingsw.ps06.model.Types.Action;
import it.polimi.ingsw.ps06.model.Types.MaterialsKind;
import it.polimi.ingsw.ps06.model.Types.PointsKind;

/**
* Classe per la gestione dello spazio di mercato
*
* @author  ps06
* @version 1.1
* @since   2017-05-10 
*/

public class Market implements PlaceSpace {
	private ArrayList<FamilyMember> memberSpaces;
	private ArrayList<Effect> bonus;
	EffectsActive attivi;
	int openedWindows;
	
	/**
	* Metodo per il piazzamento di un familiare nel mercato, include controlli di posizionamento
	*
	* @param 	member			Familiare che si vuole piazzare
	* @param 	chosenAction 	Codice dell'azione da eseguire
	* @return 					Nothing
	*/
	@Override
	public void placeMember(FamilyMember member, Action chosenAction) throws IllegalStateException {
		boolean multi = attivi.getMulti();
		//int actionIndex = Action.MARKET_1.getIndex();
		int actionIndex = Action.valueOf("MARKET_1").ordinal();
		int memberIndex = 0;
		
		
		if(chosenAction==Action.getAction(actionIndex)){
			if(memberSpaces.get(memberIndex) == null || multi ){
				memberSpaces.add(memberIndex, member); 
			} else throw new IllegalStateException();
		}
		
		
		if(chosenAction==Action.MARKET_2){
			if(memberSpaces.get(1) == null || multi){
				memberSpaces.add(1, member); 
			} else throw new IllegalStateException();
		}
		
		
		if(chosenAction==Action.MARKET_3){
			if(memberSpaces.get(2) == null || multi){
				memberSpaces.add(2, member); 
			} else throw new IllegalStateException();
		}
		
		
		if(chosenAction==Action.MARKET_4){
			if(memberSpaces.get(3) == null || multi){
				memberSpaces.add(3, member); 
			} else throw new IllegalStateException();
		}
		
		
		if(chosenAction==Action.MARKET_5){
			if(memberSpaces.get(4) == null || multi){
				memberSpaces.add(4, member); 
			} else throw new IllegalStateException();
		}
			
	}
	
	/**
	* Costruttore del mercato. Si occupa di impostarlo in maniera adeguata
	*
	* @param 	numberPlayers	Numero di giocatori della partita
	* @return 	Nothing
	*/
	public Market(int numberPlayers) {
		
		attivi = new EffectsActive();
		bonus = new ArrayList();
		setSpaces(numberPlayers);
		initBonus(numberPlayers);
		
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
		case 4:
			openedWindows = 4;
		case 5:
			openedWindows = 5;
		default:
			throw new IllegalArgumentException("Il numero di giocatori non è accettato");
		}
		
	/*	
		memberSpaces.add(new FamilyMember());
		memberSpaces.add(new FamilyMember());
		
		if(numberPlayers>3){
			memberSpaces.add(new FamilyMember());
			memberSpaces.add(new FamilyMember());
		
			if(numberPlayers>5){
				memberSpaces.add(new FamilyMember());
			}
		}
	*/
	
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
			bonus.add(new EffectsActions());
			
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
	* Metodo per reimpostare i familiari alla loro posizione di partenza
	*
	* @author  ps06
	* @version 1.0
	* @since   2017-05-11 
	*/
	public void cleanMarket() {
		memberSpaces.clear();
	}
	
	
}
