package it.polimi.ingsw.ps06.model;

public interface EventVisitor {

	//public void visit(Trouble v);
	public void visit(MemberPlaced memberPlaced);
	public void visit(LeaderDiscarded leaderDiscarded);
}