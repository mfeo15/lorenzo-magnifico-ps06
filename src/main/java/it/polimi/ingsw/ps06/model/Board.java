package it.polimi.ingsw.ps06.model;

/**
* Classe per la gestione del tabellone
*
* @author  ps06
* @version 1.0
* @since   2017-05-10
*/

public class Board {
	
	/**
	* Metodo per inizializzare il tabellone
	*
	* @param 	giocatori	Numero di giocatori della partita
	* @return 				Nothing
	*/
	public void setup(int giocatori){
		Market.setSpaces(giocatori);
		Towers.setCards();
		HarvestProduction.setSpaces(giocatori);
	}
	
	/**
	* Metodo per ripulire il tabellone alla fine di una fase
	*
	* @param 	Unused
	* @return 	Nothing
	*/
	public void clean(){
		Market.cleanMarket();
		Towers.cleanTowers();
		HarvestProduction.cleanZone();
		CouncilPalace.checkOrder();
		CouncilPalace.cleanPalace();
	}

}