package it.polimi.ingsw.ps06.model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps06.model.Types.MaterialsKind;
import it.polimi.ingsw.ps06.model.Types.PointsKind;
import it.polimi.ingsw.ps06.model.cards.Building;
import it.polimi.ingsw.ps06.model.cards.Character;
import it.polimi.ingsw.ps06.model.cards.Territory;
import it.polimi.ingsw.ps06.model.cards.Venture;
import it.polimi.ingsw.ps06.networking.messages.MessagePersonalBoardResourcesStatus;

/**
* Classe per la modellizzazione della tessera personale
*
* @author  ps06
* @version 1.1
* @since   2017-05-09 
*/
public class PersonalBoard extends Observable {
	
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
	
	public int getAmount(MaterialsKind kind){
		return inventory.getMaterial(kind);
	}
	
	/**
	* Metodo che restituisce la quantità di pietre, servitori, legno o coins a seconda di ciò che è richiesto
	*
	* @param 	type	Tipo di punti a cui applicare l'operazione
	* @return 	inventory.getPoints(kind)  metodo di warehouse che restituisce il numero dei punti che ho chiesto
	*/
	
	public int getAmount(PointsKind type){
		return inventory.getPoints(type);
	}	
	
	/**
	* Metodo che restituisce i territori presenti sulla plancia giocatore
	*
	* @param 	none
	* 
	* @return 	territories		territori sulla plancia
	*/
	
	public ArrayList<Territory> getTerritories(){
		return territories;
	}	
	
	/**
	* Metodo che restituisce gli edifici presenti sulla plancia giocatore
	*
	* @param 	none
	* 
	* @return 	buildings	edifici sulla plancia
	*/
	
	public ArrayList<Building> getBuildings(){
		return buildings;
	}	
	
	/**
	* Metodo che restituisce le imprese presenti sulla plancia giocatore
	*
	* @param 	none
	* 
	* @return 	ventures	imprese sulla plancia
	*/
	
	public ArrayList<Venture> getVentures(){
		return ventures;
	}	
	
	/**
	* Metodo che restituisce i personaggi presenti sulla plancia giocatore
	*
	* @param 	none
	* 
	* @return 	characters	personaggi sulla plancia
	*/
	
	public ArrayList<Character> getCharacters(){
		return characters;
	}	
	/**
	* Metodo che permette di aggiungere materiali al warehouse
	*
	* @param 	kind		Tipo di materiale a cui applicare l'operazione
	* @param	quantity	quantità di materiale da aggiungere
	* @return 	nothing
	*/
	
	public void addResource(MaterialsKind kind, int quantity){
		inventory.increaseMaterials(kind, quantity);
		notifyChangement();
	}
	
	/**
	* Metodo che permette di aggiungere punti al warehouse
	*
	* @param 	type		Tipo di punti a cui applicare l'operazione
	* @param    amount 		Quantità di cui si vogliono incrementare i punti
	* @return 	nothing
	*/
	
	public void addResource(PointsKind kind, int quantity){
		inventory.increasePoints(kind, quantity);
		notifyChangement();
	}
	

	/**
	* Metodo che permette di aggiungere un intero tipo risorsa al warehouse
	*
	* @param 	Resources	risorse da aggiungere al warehouse
	* @return 	nothing
	*/
	
	public void addResource(Resources r){
		inventory.addResources(r);
		notifyChangement();
	}
	
	/**
	* Metodo che permette di aggiungere territory sulla plancia giocatore
	*
	* @param 	cardTerritory		carta da aggiungere alla carta
	* @return 	nothing
	*/
	
	public void addCard(Territory cardTerritory) {
		
			switch(territories.size()) {
			case 0:
			case 1:
				territories.add(cardTerritory);
				break;
			case 2:
				if(inventory.getPoints(PointsKind.MILITARY_POINTS)>=3){
					territories.add(cardTerritory);
				}
				else {
				//	controller.handleNotEnoughMILPoints();		da implementare ancora il controller
				}
				break;
			case 3:
				if(inventory.getPoints(PointsKind.MILITARY_POINTS)>=7){
					territories.add(cardTerritory);
				}
				else {
					//controller.handleNotEnoughMILPoints();
				}
				break;
			case 4:
				if(inventory.getPoints(PointsKind.MILITARY_POINTS)>=12){
					territories.add(cardTerritory);
				}
				else {
					//controller.handleNotEnoughMILPoints();
				}
				break;
			case 5:
				if(inventory.getPoints(PointsKind.MILITARY_POINTS)>=18) {
					territories.add(cardTerritory);
				}
				else {
					//controller.handleNotEnoughMILPoints(); 
				}
				break;		
			default:
				//Controller.handleMaxTerritories();  Controller ancora da implementare, dovrà gestire e informare la view che è stato raggiunto il numero massimo di territori
				break;
				}			
	}
	
	/**
	* Metodo che permette di aggiungere edifici sulla plancia giocatore
	*
	* @param 	cardBuilding		carta da aggiungere alla carta
	* @return 	nothing
	*/

	public void addCard(Building cardBuilding) {
		if (buildings.size() < 6)
			buildings.add(cardBuilding);
		else
			//Controller.handleMaxBuildings();  controller ancora da implementare
	return;
	}
	
	/**
	* Metodo che permette di aggiungere personaggi sulla plancia giocatore
	*
	* @param 	cardTerritory		carta da aggiungere alla carta
	* @return 	nothing
	*/
	
	public void addCard(Character cardCharacter) {
		characters.add(cardCharacter);
	}

	/**
	* Metodo che permette di aggiungere imprese sulla plancia giocatore
	*
	* @param 	cardVenture		carta da aggiungere alla carta
	* @return 	nothing
	*/
	
	public void addCard(Venture cardVenture) {
		ventures.add(cardVenture);		
	}
	
	/**
	 * Metodo che permette di togliere dall'inventory un tipo risorsa
	 * 
	 * @param r	risorse da togliere
	 * @return	nothing
	 */

	public void reduceResource(Resources r){
		inventory.reduceRes(r);
		notifyChangement();
	}

	/**
	 * Metodo che permette di togliere una certa quantità di un materiale dell'inventory 
	 * 
	 * @param kind 	tipo di materiale da togliere
	 * @param x		quantità di materiale
	 * @return	nothing
	 */

	public void reduceResource(MaterialsKind kind, int x){
		inventory.decreaseMaterial(kind, x);
		notifyChangement();
	}
	
	/**
	 * Metodo che permette di togliere una certa quantità di un tipo di punti dell'inventory 
	 * 
	 * @param kind 	tipo di punti da togliere
	 * @param x		quantità di punti
	 * @return	nothing
	 */

	public void reduceResource(PointsKind kind, int x){
		inventory.decreasePoints(kind, x);
		notifyChangement();
	}
	
	public void notifyChangement() {
		
		MessagePersonalBoardResourcesStatus resStatus = new MessagePersonalBoardResourcesStatus( getInventory() );
		setChanged();
		notifyObservers(resStatus);
	}
	
	public void addNewObserver(Observer obs) {
		addObserver(obs);
	}
	
	public void deleteAnObserver(Observer obs) {
		deleteObserver(obs);
	}
}