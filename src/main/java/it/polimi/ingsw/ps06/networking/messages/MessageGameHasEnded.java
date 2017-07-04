package it.polimi.ingsw.ps06.networking.messages;

public class MessageGameHasEnded extends Broadcast {

	/**
	 * 
	 */
	private static final long serialVersionUID = 510372298179762675L;
	
	private int winnerPlayerCode;
	
	
	public MessageGameHasEnded(int winnerPlayerCode) {
		this.winnerPlayerCode = winnerPlayerCode;
	}
	
	
	public int getWinnerPlayerCode() {
		return winnerPlayerCode;
	}

	public void setWinnerPlayerCode(int winnerPlayerCode) {
		this.winnerPlayerCode = winnerPlayerCode;
	}

	@Override
	public void accept(MessageVisitor visitor) {
		visitor.visit(this);
	}
}
