package it.polimi.ingsw.ps06.model.messages;

public class MessageGameStart extends Client2Server {

	private int playersNumber;
	
	public MessageGameStart(int playersNumber) {
		this.playersNumber = playersNumber;
	}
	
	public int getPlayersNumber() {
		return this.playersNumber;
	}
	
	public void setPlayersNumber(int playersNumber) {
		this.playersNumber = playersNumber;
	}
	
	@Override
	public void accept(MessageVisitor visitor) {
		//visitor.visit(this);
	}

}
