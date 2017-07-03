package it.polimi.ingsw.ps06.model.cards.leader;

public class LeaderOnTableFaceUp implements LeaderState {

	private Leader leader;
	
	public LeaderOnTableFaceUp(Leader leader) {
		this.leader = leader;
	}
	
	@Override
	public boolean playLeader() {
		return false;
	}

	@Override
	public boolean activateLeader() {
		
		if (! leader.isOncePerRoundEffect())
			return false;
		
		leader.setLeaderState( leader.getActivatedLeaderState() );
		return true;
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
