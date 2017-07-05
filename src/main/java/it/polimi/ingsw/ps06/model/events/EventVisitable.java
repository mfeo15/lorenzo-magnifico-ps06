package it.polimi.ingsw.ps06.model.events;

public interface EventVisitable {

	/**
	 * Metodo di parsing per un evento tramite il Visitor Pattern Design
	 * 
	 * @param	visitor		parser implementativo
	 */
	void accept(EventVisitor visitor);
}
