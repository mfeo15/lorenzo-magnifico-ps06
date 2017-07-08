package it.polimi.ingsw.ps06.model.bonus_malus;

import java.util.ArrayList;

import it.polimi.ingsw.ps06.model.Types;
import it.polimi.ingsw.ps06.model.Types.ActionCategory;
import it.polimi.ingsw.ps06.model.Types.ColorPalette;

/**
 * Classe per la gestione della collezione di Bonus Malus di un giocatore
 *
 * @author  ps06
 * @version 1.1
 * @since   2017-06-26
 */
public class BonusMalusSet {
	
	private ArrayList<BonusMalus> bonusMalus;
	
	/**
	 * Costruttore della classe
	 */
	public BonusMalusSet() {
		bonusMalus = new ArrayList<BonusMalus>();
	}
	
	/**
	 * Metodo per l'inserimento di un Bonus Malus nella collezione
	 * 
	 * @param	bm	Bonus Malus da inserire
	 */
	public void add(BonusMalus bm) {
		bonusMalus.add(bm);
	}
	
	/**
	 * Getter dei Bonus Malus di tipo Action tramite ricerca per tipo
	 * 
	 * @param	a	tipo di azione per la quale verificare la presenza di un Bonus Malus
	 * 
	 * @return		<p>il Bonus Malus relativo all'azione richiesta</p>
	 * 				<p>null nel caso in cui il Bonus Malus non esistesse</p>
	 * 
	 * @see			it.polimi.ingsw.ps06.model.Types
	 */
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
	
	/**
	 * Getter dei Bonus Malus di tipo Member tramite ricerca per colore del dado associato
	 * 
	 * @param	c	colore del dado associato al famigliare per cui trovare il Bonus Malus
	 * 
	 * @return		<p>il Bonus Malus relativo al member richiesto</p>
	 * 				<p>null nel caso in cui il Bonus Malus non esistesse</p>
	 * 
	 * @see			it.polimi.ingsw.ps06.model.Types
	 */
	public BonusMalusMember getBonusMalus(ColorPalette c) {
		
		if (! contains(BonusMalusMember.class))
			return null;
		
		for (BonusMalus bm : this.bonusMalus) {
			if (bm instanceof BonusMalusMember)
				if ( ((BonusMalusMember) bm).getMemberColors().contains(c) ) 
					return ((BonusMalusMember) bm);
		}
		
		return null;
	}
	
	/**
	 * Metodo per la verifica di presenza di un certo Bonus Malus relativamente ad una azione
	 * 
	 * @param	a		tipo di azione da trovare
	 * 
	 * @return	true	se esiste almeno un elemento della collezione che sia associato a tale azione
	 * 
	 * @see				it.polimi.ingsw.ps06.model.Types
	 */
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
	
	
	/**
	 * Metodo per la verifica di presenza di un certo Bonus Malus all'interno della collezione
	 * 
	 * @param	cls		classe del Bonus Malus da trovare
	 * 
	 * @return	true	se esiste almeno un elemento della collezione che sia della classe richiesta
	 */
	public boolean contains(Class<?> cls) {
		boolean result = false;
		for (BonusMalus element : this.bonusMalus ) {
			if (element.getClass() == cls) result = true;
		}
		
		return result;
	}
}
