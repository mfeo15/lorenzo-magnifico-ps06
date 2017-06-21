package it.polimi.ingsw.ps06.model.messages;

public class MessageBoardSetupDevCards extends Broadcast {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1599520886249943921L;
	
	private int[] roundCards;
	
	public MessageBoardSetupDevCards( int[] roundCards) {
		this.roundCards = roundCards;
	}
	
	public int[] getRoundCards() {
		return this.roundCards;
	}

	@Override
	public void accept(MessageVisitor visitor) {
		visitor.visit(this);
	}

}
