package it.polimi.ingsw.ps06.model;

import java.util.ArrayList;
import java.util.Random;

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
	
	private int random1, random2, random3;
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
		random1 = (new Random()).nextInt(8);
		random2 = 7 + (new Random()).nextInt(8);
		random3 = 14 + (new Random()).nextInt(8);
	}
	
	public ExcommunicationTile getTiles(int period){
		int param = period - 1;
		return excommunicationTiles[param];
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

}
