package it.polimi.ingsw.ps06.networking.messages;

public interface MessageVisitor {
	
	/**
	 * Messaggio contenitore di un evento che fa da tramite tra la View (Client) ed il Model (Server)
	 * 
	 * @param 	message		messaggio da visitare
	 */
	void visit(MessageEvent message);
	
	/**
	 * Messaggio di inizio connessione di un Client al Server
	 * 
	 * @param 	message		messaggio da visitare
	 */
	void visit(MessageConnectionStart message);
	
	/**
	 * Messaggio di disconnesione di un Client dal Server
	 * 
	 * @param 	message		messaggio da visitare
	 */
	void visit(MessageDisconnect message);
	
	/**
	 * Messaggio contenitore della stringa inviata da un Client in chat
	 * 
	 * @param 	message		messaggio da visitare
	 */
	void visit(MessageTelegram message);
	
	/**
	 * <p>Messaggio di aggiornamento della chat di tutti i client</p>
	 * <p>Il Messaggio viene generato a seguito della ricezione di un MessageTelegram</p>
	 * 
	 * @param 	message		messaggio da visitare
	 * 
	 * @see					it.polimi.ingsw.ps06.networking.messages.MessageTelegram
	 */
	void visit(MessageTelegramHasBeenSent message);
	
	/**
	 * Messaggio contenitore del codice identificativo del giocatore connesso
	 * 
	 * @param 	message		messaggio da visitare
	 */
	void visit(MessagePlayerID message);
	
	/**
	 * Messaggio contenitore del codice identificativo del giocatore corrente della partita
	 * 
	 * @param 	message		messaggio da visitare
	 */
	void visit(MessageCurrentPlayer message);
	
	/**
	 * Messaggio contenitore dell'insieme di giocatori ordinati per turno di gioco
	 * 
	 * @param 	message		messaggio da visitare
	 */
	void visit(MessageCurrentPlayerOrder message);
	
	/**
	 * Messaggio contenitore delle credenziali di accesso ad una utenza di gioco
	 * 
	 * @param 	message		messaggio da visitare
	 */
	void visit(MessageUser message);
	
	/**
	 * <p>Messaggio di aggiornamento delle connessioni a seguito di un Login</p>
	 * <p>Il Messaggio viene generato a seguito della ricezione di un MessageUser</p>
	 * 
	 * @param 	message		messaggio da visitare
	 * 
	 * @see					it.polimi.ingsw.ps06.networking.messages.MessageUser
	 */
	void visit(MessageUserHasLogged message);
	
	/**
	 * Messaggio contenitore dell'insieme di connessioni in attesa di una nuova partita
	 * 
	 * @param 	message		messaggio da visitare
	 */
	void visit(MessageWaitingRoomConnections message);
	
	/**
	 * Messaggio contenitore dell'insieme di giocatori attualmente impegnati in una partita
	 * 
	 * @param 	message		messaggio da visitare
	 */
	void visit(MessagePlayingConnections message);
	
	/**
	 * Messaggio di una azione di "passo" da parte di un Client
	 * 
	 * @param 	message		messaggio da visitare
	 */
	void visit(MessagePlayerPassed message);
	
	/**
	 * Messaggio di inzio di una nuova partita
	 * 
	 * @param 	message		messaggio da visitare
	 */
	void visit(MessageGameStart message);
	
	/**
	 * <p>Messaggio di inizio partita per tutte le connessioni in Waiting Room</p>
	 * <p>Il Messaggio viene generato a seguito della ricezione di un MessageGameStart</p>
	 * 
	 * @param 	message		messaggio da visitare
	 * 
	 * @see					it.polimi.ingsw.ps06.networking.messages.MessageGameStart
	 */
	void visit(MessageGameHasStarted message);
	
	/**
	 * Messaggio contenitore delo stato della partita (round e periodo)
	 * 
	 * @param 	message		messaggio da visitare
	 */
	void visit(MessageGameStatus message);
	
	/**
	 * Messaggio di termine partita
	 * 
	 * @param 	message		messaggio da visitare
	 */
	void visit(MessageGameHasEnded message);
	
	/**
	 * Messaggio contenitore dell'insieme di giocatori scomunicati a fine periodo
	 * 
	 * @param 	message		messaggio da visitare
	 */
	void visit(MessageVaticanReport message);
	
	
	/**
	 * Messaggio informativo dello stato operativo della View Board
	 * 
	 * @param 	message		messaggio da visitare
	 */
	void visit(MessageBoardReady message);
	
	/**
	 * Messaggio di setup del timeout per l'azione giocatore
	 * 
	 * @param 	message		messaggio da visitare
	 */
	void visit(MessageBoardSetupTimeoutAction message);
	
	/**
	 * Messaggio di setup del valore dei tre dadi
	 * 
	 * @param 	message		messaggio da visitare
	 */
	void visit(MessageBoardSetupDice message);
	
	/**
	 * Messaggio di setup delle carte sviluppo
	 * 
	 * @param 	message		messaggio da visitare
	 */
	void visit(MessageBoardSetupDevCards message);
	
	/**
	 * Messaggio di setup delle tessere scomunica
	 * 
	 * @param 	message		messaggio da visitare
	 */
	void visit(MessageBoardSetupExcomCards message);
	
	/**
	 * <p>Messaggio di posizionamento nella View di tutti di un famigliare piazzato da un Client</p>
	 * <p>Il Messaggio viene generato a seguito della ricezione di un MessageEvent contenente un EventMemberPlaced</p>
	 * 
	 * @param 	message		messaggio da visitare
	 * 
	 * @see					it.polimi.ingsw.ps06.networking.messages.MessageEvent
	 * @see					it.polimi.ingsw.ps06.model.events.EventMemberPlaced
	 */
	void visit(MessageBoardMemberHasBeenPlaced message);
	
	/**
	 * Messaggio contenitore dell'insieme di carte leader per il Client
	 * 
	 * @param 	message		messaggio da visitare
	 */
	void visit(MessageLeaderCards message);
	
	/**
	 * <p>Messaggio di modifica del player verso uno stato di gioco</p>
	 * <p>Il Messaggio viene generato a seguito della ricezione di un MessageEvent contenente un EventLeaderPlayed</p>
	 * 
	 * @param 	message		messaggio da visitare
	 * 
	 * @see					it.polimi.ingsw.ps06.networking.messages.MessageEvent
	 * @see					it.polimi.ingsw.ps06.model.events.EventMemberPlaced
	 */
	void visit(MessageLeaderHasBeenPlayed message);
	
	/**
	 * <p>Messaggio di modifica del player verso uno stato di attivazione</p>
	 * <p>Il Messaggio viene generato a seguito della ricezione di un MessageEvent contenente un EventLeaderActivated</p>
	 * 
	 * @param 	message		messaggio da visitare
	 * 
	 * @see					it.polimi.ingsw.ps06.networking.messages.MessageEvent
	 * @see					it.polimi.ingsw.ps06.model.events.EventMemberPlaced
	 */
	void visit(MessageLeaderHasBeenActivated message);
	
	/**
	 * <p>Messaggio di modifica del player verso uno stato scartata</p>
	 * <p>Il Messaggio viene generato a seguito della ricezione di un MessageEvent contenente un EventLeaderDiscarded</p>
	 * 
	 * @param 	message		messaggio da visitare
	 * 
	 * @see					it.polimi.ingsw.ps06.networking.messages.MessageEvent
	 * @see					it.polimi.ingsw.ps06.model.events.EventMemberPlaced
	 */
	void visit(MessageLeaderHasBeenDiscarded message);
	
	/**
	 * <p>Messaggio contenitore di tutti i dati della Personal View (risorse, carte, ecc)</p>
	 * <p>Il Messaggio viene generato a seguito della ricezione di un MessageObtainPersonalBoardStatus</p>
	 * 
	 * @param 	message		messaggio da visitare
	 * 
	 * @see					it.polimi.ingsw.ps06.networking.messages.MessageObtainPersonalBoardStatus
	 */
	void visit(MessagePersonalBoardStatus message);
	
	/**
	 * Messaggio di richiesta dati di una Personal Board
	 * 
	 * @param 	message		messaggio da visitare
	 */
	void visit(MessageObtainPersonalBoardStatus message);
	
	/**
	 * Messaggio contenitore dell'insieme di risorse di un utente
	 * 
	 * @param 	message		messaggio da visitare
	 */
	void visit(MessagePersonalBoardResourcesStatus message);
	
	/**
	 * Messaggio contenitore di una striga da comunicare al Client
	 * 
	 * @param 	message		messaggio da visitare
	 */
	void visit(MessageModel2ViewNotification message);
}