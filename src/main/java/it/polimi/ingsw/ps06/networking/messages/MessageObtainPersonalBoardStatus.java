package it.polimi.ingsw.ps06.networking.messages;

public class MessageObtainPersonalBoardStatus extends Client2Server {

	private int player;
	
	public MessageObtainPersonalBoardStatus(int player) {
		this.player = player;
	}
	
	public int getPlayer() {
		return player;
	}

	public void setPlayer(int player) {
		this.player = player;
	}
	
	@Override
	public void accept(MessageVisitor visitor) {
		visitor.visit(this);
	}

}
