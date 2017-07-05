package it.polimi.ingsw.ps06.model.cards.leader;

public interface LeaderState {

	
	/**
	 * Metodo invocato per cambiare stato al'oggetto, da uno qualunque 
	 * a quello LeaderState.ON_TABLE_FACE_UP
	 * 
	 * @return	true	se lo stato attuale ha permesso il completamento dell'operazione
	 */
	boolean playLeader();
	
	/**
	 * Metodo invocato per cambiare stato al'oggetto, da uno qualunque 
	 * a quello LeaderState.ON_TABLE_FACE_DOWN
	 * 
	 * @return	true	se lo stato attuale ha permesso il completamento dell'operazione
	 */
	boolean activateLeader();
	
	/**
	 * Metodo invocato per cambiare stato al'oggetto, da uno qualunque 
	 * a quello LeaderState.ON_TABLE_FACE_UP
	 * 
	 * @return	true	se lo stato attuale ha permesso il completamento dell'operazione
	 */
	boolean deactivateLeader();
	
	/**
	 * Metodo invocato per cambiare stato al'oggetto, da uno qualunque 
	 * a quello LeaderState.DISCARDED
	 * 
	 * @return	true	se lo stato attuale ha permesso il completamento dell'operazione
	 */
	boolean discardLeader();
}
