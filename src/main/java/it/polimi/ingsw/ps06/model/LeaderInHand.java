package it.polimi.ingsw.ps06.model;

public class LeaderInHand implements LeaderState {

	private Leader leader;
	
	public LeaderInHand(Leader leader) {
		this.leader = leader;
	}
	
	@Override
	public String playLeader() {
		leader.setLeaderState( leader.getActivatedLeaderState() );		
		return "La carta Leader " + leader.title + "è stata giocata";
	}

	@Override
	public String activateLeader() {
		return "La carta Leader " + leader.title + "deve essere giocata prima di capovolgerla";
	}

	@Override
	public String deactivateLeader() {	
		System.out.println("LEADER MUST BE PLAYIED AND ACTIVATED BEFORE DEACTIVATE");
		return "La carta Leader " + leader.title + "deve essere giocata ed attivata prima di capovolgerla";
	}

	@Override
	public String discardLeader() {	
		leader.setLeaderState( leader.getDiscardedLeaderState() );		
		return "La carta Leader " + leader.title + "è stata scartata";
	}

}
