package it.polimi.ingsw.ps06.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps06.SocketServer;
import it.polimi.ingsw.ps06.model.Types.Action;
import it.polimi.ingsw.ps06.model.Types.MaterialsKind;
import it.polimi.ingsw.ps06.model.Types.PointsKind;
import it.polimi.ingsw.ps06.model.messages.MessageBoardMemberHasBeenPlaced;
import it.polimi.ingsw.ps06.model.messages.MessageModel2ViewNotification;

/**
* Classe per la gestione dello spazio di mercato
*
* @author  ps06
* @version 1.2
* @since   2017-05-10 
*/

public class Market extends Observable implements PlaceSpace {
	private FamilyMember[] memberSpaces;
	private ArrayList<Effect> bonus = new ArrayList<Effect>();
	private Effect e;
	private int openedWindows;
	private int marketIndex = Action.valueOf("MARKET_1").ordinal();
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
		
		boolean multi = false; //attivi.getMulti();
		int relativeIndex = chosenAction.ordinal() - marketIndex;
		int errorCode;
		
		// Gestire carta scomunica del mercato if(EffectsActive.checkNoMarket == true) handle();
		
		// Gestione condizione di selezione sbagliata
		if ( (relativeIndex > openedWindows) || (member.getValue()<1) ) {errorCode=1; handle(errorCode);}
		
		else{
			
			
			// Gestione condizione base in cui il campo è vuoto
			if(memberSpaces[relativeIndex] == null){
				memberSpaces[relativeIndex] = member;
				giveBonus(member, chosenAction);
					
			} 
			else  {
					
				// Gestione regola del colore in caso di bonus attivo di azione multipla
				if( multi && (member.getFakePlayer() != memberSpaces[relativeIndex].getFakePlayer()) ){
							
					// Solo i familiari non neutri contano al fine di non ripetere familiari dello stesso colore nello stesso spazio
					if(member.getFakePlayer()!=null) { memberSpaces[relativeIndex] = member;}
					giveBonus(member, chosenAction);
					
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
		
		memberSpaces = new FamilyMember[4];
		bonus = new ArrayList<Effect>();
		
		setSpaces(numberPlayers);
		initBonus(numberPlayers);
	}
	
	/**
	* Gestisci errori di posizionamento familiare
	*
	* @param	code		codice errore
	* @return 	Nothing
	*/
	private void handle(int code) {
		
		String notification = "";
		
		switch(code) {
		case 1: notification = "BROKEN MODEL FOR MARKET! THE MEMBER VALUE IS INCORRECT OR THE ACTION INDEX IS NOT COMPATIBLE";
			break;
		case 2: notification = "THE CHOSEN ACTION SPACE IS ALREADY OCCUPAYED OR NOT COMPATIBLE WITH THE COLOR RULE";
			break;
		default: notification = "UNKNOWN ERROR ON MARKET";
		}
		
		MessageModel2ViewNotification m = new MessageModel2ViewNotification(notification);
		notifyChangement(m);
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
	private void giveBonus(FamilyMember member, Action chosenAction) {
		
		Player player = member.getPlayer();
		
		System.out.println("Player" + player.getID() + " has"
				+ "\nCOINS:" + player.getPersonalBoard().getMaterialsCount(MaterialsKind.COIN) 
				+ "\nWOOD:" + player.getPersonalBoard().getMaterialsCount(MaterialsKind.WOOD)
				+ "\nSTONE:" + player.getPersonalBoard().getMaterialsCount(MaterialsKind.STONE)
				+ "\nSERVANTS:" + player.getPersonalBoard().getMaterialsCount(MaterialsKind.SERVANT));
		
		System.out.println("ACTIVATE EFFECT!!!!!");
		
		e = bonus.get( chosenAction.ordinal() - marketIndex );
		e.activate(player);
		
		System.out.println("Player" + player.getID() + " has"
				+ "\nCOINS:" + player.getPersonalBoard().getMaterialsCount(MaterialsKind.COIN) 
				+ "\nWOOD:" + player.getPersonalBoard().getMaterialsCount(MaterialsKind.WOOD)
				+ "\nSTONE:" + player.getPersonalBoard().getMaterialsCount(MaterialsKind.STONE)
				+ "\nSERVANTS:" + player.getPersonalBoard().getMaterialsCount(MaterialsKind.SERVANT));
		
		if( (chosenAction.ordinal() - marketIndex) == 3 ) e.activate(player);
		
		MessageBoardMemberHasBeenPlaced newMember = new MessageBoardMemberHasBeenPlaced(chosenAction, member.getColor(), player.getID() );
		notifyChangement(newMember);
	}
	
	/**
	* Metodo per rimuovere i familiari dagli spazi
	*
	* @return 	Nothing
	*/
	public void cleanMarket() {
		for (int i=0; i < 4; i++) memberSpaces[i] = null;
	}
	
	/**
	* Metodo per ritornare l'arraylist di familiari
	*
	* @return 	memberSpaces	ArrayList familiari
	*/
	public ArrayList<FamilyMember> getFamilyList(){
		//return memberSpaces;
		
		return new ArrayList<FamilyMember>(Arrays.asList(memberSpaces));
	}
	
	/**
	* Metodo per ritornare l'arraylist di bonus
	*
	* @return 	memberSpaces	ArrayList bonus
	*/
	public ArrayList<Effect> getBonuses(){
		return bonus;
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
