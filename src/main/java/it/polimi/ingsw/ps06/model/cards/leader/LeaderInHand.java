package it.polimi.ingsw.ps06.model.cards.leader;

public class LeaderInHand implements LeaderState {

	private Leader leader;
	
	public LeaderInHand(Leader leader) {
		this.leader = leader;
	}
	
	@Override
	public String playLeader() {
		leader.setLeaderState( leader.getActivatedLeaderState() );		
		return "La carta Leader " + leader.getTitle() + "è stata giocata";
	}

	@Override
	public String activateLeader() {
		return "La carta Leader " + leader.getTitle() + "deve essere giocata prima di capovolgerla";
	}

	@Override
	public String deactivateLeader() {	
		return "La carta Leader " + leader.getTitle() + "deve essere giocata ed attivata prima di capovolgerla";
	}

	@Override
	public String discardLeader() {	
		leader.setLeaderState( leader.getDiscardedLeaderState() );		
		return "La carta Leader " + leader.getTitle() + "è stata scartata";
	}

}
