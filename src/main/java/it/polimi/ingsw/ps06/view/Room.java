package it.polimi.ingsw.ps06.view;

import java.io.IOException;
import java.util.Observer;

/**
 * Interfaccia comune per tutti i tipi di Room View (CLI/GUI) per incapsularne il comportamento a discapito delle tecnologie differenti
 *
 * @author  ps06
 * @version 1.1
 * @since   2017-05-18
 */
public interface Room {

	/**
	 * Metodo per impostare i dettagli di un giocatore connesso alla partita
	 * 
	 * @param 	name	nome utente del giocatore
	 * @param 	index	indice di ordine del giocatore
	 */
	void setPlayer(String name, int index);

	/**
	 * Metodo per svuotare la lista dei giocatori
	 */
	void clearPlayers(); 

	/**
	 * Metodo per eseguire il Login con le credenziali personali
	 * 
	 * @param	username	stringa per lo username dell'utenza
	 * @param 	password	stringa per la password dell'utenza
	 */
	void giveCredentials(String username, String password);

	/**
	 * Metodo per l'aggiunta di un nuovo observer alla View
	 * 
	 * @param	o	observer da aggiungere
	 */
	void addNewObserver(Observer o);

	/**
	 * Metodo per invocare un inizio di partita da uno dei giocatori
	 */
	void startGame();

	/**
	 * Metodo invocato per chiudere l'appliazione
	 */
	void notifyExit();

	/**
	 * Metodo per comunicare alla View di questo Client che è incominciata una partita
	 */
	void hasStarted();

	/**
	 * Metodo invocato per caricare la View, mostrando a video il proprio contenuto
	 * 
	 * @throws	IOException		se la visualizzazione del contenuto ha causao qualche errore
	 */
	void show() throws IOException;

	/**
	 * Metodo invocato al termine del caricamento della View e per comunicare che
	 * lo stato pronto per proseguire
	 */
	void hasLoaded();

	/**
	 * Metodo invocato a seguito di un Login avvenuto con successo
	 * 
	 * @param 	username	nome utente dell'utenza appena loggata
	 * @param 	stat1		valore della statistica1 del giocatore
	 * @param 	stat2		valore della statistica2 del giocatore
	 * @param 	stat3		valore della statistica3 del giocatore
	 * @param 	stat4		valore della statistica4 del giocatore
	 */
	void userHasLoggedIn(String username, int stat1, int stat2, int stat3, int stat4);
}
