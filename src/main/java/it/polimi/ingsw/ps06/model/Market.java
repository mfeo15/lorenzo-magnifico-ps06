package it.polimi.ingsw.ps06.model;

import it.polimi.ingsw.ps06.model.Types.Action;

/**
* Classe per la gestione dello spazio di mercato
*
* @author  ps06
* @version 1.0
* @since   2017-05-10 
*/

public class Market implements ActionSpace {
	private FamilyMember market1, market2, market3, market4;
	private Player player;
	
	
	/**
	* Metodo per il piazzamento di un familiare su di uno degli spazi del mercato, include controlli di posizionamento
	*
	* @author  ps06
	* @version 1.1
	* @since   2017-05-11 
	*/
	@Override
	public void placeMember(FamilyMember member, Action chosenAction) throws IllegalStateException {
		
		if(chosenAction==Action.MARKET_1) if(market1 != null) market1=member; else throw new IllegalStateException();
		if(chosenAction==Action.MARKET_2) if(market2 != null) market2=member; else throw new IllegalStateException();
		if(chosenAction==Action.MARKET_3) if(market3 != null) market3=member; else throw new IllegalStateException();
		if(chosenAction==Action.MARKET_4) if(market4 != null) market4=member; else throw new IllegalStateException();
		
		// player = member.associatedPlayer();
	}
	
	public Market(int numberPlayers) {
		
	}
	
	/**
	* Metodo per rendere disponibli solo il numero di finestre di cui si hanno bisogno, a seconda del numero di giocatori
	*
	* @author  ps06
	* @version 1.0
	* @since   2017-05-11 
	*/
	public void setSpaces(int giocatori) {
		
	}
	
	/**
	* Metodo per aumentare le risorse di chi usa una casella del mercato
	*
	* @author  ps06
	* @version 1.0
	* @since   2017-05-11 
	*/
	public void giveResources(Player player, Action chosenAction) {
		
	}
	
	/**
	* Metodo per reimpostare i familiari alla loro posizione di partenza
	*
	* @author  ps06
	* @version 1.0
	* @since   2017-05-11 
	*/
	public void cleanMarket() {
		
	}
	
	
}
