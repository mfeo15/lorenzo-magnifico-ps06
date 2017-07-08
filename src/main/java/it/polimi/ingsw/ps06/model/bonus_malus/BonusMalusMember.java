package it.polimi.ingsw.ps06.model.bonus_malus;

import java.util.ArrayList;

import it.polimi.ingsw.ps06.model.Types;
import it.polimi.ingsw.ps06.model.Types.ColorPalette;

/**
 * Classe per il Bonus Malus associato ad un famigliare
 *
 * @author  ps06
 * @version 1.1
 * @since   2017-06-26
 */
public class BonusMalusMember extends BonusMalus {

	private ArrayList<ColorPalette> membersColor;
	
	/**
	 * Costruttore della classe
	 * 
	 * @param 	value			valore del Bonus Malus
	 * @param	membersColor	colori associati ai famigiare da modificare
	 * 
	 * @see						it.polimi.ingsw.ps06.model.Types
	 */
	public BonusMalusMember(int value, ArrayList<ColorPalette> membersColor) {
		super(value);
		this.membersColor = membersColor;
	}
	
	/**
	 * Getter per i colori associati ai famigliare
	 * 
	 * @return	i colori associati ai famigliare
	 */	
	public ArrayList<ColorPalette> getMemberColors() {
		return membersColor;
	}
	
	/**
	 * Setter per i colori associati ai famigliari
	 * 
	 * @param	membersColor	insieme di colori da settare
	 */
	public void setMemberColors(ArrayList<ColorPalette> membersColor) {
		this.membersColor = membersColor;
	}
}
