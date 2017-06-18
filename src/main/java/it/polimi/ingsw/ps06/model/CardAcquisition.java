package it.polimi.ingsw.ps06.model;

import it.polimi.ingsw.ps06.model.Types.Action;

/**
* Classe per la gestione delle azioni che comportano un acquisizione di una carta
*
* @author  ps06
* @version 1.0
* @since   2017-05-16
*/
public class CardAcquisition extends Actions {

	private DevelopementCard card;
	private Player p;
	
	public CardAcquisition(DevelopementCard card, Player p){
		this.card = card;
		this.p = p;
	}

	/**
	* Metodo per verificare i costi dovuti all'azione che comporta l'acquisizione di una carta
	*
	* @param 	player			Giocatore su cui fare la verifica della disponibilità di risorse
	* @param 	chosenAction	Codice dell'azione da eseguire	
	* @return 	
	*/
	public boolean checkCosts(Action chosenAction, Boolean extraCost) {
		//chiama risorse
		
		if (card instanceof Territory)
			return true;
		
		if (card instanceof Building)
			return p.getPersonalBoard().getInventory().isBiggerThan( ((Building) card).getRequirement() );
		
		if (card instanceof Character)
			return p.getPersonalBoard().getInventory().isBiggerThan( ((Character) card).getRequirement() );
		
		if (card instanceof Venture)
			//return p.getPersonalBoard().getInventory().isBiggerThan( ((Venture) card).getRequirement() );
			return true;
		
		return false;
	}
	
	/**
	* Metodo per verificare i costi dovuti all'azione che comporta l'acquisizione di una carta
	*
	* @param 	player			Giocatore su cui fare la verifica della disponibilità di risorse
	* @param 	chosenAction	Codice dell'azione da eseguire	
	* @return 	
	*/
	public boolean checkRequirements(Action chosenAction){
		
		if (!(card instanceof Venture))
			return true;
		
		return true;
	}
	

	/**
	* Metodo per assegnare la carta ad un giocatore
	*
	* @param 	player			Giocatore su cui fare la verifica della disponibilità di risorse
	* @return 	
	*/
	@Override
	public void activate() {
		
		if(card instanceof Territory) {
			p.getPersonalBoard().addCard( (Territory) card);
			return;
		}
		
		if(card instanceof Building) {
			p.getPersonalBoard().addCard( (Building) card);
			p.getPersonalBoard().getInventory().decreaseResources( ((Building) card).getRequirement() );
			return;
		}
		
		if(card instanceof Character) {
			p.getPersonalBoard().addCard( (Character) card);
			p.getPersonalBoard().getInventory().decreaseResources( ((Character) card).getRequirement() );
			return;
		}
		
		if(card instanceof Venture) {
			p.getPersonalBoard().addCard( (Venture) card);
			p.getPersonalBoard().getInventory().decreaseResources( ((Building) card).getRequirement() );
			return;
		}
	}

}
