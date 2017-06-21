package it.polimi.ingsw.ps06.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps06.model.Types.ColorPalette;
import it.polimi.ingsw.ps06.model.Types.LeaderStates;
import it.polimi.ingsw.ps06.model.messages.Message;
import it.polimi.ingsw.ps06.model.messages.MessageLeaderCards;
import it.polimi.ingsw.ps06.model.messages.Server2Client;

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
	
	private Observer o;
	 
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
		
		this.personalBoard = new PersonalBoard();
		this.leaders = new ArrayList<Leader>();
	}
	
	public void setID(int ID) {
		this.ID = ID;
	}
	
	public int getID() {
		return ID;
	}
	
	public String getColorAssociatedToID() {
		
		switch ( ID ) {
		case 0: return "Rosso";
		case 1: return "Verde";
		case 2: return "Blu";
		case 3: return "Giallo";
		default:
			return null;
		}
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
			if (l.code == code) leader = l;
		
		return leader;
	}
	
	public ArrayList<Leader> getLeaders() {
		return leaders;
	}
	
	public void addLeaders(ArrayList<Leader> list) {
		this.leaders.addAll(list);
		this.leaders.forEach(l -> l.addNewObserver(this.o));
		
		HashMap<Integer, LeaderStates> ls = new HashMap<Integer, LeaderStates>();
		this.leaders.forEach(leader -> ls.put(leader.code, leader.getLeaderState() ));  
		
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
	public void doLeaderDiscarding(int value, ColorPalette color){
		
	}
	
	
	public void notifyChangement(Object o) {
		setChanged();
		notifyObservers(o);
	}
	
	public void addNewObserver(Observer obs) {
		addObserver(obs);
		
		this.o = obs;
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