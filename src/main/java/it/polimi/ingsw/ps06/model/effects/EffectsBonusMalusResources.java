package it.polimi.ingsw.ps06.model.effects;

import java.util.ArrayList;

import it.polimi.ingsw.ps06.model.Player;
import it.polimi.ingsw.ps06.model.Types.MaterialsKind;
import it.polimi.ingsw.ps06.model.bonus_malus.BonusMalusResources;

/**
 * Classe per la gestione degli effetti permanenti sul valore delle risorse
 *
 * @author  ps06
 * @version 1.0
 * @since   2017-05-11
 */
public class EffectsBonusMalusResources extends EffectsBonusMalus {
	
	private ArrayList<MaterialsKind> kinds;
	
	/**
	 * Costruttore della classe
	 * 
	 * @param 	value		valore del Bonus/Malus
	 * @param 	kinds		materiali ai quali associare il Bonus Malus
	 * 
	 * @see						it.polimi.ingsw.ps06.model.Types
	 */
	public EffectsBonusMalusResources(int value, ArrayList<MaterialsKind> kinds) {
		super(value);
		this.kinds = kinds;
	}

	@Override
	public void activate(Player p) {
		BonusMalusResources bm = new BonusMalusResources(this.value, this.kinds);
		p.getBonusMalusCollection().add( bm );
	}	
}