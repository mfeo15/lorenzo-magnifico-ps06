package it.polimi.ingsw.ps06.model;

import java.util.ArrayList;

import it.polimi.ingsw.ps06.model.Types.Action;

/**
* Classe per la gestione delle azioni di produzione/raccolto
*
* @author  ps06
* @version 1.0
* @since   2017-05-16
*/


public class Gathering extends Actions {
	
	private int value;
	private boolean production;
	Action chosenAction;
	
	private Player p;
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return (this.value);
	}
	
	/**
	* Costruttore della classe
	*
	* @param 	value			Valore dell'azione Produzione/raccolto
	* @param 	chosenAction	Codice dell'azione da eseguire	
	* @return 					Nothing
	*/
	public Gathering (Action chosenAction, int value, Player p) {
		
		this.chosenAction=chosenAction;
		this.value=value;
		checkIfProduction();
		
		setChanged();
        notifyObservers(p);
	}
	
	/**
	* Metodo per controllare se si tratta di Produzione o Raccolto
	*
	* @param 	p		Giocatore a cui attivare l'azione
	* @return 			Nothing
	*/
	private void checkIfProduction(){
		
		switch (chosenAction){
			
			case HARVEST_1:
			case HARVEST_2:
				production=false;
				break;
			
			case PRODUCTION_1:
			case PRODUCTION_2:
				production=true;
				break;

			default:
				throw new IllegalArgumentException();
		
		}
	}
	
	/**
	* Metodo per controllare eventuali Malus/Bonus
	*
	* @param 	p		Giocatore a cui attivare l'azione
	* @return 			Nothing
	* 
	* @throws InterruptedException 
	*/
	private void checkGatheringBonus (Player p) {
		// malus = EffectsActive.gatheringBonus(p);
		// value = value + malus;
	}
	
	/**
	* Metodo di attivazione dell'azione
	*
	* @param 	p		Giocatore a cui attivare l'azione
	* @return 			Nothing
	*/
	@Override
	public void activate() {
		checkGatheringBonus(p);
		
		if(production==true)
		{
			for (Building b : p.getPersonalBoard().getBuildings())
				b.activateProduction(p);
		}
		else
		{	
			for (Territory t : p.getPersonalBoard().getTerritories())
				t.activateHarvest(p);
		}
		
		
	}

}
