package it.polimi.ingsw.ps06.model;

import it.polimi.ingsw.ps06.model.Types.Action;
import it.polimi.ingsw.ps06.model.Types.MaterialsKind;
import it.polimi.ingsw.ps06.model.cards.developement.Building;
import it.polimi.ingsw.ps06.model.cards.developement.Territory;

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
	* @param 	chosenAction	tipo di azione da eseguire
	* @param	member			Family Member utilizzato per l'azione
	* @param	servants		numero di servitori impiegato per completare l'azione	
	*/
	public Gathering (Action chosenAction, FamilyMember member, int servants) {
		super(servants);
		
		this.chosenAction = chosenAction;
		this.member = member;
	}
	
	/**
	* Metodo per controllare se si tratta di Produzione o Raccolto
	* @return	true	se l'azione eseguita è di produzione
	*/
	private boolean isProduction() {
		
		if (chosenAction == Action.PRODUCTION_1 || chosenAction == Action.PRODUCTION_2)
			return true;
		
		return false;
	}
	
	/**
	* Metodo per controllare eventuali Malus/Bonus
	*
	* @param 	p	giocatore da verificare
	* @return 		il bonus/malus in possesso dal giocatore, zero nel caso non ci siano
	*/
	private int getGatheringBonus(Player p) {
		if (! member.getPlayer().getBonusMalusCollection().contains(chosenAction.getActionCategory())) 
			return 0;
		
		return ( member.getPlayer().getBonusMalusCollection().getBonusMalus(chosenAction.getActionCategory()).getValue() );
	}
	
	/**
	* Metodo eseguire l'azione di raccolto o produzione
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
