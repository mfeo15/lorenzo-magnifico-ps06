package it.polimi.ingsw.ps06.model;

import java.util.ArrayList;
import it.polimi.ingsw.ps06.model.Resources;
import it.polimi.ingsw.ps06.model.Warehouse;
import it.polimi.ingsw.ps06.model.Types.MaterialsKind;
import it.polimi.ingsw.ps06.model.Types.PointsKind;

/**
* Classe per la modellizzazione della tessera personale
*
* @author  ps06
* @version 1.1
* @since   2017-05-09 
*/
public class PersonalBoard {
	
	private ArrayList<Territory> territories;
	private ArrayList<Building> buildings;
	private ArrayList<Character> characters;
	private ArrayList<Venture> ventures;
	
	private BonusTile bonusTile;
	
	private Warehouse inventory;
	
	/**
	* costruttore della plancia giocatore, alloca gli arraylist per le carte che si 
	* aggiungono man mano, un warehouse contenitore delle risorse e una tessera bonus personale
	*
	* @param 	none
	* 
	* @return 	Nothing
	*/
	
	public PersonalBoard() {
		territories = new ArrayList<Territory>();
		buildings = new ArrayList<Building>();
		characters = new ArrayList<Character>();
		ventures = new ArrayList<Venture>();
		inventory = new Warehouse();
		bonusTile = new BonusTile();
	}
	
	/**
	* Metodo che restituisce le tessera bonus personale
	* 
	* @param 	none
	* @return 	bonusTile	la tessera personale
	*/
	
	public BonusTile getBonusTile(){
		return bonusTile;
	}
	
	/**
	* Metodo che restituisce le risorse presenti nel warehouse
	* 
	* @param 	none
	* @return 	inventory   attributo di tipo risorsa
	*/
	
	public Resources getInventory(){
		return inventory.getStatus();
	}
	
	/**
	* Metodo che restituisce la quantità di pietre, servitori, legno o coins a seconda di ciò che è richiesto
	*
	* @param 	kind	Tipo di materiale a cui applicare l'operazione
	* @return 	inventory.getMaterial(kind)  metodo di warehouse che restituisce la quantità del materiale che ho chiesto
	*/
	
	public int getMaterialsCount(MaterialsKind kind){
		return inventory.getMaterial(kind);
	}
	
	/**
	* Metodo che restituisce la quantità di pietre, servitori, legno o coins a seconda di ciò che è richiesto
	*
	* @param 	type	Tipo di punti a cui applicare l'operazione
	* @return 	inventory.getPoints(kind)  metodo di warehouse che restituisce il numero dei punti che ho chiesto
	*/
	
	public int getPointsCount(PointsKind type){
		return inventory.getPoints(type);
	}	
	
	/* public **** showMaterials(){
	
	}
	   public **** showPoints(){
	   
	   }
	*/
	
	/**
	* Metodo che permette di aggiungere materiali al warehouse
	*
	* @param 	kind		Tipo di materiale a cui applicare l'operazione
	* @param	quantity	quantità di materiale da aggiungere
	* @return 	nothing
	*/
	
	public void addMaterials(MaterialsKind kind, int quantity){
		inventory.increaseMaterials(kind, quantity);
		return;
	}
	
	/**
	* Metodo che permette di aggiungere punti al warehouse
	*
	* @param 	type		Tipo di punti a cui applicare l'operazione
	* @param    amount 		Quantità di cui si vogliono incrementare i punti
	* @return 	nothing
	*/
	
	public void addPoints(PointsKind type, int amount){
		inventory.increasePoints(type, amount);
		return;
	}
	
	/**
	* Metodo che permette di aggiungere territory sulla plancia giocatore
	*
	* @param 	cardTerritory		carta da aggiungere alla carta
	* @return 	nothing
	*/
	
	public void addTerritory(Territory cardTerritory) {
		
			switch(territories.size()){
			case 0:
				territories.add(cardTerritory);
				break;
			case 1:
				territories.add(cardTerritory);
				break;
			case 2:
				if(inventory.getPoints(PointsKind.MILITARY_POINTS)>=3){
					territories.add(cardTerritory);
				}
				else {
					controller.handleNotEnoughMILPoints();		//da implementare ancora il controller
				}
				break;
			case 3:
				if(inventory.getPointsCount(PointsKind.MILITARY_POINTS)>=7){
					territories.add(cardTerritory);
				}
				else {
					controller.handleNotEnoughMILPoints();
				}
				break;
			case 4:
				if(inventory.getPointsCount(PointsKind.MILITARY_POINTS)>=12){
					territories.add(cardTerritory);
				}
				else {
					controller.handleNotEnoughMILPoints();
				}
				break;
			case 5:
				if(inventory.getPointsCount(PointsKind.MILITARY_POINTS)>=18) {
					territories.add(cardTerritory);
				}
				else {
					controller.handleNotEnoughMILPoints(); 
				}
				break;		
			default:
				Controller.handleMaxTerritories();  //Controller ancora da implementare, dovrà gestire e informare la view che è stato raggiunto il numero massimo di territori
				break;
				}			
	}
	
	/**
	* Metodo che permette di aggiungere edifici sulla plancia giocatore
	*
	* @param 	cardBuilding		carta da aggiungere alla carta
	* @return 	nothing
	*/

	public void addBuilding(Building cardBuilding) {
		if(buildings.size()<6)
			buildings.add(cardBuilding);
		else
			Controller.handleMaxBuildings();  // controller ancora da implementare
	return;
	}
	
	/**
	* Metodo che permette di aggiungere personaggi sulla plancia giocatore
	*
	* @param 	cardTerritory		carta da aggiungere alla carta
	* @return 	nothing
	*/
	
	public void addCharacter(Character cardCharacter) {
		characters.add(cardCharacter);
		return;
	}

	/**
	* Metodo che permette di aggiungere imprese sulla plancia giocatore
	*
	* @param 	cardVenture		carta da aggiungere alla carta
	* @return 	nothing
	*/
	
	public void addVenture(Venture cardVenture) {
		ventures.add(cardVenture);
		return;		
	}

}