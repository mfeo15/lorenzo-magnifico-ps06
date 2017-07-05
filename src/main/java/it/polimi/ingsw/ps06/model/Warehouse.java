package it.polimi.ingsw.ps06.model;

import it.polimi.ingsw.ps06.model.Types.MaterialsKind;
import it.polimi.ingsw.ps06.model.Types.PointsKind;
import it.polimi.ingsw.ps06.model.Resources;

/**
* Classe associata ad un player che fa da contenitore per tutte le risorse (punti compresi) che il player ha disponibili
*
* @author  ps06
* @version 1.1
* @since   2017-05-13
*/
public class Warehouse {
	
	private Resources status;
	
	/**
	 * Costruttore della classe, alloca l'attributo di tipo risorsa e setta il valore dei materiali iniziali
	 */
	public Warehouse() {
		status = new Resources();		
		initialSetup();	
	}
	
	/**
	 * Metodo che setta i valori iniziali dei materiali
	 */
	private void initialSetup(){
		status.setResourceValue(MaterialsKind.STONE, 2);
		status.setResourceValue(MaterialsKind.WOOD, 2);
		status.setResourceValue(MaterialsKind.SERVANT, 3);
	}
	
	/**
	 * Getter delle risorse presenti nel warehouse
	 * 
	 * @return	risorsa attuale del Warehouse
	 */
	public Resources getStatus(){
		return this.status;
	}
	
	/**
	* Getter per una specifica quantità di tipo materiale 
	* 
	* @param	kind	tipo di materiale richiesto
	* @return 			quantità del materiale
	*/
	public int getMaterial(MaterialsKind kind){
		return status.getResourceValue(kind);
	}
	
	/**
	* Getter per una specifica quantità di tipo punteggio 
	* 
	* @param	kind	tipo di punteggio richiesto
	* @return 			quantità del punteggio
	*/
	public int getPoints(PointsKind kind){
		return status.getResourceValue(kind);
	}
	
	/**
	* Metodo per aggiungere una certa quantità ad un materiale
	* 
	* @param 	kind		tipo del materiale da aumentare
	* @param	increase	quantità di cui aumentare il materiale
	*/
	public void increaseMaterials(MaterialsKind kind, int increase){
		status.increaseResourceValue(kind, increase);
	}
	
	/**
	* Metodo per aggiungere una certa quantità ad un punteggio
	* 
	* @param 	type		tipo di punteggio da aumentare
	* @param	increase	quantità di cui aumentare i punteggio
	*/
	public void increasePoints(PointsKind type, int increase){
		status.increaseResourceValue(type, increase);
	}
	
	/**
	* Metodo per aggiungere una risorsa intera al Warehouse
	* 
	* @param	res		risorsa da aggiungere 
	*/
	public void addResources(Resources res){
		status.add(res);
	}
	
	/**
	 * Metodo per rimuovere una risorsa intera al Warehouse
	 * 
	 * @param	res		risorse da togliere
	 */
	public void reduceRes(Resources res){
		status.subtract(res);
	}
	
	/**
	* Metodo per sottrare una certa quantità ad un materiale
	* 
	* @param 	kind		tipo di materiale da sottrare
	* @param	decrese		quantità di cui sottrare il materiale
	*/
	public void decreaseMaterial(MaterialsKind kind, int decrese){
		status.decreaseResourceValue(kind, decrese);
	}
	
	/**
	* Metodo per sottrare una certa quantità ad un punteggio
	* 
	* @param 	kind		tipo di punteggio da sottrare
	* @param	decrese		quantità di cui sottrare il punteggio
	*/
	public void decreasePoints(PointsKind kind, int decrese){
		status.decreaseResourceValue(kind, decrese);
	}
	
}