package it.polimi.ingsw.ps06.model.board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import it.polimi.ingsw.ps06.model.FamilyMember;
import it.polimi.ingsw.ps06.model.Player;
import it.polimi.ingsw.ps06.model.Types.Action;
import it.polimi.ingsw.ps06.model.Types.CouncilPrivilege;
import it.polimi.ingsw.ps06.model.XMLparser.ParserBonusBoard;
import it.polimi.ingsw.ps06.model.XMLparser.ParserExcommunicationTiles;
import it.polimi.ingsw.ps06.model.cards.ExcommunicationTile;
import it.polimi.ingsw.ps06.networking.messages.MessageBoardSetupExcomCards;

/**
* Classe per la gestione del tabellone
*
* @author  ps06
* @version 1.1
* @since   2017-05-10
*/

public class Board extends Observable{
	
	private Towers towersZone;
	private Market marketZone;
	private CouncilPalace councilPalaceZone;
	private HarvestProduction harvestProductionZone;
	
	private ArrayList<Player> order;
	
	private ExcommunicationTile excommunicationTilePeriodOne;
	private ExcommunicationTile excommunicationTilePeriodTwo;
	private ExcommunicationTile excommunicationTilePeriodThree;
	
	
	/**
	* Costruttore della Board. Si occupa dell'inizializzazione delle zone 
	* e la definizione delle carte scomunica per la partita in corso
	*
	* @param 	numberPlayers	Numero di giocatori della partita
	* @return 	Nothing
	*/
	public Board(int numberPlayers) {
		towersZone = new Towers();
		marketZone = new Market(numberPlayers);
		councilPalaceZone = new CouncilPalace();
		harvestProductionZone = new HarvestProduction(numberPlayers);
	}
	
	/**
	* Metodo per ritornare l'ordine dei familiari, per stabilire l'ordine di gioco
	*
	* @return 		order	ordine dei familiari
	*/ 
	private void drawExcommunicationTiles() 
	{
		ArrayList<ExcommunicationTile> tiles = (new ParserExcommunicationTiles("resources/XML/ExcommunicationCards.xml")).getTiles();
		excommunicationTilePeriodOne = tiles.get( (new Random()).nextInt(7) );
		excommunicationTilePeriodTwo = tiles.get( 7 + (new Random()).nextInt(7) );
		excommunicationTilePeriodThree = tiles.get( 14 + (new Random()).nextInt(7) );
		
		
		MessageBoardSetupExcomCards excomCards = new MessageBoardSetupExcomCards(excommunicationTilePeriodOne.getCode(), 
																				 excommunicationTilePeriodTwo.getCode(), 
																				 excommunicationTilePeriodThree.getCode()
																				);
		System.out.println("SCOMUNICHE: " + excommunicationTilePeriodOne.getCode() + " " + excommunicationTilePeriodTwo.getCode() + " " + excommunicationTilePeriodThree.getCode());
		notifyChangement(excomCards);
	}
	
	public ExcommunicationTile getTiles(int period) {
		
		if (period == 1) return excommunicationTilePeriodOne;
		if (period == 2) return excommunicationTilePeriodTwo;
		if (period == 3) return excommunicationTilePeriodThree;
		
		return null;
	}
	
	public int getFaithTrackReward(int position) {
		
		return (new ParserBonusBoard("resources/XML/BonusTabellone.xml")).getFaithPoints().get(position);
	}
	
	/**
	* Metodo per ritornare l'ordine dei familiari, per stabilire l'ordine di gioco
	*
	* @return 		order	ordine dei familiari
	*/ 
	public ArrayList<Player> getOrder() {
		return councilPalaceZone.checkOrder();
	}
	
	/**
	* Metodo per impostare il gioco per un nuovo round
	*
	* @param 	giocatori	Numero di giocatori della partita
	* @return 				Nothing
	*/ 
	public void setupRound(int period, int round) {
		if (round == 1 && period == 1)
			drawExcommunicationTiles();
		
		clean();
	}
	
	
	/**
	* Metodo per ripulire il tabellone alla fine di una fase
	*
	* @param 	Unused
	* @return 	Nothing
	*/
	public void clean() {
		
		marketZone.cleanMarket();
		towersZone.cleanTowers();
		harvestProductionZone.cleanZone();
		councilPalaceZone.cleanPalace();
	}
	
	public void placeMember(FamilyMember member, Action chosenAction, int servants, CouncilPrivilege privilege) {
		if (chosenAction != Action.COUNCIL_SPACE)
			return;
		
		councilPalaceZone.setChosenCouncilPrivilege(privilege);
		councilPalaceZone.placeMember(member, chosenAction, servants);
	}
	
	public void placeMember(FamilyMember member, Action chosenAction, int servants) {
		
		switch (chosenAction) {
		case MARKET_1:
		case MARKET_2:
		case MARKET_3:
		case MARKET_4:
		case MARKET_5:
			marketZone.placeMember(member, chosenAction, servants);
			break;
		case HARVEST_1:
		case HARVEST_2:
		case PRODUCTION_1:
		case PRODUCTION_2:
			harvestProductionZone.placeMember(member, chosenAction, servants);
			break;
		case TOWER_BLUE_1:
		case TOWER_BLUE_2:
		case TOWER_BLUE_3:
		case TOWER_BLUE_4:
		case TOWER_GREEN_1:
		case TOWER_GREEN_2:
		case TOWER_GREEN_3:
		case TOWER_GREEN_4:
		case TOWER_PURPLE_1:
		case TOWER_PURPLE_2:
		case TOWER_PURPLE_3:
		case TOWER_PURPLE_4:
		case TOWER_YELLOW_1:
		case TOWER_YELLOW_2:
		case TOWER_YELLOW_3:
		case TOWER_YELLOW_4:
			towersZone.placeMember(member, chosenAction, servants);
			break;
		default:
			break;
		
		}
	}
	
	public void notifyChangement(Object o) {
		setChanged();
		notifyObservers(o);
	}
	
	public void addNewObserver(Observer obs) {
		
		addObserver(obs);
		
		marketZone.addNewObserver(obs);
		towersZone.addNewObserver(obs);
		harvestProductionZone.addNewObserver(obs);
		councilPalaceZone.addNewObserver(obs);
	}
	
	public void deleteAnObserver(Observer obs) {
		deleteObserver(obs);
	}
}
