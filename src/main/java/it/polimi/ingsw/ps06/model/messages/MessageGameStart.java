package it.polimi.ingsw.ps06.model.messages;

public class MessageGameStart extends Client2Server {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1414649115193096951L;

	public MessageGameStart() {
		
	}
	
	@Override
	public void accept(MessageVisitor visitor) {
		visitor.visit(this);
	}

}
