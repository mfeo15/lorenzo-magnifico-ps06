package it.polimi.ingsw.ps06.model.messages;

public class MessagePlayerID extends Server2Client {
	
	private int ID;
	
	public MessagePlayerID(int ID) {
		this.ID = ID;
	}
	
	public void setID(int ID) {
		this.ID = ID;
	}
	
	public int getID() {
		return this.ID;
	}

	@Override
	public void accept(MessageVisitor visitor) {
		visitor.visit(this);
	}

}
