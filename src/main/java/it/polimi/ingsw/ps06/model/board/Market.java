package it.polimi.ingsw.ps06.model.board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps06.model.FamilyMember;
import it.polimi.ingsw.ps06.model.Player;
import it.polimi.ingsw.ps06.model.Resources;
import it.polimi.ingsw.ps06.model.Types;
import it.polimi.ingsw.ps06.model.Types.Action;
import it.polimi.ingsw.ps06.model.Types.MaterialsKind;
import it.polimi.ingsw.ps06.model.Types.PointsKind;
import it.polimi.ingsw.ps06.model.XMLparser.ParserBonusBoard;
import it.polimi.ingsw.ps06.model.bonus_malus.BonusMalusNoMarket;
import it.polimi.ingsw.ps06.model.effects.Effect;
import it.polimi.ingsw.ps06.model.effects.EffectsResources;
import it.polimi.ingsw.ps06.networking.SocketServer;
import it.polimi.ingsw.ps06.networking.messages.MessageBoardMemberHasBeenPlaced;
import it.polimi.ingsw.ps06.networking.messages.MessageModel2ViewNotification;

/**
* Classe per la gestione dello spazio di mercato
*
* @author  ps06
* @version 1.2
* @since   2017-05-10 
*/
public class Market extends Observable implements PlaceSpace {
	
	private FamilyMember[] memberSpaces;
	private int openedWindows;
	private int marketIndex = Action.valueOf("MARKET_1").ordinal();
	
	
	@Override
	public void placeMember(FamilyMember member, Action chosenAction, int servants) throws IllegalArgumentException {
		
		boolean multi = false; //attivi.getMulti();
		int relativeIndex = chosenAction.ordinal() - marketIndex;
		
		// Gestione carta scomunica del mercato
		if (member.getPlayer().getBonusMalusCollection().contains(BonusMalusNoMarket.class)) {
			handleBadPlacing(3, member);
			return;
		}
		
		// Gestione condizione di selezione sbagliata
		if ( ( relativeIndex > openedWindows ) || ( (member.getValue() + servants) < 1 ) ) {
			handleBadPlacing(1, member);
			return;
		}
			
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

			} else handleBadPlacing(2, member);

		}
	}
	
	/**
	 * Costruttore del mercato. Si occupa di impostarlo in maniera adeguata
	 *
	 * @param 	numberPlayers	numero di giocatori della partita
	 */
	public Market(int numberPlayers) 
	{	
		memberSpaces = new FamilyMember[4];
		
		setSpaces(numberPlayers);
	}
	
	@Override
	public void handleBadPlacing(int code, FamilyMember member) {
		
		String notification = "Il giocatore " + member.getPlayer().getColorAssociatedToID() + " ha piazzato un familiare ";
		
		switch(code) {
		case 1: notification += " di valore non sufficiente per l'azione";
			break;
		case 2: notification = ", ma non rispetta le regola del colore";
			break;
		case 3: notification = ", ma è stato precedentemente scomunicato";
			break;
		default: notification = "UNKNOWN ERROR ON MARKET";
		}
		
		MessageModel2ViewNotification m = new MessageModel2ViewNotification(notification);
		notifyChangement(m);
	}
	
	/**
	 * Metodo per rendere disponibli solo il numero di finestre di cui si hanno bisogno, a seconda del numero di giocatori
	 *
	 * @param	numberPlayers				numero di giocatori della partita
	 * 
	 * @throws 	IllegalArgumentException	se il numero di giocatori passato non ì compatibile con il model
	 */
	private void setSpaces(int numberPlayers) 
	{	
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
	 * Metodo per assegnare le risorse ai giocatori
	 *
	 * @param 	member			familiare che è stato piazzato nel mercato
	 * @param	chosenAction	tipologia di azione
	 */
	private void giveBonus(FamilyMember member, Action chosenAction) {
		
		ParserBonusBoard p = new ParserBonusBoard("resources/XML/BonusTabellone.xml");
		Resources r = p.getBonusRescourcesForActionSpace(chosenAction);
		
		if ( r != null)
			(new EffectsResources( r )).activate( member.getPlayer() );
		
		//Tell the view what happened
		MessageBoardMemberHasBeenPlaced newMember 
				= new MessageBoardMemberHasBeenPlaced(chosenAction, member.getColor(), member.getPlayer().getID() );
		notifyChangement(newMember);
		
		//Tell the model what happened
		Integer i = 1;
		notifyChangement(i);
	}
	
	/**
	 * Metodo per rimuovere i familiari dagli spazi
	 */
	public void cleanMarket() {
		for (int i=0; i < 4; i++) memberSpaces[i] = null;
	}
	
	/**
	 * Getter l'arraylist di familiari
	 *
	 * @return 	familiare piazzati nel mercato
	 */
	public ArrayList<FamilyMember> getFamilyList(){
		return new ArrayList<FamilyMember>(Arrays.asList(memberSpaces));
	}
	
	/**
	 * Getter per uno specifico posto nell'insieme dei familiari piazzati nel Mercato
	 * 
	 * @param	space	spazio azione di cui ottenere il famigliare piazzato
	 * 
	 * @return			il famigliare piazzato
	 */
	public FamilyMember getFamilySpace(Action space) {
		return ( this.memberSpaces[ space.ordinal() - marketIndex ] );
	}
	
	/**
	 * Setter per uno specifico posto nell'insieme dei familiari piazzati nel Mercato
	 * 
	 * @param 	space		spazio azione di cui ottenere il famigliare piazzato
	 * @param 	member		familiare da piazzare
	 */
	public void setFamilySpace(Action space, FamilyMember member) {
		this.memberSpaces[ space.ordinal() - marketIndex ] = member;
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
