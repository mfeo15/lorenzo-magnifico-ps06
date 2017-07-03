package it.polimi.ingsw.ps06.networking.messages;

public class MessageBoardSetupExcomCards extends Broadcast {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1599520886249943921L;
	
	private int periodOne;
	private int periodTwo;
	private int periodThree;
	
	public MessageBoardSetupExcomCards( int periodOne, int periodTwo, int periodThree) {
		this.periodOne = periodOne;
		this.periodTwo = periodTwo;
		this.periodThree = periodThree;
	}
	
	public int getPeriodOne() {
		return this.periodOne;
	}
	
	public int getPeriodTwo() {
		return this.periodTwo;
	}
	
	public int getPeriodThree() {
		return this.periodThree;
	}

	@Override
	public void accept(MessageVisitor visitor) {
		visitor.visit(this);
	}

}
