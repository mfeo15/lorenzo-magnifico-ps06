package it.polimi.ingsw.ps06.model.cards.leader;

import java.util.Observer;

import it.polimi.ingsw.ps06.model.LeaderRequirement;
import it.polimi.ingsw.ps06.model.Types;
import it.polimi.ingsw.ps06.model.Types.LeaderStates;
import it.polimi.ingsw.ps06.model.cards.Card;
import it.polimi.ingsw.ps06.networking.messages.MessageModel2ViewNotification;

/**
 * Classe per la definizione e gestione delle carte leader
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
	 * <p>Costruttore della classe Leader.</p>
	 * <p>Si occupa di inizializzare lo State Pattern correttamente. Lo stato di default per l'oggetto è "IN_HAND". </p>
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
	
	/**
	 * Getter per il parametro del tipo di effetto permamente
	 * 
	 * @return	true	se l'effetto è del tipo "una volta per turno"
	 */
	public boolean isOncePerRoundEffect() {
		return OncePerRoundEffect;
	}

	/**
	 * Setter per il parametro che indica se l'effetto della carta
	 * è del tipo "una volta per turno"
	 * 
	 * @param oncePerRoundEffect	booleano da settare al parametro
	 */
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
		
		if (this.leaderState == discarded) 
			return	LeaderStates.DISCARDED;
		
		return null;
	}
	
	/**
	 * <p>STATE PATTERN DESIGN:</p>
	 * <p>Modifica lo stato attuale della classe</p> 
	 * 
	 * @param leaderState	nuovo stato della classe
	 */
	public void setLeaderState(LeaderState leaderState) {
		this.leaderState = leaderState;
	}
	
	/**
	 * Metodo per giocare una carta
	 * 
	 * @return	true	se lo stato attuale ha permesso di completare l'azione
	 */
	public boolean playLeader() {
		return( leaderState.playLeader() );
	}
	
	/**
	 * Metodo per attivare una carta
	 * 
	 * @return	true	se lo stato attuale ha permesso di completare l'azione
	 */
	public boolean activateLeader() {
		return( leaderState.activateLeader() );
	}


	/**
	 * Metodo per disattivare una carta
	 * 
	 * @return	true	se lo stato attuale ha permesso di completare l'azione
	 */
	public boolean deactivateLeader() {
		return( leaderState.deactivateLeader() );
	}
	
	/**
	 * Metodo per scartare una carta
	 * 
	 * @return	true	se lo stato attuale ha permesso di completare l'azione
	 */
	public boolean discardLeader() {
		return( leaderState.discardLeader() );
	}
	
	/**
	 * <p>STATE PATTERN DESIGN:</p>
	 * <p>Ottieni lo stato IN_HAND della classe</p>
	 * 
	 * @return 	LeaderState.IN_HAND : stato del leader nel momento in cui non è stato ancora giocato
	 */
	public LeaderState getInHandState() {
		return inHand;
	}
	
	/**
	 * <p>STATE PATTERN DESIGN:</p>
	 * <p>Ottieni lo stato ON_TABLE_FACE_UP della classe</p>
	 * 
	 * @return 	LeaderState.ON_TABLE_FACE_UP : stato del leader nel momento in cui è stato giocato
	 */
	public LeaderState getActivatedLeaderState() {
		return onTableFaceUp;
	}
	
	/**
	 * <p>STATE PATTERN DESIGN:</p>
	 * <p>Ottieni lo stato ON_TABLE_FACE_DOWN della classe</p>
	 * 
	 * @return 	LeaderState.ON_TABLE_FACE_DOWN : stato del leader nel momento in cui è stato ancora giocato
	 * 												ed attivato (valido unicamente per gli effetti "uno per turno")
	 */
	public LeaderState getDeactivatedLeaderState() {
		return onTableFaceDown;
	}
	
	/**
	 * <p>STATE PATTERN DESIGN:</p>
	 * <p>Ottieni lo stato DISCARDED della classe</p>
	 * 
	 * @return	LeaderState.DISCARDED : stato nel quale la carta è stata scartata
	 */
	public LeaderState getDiscardedLeaderState() {
		return discarded;
	}

	
	/**
	* Setter per il requirement del Leader
	* 
	* @param 	requirement		attributo requisito della classe Leader
	*/
	public void setRequirement(LeaderRequirement requirement) {
		this.requirement = requirement;
	}
}
