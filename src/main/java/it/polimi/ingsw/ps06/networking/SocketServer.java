package it.polimi.ingsw.ps06.networking;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Timer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import it.polimi.ingsw.ps06.model.TimeSettings;
import it.polimi.ingsw.ps06.model.XMLparser.ParserTimeSettings;
import it.polimi.ingsw.ps06.networking.messages.Message;
import it.polimi.ingsw.ps06.networking.messages.MessageGameHasStarted;
import it.polimi.ingsw.ps06.networking.messages.MessageWaitingRoomConnections;

/**
 * Classe implementativa dell'interfaccia Server per il protocollo Socket
 *
 * @author  ps06
 * @version 1.0
 * @since   2017-06-03 
 */
public class SocketServer implements Server {

	private static SocketServer instance = null;

	private static final int PORT = 12345;

	private ServerSocket serverSocket;

	private ExecutorService executor = 
			Executors.newFixedThreadPool(128);

	private ArrayList<Connection> connections = new ArrayList<Connection>();

	private ArrayList<Connection> waitingConnection = new ArrayList<Connection>();

	private ArrayList< MatchSet > playingConnection = new ArrayList< MatchSet >();

	private HashMap<MatchSet, Integer> queuedMessageCounter = new HashMap<MatchSet, Integer>();

	private TimeSettings timeSettings = (new ParserTimeSettings("resources/XML/TimeSettings.xml")).getSettings();

	private Timer t;

	/**
	 * Costruttore della classe con inizializzazione del Socket
	 */
	public SocketServer()  {

		try {
			System.out.println();
			System.out.println();
			System.out.println("Lorenzo Server : ON \n");
			System.out.println("[ SERVER ] Server Socket inizialiazed : " + InetAddress.getLocalHost() + " \n");

			this.serverSocket = new ServerSocket(PORT);

		} catch (IOException e) {
			System.err.println("Impossibile inizializzare il server: " + e.getMessage() + "!");
			e.printStackTrace();
		}
	}

	/**
	 * <p>SINGLETON DESIGN PATTERN</p>
	 * <p>Metodo per ottenere l'istanza statica della classe, in modo univoco</p>
	 * 
	 * @return	istanza dell'oggetto Client
	 */
	public static SocketServer getInstance() {
		if (instance == null)
			instance = new SocketServer();

		return instance;
	}

	/**
	 * Metodo per la messa in opera del Server. Mantiene la classe in esecuzione, 
	 * costantemente in attesa di connessioni in ingresso
	 * 
	 */
	@Override
	public void start()
	{
		while(true) {
			try {
				Socket connectionSocket = serverSocket.accept();

				System.out.println();
				System.out.println("[ SERVER ] New Client IP Address : " + connectionSocket.getInetAddress() + "\n");

				Connection connection = new Connection(connectionSocket);
				registerConnection(connection);

				executor.submit(connection);//Equivalente a new Thread(c).start();				
			} catch (IOException e) {
				System.out.println("Errore di connessione!");
			}
		}
	}

	/**
	 * Getter per il timeout Action
	 * 
	 * @return	il timeout Action
	 */
	public int getTimeoutAction() {
		return timeSettings.getTimeoutAction();
	}

	/**
	 * Metodo invocato ogni qualvolta una nuova connessione viene instaurata. 
	 * La connessione viene archiviata all'interno di una collezione per aggiornare
	 * lo stato del server
	 * 
	 * @param	c	Connessione da aggiungere alla collezione
	 */
	@Override
	public synchronized void registerConnection(Connection c) {

		connections.add(c);
		System.out.println("[ SERVER ] Connection " + c.getInetAddress() + " registered \n");
	}

	/**
	 * Metodo invocato quando una connessione si distacca dal Server.
	 * La connessione viene rimossa dalla collezione per aggiornare lo stato.
	 * 
	 * @param	c	Connessione da rimuovere
	 */
	@Override
	public synchronized void deregisterConnection(Connection c) {
		connections.remove(c);

		if ( waitingConnection.contains(c) ) waitingConnection.remove(c);
	}

	/**
	 * Metodo invocato a seguito di una nuova registrazione di connessione.
	 * Le connessioni vengono posizionate in una collezione d'attesa fino
	 * al momento di raggiungere abbastanza giocatori per instaurare una nuova partita
	 * 
	 * @param	c	Connessione da aggiungere alla lista d'attesa
	 */
	public synchronized void rednezvous(Connection c) {

		waitingConnection.add(c);
		if (waitingConnection.size() == 2)
			startCountdown( timeSettings.getTimeoutWaitingConnections() );

		System.out.println("[ SERVER ] Connection " + c.getInetAddress() + " in Waiting Room \n");

		sendWaitingConnectionsStats();

		if (waitingConnection.size() == 4) {

			stopCountdown();
			startCountdown( 10 );
		}
	}

	/**
	 * Metodo per richiedere l'invio di un messaggio ad un insieme di connessioni
	 * 
	 * @param	cs	insieme di connessioni a cui delegare la spedizione
	 * @param 	m	messaggio da inviare
	 */
	public void sendToConnections(ArrayList<Connection> cs, Message m) {
		cs.forEach(connection -> connection.asyncSend(m));
	}

	/**
	 * Metodo per richiedere l'invio di un messaggio ad ogni connessione che è
	 * impegnato in una partita con la connessione passata come parametro
	 * 
	 * @param	c	connessione dalla quale determinare gli sfidanti
	 * @param 	m	messaggio da inviare
	 */
	public void sendToPlayingConnections(Connection c, Message m) {	
		retrieveMatch(c).getAll().forEach(connection -> connection.asyncSend(m));
	}

	/**
	 * Metodo di supporto all'invio di updates sullo stato della WaitingRoom
	 */
	public void sendWaitingConnectionsStats() {
		ArrayList<String> a = new ArrayList<String>();
		waitingConnection.forEach(connection -> a.add(connection.getUsername()));		
		sendToConnections(waitingConnection, new MessageWaitingRoomConnections(a) );
	}

	/**
	 * <p>Metodo invocato per la creazione di una nuova partita</p>
	 * <p>Si occupa di definire un nuovo Model, svuotare la waitingRoom ed aggiornare i client dell'accaduto</p>
	 */
	public void startNewGame() {

		stopCountdown();

		try {
			MatchSet match = new MatchSet(waitingConnection);
			match.createGame();
			playingConnection.add(match);

			sendToConnections(waitingConnection, new MessageGameHasStarted() );
			waitingConnection.clear();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo invocato per determinare il MatchSet a cui appartiene una connessione
	 * 
	 * @param	c	connessione di ricerca
	 * 
	 * @return		<p>il MatchSet contenente la connessione c</p>
	 * 				<p>null nel caso in cui il MatchSet non sia presente</p>
	 */
	public MatchSet retrieveMatch(Connection c) {
		Iterator<MatchSet> i = playingConnection.iterator();

		while ( i.hasNext() ) {
			MatchSet m = i.next();
			if (m.contains(c)) 
				return m;
		}

		return null;
	}

	/**
	 * Metodo per aggiungere un nuovo contatore alla coda
	 * 
	 * @param	m	matchSet di riferimento
	 */
	public void addElementQueue(MatchSet m) {
		queuedMessageCounter.put(m, 0);
	}

	/**
	 * Metodo per incrementare il contatore dei messaggi in coda
	 * 
	 * @param	m	matchSet di riferimento
	 */
	public void increaseQueue(MatchSet m) {
		if (!(queuedMessageCounter.containsKey(m)))
			addElementQueue(m);

		queuedMessageCounter.put(m, queuedMessageCounter.get(m) + 1);
	}

	/**
	 * Getter per il contatore della coda
	 * 
	 * @param	m	matchSet di riferimento
	 * 
	 * @return		il numero di messaggi accodati per il matchSet di riferimento
	 */
	public int getElementQueue(MatchSet m) {
		return queuedMessageCounter.get(m);
	}

	/**
	 * Metodo per azzerare il contatore dei messaggi in coda
	 * 
	 * @param	m	matchSet di riferimento
	 */
	public void clearQueue(MatchSet m) {
		queuedMessageCounter.put(m, 0);
	}

	/**
	 * Metodo di verifica della completezza della coda
	 * 
	 * @param	m		matchSet di riferimento
	 * 
	 * @return	true	se la coda è piena
	 */
	public boolean isFullQueue(MatchSet m) {
		return ( queuedMessageCounter.get(m) == m.getAll().size() );
	}


	/**
	 * Metodo per l'avvio di un contatore che allo scadere del tempo avvia una nuova partita
	 * 
	 * @param	seconds		numero di secondi da impostare
	 */
	public void startCountdown(int seconds) {

		t = new Timer();

		t.schedule( 
				new java.util.TimerTask() {
					@Override
					public void run() {
						startNewGame();
					}
				}, 
				( seconds * 1000 )
				);
	}

	/**
	 * Metodo per l'annullamento del contatore
	 */
	public void stopCountdown() {
		t.cancel();
		t.purge();
	}

	/**
	 * Metodo invocato per concludere la normale attività del Server. 
	 * Predispone la chiusura del proprio socket e di tutte le connessioni attive.
	 * 
	 * @throws IOException
	 */
	@Override
	public void close() throws IOException {
		serverSocket.close();

		connections.forEach( c -> c.closeConnection() );
	}
}