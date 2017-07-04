package it.polimi.ingsw.ps06.model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps06.model.Types.CardType;
import it.polimi.ingsw.ps06.model.Types.ColorPalette;
import it.polimi.ingsw.ps06.model.Types.MaterialsKind;
import it.polimi.ingsw.ps06.model.Types.PointsKind;
import it.polimi.ingsw.ps06.model.bonus_malus.BonusMalusSet;
import it.polimi.ingsw.ps06.model.cards.leader.Leader;
import it.polimi.ingsw.ps06.networking.messages.MessageLeaderCards;
import it.polimi.ingsw.ps06.networking.messages.MessageLeaderHasBeenActivated;
import it.polimi.ingsw.ps06.networking.messages.MessageLeaderHasBeenDiscarded;
import it.polimi.ingsw.ps06.networking.messages.MessageLeaderHasBeenPlayed;
import it.polimi.ingsw.ps06.networking.messages.MessageModel2ViewNotification;
import it.polimi.ingsw.ps06.networking.messages.Server2Client;

/**
* Classe rappresentante un singolo giocatore in una partita
*
* @author  ps06
* @version 1.0
* @since   2017-05-10 
*/
public class Player extends Observable implements Observer {
	
	private int ID;
	
	private ArrayList<Leader> leaders;
	
	private PersonalBoard personalBoard;
	
	private FamilyMember memberBlack;
	private FamilyMember memberWhite;
	private FamilyMember memberOrange;
	private FamilyMember memberUncolored;
	
	private BonusMalusSet bonusMalusCollection;
	 
	/**
	* Costruttore della classe
	* 
	* @param	ID	unficativo univoco per il giocatore
	*/
	public Player(int ID) {
		
		this.ID = ID;
		
		memberBlack = new FamilyMember(this, ColorPalette.DICE_BLACK);
		memberWhite = new FamilyMember(this, ColorPalette.DICE_WHITE);
		memberOrange = new FamilyMember(this, ColorPalette.DICE_ORANGE);
		memberUncolored = new FamilyMember(this);
		
		this.personalBoard = new PersonalBoard(this);
		this.leaders = new ArrayList<Leader>();
		
		this.bonusMalusCollection = new BonusMalusSet();
	}
	
	/**
	* Setter per il costruttore univoco
	* 
	* @param	ID	unficativo univoco da settare al giocatore
	*/
	public void setID(int ID) {
		this.ID = ID;
	}
	
	/**
	* Getter per il costruttore univoco
	* 
	* @return	unficativo univoco associato al giocatore
	*/
	public int getID() {
		return ID;
	}
	
	/**
	* Metodo che associa un colore al unficativo univoco del giocatore
	* 
	* @return	stringa indicante il colore associato
	*/
	public String getColorAssociatedToID() 
	{	
		switch ( ID ) {
		case 0: return "Rosso";
		case 1: return "Verde";
		case 2: return "Blu";
		case 3: return "Giallo";
		default:
			return null;
		}
	}
	
	/**
	* Getter per la collezione di Bonus Malus
	* 
	* @return	stringa indicante il colore associato
	*/
	public BonusMalusSet getBonusMalusCollection() {
		return this.bonusMalusCollection;
	}
	
	/**
	* Getter per i Family Member associati al giocatore. La ricerca del familiare
	* corretto avviene tramite il colore di dado associato a quest'ultimo
	* 
	* @param	color	colore del dado associato al Family Member
	* 
	* @return			il Family Member del colore color, null in caso di errori
	* 
	* @see		it.polimi.ingsw.ps06.model.Types
	* 
	*/
	public FamilyMember getMember(ColorPalette color) {
		
		if (color == ColorPalette.DICE_BLACK) return memberBlack;
		if (color == ColorPalette.DICE_WHITE) return memberWhite;
		if (color == ColorPalette.DICE_ORANGE) return memberOrange;
		if (color == ColorPalette.UNCOLORED) return memberUncolored;
		
		return null;
	}
	
	/**
	* Setter del valore associato ai Family Member di un giocatore
	* 
	* @param	black	valore da settare al Family Member associato al dado nero
	* @param	white	valore da settare al Family Member associato al dado bianco
	* @param	orange	valore da settare al Family Member associato al dado arancione
	* 
	*/
	public void setFamilyMemberValues(int black, int white, int orange) {
		memberBlack.setValue(black);
		memberWhite.setValue(white);
		memberOrange.setValue(orange);
	}
	
	/**
	* Getter di un leader appartenente al giocatore. Ricerca tramite code della carta
	* 
	* @param	code	codice della carta da trovare
	* 
	* @return			il Leader posseduto dal giocatore con il codice uguale al code passato,
	* 					null nel caso in cui il leader cercato non appartiene al giocatore
	* 
	*/
	public Leader getLeader(int code) {
		Leader leader = null;
		for (Leader l : leaders) 
			if (l.getCode() == code) leader = l;
		
		return leader;
	}
	
	/**
	* Getter della collezione di Leader posseduta dal giocatore
	* 
	* @return			l'insieme di leader posseduto dal giocatore
	* 
	*/
	public ArrayList<Leader> getLeaders() {
		return leaders;
	}
	
	/**
	* Setter dei Leader tramite passaggio completo dell'insieme come parametro
	* 
	* @param	list	insieme di leader da aggiungere al giocatore
	* 
	*/
	public void addLeaders(ArrayList<Leader> list) {
		this.leaders.addAll(list);
		
		ArrayList<Integer> ls = new ArrayList<Integer>();
		this.leaders.forEach(leader -> ls.add(leader.getCode() ));
		
		System.out.println("LEADERS: " + ls.get(0) + " " + ls.get(1) + " " + ls.get(2) + " " + ls.get(3));
		
		MessageLeaderCards leaderCards = new MessageLeaderCards(ls);
		leaderCards.setRecipient(this.getID());
		notifyChangement(leaderCards);
	}
	
	/**
	* Getter della Personal Board del giocatore
	* 
	* @return	la Personal Board del giocatore
	* 
	*/
	public PersonalBoard getPersonalBoard() {
		return personalBoard;
	}
	
	/**
	* Metodo per scartare una carta leader dal mazzo del giocatore.
	* Gestisce i controlli su eventuali errori e l'aggiornamento ai vari
	* Obersvers dell'avvenimento
	*
	* @param 	n	n-esimo leader da scartare
	* 
	*/
	public void doLeaderDiscarding(int n) {
		Leader leader = this.leaders.get(n);
		
		boolean result = leader.discardLeader();
		
		if ( result == true ) 
		{
			MessageLeaderHasBeenDiscarded leaderDiscarded = new MessageLeaderHasBeenDiscarded( n );
			leaderDiscarded.setRecipient(this.getID());
			notifyChangement(leaderDiscarded);
			
		} else handleErrorLeader(-1, leader);
	}
	
	/**
	* Metodo per giocare una carta leader dal mazzo del giocatore.
	* Gestisce i controlli su eventuali errori, risorse non sufficienti per tale carta
	* e l'aggiornamento ai vari Obersvers dell'avvenimento
	*
	* @param 	n	n-esimo leader da scartare
	* 
	*/
	public void doLeaderPlaying(int n) {
		LeaderRequirement playerStats = generatePlayerStats();
		Leader leader = this.leaders.get(n);
		
		System.out.println("PLAYER:\n" + playerStats.toString());
		System.out.println("");
		System.out.println("LEADER:\n" + leader.getRequirement().toString());
		
		if ( playerStats.isBiggerThan( leader.getRequirement() ) ) {
			
			boolean result = leader.playLeader();
			if (result == true) 
			{
				MessageLeaderHasBeenPlayed leaderPlayed = new MessageLeaderHasBeenPlayed( n );
				leaderPlayed.setRecipient(this.getID());
				notifyChangement(leaderPlayed);
				
			} else handleErrorLeader(-1, leader);
			
		} else handleErrorLeader(1, leader);
	}

	/**
	* Metodo per attivare una carta leader dal mazzo del giocatore che possiede un effeto tipo "uno per turno".
	* Gestisce i controlli su eventuali errori e l'aggiornamento ai vari Obersvers dell'avvenimento
	*
	* @param 	n	n-esimo leader da scartare
	* 
	*/
	public void doLeaderActivating(int n) {
		Leader leader = this.leaders.get(n);
		
		boolean result = leader.activateLeader();
		
		if ( result == true) 
		{
			MessageLeaderHasBeenActivated leaderActivated = new MessageLeaderHasBeenActivated( n );
			leaderActivated.setRecipient(this.getID());
			notifyChangement(leaderActivated);
			
		} else handleErrorLeader(2, leader);
	}
	
	/**
	* Metodo per lo sviluppo di un oggetto LeaderRequirement, contenitore di status
	* completo sulle risorse e carte possedute dal giocatore
	*
	*@return	status completo del giocatore
	*
	* @see		it.polimi.ingsw.ps06.model.LeaderRequirement
	* 
	*/
	private LeaderRequirement generatePlayerStats() {
		LeaderRequirement r = new LeaderRequirement();
		
		for (MaterialsKind m : MaterialsKind.values())
			r.setResourceValue(m, getPersonalBoard().getAmount(m));
		
		for (PointsKind p : PointsKind.values())
			r.setResourceValue(p, getPersonalBoard().getAmount(p));
		
		r.setResourceValue(CardType.TERRITORY, getPersonalBoard().getTerritories().size());
		r.setResourceValue(CardType.BUILDING, getPersonalBoard().getBuildings().size());
		r.setResourceValue(CardType.CHARACTER, getPersonalBoard().getCharacters().size());
		r.setResourceValue(CardType.VENTURE, getPersonalBoard().getVentures().size());
		
		return r;
	}
	
	/**
	* Metodo per la gestione degli errori, da comunicare agli Observers, a seguito di azioni
	* sulle carte Leader possedute
	*
	* @param	code	codice di errore
	* @param	l		leader sul quale è avvenuto l'errore
	*
	* @see		it.polimi.ingsw.ps06.model.LeaderRequirement
	* 
	*/
	private void handleErrorLeader(int code, Leader l) {
		
		String notification = "Giocatore " + getColorAssociatedToID();
		
		switch(code) {
		case 1: notification += " non è riuscito a giocare la carta " + l.getTitle() + " per mancanza di requisiti";
			break;
		case 2: notification += ": la carta " + l.getTitle() + " non ha effetto \"una volta per turno\"";
			break;
		default: notification = "UNKNOWN ERROR ON LEADERS HANDLING";
		}
		
		MessageModel2ViewNotification m = new MessageModel2ViewNotification(notification);
		notifyChangement(m);
	}
	
	
	public void notifyChangement(Object o) {
		setChanged();
		notifyObservers(o);
	}
	
	public void addNewObserver(Observer obs) {
		addObserver(obs);

		this.personalBoard.addNewObserver(this);
	}
	
	public void deleteAnObserver(Observer obs) {
		deleteObserver(obs);
	}

	@Override
	public void update(Observable o, Object arg) {
		
		if (arg instanceof Server2Client) 
			((Server2Client) arg).setRecipient(this.getID());
		
		notifyChangement(arg);
	}
}