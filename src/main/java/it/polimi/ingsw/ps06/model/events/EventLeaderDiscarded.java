package it.polimi.ingsw.ps06.model.events;

public class EventLeaderDiscarded extends EventLeader{
	
	public EventLeaderDiscarded(int leaderCode) {
		super(leaderCode);
	}

	@Override
	public void accept(EventVisitor visitor) {
		visitor.visit(this);
	}
}
