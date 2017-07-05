package it.polimi.ingsw.ps06.networking.messages;

public class MessageLeaderHasBeenPlayed extends MessageLeader {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8227167808094521879L;

	public MessageLeaderHasBeenPlayed(int leaderCode) {
		super(leaderCode);
	}
	
	@Override
	public void accept(MessageVisitor visitor) {
		visitor.visit(this);
	}

}
