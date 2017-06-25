package it.polimi.ingsw.ps06.networking.messages;

public class MessageUser extends Client2Server {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -886537323937702140L;
	
	private String username;
	
	public MessageUser(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username=username;
	}
	
	@Override
	public void accept(MessageVisitor visitor) {
		 visitor.visit(this);
	}

}
