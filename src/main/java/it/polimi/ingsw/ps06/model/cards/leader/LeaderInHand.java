package it.polimi.ingsw.ps06.model.cards.leader;

public class LeaderInHand implements LeaderState {

	private Leader leader;
	
	public LeaderInHand(Leader leader) {
		this.leader = leader;
	}
	
	@Override
	public boolean playLeader() {
		leader.setLeaderState( leader.getActivatedLeaderState() );		
		return true;
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
		leader.setLeaderState( leader.getDiscardedLeaderState() );		
		return true;
	}

}
