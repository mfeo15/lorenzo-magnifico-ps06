package it.polimi.ingsw.ps06.model.events;

import it.polimi.ingsw.ps06.model.events.EventClose;
import it.polimi.ingsw.ps06.model.events.EventLeaderActivated;
import it.polimi.ingsw.ps06.model.events.EventLeaderDiscarded;
import it.polimi.ingsw.ps06.model.events.EventLeaderPlayed;
import it.polimi.ingsw.ps06.model.events.EventMemberPlaced;
import it.polimi.ingsw.ps06.model.events.StoryBoard2Board;
import it.polimi.ingsw.ps06.model.events.StoryBoard2Room;

public interface EventVisitor {

	/**
	 * <p>Metodo per la visita di un evento EventClose</p>
	 * <p>L'evento viene richiamato quando un Client ha scaturito
	 * un termine dell'applicazione, richiedendo di uscire dal gioco</p>
	 * 
	 * @param	event	evento da visitare
	 */
	void visit(EventClose event);

	/**
	 * <p>Metodo per la visita di un evento StoryBoard2Room</p>
	 * <p>L'evento viene richiamato quando un Client ha richiesto di accedere alla view Room</p>
	 * 
	 * @param	event	evento da visitare
	 */
	void visit(StoryBoard2Room event);

	/**
	 * <p>Metodo per la visita di un evento StoryBoard2Board</p>
	 * <p>L'evento viene richiamato quando un Client ha richiesto di accedere alla view Board</p>
	 * 
	 * @param	event	evento da visitare
	 */
	void visit(StoryBoard2Board event);

	/**
	 * <p>Metodo per la visita di un evento RoomHasLoaded</p>
	 * <p>L'evento viene richiamato quando la view Room ha terminato con successo
	 * di svilupparsi ed è quindi pronta a proseguire</p>
	 * 
	 * @param	event	evento da visitare
	 */
	public void visit(RoomHasLoaded event);

	/**
	 * <p>Metodo per la visita di un evento BoardHasLoaded</p>
	 * <p>L'evento viene richiamato quando la view Board ha terminato con successo
	 * di svilupparsi ed è quindi pronta a proseguire</p>
	 * 
	 * @param	event	evento da visitare
	 */
	public void visit(BoardHasLoaded event);

	/**
	 * <p>Metodo per la visita di un evento MemberPlaced</p>
	 * <p>L'evento viene richiamato quando la l'utente posiziona un membro in uno
	 * dei vari spazi azione della Board</p>
	 * 
	 * @param	event	evento da visitare
	 */
	public void visit(EventMemberPlaced event);

	/**
	 * <p>Metodo per la visita di un evento LeaderDiscarded</p>
	 * <p>L'evento viene richiamato quando la l'utente scarta una carta Leader</p>
	 * 
	 * @param	event	evento da visitare
	 */
	public void visit(EventLeaderDiscarded event);

	/**
	 * <p>Metodo per la visita di un evento LeaderActivated</p>
	 * <p>L'evento viene richiamato quando la l'utente attiva una carta Leader</p>
	 * 
	 * @param	event	evento da visitare
	 */
	public void visit(EventLeaderActivated event);

	/**
	 * <p>Metodo per la visita di un evento LeaderPlayed</p>
	 * <p>L'evento viene richiamato quando la l'utente gioca una carta Leader</p>
	 * 
	 * @param	event	evento da visitare
	 */
	public void visit(EventLeaderPlayed event);

}