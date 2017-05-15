package it.polimi.ingsw.ps06.model;

import java.util.EnumMap;

import it.polimi.ingsw.ps06.model.Types.MaterialsKind;
import it.polimi.ingsw.ps06.model.Types.PointsKind;

/**
* Classe associata ad un player che fa da contenitore per tutte le risorse che il player ha disponibili
*
* @author  ps06
* @version 1.0
* @since   2017-05-13
*/
public class Warehouse {
	
	private Resources status;
	
	public Warehouse() {
		status = new Resources();
		initMaterials();
		initPoints();
		
	}
	
	public void addResources(Resources r){
		status.add(r);
	}
	
	private void initMaterials() {	
		
		status.setResourceValue(MaterialsKind.WOOD, 2);
		status.setResourceValue(MaterialsKind.STONE, 2);
		status.setResourceValue(MaterialsKind.SERVANT, 3);
	}
	
	private void initPoints() {
		
		status.setResourceValue(PointsKind.VICTORY_POINTS, 0);
		status.setResourceValue(PointsKind.FAITH_POINTS, 0);
		status.setResourceValue(PointsKind.MILITARY_POINTS, 0);
	}
}
