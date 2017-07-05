package it.polimi.ingsw.ps06.model.bonus_malus;

import java.util.ArrayList;

import it.polimi.ingsw.ps06.model.Types;
import it.polimi.ingsw.ps06.model.Types.ActionCategory;
import it.polimi.ingsw.ps06.model.Types.ColorPalette;

public class BonusMalusSet {
	
	private ArrayList<BonusMalus> bonusMalus;
	
	/**
	 * Costruttore di default
	 */
	public BonusMalusSet() {
		bonusMalus = new ArrayList<BonusMalus>();
	}
	
	public void add(BonusMalus bm) {
		bonusMalus.add(bm);
	}
	
	public BonusMalusAction getBonusMalus(ActionCategory a) {
		
		if (! contains(BonusMalusAction.class))
			return null;
		
		if (! contains(a))
			return null;
		
		for (BonusMalus bm : this.bonusMalus) {
			if (bm instanceof BonusMalusAction)
				if ( ((BonusMalusAction) bm).getActionCategory().equals(a) ) 
					return ((BonusMalusAction) bm);
		}
		
		return null;
	}
	
	public BonusMalusAction getBonusMalus(ColorPalette c) {
		
		if (! contains(BonusMalusMember.class))
			return null;
		
		for (BonusMalus bm : this.bonusMalus) {
			if (bm instanceof BonusMalusMember)
				if ( ((BonusMalusMember) bm).getMemberColors().contains(c) ) 
					return ((BonusMalusAction) bm);
		}
		
		return null;
	}
	
	public boolean contains(ActionCategory a) {
		
		boolean result = false;
		for (BonusMalus element : this.bonusMalus ) {
			try {
				if ( ((BonusMalusAction) element).getActionCategory().equals(a) ) 
					result = true;
				
			} catch(Exception e) { }
		}
		
		return result;
	}
	
	public boolean contains(Class<?> cls) {
		boolean result = false;
		for (BonusMalus element : this.bonusMalus ) {
			if (element.getClass() == cls) result = true;
		}
		
		return result;
	}
}
