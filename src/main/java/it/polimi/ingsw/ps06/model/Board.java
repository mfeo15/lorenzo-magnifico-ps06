package it.polimi.ingsw.ps06.model;

import java.util.ArrayList;

/**
* Classe per la gestione del tabellone
*
* @author  ps06
* @version 1.0
* @since   2017-05-10
*/

public class Board {
	
	private Towers towersZone;
	private Market marketZone;
	private CouncilPalace councilPalaceZone;
	private HarvestProduction harvestProductionZone;
	
	private ArrayList<Player> order;
	
	private ExcommunicationTile excomunicationTiles[];
	
	private int random1, random2, random3;
	
	
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
	
		drawExcommunicationTiles();
	}
	
	private void drawExcommunicationTiles() {
		random1 = (int)(Math.random() * 8);
		random2 = 7 + (int)(Math.random() * 8);
		random3 = 14 + (int)(Math.random() * 8);
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
		towersZone.setCards();
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
