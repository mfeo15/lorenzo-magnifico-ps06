package it.polimi.ingsw.ps06;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import it.polimi.ingsw.ps06.model.Game;
import it.polimi.ingsw.ps06.model.messages.Message;
import it.polimi.ingsw.ps06.model.messages.MessageGameHasStarted;
import it.polimi.ingsw.ps06.model.messages.MessageWaitingRoomConnections;

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
		System.out.println("[ ] Connection " + c.getInetAddress() + " registered \n");
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
		
		/*
		Connection enemy = playingConnection.get(c);
		if(enemy != null)
		enemy.closeConnection();
		playingConnection.remove(c);
		playingConnection.remove(enemy);
		*/

		waitingConnection.remove(c);
		
		/*
		Iterator<SocketAddress> iterator = waitingConnection.keySet().iterator();
		while(iterator.hasNext()){
			if(waitingConnection.get(iterator.next())==c){
				iterator.remove();
			}
		}
		*/
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
		System.out.println("[ ] Connection " + c.getInetAddress() + " in Waiting Room \n");
		
		sendWaitingConnectionsStats();

		if (waitingConnection.size() == 4)
		{
			/*
			List<String> keys = new ArrayList<>(waitingConnection.keySet());
			Connection c1 = waitingConnection.get(keys.get(0));
			Connection c2 = waitingConnection.get(keys.get(1));
			RemoteView player1 = new RemoteView(new Player(keys.get(0)), keys.get(1), c1);
			RemoteView player2 = new RemoteView(new Player(keys.get(1)), keys.get(0), c2);
			Model model = new Model();
			Controller controller = new Controller(model);
			model.addObserver(player1);
			model.addObserver(player2);
			player1.addObserver(controller);
			player2.addObserver(controller);
			*/
			
			startNewGame();
		}
	}
	
	public SocketServer() throws IOException {
		
		System.out.println();
		System.out.println();
		System.out.println("Lorenzo Server : ON \n");
		System.out.println("[] Server Socket inizialiazed : " + InetAddress.getLocalHost() + " \n");
		
		this.serverSocket = new ServerSocket(PORT);
	}
	
	public static SocketServer getInstance() throws IOException {
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
				System.out.println("[ ] New Client IP Address : " + connectionSocket.getInetAddress() + "\n");
				
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
	
	public void sendWaitingConnectionsStats() {
		ArrayList<String> a = new ArrayList<String>();
		waitingConnection.forEach(connection -> a.add(connection.getUsername()));		
		sendToConnections(waitingConnection, new MessageWaitingRoomConnections(a) );
	}
	
	public void startNewGame() {
		
		sendToConnections(waitingConnection, new MessageGameHasStarted() );
		
		try {
			MatchSet match = new MatchSet(waitingConnection);
			match.createGame();
			playingConnection.add(match);

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