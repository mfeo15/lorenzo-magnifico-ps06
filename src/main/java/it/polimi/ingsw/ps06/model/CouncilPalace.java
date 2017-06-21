package it.polimi.ingsw.ps06.model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps06.model.Types.Action;
import it.polimi.ingsw.ps06.model.Types.CouncilPrivilege;
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

	private CouncilPrivilege chosenCouncilPrivilege;
	
	public CouncilPrivilege getChosenCouncilPrivilege() {
		return chosenCouncilPrivilege;
	}

	public void setChosenCouncilPrivilege(CouncilPrivilege chosen) {
		this.chosenCouncilPrivilege = chosen;
	}

	/**
	* Metodo per il piazzamento di un familiare nel palazzo del consiglio
	*
	* @param 	member			Familiare che si vuole piazzare
	* @param 	chosenAction 	Codice dell'azione da eseguire
	* @return 					Nothing
	*/
	@Override
	public void placeMember(FamilyMember member, Action chosenAction, int servants) {
		
		if( (member.getValue() + servants) < 1) {
			handle(1, member);
			return;
		}

		memberSpaces.add(member);
		giveRewards(member, servants, chosenCouncilPrivilege);

		//Tell the view what happened
		MessageBoardMemberHasBeenPlaced newMember = new MessageBoardMemberHasBeenPlaced(chosenAction, member.getColor(), (member.getPlayer()).getID() );
		notifyChangement(newMember);
		
		//Tell the model what happened
		Integer i = 1;
		notifyChangement(i);
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
	}
	
	/**
	* Gestisci errori di posizionamento familiare
	*
	* @param	code		codice errore
	* @return 	Nothing
	*/
	private void handle(int code, FamilyMember member) {
		
		String notification = "Il giocatore " + member.getPlayer().getColorAssociatedToID() + " ha piazzato un familiare ";
		
		switch(code) {
		case 1: notification += " di valore non sufficiente per l'azione";
			break;
		default: notification = "UNKNOWN ERROR ON COUNCIL PALACE";
		}
		
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
	* Metodo per assegnare le risorse che sono state scelte dal giocatore
	*
	* @param 	player			Giocatore a cui dare le risorse
	* @param 	chosenAction	Codice dell'azione da eseguire	
	* @return 					Nothing
	*/
	public void giveRewards(FamilyMember member, int servantsUsed, CouncilPrivilege chosen){
		
		Privilege priv = new Privilege(servantsUsed, member, chosen);
		priv.activate();
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
