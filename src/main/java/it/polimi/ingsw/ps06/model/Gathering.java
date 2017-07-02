package it.polimi.ingsw.ps06.model;

import it.polimi.ingsw.ps06.model.Types.Action;
import it.polimi.ingsw.ps06.model.Types.MaterialsKind;
import it.polimi.ingsw.ps06.model.cards.Building;
import it.polimi.ingsw.ps06.model.cards.Territory;

/**
* Classe per la gestione delle azioni di produzione/raccolto
*
* @author  ps06
* @version 1.0
* @since   2017-05-16
*/


public class Gathering extends Actions {
	
	private Action chosenAction;	
	private FamilyMember member;
	
	/**
	* Costruttore della classe
	*
	* @param 	value			Valore dell'azione Produzione/raccolto
	* @param 	chosenAction	Codice dell'azione da eseguire	
	* @return 					Nothing
	*/
	public Gathering (Action chosenAction, FamilyMember member, int servants) {
		super(servants);
		
		this.chosenAction = chosenAction;
		this.member = member;
	}
	
	/**
	* Metodo per controllare se si tratta di Produzione o Raccolto
	*
	* @param 	p		Giocatore a cui attivare l'azione
	* @return 			Nothing
	*/
	private boolean isProduction() {
		
		if (chosenAction == Action.PRODUCTION_1 || chosenAction == Action.PRODUCTION_2)
			return true;
		
		return false;
	}
	
	/**
	* Metodo per controllare eventuali Malus/Bonus
	*
	* @param 	p		Giocatore a cui attivare l'azione
	* @return 			Nothing
	* 
	* @throws InterruptedException 
	*/
	private int getGatheringBonus(Player p) {
		if (! member.getPlayer().getBonusMalusCollection().contains(chosenAction.getActionCategory())) 
			return 0;
		
		return ( member.getPlayer().getBonusMalusCollection().getBonusMalus(chosenAction.getActionCategory()).getValue() );
	}
	
	/**
	* Metodo di attivazione dell'azione
	*
	* @param 	p		Giocatore a cui attivare l'azione
	* @return 			Nothing
	*/
	@Override
	public void activate() {
		
		if (servants > 0)
			member.getPlayer().getPersonalBoard().reduceResource(MaterialsKind.SERVANT, servants);
		
		if( isProduction() == true) {
		
			for (Building b : member.getPlayer().getPersonalBoard().getBuildings())
				if ( b.check_dice( member.getValue() + servants + getGatheringBonus(member.getFakePlayer())) )
						b.activateEffect(member.getPlayer());
		}
		else
		{	
			for (Territory t : member.getPlayer().getPersonalBoard().getTerritories())
				if ( t.check_dice( member.getValue() + servants + getGatheringBonus(member.getFakePlayer())) )
					t.activateEffect(member.getPlayer());
		}
	}

}
