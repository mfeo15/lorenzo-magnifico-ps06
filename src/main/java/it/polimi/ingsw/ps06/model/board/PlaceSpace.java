package it.polimi.ingsw.ps06.model.board;

import static it.polimi.ingsw.ps06.model.Types.Action;
import it.polimi.ingsw.ps06.model.FamilyMember;

public interface PlaceSpace 
{	
	/**
	 * Metodo per il piazzamento di un familiare nella zona
	 *
	 * @param 	member			Familiare che si vuole piazzare
	 * @param 	chosenAction 	tipo di azione da eseguire
	 * @param	servants		numero di servitori impiegati per il piazzamento
	 * 
	 * @see						it.polimi.ingsw.ps06.model.Types
	 */
	void placeMember(FamilyMember member, Action chosenAction, int servants);
	
	/**
	 * Gestisci errori di posizionamento familiare
	 *
	 * @param	code		codice errore
	 * @param	member		famigliare che ha generato un errore nel piazzamento
	 */
	void handleBadPlacing(int code, FamilyMember member);
}
