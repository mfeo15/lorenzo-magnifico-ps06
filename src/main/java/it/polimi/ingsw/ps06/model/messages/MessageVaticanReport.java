package it.polimi.ingsw.ps06.model.messages;

import java.util.ArrayList;

public class MessageVaticanReport extends Broadcast {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7476682130116900548L;
	
	private int period;

	private ArrayList<Integer> excommunicatedPlayers;
	
	public MessageVaticanReport(int period, ArrayList<Integer> a) {
		this.period = period;
		this.excommunicatedPlayers = a;
	}
	
	public ArrayList<Integer> getExcommunicatedPlayers() {
		return excommunicatedPlayers;
	}
	
	public void setExcommunicatedPlayers(ArrayList<Integer> a) {
		this.excommunicatedPlayers = a;
	}
	
	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	@Override
	public void accept(MessageVisitor visitor) {
		visitor.visit(this);
	}

}
