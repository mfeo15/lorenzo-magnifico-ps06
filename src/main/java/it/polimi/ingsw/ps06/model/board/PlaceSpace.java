package it.polimi.ingsw.ps06.model.board;

import static it.polimi.ingsw.ps06.model.Types.Action;
import it.polimi.ingsw.ps06.model.FamilyMember;

public interface PlaceSpace 
{	
	void placeMember(FamilyMember member, Action chosenAction, int servants);
}
