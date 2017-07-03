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
import it.polimi.ingsw.ps06.model.XMLparser.ParserLeaders;
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
	* @param	numberPlayers	numero di giocatori in una partita
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
		
		leaders = (new ParserLeaders("resources/XML/Leaders.xml")).getCards();
		Collections.shuffle( leaders, new Random(System.nanoTime()) );
		
		bonusTiles = new ArrayList<Integer>();
		for (int j = 1; j <= 4; j++) bonusTiles.add(j);
		Collections.shuffle( bonusTiles, new Random(System.nanoTime()) );
		
		period = 1;
		round = 1;
		
		currentPlayerIndex = 0;
	}
	
	/**
	* Setter dell'indice del giocatore corrente 
	*
	* @param	currentPlayerIndex		nuovo indice del giocatore corrente
	* 
	*/
	public void setCurrentPlayerIndex(int currentPlayerIndex) {
		this.currentPlayerIndex = currentPlayerIndex;
		
		MessageCurrentPlayer messageCurrentP = new MessageCurrentPlayer( getCurrentPlayer().getID() );
		notifyChangement(messageCurrentP);
	}
	
	/**
	* Metodo per l'aggiunta di un nuovo giocatore alla partita.
	* Si occupa di assegnare casualmente una BonusTile tra quelle ancora disponibili 
	*
	* @param	newPlayer	nuovo giocatore
	* 
	*/
	public void addPlayer(Player newPlayer) {
		newPlayer.getPersonalBoard().setBonusTile( bonusTiles.remove(0) );
		players.add(newPlayer);
	}
	
	/**
	* Getter dell'intero Array di giocatori presenti nella partita 
	*
	* @return		l'array dei giocatori
	* 
	*/
	public ArrayList<Player> getPlayerList() {
		return players;
	}
	
	/**
	* Getter del giocatore tramite ricerca per identificativo ID 
	*
	* @param	ID		identificativo univoco del giocatore da trovare
	* 
	* @return			il gicatore il cui identificativo è pari a ID passato.
	* 					In caso in cui il giocatore non ci fosse, ritorna null
	* 
	*/
	public Player getPlayer(int ID) {
		
		for (Player p : players) 
			if ( p.getID() == ID ) return p;
		
		return null;
	}
	
	/**
	* Getter del giocatore corrente 
	* 
	* @return			il gicatore il giocatore corrent
	* 
	*/
	public Player getCurrentPlayer() {
		
		return players.get( currentPlayerIndex % players.size() );
	}
	
	/**
	* Metodo per gestire l'avanzamento del turno al prossimo giocatore. Nel caso in cui
	* tutti i FamilyMember siano stati piazzati viene fatto avanzare il Round
	* 
	*/
	public void advanceCurrentPlayer() {
		
		int total_number_members = players.size() * 4;
		
		if (total_number_members - 1 == currentPlayerIndex) {
			advanceRound();
			return;
		}
		
		setCurrentPlayerIndex(currentPlayerIndex + 1);
	}
	
	/**
	* Metodo per gestire l'avanzamento del Round. Nel caso in cui si sia
	* raggiunto il numero massimo di Round (definito nella costante NUMBER_OF_ROUNDS)
	* viene fatto avanzare il Periodo
	* 
	*/
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
	
	/**
	* Metodo per gestire l'avanzamento del Periodo. Nel caso in cui si sia
	* raggiunto il numero massimo di Periodi (definito nella costante NUMBER_OF_PERIODS)
	* viene fatto scattare il termine della partita.
	* 
	* Ogni avanzamento comporta il check del Vatican Report
	* 
	*/
	public void advancePeriod() {
		
		if (period + 1 > NUMBER_OF_PERIODS) {
			end();
			return;
		}
		
		period++;	
		vaticanReport(period);
		
		gameStatusUpdate();
	}
	
	/**
	* Metodo per inviare comunicare agli Observers lo stato attuale della partita (round e period)	
	*  
	*/
	public void gameStatusUpdate() {
		MessageGameStatus stat = new MessageGameStatus(period, round);
		notifyChangement(stat);
	}
	
	/**
	 * Getter del dado color nero
	 * 
	 * @return	valore del dado color nero
	 */
	public Dice getDiceBlack() {
		return diceBlack;
	}

	/**
	 * Setter del dado color nero
	 * 
	 * @param 	diceBlack 	valore del dado nero da settare
	 */
	public void setDiceBlack(Dice diceBlack) {
		this.diceBlack = diceBlack;
	}

	/**
	 * Getter del dado color bianco
	 * 
	 * @return valore del dado color bianco
	 */
	public Dice getDiceWhite() {
		return diceWhite;
	}

	/**
	 * Setter del dado color bianco
	 * 
	 * @param 	diceWhite 	valore del dado bianco da settare
	 */
	public void setDiceWhite(Dice diceWhite) {
		this.diceWhite = diceWhite;
	}

	/**
	 * Setter del dado color arancione
	 * 
	 * @return	valore del dado color nero
	 */
	public Dice getDiceOrange() {
		return diceOrange;
	}

	/**
	 * Setter del dado color arancione
	 * 
	 * @param 	diceOrange	valore del dado arancione da settare
	 */
	public void setDiceOrange(Dice diceOrange) {
		this.diceOrange = diceOrange;
	}

	/**
	* Metodo invocato per il setup di ogni singolo nuovo round. 
	* Si occupa di far partire la gestione del turno giocatore, tirare i dadit 
	* ed il setup della board.
	*
	*/
	public void setupRound() 
	{
		reorderPlayers();
		
		rollDices();
		
		board.setupRound(period, round);
	}
	
	/**
	* Metodo invocato per generare una nuova tirata di dadi. I nuovi valori vengono generati, 
	* assegnati ai Family Member e condivisi con gli Observers
	*
	*/
	public void rollDices() {
		diceBlack.roll();
		diceWhite.roll();
		diceOrange.roll();
		
		for (Player p : players) p.setFamilyMemberValues(diceBlack.getValue(), diceWhite.getValue(), diceOrange.getValue());
		
		MessageBoardSetupDice messageDice = new MessageBoardSetupDice(diceBlack.getValue(), diceWhite.getValue(), diceOrange.getValue() );
		notifyChangement(messageDice);
	}
	
	/**
	* Metodo invocato per la gestione della fase Vatican Report
	*
	* @param 	period	contatore del periodo
	* 
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
	* @return 			requisito associato al periodo o -1 in caso
	* 					di input non accettato
	*/
	private int VaticanRequirementOnPeriod(int period) 
	{
		if (period == 1) return VATICAN_REQUIREMENT_PERIOD_1;
		if (period == 2) return VATICAN_REQUIREMENT_PERIOD_2;
		if (period == 3) return VATICAN_REQUIREMENT_PERIOD_3;
		
		return(-1);
	}
	
	/**
	* Metodo utile al riordinamento dei player. Ottiene dalla board l'ordine dei 
	* Players posizionati nel consiglio ed aggiorna di conseguenza lo stato del current player.
	* 
	* Le modifiche vengono comunicate agli Observers
	* 
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
	* Metodo invocato per l'esecuzione di un piazzamento di un Family Member 
	* da parte di un giocatore
	*
	* @param	p			giocatore che ha eseguito il piazzamento
	* @param 	action		azione eseguita dal giocatore 
	* @param 	color		colore del familiare usato
	* @param	servants	numero di servitori impiegati per alterare il valore del familiare
	* 
	* @see		it.polimi.ingsw.ps06.model.Types
	* 
	*/
	public void doMemberPlacement(Player p, Action action, ColorPalette color, int servants) {
		
		if (players.contains(p)) 
			board.placeMember(p.getMember(color), action, servants);
	}
	
	/**
	* Metodo invocato per l'esecuzione di un piazzamento di un Family Member 
	* da parte di un giocatore all'interno del Palazzo del Consiglio
	*
	* @param	p			giocatore che ha eseguito il piazzamento
	* @param 	action		azione eseguita dal giocatore	
	* @param 	color		colore del familiare usato
	* @param	servants	numero di servitori impiegati per alterare il valore del familiare
	* @param	privilege	tipo di privilegio richiesto al consiglio	
	* 
	* @see		it.polimi.ingsw.ps06.model.Types
	* 
	*/
	public void doMemberPlacement(Player p, Action action, ColorPalette color, int servants, CouncilPrivilege privilege) {
		
		if (players.contains(p)) 
			board.placeMember(p.getMember(color), action, servants, privilege);
	}
	
	/**
	* Metodo per gestire, a seguito di una corretta inizializzazione della classe, l'inizio della partita.
	* Vengono assegnate risorse e leader ai vari giocatori
	*
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
	
	/**
	* Metodo per gestire il termine di una partita, occupandosi di comunicare l'avvenimento ai vari
	* Observers e di computare il risultato finale del gioco
	*
	*/
	public void end() {
		//Notify controller that the game is over
	}
	
	/**
	* Metodo invocato per computare il punteggio finale e ritornare il vincitore della partita
	* 
	* @return	il giocatore vincente della partita
	*
	*/
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
