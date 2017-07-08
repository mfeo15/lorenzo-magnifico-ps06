package it.polimi.ingsw.ps06.networking;

/**
 * Classe per la gestione delle utenze connesse al Server
 *
 * @author  ps06
 * @version 1.0
 * @since   2017-07-06 
 */
public class User {

	private String username;
	private String password;

	private int gameCounter;
	private int winCounder;
	private int secondPlaceCounter;

	private int maxScore;

	/**
	 * Costruttore della classe
	 */
	public User() {
		this.gameCounter = 0;
		this.winCounder = 0;
		this.secondPlaceCounter = 0;

		this.maxScore = 0;
	}

	/**
	 * Getter per lo username
	 * 
	 * @return	lo username dell'utenza
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Setter per lo username
	 * 
	 * @param 	username	nome utente da settare
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Getter per la password
	 * 
	 * @return	la password dell'utente
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Setter per la password
	 * 
	 * @param 	password	password da settare
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Getter per il contatore delle partite giocate
	 * 
	 * @return	il numero di partite giocate
	 */
	public int getGameCounter() {
		return gameCounter;
	}

	/**
	 * Setter per il contatore delle partite giocate
	 * 
	 * @param	gameCounter		numero delle partite giocate da settare
	 */
	public void setGameCounter(int gameCounter) {
		this.gameCounter = gameCounter;
	}

	/**
	 * Getter per il contatore delle partite vinte
	 * 
	 * @return	il numero di partite vinte
	 */
	public int getWinCounder() {
		return winCounder;
	}

	/**
	 * Setter per il contatore delle partite vinte
	 * 
	 * @param 	winCounder	il numero di partite vinte da settare
	 */
	public void setWinCounder(int winCounder) {
		this.winCounder = winCounder;
	}

	/**
	 * Getter per il contatore delle partite con piazzamento al secondo posto
	 * 
	 * @return	il numero di partite con piazzamento al secondo posto
	 */
	public int getSecondPlaceCounter() {
		return secondPlaceCounter;
	}

	/**
	 * Setter per il contatore delle partite con piazzamento al secondo posto
	 * 
	 * @param 	secondPlaceCounter	il numero di partite con piazzamento al secondo posto da settare
	 */
	public void setSecondPlaceCounter(int secondPlaceCounter) {
		this.secondPlaceCounter = secondPlaceCounter;
	}

	/**
	 * Getter per il punteggio massimo ottenuto
	 * 
	 * @return	il numero di partite giocate
	 */
	public int getMaxScore() {
		return maxScore;
	}

	/**
	 * Setter per il punteggio massimo totalizzato
	 * 
	 * @param 	maxScore	punteggio massimo totalizzato da settare
	 */
	public void setMaxScore(int maxScore) {
		this.maxScore = maxScore;
	}

	/**
	 * Metodo per la gestione dell'autenticazione, confrontando i parametri di accesso
	 * 
	 * @param 	username	nome utente di accesso
	 * @param 	password	password di acesso
	 * 
	 * @return	true		se la combinazione username + password è corretta
	 */
	public boolean autenticate(String username, String password) {
		return (this.username.equals(username) && this.password.equals(password));
	}
}
