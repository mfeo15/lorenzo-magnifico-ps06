package it.polimi.ingsw.ps06.networking.messages;

public class MessageUser extends Client2Server {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -886537323937702140L;
	
	private String username;
	private String password;
	
	public MessageUser(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public void accept(MessageVisitor visitor) {
		 visitor.visit(this);
	}

}
