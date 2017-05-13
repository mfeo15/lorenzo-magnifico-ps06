package it.polimi.ingsw.ps06.model;

import static it.polimi.ingsw.ps06.model.Types.ColorPalette;

/**
* Classe che modellizza una partita tra n giocatori
*
* @author  ps06
* @version 1.0
* @since   2017-05-09 
*/
public class Game {
	
	private final int NUMBER_OF_PERIODS	= 3;
	private final int NUMBER_OF_ROUNDS	= 2;
	private final int NUMBER_OF_PHASES 	= 4;
	
	private Player[] players;
	
	private Board board;
	
	private Dice diceBlack;
	private Dice diceWhite;
	private Dice diceOrange;
	
	public Game(int numberPlayers) {
		
		diceBlack = new Dice(ColorPalette.DICE_BLACK);
		diceWhite = new Dice(ColorPalette.DICE_WHITE);
		diceOrange = new Dice(ColorPalette.DICE_ORANGE);
		
		board = new Board(numberPlayers);
		
		players = new Player[numberPlayers];
		for (Player p : players) p = new Player("Lorenzo", ColorPalette.UNCOLORED);
	}
	
	public void addPlayer(Player newPlayer) {
		
	}
	
	
	public void setupRound() {
		
	}
	
	public void vaticanReport() {
		
	}
	
	private void reorderPlayers() {
		
	}
	
	public void start() 
	{
		for(int period = 0; period < NUMBER_OF_PERIODS; period++) 
		{
			for(int round = 0; round < NUMBER_OF_ROUNDS; round++) 
			{
				for(int phase = 0; phase < NUMBER_OF_PERIODS; phase++) 
				{
					switch (phase) {
						case 0:
							setupRound();
						case 1: 
							for( Player p : players) {
								
							}
						case 2: 
							if (round == 2 || round == 4 || round == 6) vaticanReport();
						case 3: //end of round
					}
				}
			}
		}
	}
}
