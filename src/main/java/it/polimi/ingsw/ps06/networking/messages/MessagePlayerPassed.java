package it.polimi.ingsw.ps06.networking.messages;

public class MessagePlayerPassed extends Client2Server {

	@Override
	public void accept(MessageVisitor visitor) {
		visitor.visit(this);
	}

}
