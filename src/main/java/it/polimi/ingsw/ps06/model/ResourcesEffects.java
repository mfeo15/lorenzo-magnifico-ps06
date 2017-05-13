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
public class ResourcesEffects extends Effect {
	private EnumMap<MaterialsKind, Integer> materials;
	private EnumMap<PointsKind, Integer> points;
	Resources bonus;
	Warehouse status;
	PersonalBoard board;
	
	/**
	* Constructor.
	* 
	*/
	public ResourcesEffects(){
		materials = new EnumMap<MaterialsKind, Integer>(MaterialsKind.class);
		points = new EnumMap<PointsKind, Integer>(PointsKind.class);
		
	}
	
	/**
	* Metodo per l'assegnazione delle risorse ad un determinato player
	*
	* @param 	player		Giocatore a cui dare le risorse
	* @return 				Nothing
	*/
	public void giveResources(PersonalBoard board, Resources r){
		bonus.add(r);
		this.board=board;
		status=board.getStatus();
		//ho il magazzino e le risorse da aggiungere, come lo faccio? 
	}
	
	/**
	* Metodo per l'assegnazione delle risorse ad un determinato player
	*
	* @param 	player		Giocatore a cui dare le risorse
	* @param 	value		Valore in punti vittoria per gli effetti finali delle carte Impresa
	* @return 				Nothing
	*/
	public void handleFinalEffects(Player player, int value){
		points.put(PointsKind.VICTORY_POINTS, value);
	}
	
	/**
	* Metodo per l'assegnazione delle risorse ad un determinato player
	*
	* @param 	Player		Giocatore a cui rimuovere le risorse
	* @return 				Nothing
	*/
	public void removeResources(Player player){
		
	}
	
	/**
	* Metodo per la gestione di effetti con l'aumento di risorse non conseguenti ad azioni ma ad abilità Leader (Sisto IV)
	* 
	* @param 	player			Giocatore interessato
	* @param 	risorse			Risorse da gestire
	* @return 					Nothing
	*/
	public void handleLeaderResources(PersonalBoard board, Resources r){
		giveResources(board,r);
		
	}
	
	
}
