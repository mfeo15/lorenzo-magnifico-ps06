package it.polimi.ingsw.ps06.model.messages;

public class MessageGameStart extends Client2Server {
	
	public MessageGameStart() {
		
	}
	
	@Override
	public void accept(MessageVisitor visitor) {
		visitor.visit(this);
	}

}
