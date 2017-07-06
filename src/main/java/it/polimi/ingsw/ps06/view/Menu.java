package it.polimi.ingsw.ps06.view;

import java.io.IOException;
import java.util.Observer;

/**
 * Interfaccia comune per tutti i tipi di Menu View (CLI/GUI) per incapsularne il comportamento a discapito delle tecnologie differenti
 *
 * @author  ps06
 * @version 1.1
 * @since   2017-05-18
 */
public interface Menu {

	/**
	 * Metodo per l'aggiunta di un nuovo observer alla View
	 * 
	 * @param	o	observer da aggiungere
	 */
	void addNewObserver(Observer o);

	/**
	 * Metodo invocato per passare alla View della WaitingRoom
	 */
	void gotToRoom();

	/**
	 * Metodo invocato per chiudere l'appliazione
	 */
	void notifyExit();

	/**
	 * Metodo invocato per caricare la View, mostrando a video il proprio contenuto
	 * 
	 * @throws	IOException		se la visualizzazione del contenuto ha causao qualche errore
	 */
	void show() throws IOException;
}
