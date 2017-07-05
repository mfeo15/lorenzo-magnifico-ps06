package it.polimi.ingsw.ps06.networking.messages;

public class MessageUserHasLogged extends Broadcast {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String username;
	
	private int gameCounter;
	private int winCounder;
	private int secondPlaceCounter;
	
	private int maxScore;
	
	public MessageUserHasLogged(String username, int gameCounter, int winCounder, int secondPlaceCounter, int maxScore) {
		this.username = username;
		this.gameCounter = gameCounter;
		this.winCounder = winCounder;
		this.secondPlaceCounter = secondPlaceCounter;
		this.maxScore = maxScore;
	}

	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
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

	@Override
	public void accept(MessageVisitor visitor) {
		visitor.visit(this);
	}

}
