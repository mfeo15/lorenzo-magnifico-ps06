package it.polimi.ingsw.ps06.model.events;

public class BoardFrozenStatus extends Event {

	private boolean frozen;
	
	public BoardFrozenStatus(boolean frozen) {
		this.frozen = frozen;
	}

	public boolean isFrozen() {
		return frozen;
	}

	public void setFrozen(boolean frozen) {
		this.frozen = frozen;
	}

	@Override
	public void accept(EventVisitor visitor) {
		// TODO Auto-generated method stub

	}

}
