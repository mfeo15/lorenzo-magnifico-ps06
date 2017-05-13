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
	
	private EnumMap<MaterialsKind, Integer> materials;
	private EnumMap<PointsKind, Integer> points;
	
	public Warehouse() {
		
		initMaterials();
		initPoints();
	}
	
	private void initMaterials() {
		materials = new EnumMap<MaterialsKind, Integer>(MaterialsKind.class);
		
		materials.put(MaterialsKind.WOOD, 2);
		materials.put(MaterialsKind.STONE, 2);
		materials.put(MaterialsKind.SERVANT, 3);
	}
	
	private void initPoints() {
		points = new EnumMap<PointsKind, Integer>(PointsKind.class);
		
		points.put(PointsKind.VICTORY_POINTS, 0);
		points.put(PointsKind.FAITH_POINTS, 0);
		points.put(PointsKind.MILITARY_POINTS, 0);
	}
}
