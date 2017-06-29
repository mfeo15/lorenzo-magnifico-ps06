package it.polimi.ingsw.ps06.networking.messages;

public class MessageGameHasStarted extends Broadcast {

	/**
	 * 
	 */
	private static final long serialVersionUID = 510372298179762675L;

	@Override
	public void accept(MessageVisitor visitor) {
		visitor.visit(this);
	}
}
