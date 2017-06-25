package it.polimi.ingsw.ps06.model;

import it.polimi.ingsw.ps06.model.Types.Action;
import it.polimi.ingsw.ps06.model.Types.MaterialsKind;
import it.polimi.ingsw.ps06.model.Types.PointsKind;
import it.polimi.ingsw.ps06.model.cards.Building;
import it.polimi.ingsw.ps06.model.cards.Character;
import it.polimi.ingsw.ps06.model.cards.DevelopementCard;
import it.polimi.ingsw.ps06.model.cards.Territory;
import it.polimi.ingsw.ps06.model.cards.Venture;

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
	
	private boolean extraCost;
	
	public CardAcquisition(DevelopementCard card, Player p, int servants) {
		super(servants);
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
	public boolean checkCosts(Action chosenAction, boolean extraCost) {
		
		this.extraCost = extraCost;
		Resources extraMoney = new Resources();
		if (extraCost) extraMoney.increaseResourceValue(MaterialsKind.COIN, 3);
		
		if (card instanceof Territory)
			return p.getPersonalBoard().getInventory().isBiggerThan( extraMoney );
		
		if (card instanceof Building)
			return p.getPersonalBoard().getInventory().isBiggerThan( extraMoney.add( ((Building) card).getRequirement() ) );
		
		if (card instanceof Character)
			return p.getPersonalBoard().getInventory().isBiggerThan( extraMoney.add( ((Character) card).getRequirement() ) );
		
		if (card instanceof Venture)
			return p.getPersonalBoard().getInventory().isBiggerThan( extraMoney.add( ((Venture) card).getRequirements().get(0) ) );
		
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
		
		
		if ( !((Venture) card).isMilRequirement() )
			return true;
		
		if ( ((Venture) card).getMilitaryRequirement() >= p.getPersonalBoard().getAmount(PointsKind.MILITARY_POINTS) )
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
		
		if (servants > 0)
			p.getPersonalBoard().getInventory().decreaseResourceValue(MaterialsKind.SERVANT, servants);
		
		if (extraCost)
			p.getPersonalBoard().getInventory().decreaseResourceValue(MaterialsKind.COIN, 3);
		
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
		
		card.activateIstantEffect(p);
	}

}
