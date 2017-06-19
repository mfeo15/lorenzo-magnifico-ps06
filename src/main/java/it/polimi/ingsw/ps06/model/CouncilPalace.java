package it.polimi.ingsw.ps06.model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps06.model.Types.Action;
import it.polimi.ingsw.ps06.model.Types.MaterialsKind;
import it.polimi.ingsw.ps06.model.messages.MessageBoardMemberHasBeenPlaced;
import it.polimi.ingsw.ps06.model.messages.MessageModel2ViewNotification;

/**
* Classe per la gestione del palazzo del consiglio
*
* @author  ps06
* @version 1.1
* @since   2017-05-10
*/

public class CouncilPalace extends Observable implements PlaceSpace {
	private ArrayList<FamilyMember> memberSpaces;
	private ArrayList<Player> players;
	private EffectsResources councilReward;
	private EffectsActions privilegeReward;
	
	/**
	* Metodo per il piazzamento di un familiare nel palazzo del consiglio
	*
	* @param 	member			Familiare che si vuole piazzare
	* @param 	chosenAction 	Codice dell'azione da eseguire
	* @return 					Nothing
	*/
	@Override
	public void placeMember(FamilyMember member, Action chosenAction, int servants) {
		int errorCode=0;
		int memberValue;
		
		if( (member.getValue() + servants) > 1){
			
			memberSpaces.add(member);
			giveRewards(member, chosenAction);
		
		} else handle(errorCode);
		
	}
	
	/**
	* Costruttore del palazzo del consiglio
	*
	* @param 	numberPlayers	Numero di giocatori della partita
	* @return 	Nothing
	*/
	public CouncilPalace(){
		memberSpaces = new ArrayList<FamilyMember>();
		players = new ArrayList<Player>();
		
		initRewards();
	}
	
	/**
	* Gestisci errori di posizionamento familiare
	*
	* @param	code		codice errore
	* @return 	Nothing
	*/
	private void handle(int code){
		
		String notification="";
		
		MessageModel2ViewNotification m = new MessageModel2ViewNotification(notification);
		notifyChangement(m);
	}
	
	/**
	* Metodo per valutare l'ordine dei giocatori per la prossima fase di gioco
	*
	* @return 	players		array di players nell'ordine giusto
	*/
	public ArrayList<Player> checkOrder(){
		
		if (memberSpaces.size() == 0)
			return null;
		
		ArrayList<Player> players = new ArrayList<Player>();
		
		memberSpaces.forEach(m -> players.add( m.getPlayer() ));
		return players;
	}
	
	/**
	* Metodo per inizializzare i rewards
	*	
	* @return 					Nothing
	*/
	private void initRewards(){
		privilegeReward = new EffectsActions(new Privilege(0));
		councilReward = new EffectsResources(new Resources(MaterialsKind.COIN,1));
	}
	
	/**
	* Metodo per assegnare le risorse che sono state scelte dal giocatore
	*
	* @param 	player			Giocatore a cui dare le risorse
	* @param 	chosenAction	Codice dell'azione da eseguire	
	* @return 					Nothing
	*/
	public void giveRewards(FamilyMember member, Action chosenAction){

		Player player = member.getPlayer();
		
		councilReward.activate(player);
		privilegeReward.activate(player);
		
		MessageBoardMemberHasBeenPlaced newMember = new MessageBoardMemberHasBeenPlaced(chosenAction, member.getColor(), player.getID() );
		notifyChangement(newMember);
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
	
	/**
	* Metodo per ritornare l'arraylist di familiari
	*
	* @return 	memberSpaces	ArrayList familiari
	*/
	public ArrayList<FamilyMember> getFamilyList(){
		return memberSpaces;
	}

	
	public void notifyChangement(Object o) {
		setChanged();
		notifyObservers(o);
	}
	
	public void addNewObserver(Observer obs) {
		addObserver(obs);
	}
	
	public void deleteAnObserver(Observer obs) {
		deleteObserver(obs);
	}
}
