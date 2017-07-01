package it.polimi.ingsw.ps06.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import it.polimi.ingsw.ps06.model.Types.Action;
import it.polimi.ingsw.ps06.model.Types.ColorPalette;
import it.polimi.ingsw.ps06.model.Types.CouncilPrivilege;
import it.polimi.ingsw.ps06.model.Types.MaterialsKind;
import it.polimi.ingsw.ps06.model.Types.PointsKind;
import it.polimi.ingsw.ps06.model.XMLparser.ParserXMLLeaders;
import it.polimi.ingsw.ps06.model.board.Board;
import it.polimi.ingsw.ps06.model.cards.leader.Leader;
import it.polimi.ingsw.ps06.networking.messages.MessageBoardSetupDice;
import it.polimi.ingsw.ps06.networking.messages.MessageCurrentPlayer;
import it.polimi.ingsw.ps06.networking.messages.MessageCurrentPlayerOrder;
import it.polimi.ingsw.ps06.networking.messages.MessageGameStatus;
import it.polimi.ingsw.ps06.networking.messages.MessageVaticanReport;

/**
* Classe che modellizza una partita tra n giocatori
*
* @author  ps06
* @version 1.0
* @since   2017-05-09 
*/
public class Game extends Observable implements Observer {
	
	private final int NUMBER_OF_PERIODS	= 3;
	private final int NUMBER_OF_ROUNDS	= 2;
	private final int NUMBER_OF_PHASES 	= 4;
	
	private final int VATICAN_REQUIREMENT_PERIOD_1 = 3;
	private final int VATICAN_REQUIREMENT_PERIOD_2 = 4;
	private final int VATICAN_REQUIREMENT_PERIOD_3 = 5;
	
	private final int STANDARD_AMOUNT_COINS_FIRST_PLAYER = 5;
	
	private int numberPlayers;
	private ArrayList<Player> players;
	private ArrayList<Leader> leaders;
	private ArrayList<Integer> bonusTiles;
	
	private Board board;
	
	private Dice diceBlack;
	private Dice diceWhite;
	private Dice diceOrange;
	
	private int currentPlayerIndex;
	
	private int round;
	private int period;
	
	
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
		board.addNewObserver(this);
		
		players = new ArrayList<Player>();

		/*
		leaders = (new ParserXMLLeaders("resources/XML/DevelopementCards.xml")).getCards();
		Collections.shuffle( leaders, new Random(System.nanoTime()) );
		*/
		
		this.leaders = new ArrayList<Leader>();

		for (int i=1; i <= 20; i++) {
			Leader l = new Leader();
			l.setCode(i);
			this.leaders.add(l);
		}
		
		Collections.shuffle( leaders, new Random(System.nanoTime()) );
		
		bonusTiles = new ArrayList<Integer>();
		for (int j = 1; j <= 4; j++) bonusTiles.add(j);
		Collections.shuffle( bonusTiles, new Random(System.nanoTime()) );
		
		period = 1;
		round = 1;
		
		currentPlayerIndex = 0;
	}
	
	public void setCurrentPlayerIndex(int currentPlayerIndex) {
		this.currentPlayerIndex = currentPlayerIndex;
		
		MessageCurrentPlayer messageCurrentP = new MessageCurrentPlayer( getCurrentPlayer().getID() );
		notifyChangement(messageCurrentP);
	}
	
	public void addPlayer(Player newPlayer) {
		newPlayer.getPersonalBoard().setBonusTile( bonusTiles.remove(0) );
		players.add(newPlayer);
	}
	
	public ArrayList<Player> getPlayerList() {
		return players;
	}
	
	public Player getPlayer(int ID) {
		
		for (Player p : players) 
			if ( p.getID() == ID ) return p;
		
		return null;
		
	}
	
	public Player getCurrentPlayer() {
		
		return players.get( currentPlayerIndex % players.size() );
	}
	
	public void advanceCurrentPlayer() {
		
		int total_number_members = players.size() * 4;
		
		if (total_number_members - 1 == currentPlayerIndex) {
			advanceRound();
			return;
		}
		
		setCurrentPlayerIndex(currentPlayerIndex + 1);
	}
	
	public void advanceRound() {
		
		if (round + 1 > NUMBER_OF_ROUNDS) {
			round = 1;
			setupRound();
			advancePeriod();
			return;
		}
		
		round++;
		gameStatusUpdate();

		setupRound();
	}
	
	public void advancePeriod() {
		
		if (period + 1 > NUMBER_OF_PERIODS) {
			end();
			return;
		}
		
		period++;	
		vaticanReport(period);
		
		gameStatusUpdate();
	}
	
	public void gameStatusUpdate() {
		MessageGameStatus stat = new MessageGameStatus(period, round);
		notifyChangement(stat);
	}
	
	/**
	 * @return the diceBlack
	 */
	public Dice getDiceBlack() {
		return diceBlack;
	}

	/**
	 * @param diceBlack the diceBlack to set
	 */
	public void setDiceBlack(Dice diceBlack) {
		this.diceBlack = diceBlack;
	}

	/**
	 * @return the diceWhite
	 */
	public Dice getDiceWhite() {
		return diceWhite;
	}

	/**
	 * @param diceWhite the diceWhite to set
	 */
	public void setDiceWhite(Dice diceWhite) {
		this.diceWhite = diceWhite;
	}

	/**
	 * @return the diceOrange
	 */
	public Dice getDiceOrange() {
		return diceOrange;
	}

	/**
	 * @param diceOrange the diceOrange to set
	 */
	public void setDiceOrange(Dice diceOrange) {
		this.diceOrange = diceOrange;
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
		
		board.setupRound();
	}
	
	public void rollDices() {
		diceBlack.roll();
		diceWhite.roll();
		diceOrange.roll();
		
		for (Player p : players) p.setFamilyMemberValues(diceBlack.getValue(), diceWhite.getValue(), diceOrange.getValue());
		
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
		ArrayList<Integer> excommunicatedPlayers = new ArrayList<Integer>();
		
		for (Player p: players) {
			int player_faith = p.getPersonalBoard().getAmount(PointsKind.FAITH_POINTS);
			
			if (player_faith < VaticanRequirementOnPeriod(period)) {			
				board.getTiles(period).activateEffect(p);
				excommunicatedPlayers.add( p.getID() );
			}
		}
		
		if ( excommunicatedPlayers.size() > 0) {
			MessageVaticanReport vaticanRep = new MessageVaticanReport(period, excommunicatedPlayers );
			notifyChangement(vaticanRep);
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
		setCurrentPlayerIndex(0);
		
		ArrayList<Player> councilPlayers = board.getOrder();
		if (councilPlayers != null) {

			ArrayList<Player> newOrderPlayers = new ArrayList<Player>();

			Iterator<Player> councilIterator = councilPlayers.iterator();
			while (councilIterator.hasNext() && newOrderPlayers.size() < numberPlayers) {
				Player p = councilIterator.next();
				if ( players.contains(p) ) newOrderPlayers.add(p);	
			}

			players.removeAll(newOrderPlayers);
			players.addAll(0, newOrderPlayers);
		}
		
		ArrayList<Integer> playersID = new ArrayList<Integer>();
		players.forEach(p -> playersID.add(p.getID()));
		MessageCurrentPlayerOrder order = new MessageCurrentPlayerOrder(playersID);
		notifyChangement(order);
	}
	
	/**
	* Metodo per l'esecuzione di un azione
	*
	* @param 	value	valore dell'azione
	* @param 	color	colore del familiare usato
	* @return 	Nothing
	*/
	public void doMemberPlacement(Player p, Action action, ColorPalette color, int servants) {
		
		if (players.contains(p)) 
			board.placeMember(p.getMember(color), action, servants);
	}
	
	/**
	* Metodo per l'esecuzione di un azione sul Palazzo del Consiglio
	*
	* @param 	value	valore dell'azione
	* @param 	color	colore del familiare usato
	* @return 	Nothing
	*/
	public void doMemberPlacement(Player p, Action action, ColorPalette color, int servants, CouncilPrivilege privilege) {
		
		if (players.contains(p)) 
			board.placeMember(p.getMember(color), action, servants, privilege);
	}
	
	/**
	* Metodo di gestione dello stato della partita. Viene invocato per iniziare il gioco
	* e scandisce il tempo di esecuzione delle varie fasi.
	*
	* @return 	Nothing
	*/
	public void start() 
	{
		//Assegnamento dei vari coin ad ogni singolo giocatore ad inizio partita in relazione alla posizione di turno
		for (int i=0; i < players.size(); i++) {
			Player p = players.get(i);
			p.getPersonalBoard().addResource(MaterialsKind.COIN, STANDARD_AMOUNT_COINS_FIRST_PLAYER + i);
			p.addLeaders( new ArrayList<Leader>( leaders.subList( (4 * p.getID()) , (4 + 4 * p.getID()) ) ) );
		}
		
		setupRound();
		gameStatusUpdate();
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
		
		board.addNewObserver(obs);
	}
	
	public void deleteAnObserver(Observer obs) {
		deleteObserver(obs);
	}

	@Override
	public void update(Observable o, Object arg) {
		
		if (!(arg instanceof Integer))
			return;
		
		if (((Integer) arg) == 1)
			advanceCurrentPlayer();
	}
}
