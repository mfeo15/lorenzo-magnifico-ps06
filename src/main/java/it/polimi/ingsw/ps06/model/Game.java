package it.polimi.ingsw.ps06.model;

/**
* Class to model a single game between multiple players
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
	
	private Dice[] dices;
	
	public Game(int numberPlayers) {
		
		dices = new Dice[3];
		dices[0] = new Dice(ColorPalette.DICE_BLACK);
		dices[1] = new Dice(ColorPalette.DICE_WHITE);
		dices[2] = new Dice(ColorPalette.DICE_ORANGE);
		
		players = new Player[numberPlayers];
		for (Player p : players) p = new Player("Lorenzo", 0);
	}
	
	public void addPlayer(Player newPlayer) {
		
	}
	
	
	public void setupGame() {
		
	}
	
	public void setupRound() {
		
	}
	
	public void vaticanReport() {
		
	}
	
	public void start() {
		
	}
}
