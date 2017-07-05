package it.polimi.ingsw.ps06.networking.messages;

public abstract class MessageLeader extends Server2Client {
	
	private int leaderCode;

	public MessageLeader(int leaderCode) {
		this.leaderCode = leaderCode;
	}
	
	public int getCode() {
		return leaderCode;
	}
	
	public void setCode(int leaderCode) {
		this.leaderCode=leaderCode;
	}
}
