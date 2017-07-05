package it.polimi.ingsw.ps06.model;

/**
* Classe per la gestione dei tempi di inizio partita e di azione
*
* @author  ps06
* @version 1.0
* @since   2017-06-16
* 
**/

public class TimeSettings {
	
	private int timeoutWaitingConnections;
	private int timeoutAction;
	
	/**
	 * Costruttore della classe
	 *
	 * @param	timeoutWaitingConnections	valore del timeout del server dopo il raggiungimento numero minimo giocatori, in secondi
	 * @param	timeoutAction				valore del timeout per ogni singola azione del giocatore, in secondi
	 */	
	public TimeSettings(int timeoutWaitingConnections, int timeoutAction) {
		this.timeoutWaitingConnections = timeoutWaitingConnections;
		this.timeoutAction = timeoutAction;
	}
	
	/**
	 * Getter del timeout del server
	 *
	 * @return	timeout del server dopo raggiungimento numero giocatori
	 */
	public int getTimeoutWaitingConnections(){
		return timeoutWaitingConnections;
	}
	
	/**
	 * Getter del timeout di una azione
	 *
	 * @return	timeout per ogni singola azione del giocatore, in secondi
	 */
	public int getTimeoutAction(){
		return timeoutAction;
	}
}
