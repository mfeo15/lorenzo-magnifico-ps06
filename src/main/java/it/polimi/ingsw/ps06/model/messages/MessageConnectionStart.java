package it.polimi.ingsw.ps06.model.messages;

public class MessageConnectionStart extends Client2Server{

	@Override
	public <T> T accept(MessageVisitor visitor) {
		visitor.visit(this);
		return null;
	}

}
