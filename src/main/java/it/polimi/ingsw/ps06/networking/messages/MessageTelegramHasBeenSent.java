package it.polimi.ingsw.ps06.networking.messages;

public class MessageTelegramHasBeenSent extends Broadcast {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int player;
	private String telegram;
	
	public MessageTelegramHasBeenSent(int player, String telegram) {
		this.player = player;
		this.telegram = telegram;
	}


	public int getPlayer() {
		return player;
	}

	public void setPlayer(int player) {
		this.player = player;
	}

	public String getTelegram() {
		return telegram;
	}

	public void setTelegram(String telegram) {
		this.telegram = telegram;
	}

	
	@Override
	public void accept(MessageVisitor visitor) {
		visitor.visit(this);
	}

}
