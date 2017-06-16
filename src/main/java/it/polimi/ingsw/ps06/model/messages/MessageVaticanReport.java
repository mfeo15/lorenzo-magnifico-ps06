package it.polimi.ingsw.ps06.model.messages;

public class MessageVaticanReport extends Broadcast {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7476682130116900548L;
	
	private int[] excommunicatedPlayers;
	
	public MessageVaticanReport(int[] a) {
		this.excommunicatedPlayers = a;
	}
	
	public int[] getWaitingConnections() {
		return excommunicatedPlayers;
	}
	
	public void setWaitingConnections(int[] a) {
		this.excommunicatedPlayers = a;
	}

	@Override
	public void accept(MessageVisitor visitor) {
		visitor.visit(this);
	}

}
