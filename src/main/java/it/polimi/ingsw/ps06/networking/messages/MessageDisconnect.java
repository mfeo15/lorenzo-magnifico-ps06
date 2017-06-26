package it.polimi.ingsw.ps06.networking.messages;

public class MessageDisconnect extends Client2Server {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6828770967227674106L;

	@Override
	public void accept(MessageVisitor visitor) {
		visitor.visit(this);
	}

}
