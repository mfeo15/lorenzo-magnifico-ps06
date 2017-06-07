package it.polimi.ingsw.ps06.model.messages;

import it.polimi.ingsw.ps06.model.Types.Action;
import it.polimi.ingsw.ps06.model.Types.ColorPalette;

public class MessageUser extends Server2Client {
	
	String username;
	
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
