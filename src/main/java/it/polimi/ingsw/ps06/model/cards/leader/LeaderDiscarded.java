package it.polimi.ingsw.ps06.model.cards.leader;

public class LeaderDiscarded implements LeaderState {

	private Leader leader;
	
	public LeaderDiscarded(Leader leader) {
		this.leader = leader;
	}
	
	@Override
	public boolean playLeader() {
		return false;
	}

	@Override
	public boolean activateLeader() {
		return false;
	}

	@Override
	public boolean deactivateLeader() {
		return false;
	}

	@Override
	public boolean discardLeader() {
		return false;
	}
}
