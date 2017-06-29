package it.polimi.ingsw.ps06.networking.messages;

public class BoardReady extends Client2Server {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7228069309671160694L;

	@Override
	public void accept(MessageVisitor visitor) {
		visitor.visit(this);
	}

}
