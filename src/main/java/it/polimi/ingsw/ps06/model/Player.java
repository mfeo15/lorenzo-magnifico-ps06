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
	* @param name	nome del giocatore
	* @param color	colore del giocatore
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
	
	public void setID(int ID) {
		this.ID = ID;
	}
	
	public int getID() {
		return ID;
	}
	
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
	
	public BonusMalusSet getBonusMalusCollection() {
		return this.bonusMalusCollection;
	}
	
	public FamilyMember getMember(ColorPalette color) {
		
		if (color == ColorPalette.DICE_BLACK) return memberBlack;
		if (color == ColorPalette.DICE_WHITE) return memberWhite;
		if (color == ColorPalette.DICE_ORANGE) return memberOrange;
		if (color == ColorPalette.UNCOLORED) return memberUncolored;
		
		return null;
	}
	
	public void setFamilyMemberValues(int black, int white, int orange) {
		memberBlack.setValue(black);
		memberWhite.setValue(white);
		memberOrange.setValue(orange);
	}
	
	public Leader getLeader(int code) {
		Leader leader = null;
		for (Leader l : leaders) 
			if (l.getCode() == code) leader = l;
		
		return leader;
	}
	
	public ArrayList<Leader> getLeaders() {
		return leaders;
	}
	
	public void addLeaders(ArrayList<Leader> list) {
		this.leaders.addAll(list);
		
		ArrayList<Integer> ls = new ArrayList<Integer>();
		this.leaders.forEach(leader -> ls.add(leader.getCode() ));
		
		MessageLeaderCards leaderCards = new MessageLeaderCards(ls);
		leaderCards.setRecipient(this.getID());
		notifyChangement(leaderCards);
	}
	
	public PersonalBoard getPersonalBoard() {
		return personalBoard;
	}
	
	/**
	* Metodo per l'esecuzione di un azione
	*
	* @param 	value	valore dell'azione
	* @param 	color	colore del familiare usato
	* @return 	Nothing
	*/
	public void doLeaderDiscarding(int code) {
		Leader leader = this.leaders.get(code);
		
		boolean result = leader.discardLeader();
		
		if ( result == true ) 
		{
			MessageLeaderHasBeenDiscarded leaderDiscarded = new MessageLeaderHasBeenDiscarded( code );
			leaderDiscarded.setRecipient(this.getID());
			notifyChangement(leaderDiscarded);
			
		} else handleErrorLeader(-1, leader);
	}
	
	public void doLeaderPlaying(int code) {
		LeaderRequirement playerStats = generatePlayerStats();
		Leader leader = this.leaders.get(code);
		
		if ( playerStats.isBiggerThan( leader.getRequirement() ) ) {
			
			boolean result = leader.playLeader();
			if (result == true) 
			{
				MessageLeaderHasBeenPlayed leaderPlayed = new MessageLeaderHasBeenPlayed( code );
				leaderPlayed.setRecipient(this.getID());
				notifyChangement(leaderPlayed);
				
			} else handleErrorLeader(-1, leader);
			
		} else handleErrorLeader(1, leader);
	}

	public void doLeaderActivating(int code) {
		Leader leader = this.leaders.get(code);
		
		boolean result = leader.activateLeader();
		
		if ( result == true) 
		{
			MessageLeaderHasBeenActivated leaderActivated = new MessageLeaderHasBeenActivated( code );
			leaderActivated.setRecipient(this.getID());
			notifyChangement(leaderActivated);
			
		} else handleErrorLeader(2, leader);
	}
	
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