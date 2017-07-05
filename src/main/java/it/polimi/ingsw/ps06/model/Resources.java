package it.polimi.ingsw.ps06.model;

import java.io.Serializable;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.Set;

import it.polimi.ingsw.ps06.model.Types.MaterialsKind;
import it.polimi.ingsw.ps06.model.Types.PointsKind;

/**
* Classe per la modelizzazione delle risorse (materiale e punteggi)
*
* @author  ps06
* @version 1.1
* @since   2017-05-13
*/
public class Resources implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9193388048987531889L;

	private EnumMap<MaterialsKind, Integer> materials;
	private EnumMap<PointsKind, Integer> points;
	
	/**
	* Costruttore di default della classe. Inizializzazione delle Mappe dipendente dalla classe Enum di definizione
	*
	*/
	public Resources() 
	{	
		materials = new EnumMap<MaterialsKind, Integer>(MaterialsKind.class);
		points = new EnumMap<PointsKind, Integer>(PointsKind.class);
		
		for (MaterialsKind kind : MaterialsKind.values()) 	materials.put(kind, 0);
		for (PointsKind kind : PointsKind.values()) 		points.put(kind, 0);
	}
	
	
	/**
	 * Costruttore della classe nel quale viene preimpostato un valore ad un materiale
	 * @param	kind	Tipo di materiale da settare	
	 * @param	value	Quantità da assegnare al materiale
	 * 
	 * @see		it.polimi.ingsw.ps06.model.Types
	 */
	public Resources(MaterialsKind kind, int value) 
	{	
		this();
		materials.put(kind, value);
	}
	
	/**
	 * Costruttore della classe nel quale viene preimpostato un valore ad un punteggio
	 * @param	kind	Tipo di punteggio da settare	
	 * @param	value	Quantità da assegnare al punteggio
	 * 
	 * @see		it.polimi.ingsw.ps06.model.Types
	 */
	public Resources(PointsKind kind, int value) 
	{	
		this();
		points.put(kind, value);
	}
	
	/**
	* Imposta un valore ad una certa risorsa di tipo materiale
	*
	* @param 	kind		Tipo di materiale a cui applicare l'operazione
	* @param	value		Valore da impostare al materiale
	*/
	public void setResourceValue(MaterialsKind kind, int value) {
		materials.put(kind, value);
	}
	
	/**
	* Imposta un valore ad una certa risorsa di tipo punteggio
	*
	* @param 	kind		Tipo di punteggio a cui applicare l'operazione
	* @param	value		Valore da impostare al punteggio
	*/
	public void setResourceValue(PointsKind kind, int value) {
		points.put(kind, value);
	}
	
	/**
	* Inizializzazione di tutte le risorse a zero
	*/
	public void clearResources() {
		Set<MaterialsKind> materialsSet = materials.keySet();
        for(MaterialsKind currentMaterial : materialsSet) setResourceValue(currentMaterial, 0);
        
        Set<PointsKind> pointsSet = points.keySet();
        for(PointsKind currentPoint : pointsSet) setResourceValue(currentPoint, 0);
	}
	
	/**
	* Somma un valore ad una certa risorsa di tipo materiale (non distruttivo)
	*
	* @param 	kind			Tipo di materiale a cui applicare l'operazione
	* @param	increaseValue	Valore da sommare al materiale
	*/
	public void increaseResourceValue(MaterialsKind kind, int increaseValue) {
		int currentValue = materials.get(kind);
		materials.put(kind, currentValue + increaseValue);
	}
	
	/**
	* Somma un valore ad una certa risorsa di tipo punteggio (non distruttivo)
	*
	* @param 	kind			Tipo di punteggio a cui applicare l'operazione
	* @param	increaseValue	Valore da sommare al punteggio
	*/
	public void increaseResourceValue(PointsKind kind, int increaseValue) {
		int currentValue = points.get(kind);
		points.put(kind, currentValue + increaseValue);
	}
	
	/**
	* Sottrai un valore ad una certa risorsa di tipo materiale
	*
	* @param 	kind			Tipo di materiale a cui applicare l'operazione
	* @param	decreaseValue	Valore da sottrarre al materiale
	* 
	* @return	false			Se sottranendo la risorsa finisce in negativo, 
	* 							true in caso positivo
	*/
	public boolean decreaseResourceValue(MaterialsKind kind, int decreaseValue) {
		
		if (decreaseValue > materials.get(kind))
			return false;
		
		int currentvalue = materials.get(kind);
		materials.put(kind, currentvalue - decreaseValue);
		return true;
	}
	
	/**
	* Sottrai un valore ad una certa risorsa di tipo punteggio
	*
	* @param 	kind			Tipo di punteggio a cui applicare l'operazione
	* @param	decreaseValue	Valore da sottrarre al punteggio
	* 
	* @return	false			Se sottranendo la risorsa finisce in negativo, 
	* 							true in caso positivo
	*/
	public boolean decreaseResourceValue(PointsKind kind, int decreaseValue) {
		
		if (decreaseValue > points.get(kind))
			return false;
		
		int currentvalue = points.get(kind);
		points.put(kind, currentvalue - decreaseValue);
		return true;
	}
	
	/**
	* Ottieni il valore corrente di una certa risorsa di tipo materiale
	*
	* @param	kind	Tipo di materiale interessato
	* 
	* @return 			Valore della risorsa 
	*/
	public int getResourceValue(MaterialsKind kind) {
		return (materials.get(kind));
	}
	
	/**
	* Ottieni il valore corrente di una certa risorsa di tipo punteggio
	*
	* @param 	kind	Tipo di punteggio interessato
	* 
	* @return 			Valore della risorsa 
	*/
	public int getResourceValue(PointsKind kind) {
		return (points.get(kind));
	}

	/**
	* Somma la risorsa corrente con una seconda risorsa, settando correttamente
	* ogni singolo campo opportunatamente 
	*
	* @param 	r			Risorsa da sommare
	* 
	* @return 	Resources	Risorsa attuale a seguito dell'addizione
	*/
	public Resources add(Resources r) {
		
		Set<MaterialsKind> materialsSet = materials.keySet();
        for(MaterialsKind currentMaterial : materialsSet) increaseResourceValue(currentMaterial, r.getResourceValue(currentMaterial));
        
        Set<PointsKind> pointsSet = points.keySet();
        for(PointsKind currentPoint : pointsSet) increaseResourceValue(currentPoint, r.getResourceValue(currentPoint));
        
        return this;
	}
	
	/**
	 * Sottrai la risorsa corrente con una seconda risorsa, settando correttamente
	 * ogni singolo campo opportunatamente
	 * 
	 * @param	r	risorse da togliere
	 */
	
	public void subtract(Resources r){
		Set<MaterialsKind> materialsSet = materials.keySet();
		for (MaterialsKind currentmaterial : materialsSet) decreaseResourceValue(currentmaterial, r.getResourceValue(currentmaterial));
		
		Set<PointsKind> pointsSet = points.keySet();
		for (PointsKind currentpoint : pointsSet) decreaseResourceValue(currentpoint, r.getResourceValue(currentpoint));
	}
	
	/**
	* Verifica se la risorsa corrente contiene una quantità di materiali e punteggi
	* uguale o maggiore rispetto ad una risorsa di controllo
	*
	* @param 	r		Risorsa di controllo per il confronto
	* 
	* @return 	true	se ogni risorsa corrente è almeno uguale alla risorsa confronto. 
	*/
	public boolean isBiggerThan(Resources r) {
		
		boolean flagMaterials = true;
		boolean flagPoints = true;
		
		Iterator<MaterialsKind> materialsIterator = materials.keySet().iterator();
        while(materialsIterator.hasNext() && flagMaterials == true) 
        {
        	MaterialsKind currentMaterial = materialsIterator.next();	
        	if ( getResourceValue(currentMaterial) < r.getResourceValue(currentMaterial) ) flagMaterials = false;
        }

        Iterator<PointsKind> pointsIterator = points.keySet().iterator();
        while(pointsIterator.hasNext() && flagPoints == true)
        {
        	PointsKind currentPoint = pointsIterator.next();	
        	if ( getResourceValue(currentPoint) < r.getResourceValue(currentPoint) ) flagPoints = false;
        }
		
        return (flagMaterials && flagPoints);
	}
	
	@Override
	public String toString() {
		String s = "Coin:" + materials.get(MaterialsKind.COIN) 
				+ " Wood:" + materials.get(MaterialsKind.WOOD)
				+ " Stone:" + materials.get(MaterialsKind.STONE)
				+ " Servant:" + materials.get(MaterialsKind.SERVANT)
				+ " Faith:" + points.get(PointsKind.FAITH_POINTS)
				+ " Military:" + points.get(PointsKind.MILITARY_POINTS)
				+ " Victory:" + points.get(PointsKind.VICTORY_POINTS);
		
		return s;
	}
}
