package it.polimi.ingsw.ps06.networking.messages;

import java.util.EnumMap;
import it.polimi.ingsw.ps06.model.Types.ColorPalette;

public class MessageBoardSetupTimeoutAction extends Broadcast {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6736709067770491733L;
	
	private int timeout;
	
	public MessageBoardSetupTimeoutAction(int timeout) {
		
		this.timeout = timeout;
	}
	
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	
	public int getTimeout() {
		return this.timeout;
	}
	
	@Override
	public void accept(MessageVisitor visitor) {
		visitor.visit(this);
	}
}
