package it.polimi.ingsw.ps06.model;

import java.util.EnumMap;

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
	* 
	* @param 	none
	* @return 	nothing
	*/
	
	public Warehouse() {
		status = new Resources();		
		initialSetup();	
	}
	
	/**
	* Metodo che setta i valori iniziali dei materiali
	* 
	* @param 	none
	* @return 	none
	*/
	
	public void initialSetup(){
		status.setMaterialsValue(MaterialsKind.STONE, 2);
		status.setMaterialsValue(MaterialsKind.WOOD, 2);
		status.setMaterialsValue(MaterialsKind.SERVANT, 3);
		return;
	}
	
	/**
	* Metodo che restituisce le risorse presenti nel warehouse
	* 
	* @param 	none
	* @return 	status   attributo di tipo risorsa
	*/
	
	public Resources getStatus(){
		return this.status;
	}
	
	/**
	* Metodo che restituisce la quantità presente nel warehouse della risorsa richiesta 
	* 
	* @param 	kind	tipo di materiale richiesto
	* @return 	quantità del materiale
	*/
	
	public int getMaterial(MaterialsKind kind){
		return status.getMaterialsValue(kind);
	}
	
	/**
	* Metodo che restituisce il numero di punti del tipo che ho richiesto 
	* 
	* @param 	type 	tipo di punti richiesto
	* @return 	quantità di punti
	*/
	
	public int getPoints(PointsKind type){
		return status.getPointsValue(type);
	}
	
	/**
	* Metodo per aggiungere una certa quantità ad un materiale
	* 
	* @param 	kind	tipo del materiale da aumentare
	* @param	int		quantità di cui aumentare il materiale
	* @return 	nothing
	*/
	
	public void increaseMaterials(MaterialsKind kind, int increase){
		status.increaseMaterialsValue(kind, increase);
	}
	
	/**
	* Metodo per aggiungere una certa quantità ad un materiale
	* 
	* @param 	type	tipo di punti da aumentare
	* @param	int		quantità di cui aumentare i punti
	* @return 	nothing
	*/
	
	public void increasePoints(PointsKind type, int increase){
		status.increasePointsValue(type, increase);
	}
	
	/**
	* Metodo per aggiungere una certa quantità di risorse al mio warehouse
	* 
	* @param 	res		risorse da aggiungere 
	* @return 	nothing
	*/
	
	public void addResources(Resources res){
		status.add(res);
		return;
	}
	
/* public **** showMaterials(){
	
	}
	   public **** showPoints(){
	   
	   }
	*/
	
}