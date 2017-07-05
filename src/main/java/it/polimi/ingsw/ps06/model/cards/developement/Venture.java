package it.polimi.ingsw.ps06.model.cards.developement;

import java.util.ArrayList;

import it.polimi.ingsw.ps06.model.Resources;

/**
* Classe per la gestione delle carte di tipo impresa
*
* @author  ps06
* @version 1.1
* @since   2017-05-11
*/
public class Venture extends DevelopementCard {
	
	private ArrayList<Resources> costs;
	private int militaryRequirement = -1;
	
	/**
	 * Costruttore di default della classe
	 */
	public Venture() {
		this.costs = new ArrayList<Resources>();
	}
	
	/**
	 * Setter per il costo della carta
	 * 
	 * @param cost	costo da settare alla carta
	 */
	public void setCost(Resources cost) {
		costs.add(cost);
	}
	
	/**
	 * Getter per i costi associati alla carta
	 * 
	 * @return	insieme di costi associati alla carta
	 */
	public ArrayList<Resources> getCosts() {
		return costs;
	}
	
	/**
	 * Setter per un eventuale requisito militare come pre-condizione
	 * all'acquisizione di una carta impresa
	 * 
	 * @param	militaryRequirement		requisito militare da associare alla carta
	 */
	public void setMilRequirement(int militaryRequirement) {
		this.militaryRequirement = militaryRequirement;
		return;
	}
	
	/**
	 * Getter per il requisito militare associato alla carta
	 * 
	 * @return	requisito militare associato alla carta
	 */
	public int getMilitaryRequirement() {
		return militaryRequirement;
	}
	
	/**
	 * Metodo per controllare la presenza di un requisito militare nella carta
	 * 
	 * @return	true	se il requisito militare è stato settato
	 */
	public boolean isMilRequirement() {
		return (militaryRequirement > 0);
	}	
}
