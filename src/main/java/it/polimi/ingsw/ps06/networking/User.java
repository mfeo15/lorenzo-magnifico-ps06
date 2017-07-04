package it.polimi.ingsw.ps06.networking;

public class User {
	
	private String username;
	private String password;
	
	private int gameCounter;
	private int winCounder;

	private int secondPlaceCounter;
	
	private int maxScore;
	
	public User() {
		this.gameCounter = 0;
		this.winCounder = 0;
		this.secondPlaceCounter = 0;
		
		this.maxScore = 0;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getGameCounter() {
		return gameCounter;
	}

	public void setGameCounter(int gameCounter) {
		this.gameCounter = gameCounter;
	}

	public int getWinCounder() {
		return winCounder;
	}

	public void setWinCounder(int winCounder) {
		this.winCounder = winCounder;
	}

	public int getSecondPlaceCounter() {
		return secondPlaceCounter;
	}

	public void setSecondPlaceCounter(int secondPlaceCounter) {
		this.secondPlaceCounter = secondPlaceCounter;
	}

	public int getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(int maxScore) {
		this.maxScore = maxScore;
	}
	
	public boolean autenticate(String username, String password) {
		return (this.username.equals(username) && this.password.equals(password));
	}
}
