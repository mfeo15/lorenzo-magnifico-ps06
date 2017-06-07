package it.polimi.ingsw.ps06.model.messages;

public class EventLeaderPlayed extends EventLeader {

	public EventLeaderPlayed(int leaderCode) {
		super(leaderCode);
	}

	@Override
	public void accept(MessageVisitor visitor) {
		visitor.visit(this);
	}
	
}
