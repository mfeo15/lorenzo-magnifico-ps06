package it.polimi.ingsw.ps06.model;

import java.util.ArrayList;
import it.polimi.ingsw.ps06.model.Types.ColorPalette;

/**
* Classe rappresentante un singolo giocatore in una partita
*
* @author  ps06
* @version 1.0
* @since   2017-05-10 
*/
public class Player {
	
	private String name;
	private ColorPalette color;
	
	private ArrayList<Leader> leaders;
	
	private PersonalBoard personalBoard;
	
	private FamilyMember memberBlack;
	private FamilyMember memberWhite;
	private FamilyMember memberOrange;
	private FamilyMember memberUncolored;
	 
	/**
	* Constructorre della classe
	* 
	* @param name	nome del giocatore
	* @param color	colore del giocatore
	*/
	public Player(String name, ColorPalette color) {
		super();
		
		this.name = name;
		this.color = color;
		
		memberBlack = new FamilyMember(this, ColorPalette.DICE_BLACK);
		memberWhite = new FamilyMember(this, ColorPalette.DICE_WHITE);
		memberOrange = new FamilyMember(this, ColorPalette.DICE_ORANGE);
		memberUncolored = new FamilyMember(this);
		
		this.personalBoard = new PersonalBoard();
	}
	
	/**
	* Metodo per l'esecuzione di un azione
	*
	* @param 	value	valore dell'azione
	* @param 	color	colore del familiare usato
	* @return 	Nothing
	*/
	public void doAction(int value, ColorPalette color){
		
	}
}