package it.polimi.ingsw.ps06.model.messages;

public class BoardReady extends Client2Server {

	@Override
	public void accept(MessageVisitor visitor) {
		visitor.visit(this);
	}

}
