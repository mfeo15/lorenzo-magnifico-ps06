package it.polimi.ingsw.ps06.model;

import java.util.Observable;

public class EffectsActiveGathering extends EffectsActive {

	public EffectsActiveGathering(Observable toStalk) {
		super(toStalk);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
		Gathering g = (Gathering) toStalk;
	}

}
