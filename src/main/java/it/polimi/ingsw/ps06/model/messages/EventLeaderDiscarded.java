package it.polimi.ingsw.ps06.model.messages;

public class EventLeaderDiscarded extends EventLeader{
	
	public EventLeaderDiscarded(int leaderCode) {
		super(leaderCode);
	}

	@Override
	public void accept(MessageVisitor visitor) {
		visitor.visit(this);
	}
}
