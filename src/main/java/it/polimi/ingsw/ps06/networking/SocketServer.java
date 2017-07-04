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
	 * Metodo invocato ogni qualvolta una nuova connessione viene instaurata. 
	 * La connessione viene archiviata all'interno di una collezione per aggiornare
	 * lo stato del server
	 * 
	 * @param c	Connessione da aggiungere alla collezione
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
	 * @param c	Connessione da rimuovere
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
	 * @param c	Connessione da aggiungere alla lista d'attesa
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
	
	
	public void sendToConnections(ArrayList<Connection> cs, Message m) {
		cs.forEach(connection -> connection.asyncSend(m));
	}
	
	public void sendToPlayingConnections(Connection c, Message m) {	
		retrieveMatch(c).getAll().forEach(connection -> connection.asyncSend(m));
	}
	
	public void sendWaitingConnectionsStats() {
		ArrayList<String> a = new ArrayList<String>();
		waitingConnection.forEach(connection -> a.add(connection.getUsername()));		
		sendToConnections(waitingConnection, new MessageWaitingRoomConnections(a) );
	}
	
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
	
	public MatchSet retrieveMatch(Connection c) {
		Iterator<MatchSet> i = playingConnection.iterator();
		
		while ( i.hasNext() ) {
			MatchSet m = i.next();
			if (m.contains(c)) 
				return m;
		}
		
		return null;
	}
	
	public void addElementQueue(MatchSet m) {
		queuedMessageCounter.put(m, 0);
	}
	
	public void increaseQueue(MatchSet m) {
		if (!(queuedMessageCounter.containsKey(m)))
			addElementQueue(m);
		
		queuedMessageCounter.put(m, queuedMessageCounter.get(m) + 1);
	}
	
	public int getElementQueue(MatchSet m) {
		return queuedMessageCounter.get(m);
	}
	
	public void clearQueue(MatchSet m) {
		queuedMessageCounter.put(m, 0);
	}
	
	public boolean isFullQueue(MatchSet m) {
		return ( queuedMessageCounter.get(m) == m.getAll().size() );
	}
	
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
	
	public void stopCountdown() {
		t.cancel();
		t.purge();
	}
	
	public int getTimeoutAction() {
		return timeSettings.getTimeoutAction();
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