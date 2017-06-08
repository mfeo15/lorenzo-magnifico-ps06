package it.polimi.ingsw.ps06.model.events;

public class EventLeaderPlayed extends EventLeader {

	public EventLeaderPlayed(int leaderCode) {
		super(leaderCode);
	}

	@Override
	public void accept(EventVisitor visitor) {
		visitor.visit(this);
	}
	
}
