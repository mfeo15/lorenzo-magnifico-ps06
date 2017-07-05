package it.polimi.ingsw.ps06.model.effects;

import java.util.ArrayList;

import it.polimi.ingsw.ps06.model.Player;
import it.polimi.ingsw.ps06.model.Types.ActionCategory;
import it.polimi.ingsw.ps06.model.Types.ColorPalette;
import it.polimi.ingsw.ps06.model.bonus_malus.BonusMalusAction;
import it.polimi.ingsw.ps06.model.bonus_malus.BonusMalusMember;

/**
 * Classe per la gestione degli effetti permanenti sul valore del Family Member
 *
 * @author  ps06
 * @version 1.0
 * @since   2017-05-11
 */
public class EffectsBonusMalusMember extends EffectsBonusMalus {
	
	private ArrayList<ColorPalette> membersColor;
	
	/**
	 * Costruttore della classe
	 * 
	 * @param 	value			valore del Bonus/Malus
	 * @param 	membersColor	colore associato al famigliare a cui consegnare il Bonus Malus
	 * 
	 * @see						it.polimi.ingsw.ps06.model.Types
	 */
	public EffectsBonusMalusMember(int value, ArrayList<ColorPalette> membersColor) {
		super(value);
		this.membersColor = membersColor;
	}

	@Override
	public void activate(Player p) {
		BonusMalusMember bm = new BonusMalusMember(this.value, this.membersColor);
		p.getBonusMalusCollection().add( bm );
	}	
}