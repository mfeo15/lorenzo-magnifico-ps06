package it.polimi.ingsw.ps06.model;

import java.util.ArrayList;
import java.util.Random;

import it.polimi.ingsw.ps06.model.Types.Action;

/**
* Classe per la gestione del tabellone
*
* @author  ps06
* @version 1.1
* @since   2017-05-10
*/

public class Board {
	
	private Towers towersZone;
	private Market marketZone;
	private CouncilPalace councilPalaceZone;
	private HarvestProduction harvestProductionZone;
	
	private ArrayList<Player> order;
	
	private ExcommunicationTile excommunicationTiles[];
	
	private ArrayList<DevelopementCard> deck;
	
	
	/**
	* Costruttore della Board. Si occupa dell'inizializzazione delle zone 
	* e la definizione delle carte scomunica per la partita in corso
	*
	* @param 	numberPlayers	Numero di giocatori della partita
	* @return 	Nothing
	*/
	public Board(int numberPlayers) {
		buildDeck();
		towersZone = new Towers(deck);
		marketZone = new Market(numberPlayers);
		councilPalaceZone = new CouncilPalace();
		harvestProductionZone = new HarvestProduction(numberPlayers);
	
		drawExcommunicationTiles();
	}
	
	private void buildDeck(){
		
		//Riempi Deck con carte dal file
	}
	
	/**
	* Metodo per ritornare l'ordine dei familiari, per stabilire l'ordine di gioco
	*
	* @return 		order	ordine dei familiari
	*/ 
	private void drawExcommunicationTiles() {
		int random1 = (new Random()).nextInt(8);
		int random2 = 7 + (new Random()).nextInt(8);
		int random3 = 14 + (new Random()).nextInt(8);
	}
	
	public ExcommunicationTile getTiles(int period){
		return excommunicationTiles[period - 1];
	}
	
	/**
	* Metodo per ritornare l'ordine dei familiari, per stabilire l'ordine di gioco
	*
	* @return 		order	ordine dei familiari
	*/ 
	public ArrayList<Player> getOrder() {
		return order;
	}
	
	/**
	* Metodo per impostare il gioco per un nuovo round
	*
	* @param 	giocatori	Numero di giocatori della partita
	* @return 				Nothing
	*/ 
	public void setupRound() {
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
		order = councilPalaceZone.checkOrder();
		councilPalaceZone.cleanPalace();
	}
	
	
	public void placeMember(FamilyMember member, Action chosenAction) {
		
		switch (chosenAction) {
		case COUNCIL_SPACE:
			councilPalaceZone.placeMember(member, chosenAction);
			break;
		case MARKET_1:
		case MARKET_2:
		case MARKET_3:
		case MARKET_4:
		case MARKET_5:
			marketZone.placeMember(member, chosenAction);
			break;
		case HARVEST_1:
		case HARVEST_2:
		case PRODUCTION_1:
		case PRODUCTION_2:
			harvestProductionZone.placeMember(member, chosenAction);
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
			towersZone.placeMember(member, chosenAction);
			break;
		default:
			break;
		
		}
	}

}
