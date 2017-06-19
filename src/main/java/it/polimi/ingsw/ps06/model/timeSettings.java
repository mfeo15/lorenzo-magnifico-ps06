package it.polimi.ingsw.ps06.model;

/**
* Classe per la gestione dei tempi di inizio partita e di azione
*
* @author  ps06
* @version 1.0
* @since   2017-06-16
* 
**/

public class timeSettings {
	private int timeoutServer;
	private int minPlayers;
	private int timeoutAction;
	
	/**
	* Costruttore della classe
	*
	*@return nothing 
	*
	**/
	
	public timeSettings(){
		
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
	
	public timeSettings(int x, int y, int z){
		this.timeoutServer=x;
		this.timeoutAction=y;
		this.minPlayers=z;
	}
	
	/**
	* Getter del timeout del server
	*
	*@return timeoutServer	time out del server dopo raggiungimento numero giocatori
	* 
	**/
	
	public int getTimeoutServer(){
		return timeoutServer;
	}
	
	/**
	* Getter del timeout azione
	*
	*@return timeoutAction	valore del tempo limite per effettuare un'azione, in secondi
	* 
	**/
	
	public int getTimeoutaction(){
		return timeoutAction;
	}
	
	/**
	* Getter del numero minimo di giocatori
	*
	*@return minPlayers		numero minimo di giocatori
	* 
	**/	

	public int getMinPlayers(){
		return minPlayers;
	}
}
