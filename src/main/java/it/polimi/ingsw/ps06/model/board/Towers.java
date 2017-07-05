package it.polimi.ingsw.ps06.model.board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import it.polimi.ingsw.ps06.model.CardAcquisition;
import it.polimi.ingsw.ps06.model.FamilyMember;
import it.polimi.ingsw.ps06.model.Player;
import it.polimi.ingsw.ps06.model.Resources;
import it.polimi.ingsw.ps06.model.Types;
import it.polimi.ingsw.ps06.model.Types.Action;
import it.polimi.ingsw.ps06.model.Types.MaterialsKind;
import it.polimi.ingsw.ps06.model.Types.PointsKind;
import it.polimi.ingsw.ps06.model.XMLparser.ParserBonusBoard;
import it.polimi.ingsw.ps06.model.XMLparser.ParserCards;
import it.polimi.ingsw.ps06.model.bonus_malus.BonusMalusNoTowersEffects;
import it.polimi.ingsw.ps06.model.cards.developement.Building;
import it.polimi.ingsw.ps06.model.cards.developement.Character;
import it.polimi.ingsw.ps06.model.cards.developement.DevelopementCard;
import it.polimi.ingsw.ps06.model.cards.developement.Territory;
import it.polimi.ingsw.ps06.model.cards.developement.Venture;
import it.polimi.ingsw.ps06.model.effects.EffectsBonusMalus;
import it.polimi.ingsw.ps06.model.effects.EffectsResources;
import it.polimi.ingsw.ps06.networking.messages.MessageBoardMemberHasBeenPlaced;
import it.polimi.ingsw.ps06.networking.messages.MessageBoardSetupDevCards;
import it.polimi.ingsw.ps06.networking.messages.MessageModel2ViewNotification;

/**
 * Classe per la gestione delle torri
 *
 * @author  ps06
 * @version 1.2
 * @since   2017-05-10
 */
public class Towers extends Observable implements PlaceSpace {
	
	private FamilyMember[] memberSpaces;
	private DevelopementCard[] deck;
	
	private static final int CARTE_TORRE = 16;

	private int deckIndex=0;
	private int towerIndex = 0;
	private int baseFloorTower = 0;
	private int topFloorTower = 0;
	
	@Override
	public void placeMember(FamilyMember member, Action chosenAction, int servants) {

		int relativeIndex = chosenAction.ordinal() - Action.valueOf("TOWER_GREEN_1").ordinal();

		boolean value = false;
		boolean colorRule; // Regola del colore
		boolean extraCost; // Familiare avversario presente, +3 oro
		
		int memberValue = member.getValue() + servants;	
		if (member.getPlayer().getBonusMalusCollection().contains(chosenAction.getActionCategory())) 
			memberValue += member.getPlayer().getBonusMalusCollection().getBonusMalus(chosenAction.getActionCategory()).getValue();
		
		// Si può piazzare solo se il valore del membro supera quello richiesto
		if( memberValue < checkRequirement(chosenAction) ) {
			handleBadPlacing(1, member);
			return;
		}
		
		
		DevelopementCard card = deck[deckIndex + relativeIndex];
		
		// Si può piazzare solo se la carta è ancora presente, se c'è sono sicuro che non c'è un familiare
		if(  card == null ) {
			handleBadPlacing(2, member);
			return;
		}
		
		CardAcquisition acquire = new CardAcquisition(card, member.getPlayer(), servants);

		checkWhichTower(chosenAction);

		colorRule = true;
		extraCost = false;
		/*
		int floor = baseFloorTower;
		while(floor <= topFloorTower) {

			// Se è presente un familiare avversario si paga un costo extra
			if(member.getFakePlayer() != null) extraCost = true; 

			// Se è presente un familiare proprio, non è possibile metterne un altro
			if(member.getFakePlayer() == memberSpaces.get(floor).getFakePlayer()) colorRule = false;
			
			floor++;
		}
		
		// Caso in cui il familiare sia neutro
		if(member.getFakePlayer() == null) colorRule=true;
		*/
		// Caso in cui il familiare può essere piazzato
		if(!colorRule) {
			handleBadPlacing(-1, member);
			return;
		}
		
		boolean satisfied1 = acquire.checkCosts(extraCost);
		boolean satisfied2 = acquire.checkRequirements();

		if (satisfied1 && satisfied2) {
			giveBonus(chosenAction, member);
			memberSpaces[relativeIndex] = member; 

			acquire.activate();

			deck[deckIndex + relativeIndex] = null;

			//Tell the view what happened
			MessageBoardMemberHasBeenPlaced newMember = new MessageBoardMemberHasBeenPlaced(chosenAction, member.getColor(), (member.getPlayer()).getID() );
			notifyChangement(newMember);
			
			//Tell the model what happened
			Integer i = 1;
			notifyChangement(i);
			
		} else handleBadPlacing(3, member);
	

		MessageBoardSetupDevCards setupCards = new MessageBoardSetupDevCards( getRoundCards() );
		notifyChangement(setupCards);
	}
	
	@Override
	public void handleBadPlacing(int code, FamilyMember member) {
		
		String notification = "Il giocatore " + member.getPlayer().getColorAssociatedToID() + " ha piazzato un familiare ";
		
		switch(code) {
		case 1: notification += " di valore non sufficiente per l'azione";
			break;
		case 2: notification += ", ma la carta sviluppo non c'era";
			break;
		case 3: notification += ", ma le risorse non sono sufficienti";
			break;
		default: notification = "UNKNOWN ERROR ON TOWERS";
		}
		
		MessageModel2ViewNotification m = new MessageModel2ViewNotification(notification);
		notifyChangement(m);
	}
	
	/**
	 * Costruttore delle torri. Si occupa di disporre le carte
	 */
	public Towers() {
		
		memberSpaces = new FamilyMember[CARTE_TORRE];
		
		this.deck = shuffleDeck( (new ParserCards("resources/XML/DevelopementCards.xml")).getCards() );
		
		this.deckIndex = - CARTE_TORRE;
	}

	
	/**
	 * Metodo per il mescolamento delle carte, 
	 * la suddivisione in turno e preparazione completa del deck
	 * 
	 * @param	deck	insieme di carte su cui operare
	 * 
	 * @return			insieme mescolato e prento da usare
	 */
	private DevelopementCard[] shuffleDeck( ArrayList<DevelopementCard> deck ) {
		
		ArrayList<DevelopementCard> a = new ArrayList<DevelopementCard>();
		a.addAll( deck.subList(0, 24) );
		a.addAll( deck.subList(48, 72) );
		a.addAll( deck.subList(24, 48) );
		a.addAll( deck.subList(72, 96) );
		
		for (int i = 0; i < 12; i++) 
			Collections.shuffle( a.subList( (0 + 8*i) , (8 + 8*i) ), new Random(System.nanoTime()));
		
		ArrayList<DevelopementCard> b = new ArrayList<DevelopementCard>();
		for (int t = 0; t < 6; t++)
			for (int q = 0; q < 4; q++) 
				b.addAll( a.subList( 4*(6*q + t) , 4*(6*q + t + 1) ));
		
		DevelopementCard[] c = new DevelopementCard[b.size()];
		c = b.toArray(c);
		
		return c;
	}
	
	
	/**
	 * Metodo per inizializzare l'array dei family member
	 */
	public void initializeArrayLists() {

		for(int j=0; j < 16; j++)
			memberSpaces[j] = null;
	}

	/**
	 * Metodo per ottenere i riferimenti adeguati alla gestione della torre
	 * richiesta dall'azione del giocatore
	 * 
	 * @param	chosenAction	tipo di azione richiesta
	 * 
	 * @see						it.polimi.ingsw.ps06.model.Types
	 */
	private void checkWhichTower(Action chosenAction) {
		
		switch(chosenAction.getActionCategory()){
			case TOWER_GREEN:
				towerIndex = 1;
				baseFloorTower = 0;
				topFloorTower = 3;
				break;
				
			case TOWER_BLUE:
				towerIndex = 2;
				baseFloorTower = 4;
				topFloorTower = 7;
				break;
				
			case TOWER_YELLOW:
				towerIndex = 3;
				baseFloorTower=8;
				topFloorTower = 11;
				break;
				
			case TOWER_PURPLE:
				towerIndex = 4;
				baseFloorTower = 12;
				topFloorTower = 15;
				break;
				
			default:
				throw new IllegalArgumentException();
		}
			
	}
	
	/**
	 * Metodo per ottenere il requisito di dado per accedere al livello
	 * della torre richiesta dall'azione
	 * 
	 * @param	chosenAction	tipo di azione richiesta
	 * 
	 * @return					requisito di dado per il piano della torre d'interesse
	 * 
	 * @see						it.polimi.ingsw.ps06.model.Types
	 */
	private int checkRequirement(Action chosenAction){
		
		switch(chosenAction) {
			case TOWER_GREEN_1:
			case TOWER_BLUE_1:
			case TOWER_YELLOW_1:
			case TOWER_PURPLE_1:
				return 1;
				
			case TOWER_GREEN_2:
			case TOWER_BLUE_2:
			case TOWER_YELLOW_2:
			case TOWER_PURPLE_2:
				return 3;
				
			case TOWER_GREEN_3:
			case TOWER_BLUE_3:
			case TOWER_YELLOW_3:
			case TOWER_PURPLE_3:
				return 5;
				
			case TOWER_GREEN_4:
			case TOWER_BLUE_4:
			case TOWER_YELLOW_4:
			case TOWER_PURPLE_4:
				return 7;
				
			default:
				throw new IllegalArgumentException();
		}
			
	}
	
	/**
	 * Metodo per l'assegnamento al giocatore del bonus ottenuto a seguito del piazzamento
	 * 
	 * @param 	chosenAction	tipo di azione eseguita
	 * @param 	member			familiare che ha eseguito l'azione corrente
	 * 
	 * @see						it.polimi.ingsw.ps06.model.Types
	 */
	private void giveBonus(Action chosenAction, FamilyMember member) {
		
		if ( member.getPlayer().getBonusMalusCollection().contains(BonusMalusNoTowersEffects.class) )
			return;
		
		ParserBonusBoard p = new ParserBonusBoard("resources/XML/BonusTabellone.xml");
		
		Resources r = p.getBonusRescourcesForActionSpace(chosenAction);
		
		if ( r != null)
			(new EffectsResources( r )).activate( member.getPlayer() );
	}
	
	/**
	 * Metodo per togliere le carte rimaste sulla torre e i familiari utilizzati
	 */
	public void cleanTowers() {
		deckIndex = deckIndex + CARTE_TORRE;
		initializeArrayLists();
		
		MessageBoardSetupDevCards setupCards = new MessageBoardSetupDevCards( getRoundCards() );
		notifyChangement(setupCards);
	}
	
	/**
	 * Metodo per ottenere la porzione di deck d'interesse al turno corrente
	 * 
	 * @return	insieme di carte al turno corrente
	 */
	public int[] getRoundCards(){
		
		int[] roundCards = new int[16];
		
		for(int i = 0; i < 16; i++) {
			if (deck[ deckIndex + i ] == null)
				roundCards[i] = -1;
			else
				roundCards[i] = deck[ deckIndex + i ].getCode();
		}
		
		return roundCards;
	}
	
	/**
	 * Getter delle carte territorio di territori
	 *
	 * @return 	l'insieme delle carte territorio
	 */
	
	
	/**
	 * Getter dei familiari posizionati nella zona
	 *
	 * @return 	l'insieme dei familiari posizionati nella zona
	 */
	public ArrayList<FamilyMember> getFamilyList(){
		return null;
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
	
	public static void main(String[] args) {
		Towers t = new Towers();
	}
}
