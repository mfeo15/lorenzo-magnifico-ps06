package it.polimi.ingsw.ps06.view;

import java.io.IOException;
import java.util.Observer;

import it.polimi.ingsw.ps06.model.Types.Action;
import it.polimi.ingsw.ps06.model.Types.ColorPalette;

public interface Board {
	
	/**
	 * Metodo per mostrare a video la Personal View di un giocatore
	 * 
	 * @param	index	identificativo del giocatore d'interesse
	 */
	void showPersonalView(int index);
	
	/**
	 * Metodo invocato per indicare che la Personal View è stata mostrata completamente
	 */
	void hasLoadedPersonalView();
	
	/**
	 * Setter per i componenti Resources della personal board
	 * 
	 * @param 	coin		numero di coin da mostrare 
	 * @param 	wood		numero di wood da mostrare
	 * @param 	stone		numero di stone da mostrare
	 * @param 	servant		numero di servant da mostrare
	 * @param 	victory		numero di vicotory points da mostrare
	 * @param 	military	numero di military points da mostrare
	 * @param 	faith		numero di faith points da mostrare
	 */
	void setResourcesPersonalView (int coin, int wood, int stone, int servant, int victory, int military, int faith);
	
	/**
	 * Setter per le carte territorio della personal board
	 * 
	 * @param 	id		code identificativo della carta
	 * @param 	index	indici di ordinamento
	 */
	void setTerritoryCardPersonalView (int id, int index);
	
	/**
	 * Setter per le carte edificio della personal board
	 * 
	 * @param 	id		code identificativo della carta
	 * @param 	index	indici di ordinamento
	 */
	void setBuildingCardPersonalView (int id, int index);
	
	/**
	 * Setter per l'indice della zona Raccolto
	 * 
	 * @param	value	indice da settare
	 */
	void setHarvestIndex(int value);
	
	/**
	 * Setter per l'indice della zona Produzione
	 * 
	 * @param	value	indice da settare
	 */
	void setProductionIndex(int value);
	
	/**
	 * Setter per l'indice del Palazzo Consiglio
	 * 
	 * @param	value	indice da settare
	 */
	void setCouncilIndex(int value);
	
	/**
	 * Setter per il timer di una azione di gioco
	 * 
	 * @param	seconds		secondi consentiti per una azione di gioco
	 */
	void setTimer(int seconds);
	
	/**
	 * Setter per il valore del periodo e del round attuale
	 * 
	 * @param 	period	valore del periodo attuale
	 * @param 	round	valore del round attuale
	 */
	void setPeriodRound(int period, int round);
	
	/**
	 * Setter per associare un particolare colore ad un giocatore
	 * 
	 * @param 	s	stringa rappresentante un colore
	 */
	void setPlayerColor(String s);
	
	/**
	 * Setter per individuare il player di riferimento a questa View
	 * 
	 * @param 	index	codice del giocatore
	 */
	void setOwnerPlayerIndex(int index);
	
	/**
	 * Setter per il nome utente di un giocatore
	 * 
	 * @param 	s		nome associato al giocatore
	 * @param 	index	indice per ordinamento
	 */
	void setPlayersNames(String s, int index);
	
	/**
	 * Setter per il giocatore corrente
	 * 
	 * @param 	id	codice identificativo del giocatore da rendere corrente
	 */
	void setCurrentPlayerID(int id);
	
	/**
	 * Setter per il valore dei dadi
	 * 
	 * @param 	black	valore del dado nero
	 * @param 	white	valore del dado bianco
	 * @param 	orange	valore del dado arancione
	 */
	void setDices(int black, int white, int orange);
	
	/**
	 * Setter per le tre carte scomuniche
	 * 
	 * @param 	code1	codice della carta scomunica del primo periodo
	 * @param 	code2	codice della carta scomunica del secondo periodo
	 * @param 	code3	codice della carta scomunica del terzo periodo
	 */
	void setExcommunicationTiles(int code1, int code2, int code3);
	
	/**
	 * Setter per le carte sviluppo del turno attuale
	 * 
	 * @param 	cards	insieme di codici identificative delle carte del turno
	 */
	void setCards(int[] cards);
	
	/**
	 * Setter per le quattro carte Leader di un giocatore
	 * 
	 * @param	code1	codice della prima carta Leader
	 * @param 	code2	codice della seconda carta Leader
	 * @param 	code3	codice della terza carta Leader
	 * @param 	code4	codice della quarta carta Leader
	 */
	void setLeaders(int code1, int code2, int code3, int code4);
	
	/**
	 * Setter per le risorse personali, unicamente relative al player della View
	 * 
	 * @param 	coin		numero di coin da mostrare
	 * @param 	wood		numero di wood da mostrare
	 * @param 	stone		numero di stone da mostrare
	 * @param 	servant		numero di servant da mostrare
	 * @param 	victory		numero di victory da mostrare
	 * @param 	military	numero di military da mostrare
	 * @param 	faith		numero di faith da mostrare
	 */
	void setPersonalResources(int coin, int wood, int stone, int servant, int victory, int military, int faith);
	
	/**
	 * Setter per il numero di giocatori in partita (setup della Board)
	 * 
	 * @param 	number	numero di giocatori nella partita
	 */
	void setPlayerNumber(int number);
	
	/**
	 * Setter per l'ordine di gioco dei vari players
	 * 
	 * @param 	players		insieme ordinato di giocatori a seconda dell'ordine di azione
	 */
	void setOrder(int[] players);	
	
	/**
	 * Metodo per resettare i Leader nella situazione "a faccia in su"
	 */
	void activateLeaders();
	
	/**
	 * Metodo invocato per avviare il Timer per l'azione
	 */
	void startTimer();
	
	/**
	 * Metodo per notificare il completamento del Timer per le azioni
	 */
	void notifyTimesUp();
	
	/**
	 * Metodo invocato per chiudere l'appliazione
	 */
	void notifyExit();
	
	/**
	 * Metodo invocato a seguito di un piazzamento del famigliare dal parte dell'utente
	 * 
	 * @param 	chosenAction	tipo di azione associata al piazzamento eseguito	
	 * @param 	memberIndex		identificativo del famigliare del piazzamento
	 * @param 	servants		numero di servitori impiegati
	 * 
	 * @see						it.polimi.ingsw.ps06.model.Types
	 */
	void notifyAction(Action chosenAction, int memberIndex, int servants);
	
	/**
	 * Metodo invocato per aggiornare la View a seguito di una modifica del Model sul piazzamento dei famigliari
	 * 
	 * @param 	chosenAction	tipo di azione associata al piazzamento eseguito
	 * @param 	color			colore del dado associato al famigliare che ha completato il piazzamento
	 * @param 	playerIndex		indice del giocatore che ha eseguito il piazzamento
	 * 
	 * @throws 	IOException
	 */
	void addMember(Action chosenAction, ColorPalette color, int playerIndex) throws IOException;
	
	/**
	 * Metodo invocato a seguito di una richiesta di "scarta Leader" dall'utente
	 * 
	 * @param 	index	numero del leader su cui applicare l'evento
	 */
	void notifyLeaderDiscard(int index);
	
	/**
	 * Metodo invocato a seguito di una richiesta di "gioca Leader" dall'utente
	 * 
	 * @param 	index	numero del leader su cui applicare l'evento
	 */
	void notifyLeaderPlacement(int index);
	
	/**
	 * Metodo invocato a seguito di una richiesta di "attiva Leader" dall'utente
	 * 
	 * @param 	index	numero del leader su cui applicare l'evento
	 */
	void notifyLeaderActivation(int index);
	
	/**
	 * Metodo invocato a seguito di un cambio stato del leader nel model
	 * 
	 * @param 	value	numero del leader su cui applicare l'evento
	 */
	void activateLeader(int value);
	
	/**
	 * Metodo invocato a seguito di un cambio stato del leader nel model
	 * 
	 * @param 	value	numero del leader su cui applicare l'evento
	 */
	void playLeader(int value);
	
	/**
	 * Metodo invocato a seguito di un cambio stato del leader nel model
	 * 
	 * @param 	value	numero del leader su cui applicare l'evento
	 */
	void discardLeader(int value);
	
	/**
	 * Metodo per mostrare un messaggio di errore sulla View
	 * 
	 * @param	s	messaggio da mostrare
	 */
	void showErrorLog(String s);
	
	/**
	 * Metodo per applicare una scomunica a seguito di una modifica del model
	 * 
	 * @param 	tileNumber		tessera scomunica di riferimento (periodo)
	 * @param 	playerIndex		identificatore del giocatore scomunicato
	 */
	void excommunicate (int tileNumber, int playerIndex);
	
	/**
	 * Setter per la BonusTile del giocatore
	 * 
	 * @param 	code	codice identificativo della BonusTile
	 * 
	 * @throws IOException
	 */
	void setBonusTilePersonalView(int code) throws IOException;
	
	/**
	 * Metodo per il setup di un nuovo round
	 */
	void setRound();
	
	/**
	 * Metodo invocato per indicare il termine di una partita e mostrare il risultato
	 * 
	 * @param 	ID		identificativo del giocatore vincente della partita
	 */
	void gameHasEnded(int ID);
	
	/**
	 * Metodo invocato per spedire un nuovo messaggio via chat
	 * 
	 * @param 	s	stringa del messaggio da spedire
	 */
	void sendChatText(String s);
	
	/**
	 * Metodo invocato per aggiungere un messaggio alla chat dopo averlo ricevuto
	 * 
	 * @param 	player		giocatore che ha scritto il messaggio
	 * @param 	s			stringa del messaggio
	 */
	void addChatText(int player, String s);
	
	/**
	 * Metodo invocato per caricare la View, mostrando a video il proprio contenuto
	 * 
	 * @throws	IOException		se la visualizzazione del contenuto ha causao qualche errore
	 */
	void show() throws IOException;
	
	/**
	 * Metodo invocato al termine del caricamento della View e per comunicare che
	 * lo stato pronto per proseguire
	 */
	void hasLoaded();
	
	/**
	 * Metodo per l'aggiunta di un nuovo observer alla View
	 * 
	 * @param	o	observer da aggiungere
	 */
	void addNewObserver(Observer o);
}
