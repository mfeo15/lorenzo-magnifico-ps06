package it.polimi.ingsw.ps06.networking.messages;

public class MessageGameStatus extends Broadcast {

	private int currentPeriod;
	private int currentRound;
	
	public MessageGameStatus(int currentPeriod, int currentRound) {
		this.currentPeriod = currentPeriod;
		this.currentRound = currentRound;
	}
	
	public int getCurrentPeriod() {
		return currentPeriod;
	}
	
	public void setCurrentPeriod(int currentPeriod) {
		this.currentPeriod = currentPeriod;
	}
	
	public int getCurrentRound() {
		return currentRound;
	}
	
	public void setCurrentRound(int currentRound) {
		this.currentPeriod = currentRound;
	}
	
	
	@Override
	public void accept(MessageVisitor visitor) {
		visitor.visit(this);
	}

}
