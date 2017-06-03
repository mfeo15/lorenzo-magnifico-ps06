package it.polimi.ingsw.ps06;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * La classe Server resta in ascolto su una specifica porta e gestisce la ripartizione delle connessioni in ingresso,
 */
public class SocketServer {
	private static final int PORT = 12345;
	
	private ServerSocket serverSocket;
	
	private ExecutorService executor = 
			Executors.newFixedThreadPool(128);
	
	private List<Connection> connections = new ArrayList<Connection>();
	
	private List<Connection> waitingConnection = new ArrayList<Connection>();
	
	private List< MatchSet > playingConnection;
	
	
	/*
	 * Registro una connessione attiva
	 */
	private synchronized void registerConnection(Connection c){
		connections.add(c);
		
		System.out.println();
		System.out.println();
		System.out.println("New connection added!");
	}
	
	/*
	 * Deregistro una connessione
	 */
	public synchronized void deregisterConnection(Connection c){
		connections.remove(c);
		
		/*
		Connection enemy = playingConnection.get(c);
		if(enemy != null)
		enemy.closeConnection();
		playingConnection.remove(c);
		playingConnection.remove(enemy);
		*/

		waitingConnection.removeIf(connection -> connection.equals(c));
		
		/*
		Iterator<SocketAddress> iterator = waitingConnection.keySet().iterator();
		while(iterator.hasNext()){
			if(waitingConnection.get(iterator.next())==c){
				iterator.remove();
			}
		}
		*/
	}
	
	/*
	 * Mi metto in attesa di un altro giocatore
	 */
	public synchronized void rednezvous(Connection c) {
		
		waitingConnection.add(c);
		
		System.out.println();
		System.out.println();
		System.out.println("New connection in the WaitingRoom");
		
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
				MatchSet m = new MatchSet();
				m.add( (ArrayList<Connection>) waitingConnection);
				waitingConnection.clear();
			
				playingConnection.add(m);
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
	
	public void start()
	{
		while(true) {
			try {
				Socket connectionSocket = serverSocket.accept();
				
				System.out.println();
				System.out.println();
				System.out.println("IP Client : " + connectionSocket.getInetAddress());
				System.out.println("-------Connection Enstabilished-------- ");
				
				Connection connection = new Connection(connectionSocket, this);
				registerConnection(connection);
				
				executor.submit(connection);//Equivalente a new Thread(c).start();				
			} catch (IOException e) {
				System.out.println("Errore di connessione!");
			}
		}
	}
	
	public void close() throws IOException 
	{
		serverSocket.close();
	}
}