package it.polimi.ingsw.ps06.model;

import java.util.ArrayList;

/**
* Classe per la gestione di effetti che comportano una modifica alle risorse di un player
*
* @author  ps06
* @version 1.0
* @since   2017-05-11
*/
public class ResourcesEffects extends Effects {
	private ArrayList<Resources> resources;
	private Resources resource;
	
	/**
	* Constructor.
	* 
	* @param resource (required) risorsa da aggiungere al giocatore
	*/
	public ResourcesEffects(ArrayList<Resources> resources){
		super();
		
		this.resources=resources;
		
	}
	
	/**
	* Metodo per l'assegnazione delle risorse ad un determinato player
	*
	* @param 	player		Giocatore a cui dare le risorse
	* @return 				Nothing
	*/
	public static void giveResources(Player player){
		
	}
	
	/**
	* Metodo per l'assegnazione delle risorse ad un determinato player
	*
	* @param 	player		Giocatore a cui dare le risorse
	* @param 	value		Valore in punti vittoria per gli effetti finali delle carte Impresa
	* @return 				Nothing
	*/
	public static void handleFinalEffects(Player player, int value){
		
	}
	
	/**
	* Metodo per l'assegnazione delle risorse ad un determinato player
	*
	* @param 	Player		Giocatore a cui rimuovere le risorse
	* @return 				Nothing
	*/
	public static void removeResources(Player player){
		
	}
	
	/**
	* Metodo per la gestione di effetti con l'aumento di risorse non conseguenti ad azioni ma ad abilità Leader (Sisto IV)
	* 
	* @param 	player			Giocatore interessato
	* @param 	risorse			Risorse da gestire
	* @return 					Nothing
	*/
	public static void handleLeaderResources(Player player, ArrayList<Resources> resources){
		giveResources(player);
		
	}
	
	
}
