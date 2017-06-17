package it.polimi.ingsw.ps06.model;

/**
 * classe per la definizione e gestione delle carte leader
 * 
 * @author ps06
 * @version 1.1
 * @since 13-05-2017
 */

public class Leader extends Card {
	
	private LeaderRequirement requirement;
	private boolean used=false;
	private boolean active=false;
	
<<<<<<< Updated upstream
	private LeaderState leaderState;
	
	private LeaderState inHand;
	private LeaderState onTableFaceUp;
	private LeaderState onTableFaceDown;
	
	public Leader() {
		inHand = new LeaderInHand(this);
		onTableFaceUp = new LeaderOnTableFaceUp(this);
		onTableFaceDown = new LeaderOnTableFaceDown(this);
		
		leaderState = inHand;
	}
	
	public void setLeaderState(LeaderState leaderState) {
		this.leaderState = leaderState;
	}
	
	public void playLeader() {
		leaderState.playLeader();
	}
	
	public void activateLeader() {
		leaderState.activateLeader();
	}

	public void deactivateLeader() {
		leaderState.deactivateLeader();
	}
	
	public LeaderState getInHandState() {
		return inHand;
	}
	
	public LeaderState getActivateLeaderState() {
		return onTableFaceUp;
	}
	
	public LeaderState getDeactivateLeaderState() {
		return onTableFaceDown;
	}
	 
=======
	/**
	* metodo per verificare se il leader è stato già utilizzato o meno in questo turno
	* 
	* @param 	none
	* 
	* @return	used	valore boolean che mi dice se è stata utilizzata o no
	*/
	
	public void setRequirement(LeaderRequirement lead){
		requirement=lead;
		return;
	}
>>>>>>> Stashed changes
	/**
	* metodo per verificare se il leader è stato già utilizzato o meno in questo turno
	* 
	* @param 	none
	* 
	* @return	used	valore boolean che mi dice se è stata utilizzata o no
	*/
	
	public Boolean checkUsed(){
		return used;
	}
	
	/**
	* metodo per resettare la carta leader e renderla disponibile all'utilizzo all'inizio di un nuovo turno
	* 
	* @param 	none
	* 
	* @return 	Nothing
	*/
	
	 public void resetUsed(){
		 if(used)
			 used=false;
	 }
	 
	/**
	* metodo per attivare la carta leader
	* 
	* @param	giocatore che utilizza la carta 
	* 
	* @return 	Nothing
	*/
	
	public void activateleader(){
		//verifica i requistiti da definire
		active=true;
	}
	
	/**
	* metodo per verificare se il leader è stato già utilizzato o meno in questo turno
	* 
	* @param 	none
	* 
	* @return	used	valore boolean che mi dice se è stata utilizzata o no
	*/
	
	public Boolean checkActive(){
		return active;
	}

	
}
