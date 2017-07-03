package it.polimi.ingsw.ps06.model.cards.leader;

public interface LeaderState {

	boolean playLeader();
	boolean activateLeader();
	boolean deactivateLeader();
	boolean discardLeader();
}
