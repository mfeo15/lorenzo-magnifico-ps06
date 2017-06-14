package it.polimi.ingsw.ps06.model;

public class LeaderInHand implements LeaderState {

	private Leader leader;
	
	public LeaderInHand(Leader leader) {
		this.leader = leader;
	}
	
	@Override
	public void playLeader() {
		
		System.out.println("LEADER CARD HAS BEEN PLAYIED");
	}

	@Override
	public void activateLeader() {
		
		System.out.println("LEADER MUST BE PLAYIED BEFORE ACTIVATING");
	}

	@Override
	public void deactivateLeader() {
		
		System.out.println("LEADER MUST BE PLAYIED AND ACTIVATED BEFORE DEACTIVATE");
	}

}
