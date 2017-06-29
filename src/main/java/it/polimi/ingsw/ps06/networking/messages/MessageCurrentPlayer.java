package it.polimi.ingsw.ps06.networking.messages;

public class MessageCurrentPlayer extends Broadcast {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2031171721562878041L;
	private int ID;
	
	public MessageCurrentPlayer(int ID) {
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
