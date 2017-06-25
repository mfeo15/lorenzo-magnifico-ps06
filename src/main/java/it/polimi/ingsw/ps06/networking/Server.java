package it.polimi.ingsw.ps06.networking;

import java.io.IOException;

/**
* Interfaccia di comportamento del Server
*
* @author  ps06
* @version 1.0
* @since   2017-06-03 
*/
public interface Server {

	/**
	 * Metodo per la messa in opera del Server. Mantiene la classe in esecuzione, 
	 * costantemente in attesa di connessioni in ingresso
	 * 
	 */
	void start();
	
	/**
	 * Metodo invocato a seguito di una nuova registrazione di connessione.
	 * Le connessioni vengono posizionate in una collezione d'attesa fino
	 * al momento di raggiungere abbastanza giocatori per instaurare una nuova partita
	 * 
	 * @param c	Connessione da aggiungere alla lista d'attesa
	 */
	void rednezvous(Connection c);
	
	/**
	 * Metodo invocato quando una connessione si distacca dal Server.
	 * La connessione viene rimossa dalla collezione per aggiornare lo stato.
	 * 
	 * @param c	Connessione da rimuovere
	 */
	void deregisterConnection(Connection c);
	
	/**
	 * Metodo invocato ogni qualvolta una nuova connessione viene instaurata. 
	 * La connessione viene archiviata all'interno di una collezione per aggiornare
	 * lo stato del server
	 * 
	 * @param c	Connessione da aggiungere alla collezione
	 */
	void registerConnection(Connection c);
	
	/**
	 * Metodo invocato per concludere la normale attività del Server. 
	 * Predispone la chiusura del proprio socket e di tutte le connessioni attive.
	 * 
	 * @throws IOException
	 */
	void close() throws IOException;
}
