package it.polimi.ingsw.ps06;

import java.io.Serializable;
import java.util.ArrayList;

public class Message implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ArrayList<String> waitingConnections;
	
	public Message(ArrayList<String> a) {
		this.waitingConnections = a;
	}
	
	public ArrayList<String> getMessage() {
		return waitingConnections;
	}
	
	/*
	private String message;
	
	public Message(String m) {
		this.message = m;
	}
	
	public String getMessage() {
		return message;
	}
	*/
}
