package it.polimi.ingsw.ps06.model.cards.leader;

public class LeaderOnTableFaceDown implements LeaderState {

	private Leader leader;
	
	public LeaderOnTableFaceDown(Leader leader) {
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
		
		leader.setLeaderState( leader.getDeactivatedLeaderState() );
		return true;
	}

	@Override
	public boolean discardLeader() {
		return false;
	}

}
