package it.polimi.ingsw.ps06.model;

import static it.polimi.ingsw.ps06.model.Types.Action;

/**
* Classe per la gestione dello spazio di mercato
*
* @author  ps06
* @version 1.0
* @since   2017-05-10 
*/

public class Market implements ActionSpace {
	private FamilyMember market1, market2, market3, market4;
	private boolean multi;
	private Player player;
	
	
	/**
	* Metodo per il piazzamento di un familiare su di uno degli spazi del mercato, include controlli di posizionamento
	*
	* @param 	member			Familiare che si vuole piazzare
	* @param 	chosenAction 	Codice dell'azione da eseguire
	* @return 					Nothing
	* @throws					IllegalArgumentException
	* @see 						IllegalArgumentException
	*/
	@Override
	public void placeMember(FamilyMember member, Action chosenAction) throws IllegalArgumentException {
		
		multi = ActiveEffects.handleLeaderMulti();
		
		if(chosenAction==Action.MARKET_1) if(market1 != null) market1=member; else throw new IllegalArgumentException();
		if(chosenAction==Action.MARKET_2) if(market2 != null) market2=member; else throw new IllegalArgumentException();
		if(chosenAction==Action.MARKET_3) if(market3 != null) market3=member; else throw new IllegalArgumentException();
		if(chosenAction==Action.MARKET_4) if(market4 != null) market4=member; else throw new IllegalArgumentException();
		
		// player = member.associatedPlayer();
		
	}
	
	/**
	* Metodo per rendere disponibli solo il numero di finestre di cui si hanno bisogno, a seconda del numero di giocatori
	*
	* @param 	giocatori	Numero di giocatori della partita
	* @return 				Nothing
	*/
	public static void setSpaces(int giocatori){
		
	}
	
	/**
	* Metodo per aumentare le risorse di chi usa una casella del mercato
	*
	* @param 	player			Giocatore a cui dare le risorse
	* @param 	chosenAction	Codice dell'azione da eseguire	
	* @return 					Nothing
	*/
	public static void giveResources(Player player, Action chosenAction) {
		
	}
	
	/**
	* Metodo per reimpostare i familiari alla loro posizione di partenza
	*
	* @param 	Unused
	* @return 	Nothing
	*/
	public static void cleanMarket() {
		
	}
	
	
}
