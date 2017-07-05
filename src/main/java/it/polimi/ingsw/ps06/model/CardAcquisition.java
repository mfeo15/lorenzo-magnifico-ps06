package it.polimi.ingsw.ps06.model;

import it.polimi.ingsw.ps06.model.Types.MaterialsKind;
import it.polimi.ingsw.ps06.model.Types.PointsKind;
import it.polimi.ingsw.ps06.model.bonus_malus.BonusMalusDoubleMaterialsFromDevCards;
import it.polimi.ingsw.ps06.model.cards.developement.Building;
import it.polimi.ingsw.ps06.model.cards.developement.Character;
import it.polimi.ingsw.ps06.model.cards.developement.DevelopementCard;
import it.polimi.ingsw.ps06.model.cards.developement.Territory;
import it.polimi.ingsw.ps06.model.cards.developement.Venture;
import it.polimi.ingsw.ps06.model.effects.Effect;
import it.polimi.ingsw.ps06.model.effects.EffectsResources;

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
	
	/**
	* Costruttore della classe
	* 
	* @param	card		carta da acquisire
	* @param	p			giocatore che vuole acquisire la carta
	* @param	servants	numero di servitori impiegati per concludere l'azione
	* 
	*/
	public CardAcquisition(DevelopementCard card, Player p, int servants) {
		super(servants);
		this.card = card;
		this.p = p;
	}

	/**
	* Metodo per verificare i costi dovuti all'azione che comporta l'acquisizione di una carta
	*
	* @param 	extraCost	flag per un extra costo di 3 COIN	
	* 
	* @return 	true		se il giocatore soddisfa tutti i costi per acquisire la carta
	* 
	*/
	public boolean checkCosts(boolean extraCost) {
		
		this.extraCost = extraCost;
		Resources extraMoney = new Resources();
		if (extraCost) extraMoney.increaseResourceValue(MaterialsKind.COIN, 3);
		
		if (card instanceof Territory)
			return p.getPersonalBoard().getInventory().isBiggerThan( extraMoney );
		
		if (card instanceof Building)
			return p.getPersonalBoard().getInventory().isBiggerThan( extraMoney.add( ((Building) card).getCost() ) );
		
		if (card instanceof Character)
			return p.getPersonalBoard().getInventory().isBiggerThan( extraMoney.add( ((Character) card).getCost() ) );
		
		if (card instanceof Venture)
			return p.getPersonalBoard().getInventory().isBiggerThan( extraMoney.add( ((Venture) card).getCosts().get(0) ) );
		
		return false;
	}
	
	/**
	* Metodo per verificare i requisiti di una carta (oltre al costo). Questo è necessario
	* per tutte le carte di tipo Impresa
	* 
	* @return 	true		se il giocatore soddisfa tutti i requisiti per acquisire la carta
	* 
	*/
	public boolean checkRequirements(){
		
		if (!(card instanceof Venture))
			return true;
		
		
		if ( !((Venture) card).isMilRequirement() )
			return true;

		if ( p.getPersonalBoard().getAmount(PointsKind.MILITARY_POINTS) >= ((Venture) card).getMilitaryRequirement() )
			return true;

		return false;
	}
	

	/**
	* Metodo per assegnare la carta ad un giocatore
	*
	*/
	@Override
	public void activate() {
		
		if (servants > 0)
			p.getPersonalBoard().reduceResource(MaterialsKind.SERVANT, servants);
		
		if (extraCost)
			p.getPersonalBoard().reduceResource(MaterialsKind.COIN, 3);
		
		if(card instanceof Territory)
			p.getPersonalBoard().addCard( (Territory) card);
		
		if(card instanceof Building) {
			p.getPersonalBoard().addCard( (Building) card);
			p.getPersonalBoard().reduceResource( ((Building) card).getCost() );
		}
		
		if(card instanceof Character) {
			p.getPersonalBoard().addCard( (Character) card);
			p.getPersonalBoard().reduceResource( ((Character) card).getCost() );
			((Character) card).activateEffect(p);
		}
		
		if(card instanceof Venture) {
			p.getPersonalBoard().addCard( (Venture) card);
			p.getPersonalBoard().reduceResource( ((Venture) card).getCosts().get(0) );
		}
		
		card.activateIstantEffect(p);
		
		if (p.getBonusMalusCollection().contains(BonusMalusDoubleMaterialsFromDevCards.class)) 
		{
			for (Effect e : card.getInstantEffects()) {
				if (e instanceof EffectsResources) {
					int bonus_faith = ((EffectsResources) e).getBonus().getResourceValue(PointsKind.FAITH_POINTS);
					int bonus_military = ((EffectsResources) e).getBonus().getResourceValue(PointsKind.MILITARY_POINTS);
					int bonus_victory = ((EffectsResources) e).getBonus().getResourceValue(PointsKind.VICTORY_POINTS);
					if ( bonus_faith == 0 && bonus_military == 0 && bonus_victory == 0 )
						e.activate(p);
				}
			}
		}
	}

}
