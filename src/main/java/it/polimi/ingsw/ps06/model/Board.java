package it.polimi.ingsw.ps06.model;

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
	
	private ExcommunicationTile excomunicationTiles[];
	
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
	
	}
	
	// DA RIMUOVERE! GIA' PREVISTO NEL COSTRUTTORE
	/**
	* Metodo per inizializzare il tabellone
	*
	* @param 	giocatori	Numero di giocatori della partita
	* @return 				Nothing
	*/ 
	public void setup(int giocatori) {
		/*
		 * COMPLETAMENTE DA RIVEDERE
		 * 
		Market.setSpaces(giocatori);
		Towers.setCards();
		HarvestProduction.setSpaces(giocatori);
		*/
	}
	
	//DA IMPLEMENTARE
	public void setupRound() {
		
	}
	
	/**
	* Metodo per ripulire il tabellone alla fine di una fase
	*
	* @param 	Unused
	* @return 	Nothing
	*/
	public void clean() {
		/*
		 * COMPLETAMENTE DA RIVEDERE
		 * 
		Market.cleanMarket();
		Towers.cleanTowers();
		HarvestProduction.cleanZone();
		CouncilPalace.checkOrder();
		CouncilPalace.cleanPalace();
		*/
	}

}
