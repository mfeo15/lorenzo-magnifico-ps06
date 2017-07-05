package it.polimi.ingsw.ps06.networking.messages;

public class MessageTelegram extends Client2Server {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -886537323937702140L;
	
	private String telegram;
	
	public MessageTelegram(String telegram) {
		this.telegram = telegram;
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
