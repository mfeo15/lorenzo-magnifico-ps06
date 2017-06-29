package it.polimi.ingsw.ps06.model;

import java.util.ArrayList;

import it.polimi.ingsw.ps06.model.Types.ActionCategory;

public class BonusMalusSet {
	
	private ArrayList<BonusMalus> bonusMalus;
	
	/**
	 * Costruttore di default
	 */
	public BonusMalusSet() {
		bonusMalus = new ArrayList<BonusMalus>();
	}
	
	public void add(BonusMalusAction bm) {
		if (contains(bm))
			return;
		
		bonusMalus.add(bm);
	}
	
	public BonusMalusAction getBonusMalus(ActionCategory a) {
		
		if (! contains(a))
			return null;
		
		for (BonusMalus bm : this.bonusMalus) {
			if (bm instanceof BonusMalusAction)
				if ( ((BonusMalusAction) bm).getActionCategory().equals(a) ) 
					return ((BonusMalusAction) bm);
		}
		
		return null;
	}
	
	public boolean contains(BonusMalus bm) {
		boolean result = false;
		for (BonusMalus element : this.bonusMalus ) {
			if (element.equals(bm)) result = true;
		}
		
		return result;
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
}
