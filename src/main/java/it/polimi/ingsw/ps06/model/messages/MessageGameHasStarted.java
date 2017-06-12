package it.polimi.ingsw.ps06.model.messages;

public class MessageGameHasStarted extends Server2Client {

	@Override
	public void accept(MessageVisitor visitor) {
		visitor.visit(this);
	}

}
