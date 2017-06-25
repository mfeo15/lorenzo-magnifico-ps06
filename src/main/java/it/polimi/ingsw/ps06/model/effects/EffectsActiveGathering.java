package it.polimi.ingsw.ps06.model.effects;

import java.util.Observable;

import it.polimi.ingsw.ps06.model.Gathering;
import it.polimi.ingsw.ps06.model.Player;

public class EffectsActiveGathering extends EffectsActive {

	private int bonusDiceValue;
	
	public EffectsActiveGathering(Observable toStalk, int bonusDiceValue) {
		super(toStalk);
		
		this.bonusDiceValue = bonusDiceValue;
	}

	@Override
	public void update(Observable o, Object arg) {
		
		if(!(o instanceof Gathering)){
			throw new IllegalArgumentException();			
		}
		
		if ( owner.equals( (Player) arg ) ) 
		{
			((Gathering) o).setValue( ((Gathering) o).getValue() + bonusDiceValue );
		}
	}
	
	
}