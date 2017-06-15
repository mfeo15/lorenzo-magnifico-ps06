package it.polimi.ingsw.ps06.model;

public class LeaderOnTableFaceUp implements LeaderState {

	private Leader leader;
	
	public LeaderOnTableFaceUp(Leader leader) {
		this.leader = leader;
	}
	
	@Override
	public void playLeader() {
		System.out.println("LEADER HAVE ALREADY BEEN PLAYED");
	}

	@Override
	public void activateLeader() {
		System.out.println("LEADER HAS BEEN ACTIVATED");
	}

	@Override
	public void deactivateLeader() {
		System.out.println("LEADER IS ALREADY DEACTIVE, PLEASE ACTIVATE IT");
	}
}
