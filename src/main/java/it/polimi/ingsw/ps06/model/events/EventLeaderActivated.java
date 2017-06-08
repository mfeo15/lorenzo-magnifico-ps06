package it.polimi.ingsw.ps06.model.events;

public class EventLeaderActivated extends EventLeader {
	
	public EventLeaderActivated(int leaderCode) {
		super(leaderCode);
	}

	@Override
	public void accept(EventVisitor visitor) {
		visitor.visit(this);
	}

}
