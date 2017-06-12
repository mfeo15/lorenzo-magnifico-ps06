package it.polimi.ingsw.ps06;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps06.model.Game;
import it.polimi.ingsw.ps06.model.Player;

/**
* Classe rappresentante un insieme di connessioni impegnate in una singola partita
* Costruita come un set per evitare l'aggiunta di giocatori molteplici volte
*
* @author  ps06
* @version 1.0
* @since   2017-06-03 
*/
/**
 * @author mfeo15
 *
 */
public class MatchSet {

	private final int MAX_SIZE = 4;
	
	private ArrayList<Connection> participants;
	
	private HashMap<Player, Connection> p;
	
	private Game game;
	
	/**
	 * Costruttore di default
	 */
	public MatchSet() {
		participants = new ArrayList<Connection>();
		game = null;
	}
	
	/**
	 * Costruttore con parametro che inizializza il contenuto del Set
	 * 
	 * @param c	insieme di connessioni da inserire nella partita
	 */
	public MatchSet(ArrayList<Connection> c) {
		this();
		
		try {
			add(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo invocato per creare una nuova partita (Model) associato al set di connessioni
	 * Viene utilizzata la dimensione del set di connessioni per creare una partita adeguata
	 */
	public void createGame() {
		if (game != null) 
			return;

		game = new Game( participants.size() + 1 );
		
		participants.forEach(c -> game.addNewObserver(c));
	}
	
	public Game getGame() {
		return this.game;
	}
	
	/**
	 * Metodo invocato per aggiungere una singola connessione all'insieme Match
	 * Vengono eseguiti controlli prima dell'inserimento per verificare che la connessione
	 * non sia già presente o che il set sia già completamente pieno
	 * 
	 * @param 	c			Connessione da aggiungere al Set
	 * @throws 	Exception	Eccezione langiata nel caso il Set sia al completo
	 */
	public void add(Connection c) throws Exception {
		
		if ( contains(c) )
			return;
		
		if ( ! isFull() ) {
			participants.add(c);
		} else {
			throw new Exception("MatchSet is full");
		}
	}
	
	/**
	 * Metodo invocato per aggiungere un array di connessioni all'insieme Match
	 * Vengono eseguiti controlli prima dell'inserimento per verificare che tutte le connessione
	 * non sia già presenti o che il set sia già completamente pieno (o strabocchi in aggiunta dell'array)
	 * 
	 * @param 	a			Insieme di connessioni da aggiungere al Set
	 * @throws	Exception	Eccezione langiata nel caso il Set strabocchi aggiungendo l'array
	 */
	public void add(ArrayList<Connection> a) throws Exception {
	
		a.removeIf( c -> contains(c) );
		
		if (a.size() + participants.size() > MAX_SIZE )
			throw new Exception("Paramater array makes MatchSet out of bound");
		
		participants.addAll(a);
	}
	
	/**
	 * Metodo che ritorna la dimensione attuale del Set
	 * 
	 * @return	int		Dimensione del set
	 */
	public int size() {
		return participants.size();
	}
	
	
	/**
	 * Metodo che ritorna vero se una particolare connessione
	 * è contenuta nel Set
	 * 
	 * @param 	c		Connessione da trovare
	 * @return	true	se la connessione è contenuta nel Set
	 */
	public boolean contains(Connection c) {	
		return participants.contains(c);
	}
	
	/**
	 * Metodo che ritorna vero nel caso in cui la dimensione del Set
	 * abbia raggiunto quella massima
	 * 
	 * @return	true	se il set è pieno
	 */
	public boolean isFull() {
		return ( size() == MAX_SIZE );
	}
	
	/**
	 * Metodo invocato per la rimozione di una connessione dal Set
	 * 
	 * @param c		Connessione da rimuovere
	 */
	public void remove(Connection c) {
		participants.remove(c);
	}
	
	public ArrayList<Connection> getAll() {
		return participants;
	}

	@Override
	public boolean equals(Object obj) {
		
		try {
			return ((MatchSet) obj).participants.equals(this.participants);
		} catch (NullPointerException e) {
		
		}
		
		return false;
	}
}