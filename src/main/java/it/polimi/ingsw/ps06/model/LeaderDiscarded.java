package it.polimi.ingsw.ps06.model;

public class LeaderDiscarded implements LeaderState {

	private Leader leader;
	
	public LeaderDiscarded(Leader leader) {
		this.leader = leader;
	}
	
	@Override
	public String playLeader() {
		return "La carta Leader " + leader.title + "è già stata scartata e non può essere rigiocata";
	}

	@Override
	public String activateLeader() {
		return "La carta Leader " + leader.title + "è già stata scartata e non può essere rigiocata";
	}

	@Override
	public String deactivateLeader() {
		return "La carta Leader " + leader.title + "è già stata scartata e non può essere capovolta";
	}

	@Override
	public String discardLeader() {
		return "La carta Leader " + leader.title + "è già stata scartata";
	}
}
