package it.polimi.ingsw.ps06.model.messages;

public class MessageGameHasStarted extends Server2Client {

	/**
	 * 
	 */
	private static final long serialVersionUID = 510372298179762675L;

	@Override
	public void accept(MessageVisitor visitor) {
		visitor.visit(this);
	}

}
