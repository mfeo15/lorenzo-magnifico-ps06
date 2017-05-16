package it.polimi.ingsw.ps06.model;

import static it.polimi.ingsw.ps06.model.Types.Action;

import java.util.ArrayList;

import it.polimi.ingsw.ps06.model.Types.MaterialsKind;

/**
* Classe per la gestione del palazzo del consiglio
*
* @author  ps06
* @version 1.0
* @since   2017-05-10
*/

public class CouncilPalace implements PlaceSpace {
	private ArrayList<FamilyMember> memberSpaces;
	private ArrayList<Player> players;
	private EffectsResources councilReward;
	
	/**
	* Metodo per il piazzamento di un familiare nel palazzo del consiglio
	*
	* @param 	member			Familiare che si vuole piazzare
	* @param 	chosenAction 	Codice dell'azione da eseguire
	* @return 					Nothing
	*/
	@Override
	public void placeMember(FamilyMember member, Action chosenAction) {
		memberSpaces.add(member);
		
	}
	
	/**
	* Costruttore del palazzo del consiglio
	*
	* @param 	numberPlayers	Numero di giocatori della partita
	* @return 	Nothing
	*/
	public CouncilPalace(){
		councilReward = new EffectsResources();
	}
	
	/**
	* Metodo per valutare l'ordine dei giocatori per la prossima fase di gioco
	*
	* @return 	players		array di players nell'ordine giusto
	*/
	public ArrayList<Player> checkOrder(){
		
		Player player;
		int index = 0;
		FamilyMember member = memberSpaces.get(0);
	
		while(member!=null)
			{
				player = member.getPlayer();
				players.add(player);
				member = memberSpaces.get(index);
			}
		return players;
	}
	
	/**
	* Metodo per assegnare le risorse che sono state scelte dal giocatore
	*
	* @param 	player			Giocatore a cui dare le risorse
	* @param 	chosenAction	Codice dell'azione da eseguire	
	* @return 					Nothing
	*/
	public void giveResources(Player player, Action chosenAction){
		// dai privilegio
		councilReward = new EffectsResources(new Resources(MaterialsKind.COIN,1));
	}
	
	/**
	* Metodo per ripulire i familiari che sono stati allocati in questa zona, metodo da utilizzare DOPO averne valutato l'ordine
	*
	* @param 	Unused
	* @return 	Nothing
	*/
	public void cleanPalace(){
		memberSpaces.clear();
	}
}
