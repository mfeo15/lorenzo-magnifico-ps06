package it.polimi.ingsw.ps06.model;

import java.util.ArrayList;

import it.polimi.ingsw.ps06.model.Types.Action;
import it.polimi.ingsw.ps06.model.Types.ColorPalette;

/**
* Classe rappresentante un singolo giocatore in una partita
*
* @author  ps06
* @version 1.0
* @since   2017-05-10 
*/
public class Player {
	
	private int ID;
	
	private ArrayList<Leader> leaders;
	
	private PersonalBoard personalBoard;
	
	private FamilyMember memberBlack;
	private FamilyMember memberWhite;
	private FamilyMember memberOrange;
	private FamilyMember memberUncolored;
	 
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
	}
	
	public void setID(int ID) {
		this.ID = ID;
	}
	
	public int getID() {
		return ID;
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
	
	public ArrayList<Leader> getLeaders() {
		return leaders;
	}
	
	public void addLeaders(ArrayList<Leader> leaders) {
		this.leaders.addAll(leaders);
	}
	
	public void removeLeader(int index) {
		this.leaders.remove(index);
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
}