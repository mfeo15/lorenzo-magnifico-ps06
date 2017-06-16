package it.polimi.ingsw.ps06.model.messages;

public class MessageModel2ViewNotification extends Server2Client {
	
	private String notification;
	private int player;
	
	public MessageModel2ViewNotification(String notification) {
		this.notification = notification;
	}
	
	public void setNotification(String notification) {
		this.notification = notification;
	}
	
	public String getNotification() {
		return this.notification;
	}

	@Override
	public void accept(MessageVisitor visitor) {
		visitor.visit(this);
	}

}
