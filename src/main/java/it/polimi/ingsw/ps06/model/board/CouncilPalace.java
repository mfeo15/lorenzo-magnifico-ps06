package it.polimi.ingsw.ps06.model.board;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps06.model.FamilyMember;
import it.polimi.ingsw.ps06.model.Player;
import it.polimi.ingsw.ps06.model.Privilege;
import it.polimi.ingsw.ps06.model.Types;
import it.polimi.ingsw.ps06.model.Types.Action;
import it.polimi.ingsw.ps06.model.Types.CouncilPrivilege;
import it.polimi.ingsw.ps06.networking.messages.MessageBoardMemberHasBeenPlaced;
import it.polimi.ingsw.ps06.networking.messages.MessageModel2ViewNotification;

/**
 * Classe per la gestione del palazzo del consiglio
 *
 * @author  ps06
 * @version 1.1
 * @since   2017-05-10
 */
public class CouncilPalace extends Observable implements PlaceSpace {
	
	private ArrayList<FamilyMember> memberSpaces;

	private CouncilPrivilege chosenCouncilPrivilege;

	/**
	 * Metodo per il piazzamento di un familiare nel palazzo del consiglio
	 *
	 * @param 	member			Familiare che si vuole piazzare
	 * @param 	chosenAction 	tipo di azione da eseguire
	 * @param	servants		numero di servitori impiegati per il piazzamento
	 * 
	 * @see						it.polimi.ingsw.ps06.model.Types
	 */
	@Override
	public void placeMember(FamilyMember member, Action chosenAction, int servants) {
		
		if( (member.getValue() + servants) < 1) {
			handleBadPlacing(1, member);
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
	 */
	public CouncilPalace(){
		memberSpaces = new ArrayList<FamilyMember>();
	}
	
	/**
	 * Getter per il privilegio richiesto
	 * 
	 * @return	il privilegio richiesto al consiglio
	 * 
	 * @see		it.polimi.ingsw.ps06.model.Types
	 */
	public CouncilPrivilege getChosenCouncilPrivilege() {
		return chosenCouncilPrivilege;
	}

	/**
	 * Setter per il privilegio da richiedere
	 * 
	 * @param 	chosen	tipo di privilegio da richiedere al consiglio
	 * 
	 * @see				it.polimi.ingsw.ps06.model.Types
	 */
	public void setChosenCouncilPrivilege(CouncilPrivilege chosen) {
		this.chosenCouncilPrivilege = chosen;
	}
	
	
	@Override
	public void handleBadPlacing(int code, FamilyMember member) {
		
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
	 * Metodo per ritornare l'ordine dei giocatori per la prossima fase di gioco
	 *
	 * @return 	array di players in coda al consiglio
	 */
	public ArrayList<Player> checkOrder() {
		
		if (memberSpaces.size() == 0)
			return null;
		
		ArrayList<Player> players = new ArrayList<Player>();
		
		memberSpaces.forEach(m -> players.add( m.getPlayer() ));
		return players;
	}
	
	/**
	 * Metodo per assegnare le risorse che sono state scelte dal giocatore
	 *
	 * @param 	member			member che ha ottenuto la possibilità del privilegio
	 * @param 	servantsUsed	numero di servitori necessari all'azione	
	 * @param 	chosen			tipo di privilegio richiesto
	 * 
	 * @see						it.polimi.ingsw.ps06.model.Types
	 */
	public void giveRewards(FamilyMember member, int servantsUsed, CouncilPrivilege chosen){
		
		Privilege priv = new Privilege(servantsUsed, member, chosen);
		priv.activate();
	}
	
	/**
	 * Metodo per ripulire i familiari che sono stati allocati in questa zona, metodo da utilizzare DOPO averne valutato l'ordine
	 */
	public void cleanPalace(){
		memberSpaces.clear();
	}
	
	/**
	 * Getter dei familiare posizionati nel palazzo
	 *
	 * @return	familiari posizionati nel palazzo
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
