package it.polimi.ingsw.ps06.networking.messages;

public interface MessageVisitable {

	/**
	 * Metodo di parsing per un messaggio tramite il Visitor Pattern Design
	 * 
	 * @param	visitor		parser implementativo
	 */
	void accept(MessageVisitor visitor);
}
