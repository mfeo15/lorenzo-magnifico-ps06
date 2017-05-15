package it.polimi.ingsw.ps06.model;

import java.util.ArrayList;
import java.util.EnumMap;

import it.polimi.ingsw.ps06.model.Types.MaterialsKind;
import it.polimi.ingsw.ps06.model.Types.PointsKind;

/**
* Classe per la gestione di effetti che comportano una modifica alle risorse di un player
*
* @author  ps06
* @version 1.0
* @since   2017-05-11
*/
public class EffectsResources extends Effect {
	private Resources bonus;
	
	/**
	* Constructor.
	* 
	*/
	public EffectsResources(Resources r){
		this.bonus = new Resources();
		bonus=r;
		
	}
	
	/**
	* Metodo per l'assegnazione delle risorse ad un determinato player
	*
	* @param 	player		Giocatore a cui dare le risorse
	* @return 				Nothing
	*/
	public void updateResources(Warehouse wh){
		wh.addResources(bonus);
	}
	
}
