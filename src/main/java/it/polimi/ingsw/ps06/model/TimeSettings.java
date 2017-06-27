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
	*@return nothing 
	*
	**/
	
	public TimeSettings() {
		
	}
	
	/**
	* Costruttore della classe
	*
	*@param x	valore del timeout del server dopo il raggiungimento numero minimo giocatori, in secondi
	*@param y	numero minimo di giocatori
	*@param z	valore del tempo limite per effettuare un'azione, in secondi
	*
	*@return nothing 
	*
	**/
	
	public TimeSettings(int x, int y) {
		this.timeoutWaitingConnections = x;
		this.timeoutAction = y;
	}
	
	/**
	* Getter del timeout del server
	*
	*@return timeoutServer	time out del server dopo raggiungimento numero giocatori
	* 
	**/
	
	public int getTimeoutWaitingConnections(){
		return timeoutWaitingConnections;
	}
	
	/**
	* Getter del timeout azione
	*
	*@return timeoutAction	valore del tempo limite per effettuare un'azione, in secondi
	* 
	**/
	
	public int getTimeoutAction(){
		return timeoutAction;
	}
}
