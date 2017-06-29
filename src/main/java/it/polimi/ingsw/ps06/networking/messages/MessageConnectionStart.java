package it.polimi.ingsw.ps06.networking.messages;

public class MessageConnectionStart extends Client2Server{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4987517289791097039L;

	@Override
	public void accept(MessageVisitor visitor) {
		visitor.visit(this);
	}

}
