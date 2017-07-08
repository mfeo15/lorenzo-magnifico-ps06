package it.polimi.ingsw.ps06.model;

import it.polimi.ingsw.ps06.model.Types.CouncilPrivilege;
import it.polimi.ingsw.ps06.model.Types.MaterialsKind;
import it.polimi.ingsw.ps06.model.Types.PointsKind;

/**
* Classe per la gestione delle azioni di scelta privilegi
*
* @author  ps06
* @version 1.0
* @since   2017-05-16
*/
public class Privilege extends Actions {
	
	private FamilyMember member;
	private CouncilPrivilege privilege;
	
	/**
	* Costruttore della classe
	* 
	* @param	servants	numero di servitori impiegati per l'azione
	* @param	member		Family Member impiegato per l'azione
	* @param	privilege	tipo di privilegio da assegnare al giocatore
	* 
	* @see		it.polimi.ingsw.ps06.model.Types	
	*/
	public Privilege(int servants, FamilyMember member, CouncilPrivilege privilege) {
		super(servants);
		this.member = member;
		this.privilege = privilege;
	}
	
	/**
	* Metodo di assegnamento del bonus del consiglio al giocatore
	*/
	@Override
	public void activate() {
		
		if (servants > 0)
			member.getPlayer().getPersonalBoard().reduceResource(MaterialsKind.SERVANT, servants);
		
		switch (privilege) {
		case BONUS_1: 
			member.getPlayer().getPersonalBoard().addResource(MaterialsKind.WOOD, 1);
			member.getPlayer().getPersonalBoard().addResource(MaterialsKind.STONE, 1);
			break;
		case BONUS_2:
			member.getPlayer().getPersonalBoard().addResource(MaterialsKind.SERVANT, 2);
			break;
		case BONUS_3:
			member.getPlayer().getPersonalBoard().addResource(MaterialsKind.COIN, 2);
			break;
		case BONUS_4:
			member.getPlayer().getPersonalBoard().addResource(PointsKind.MILITARY_POINTS, 2);
			break;
		case BONUS_5:
			member.getPlayer().getPersonalBoard().addResource(PointsKind.FAITH_POINTS, 1);
			break;
		default:
			break;
		}
	}
}
