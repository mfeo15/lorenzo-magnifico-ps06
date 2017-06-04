package it.polimi.ingsw.ps06;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
* Classe implementativa dell'interfaccia Server per il protocollo Socket
*
* @author  ps06
* @version 1.0
* @since   2017-06-03 
*/
public class SocketServer implements Server, Observer {
	
	private static final int PORT = 12345;
	
	private ServerSocket serverSocket;
	
	private ExecutorService executor = 
			Executors.newFixedThreadPool(128);
	
	private ArrayList<Connection> connections = new ArrayList<Connection>();
	
	private ArrayList<Connection> waitingConnection = new ArrayList<Connection>();
	
	private ArrayList< MatchSet > playingConnection;
	
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
		System.out.println("[ ] Connection " + c.ID() + " registered \n");
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
		System.out.println("[ ] Connection " + c.ID() + " in Waiting Room \n");
		
		
		ArrayList<String> a = new ArrayList<String>();
		waitingConnection.forEach(connection -> a.add(connection.ID()));		
		sendToConnections(waitingConnection, new Message(a) );

		
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
			try {
				MatchSet match = new MatchSet();
				match.add( (ArrayList<Connection>) waitingConnection);
				waitingConnection.clear();
			
				playingConnection.add(match);
			} catch (Exception e) {
				
			}
		}
	}
	
	public SocketServer() throws IOException {
		
		System.out.println();
		System.out.println();
		System.out.println("Lorenzo Server : ON");
		
		this.serverSocket = new ServerSocket(PORT);
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
				
				Connection connection = new Connection(connectionSocket, this);
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

	
	/* MVC - SERVER IS MODEL AND SO IS AN OBSERVER OBJECT */

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}