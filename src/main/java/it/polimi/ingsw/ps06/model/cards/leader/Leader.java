package it.polimi.ingsw.ps06.model.cards.leader;

import java.util.Observer;

import it.polimi.ingsw.ps06.model.LeaderRequirement;
import it.polimi.ingsw.ps06.model.Types;
import it.polimi.ingsw.ps06.model.Types.LeaderStates;
import it.polimi.ingsw.ps06.model.cards.Card;
import it.polimi.ingsw.ps06.networking.messages.MessageModel2ViewNotification;

/**
 * classe per la definizione e gestione delle carte leader
 * 
 * @author ps06
 * @version 1.1
 * @since 13-05-2017
 */

public class Leader extends Card {
	
	private LeaderRequirement requirement;

	private boolean OncePerRoundEffect;

	private LeaderState leaderState;
	
	private LeaderState inHand;
	private LeaderState onTableFaceUp;
	private LeaderState onTableFaceDown;
	private LeaderState discarded;
	
	/**
	 * Costruttore della classe Leader.
	 * Si occupa di inizializzare lo State Pattern correttamente. Lo stato di default
	 * per l'oggetto è "IN_HAND"
	 */
	public Leader() {
		inHand = new LeaderInHand(this);
		onTableFaceUp = new LeaderOnTableFaceUp(this);
		onTableFaceDown = new LeaderOnTableFaceDown(this);
		discarded = new LeaderDiscarded(this);
		
		leaderState = inHand;
	}
	
	public LeaderRequirement getRequirement() {
		return requirement;
	}
	
	public boolean isOncePerRoundEffect() {
		return OncePerRoundEffect;
	}

	public void setOncePerRoundEffect(boolean oncePerRoundEffect) {
		OncePerRoundEffect = oncePerRoundEffect;
	}
	
	/**
	 * Correlazione biunivoca tra lo stato (State Pattern) della classe
	 * ed un enum di riferimento contenuto in Types
	 * 
	 * @return	LeaderStates	valore dell'enum associato allo stato della classe
	 */
	public LeaderStates getLeaderState() {
		if (this.leaderState == inHand) 			
			return LeaderStates.IN_HAND;
		
		if (this.leaderState == onTableFaceUp) 		
			return LeaderStates.ON_TABLE_FACE_UP;
		
		if (this.leaderState == onTableFaceDown)	
			return LeaderStates.ON_TABLE_FACE_DOWN;
		
		//if (this.leaderState == inHand) 
			//return;
		
		return null;
	}
	
	/**
	 * STATE PATTERN DESIGN:
	 * Modifica lo stato attuale della classe 
	 * 
	 * @param leaderState	nuovo stato della classe
	 */
	public void setLeaderState(LeaderState leaderState) {
		this.leaderState = leaderState;
	}
	
	public boolean playLeader() {
		return( leaderState.playLeader() );
	}
	
	public boolean activateLeader() {
		return( leaderState.activateLeader() );
	}

	public boolean deactivateLeader() {
		return( leaderState.deactivateLeader() );
	}
	
	public boolean discardLeader() {
		return( leaderState.discardLeader() );
	}
	
	/**
	 * STATE PATTERN DESIGN:
	 * Ottieni lo stato IN_HAND della classe
	 * 
	 * @return 	IN_HAND		stato del leader nel momento in cui non è stato ancora giocato
	 */
	public LeaderState getInHandState() {
		return inHand;
	}
	
	/**
	 * STATE PATTERN DESIGN:
	 * Ottieni lo stato ON_TABLE_FACE_UP della classe
	 * 
	 * @return 	ON_TABLE_FACE_UP	stato del leader nel momento in cui è stato giocato
	 */
	public LeaderState getActivatedLeaderState() {
		return onTableFaceUp;
	}
	
	/**
	 * STATE PATTERN DESIGN:
	 * Ottieni lo stato ON_TABLE_FACE_DOWN della classe
	 * 
	 * @return 	ON_TABLE_FACE_DOWN	stato del leader nel momento in cui è stato ancora giocato
	 * 									ed attivato (valido unicamente per gli effetti "uno per turno")
	 */
	public LeaderState getDeactivatedLeaderState() {
		return onTableFaceDown;
	}
	
	/**
	 * STATE PATTERN DESIGN:
	 * Ottieni lo stato DISCARDED della classe
	 * 
	 * @return DISCARDED	stato del leader nel momento in cui è stata scartato
	 */
	public LeaderState getDiscardedLeaderState() {
		return discarded;
	}

	
	/**
	* Setter per il requirement del Leader
	* 
	* @param 	requirement		attributo requisito della classe Leader
	* 
	* @return	nessuno
	*/
	public void setRequirement(LeaderRequirement requirement) {
		this.requirement = requirement;
	}
}
