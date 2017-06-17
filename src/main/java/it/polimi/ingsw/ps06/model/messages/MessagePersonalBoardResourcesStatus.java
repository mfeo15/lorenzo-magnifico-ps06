package it.polimi.ingsw.ps06.model.messages;

import java.util.ArrayList;

import it.polimi.ingsw.ps06.model.Resources;

public class MessagePersonalBoardResourcesStatus extends Server2Client {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4539908542621428281L;
	
	private Resources warehouse;
	
	public MessagePersonalBoardResourcesStatus(Resources warehouse) {
		
		this.warehouse = warehouse;
	}
	
	public Resources getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Resources warehouse) {
		this.warehouse = warehouse;
	}
	
	@Override
	public void accept(MessageVisitor visitor) {
		visitor.visit(this);
	}

}
