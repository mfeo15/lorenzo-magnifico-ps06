package it.polimi.ingsw.ps06.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps06.model.Types.ColorPalette;
import it.polimi.ingsw.ps06.model.Types.MaterialsKind;
import it.polimi.ingsw.ps06.model.messages.MessageBoardSetupDice;

/**
* Classe che modellizza una partita tra n giocatori
*
* @author  ps06
* @version 1.0
* @since   2017-05-09 
*/
public class Game extends Observable {
	
	private final int NUMBER_OF_PERIODS	= 3;
	private final int NUMBER_OF_ROUNDS	= 2;
	private final int NUMBER_OF_PHASES 	= 4;
	
	private final int VATICAN_REQUIREMENT_PERIOD_1 = 3;
	private final int VATICAN_REQUIREMENT_PERIOD_2 = 4;
	private final int VATICAN_REQUIREMENT_PERIOD_3 = 5;
	
	private final int STANDARD_AMOUNT_COINS_FIRST_PLAYER = 5;
	
	private int numberPlayers;
	private ArrayList<Player> players;
	
	private Board board;
	
	private Dice diceBlack;
	private Dice diceWhite;
	private Dice diceOrange;
	
	
	/**
	* Costruttore di una partita. 
	* Vengono istanziati i componenti di base (dadi, board, players, ecc) ed impostati.
	*
	* @param 	numPlayers		numero di giocatori in una partita
	* 
	*/
	public Game(int numberPlayers) {
		
		this.numberPlayers = numberPlayers;
		
		diceBlack = new Dice(ColorPalette.DICE_BLACK);
		diceWhite = new Dice(ColorPalette.DICE_WHITE);
		diceOrange = new Dice(ColorPalette.DICE_ORANGE);
		
		board = new Board(numberPlayers);
		
		players = new ArrayList<Player>();
	}
	
	public void addPlayer(Player newPlayer) {
		players.add(newPlayer);
	}
	
	/**
	* Metodo invocato per il setup di ogni singolo nuovo round. 
	* Si occupa di far partire la gestione del turno giocatore ed il setup
	* della board.
	* 
	* @return 	Nothing
	*/
	public void setupRound() 
	{
		reorderPlayers();
		
		rollDices();
		
		//board.setupRound();
	}
	
	public void rollDices() {
		diceBlack.roll();
		diceWhite.roll();
		diceOrange.roll();
		
		MessageBoardSetupDice messageDice = new MessageBoardSetupDice(diceBlack.getValue(), diceWhite.getValue(), diceOrange.getValue() );
		
		notifyChangement(messageDice);
	}
	
	/**
	* Metodo invocato per la gestione della fase Vatican Report.
	*
	* @param 	period	contatore del periodo
	* 
	* @return 	Nothing
	*/
	public void vaticanReport(int period) 
	{
		for (Player p: players) 
		{
			int player_faith = 0;
			
			//Get the Player Faith points, p ==> player_faith = p.getFaith();
			
			if (player_faith < VaticanRequirementOnPeriod(period)) {
				//board.getTiles(period).activate(p);   <== Mancano implementazioni di metodi
			}

		}
	}
	
	
	/**
	* Metodo per il calcolo del requisito di fede da parte del Vaticano
	* in relazione al periodo attuale di gioco.
	*
	* @param 	period	contatore del periodo
	* 
	* @return 	int		requisito associato al periodo
	*/
	private int VaticanRequirementOnPeriod(int period) 
	{
		if (period == 1) return VATICAN_REQUIREMENT_PERIOD_1;
		if (period == 2) return VATICAN_REQUIREMENT_PERIOD_2;
		if (period == 3) return VATICAN_REQUIREMENT_PERIOD_3;
		
		return(-1);
	}
	
	/**
	* Metodo utile al riordinamento dell'ordine di gioco. Ottiene dalla board l'ordine dei 
	* Players posizionati nel consiglio ed aggiorna di conseguenza lo stato
	* 
	* @return 	Nothing.
	*/
	private void reorderPlayers() 
	{
		ArrayList<Player> councilPlayers = board.getOrder();
		if (councilPlayers == null)
			return;
		
		ArrayList<Player> newOrderPlayers = new ArrayList<Player>();
		
		Iterator<Player> councilIterator = councilPlayers.iterator();
		while (councilIterator.hasNext() && newOrderPlayers.size() < numberPlayers) {
			Player p = councilIterator.next();
			if ( players.contains(p) ) newOrderPlayers.add(p);	
		}
		
		players.removeAll(newOrderPlayers);
		players.addAll(0, newOrderPlayers);
	}
	
	/**
	* Metodo di gestione dello stato della partita. Viene invocato per iniziare il gioco
	* e scandisce il tempo di esecuzione delle varie fasi.
	*
	* @return 	Nothing
	*/
	public void start() 
	{
		//Bad placement, temporary
		//Assegnamento dei vari coin ad ogni singolo giocatore ad inizio partita in relazione alla posizione di turno
		for (int i=0; i < players.size(); i++) {
			Player p = players.get(i);
			p.getPersonalBoard().addMaterials(MaterialsKind.COIN, STANDARD_AMOUNT_COINS_FIRST_PLAYER + i);
		}
		
		for(int period = 0; period < NUMBER_OF_PERIODS; period++) 
		{
			for(int round = 0; round < NUMBER_OF_ROUNDS; round++) 
			{
				for(int phase = 1; phase < NUMBER_OF_PHASES; phase++) 
				{
					switch (phase) {
						case 1:
							setupRound();
							break;
						case 2: 
							for( Player p : players) {
								
							}
							break;
						case 3: 
							if (round == 2 || round == 4 || round == 6) vaticanReport(period);
							break;
					}
				}
			}
		}
		
		end();
	}
	
	public void end() {
		//Notify controller that the game is over
	}
	
	public Player computeWinnerPlayer()
	{
		Player winnerPlayer = null;
		
		for (Player p: players) {
			
		}
		
		return winnerPlayer;
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
