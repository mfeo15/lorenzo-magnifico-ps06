package it.polimi.ingsw.ps06.model;

/**
* Classe per la gestione di effetti particolari privi di categorizzazione
*
* @author  ps06
* @version 1.0
* @since   2017-05-11
*/
public class OtherEffects extends Effects {
	
	/**
	* Metodo per controllare l'effetto di un leader che permette il posizionamento di un familiari indipendentemente dal fatto che ce ne sia già 
	* un altro posizionato
	* @author  ps06
	* @version 1.0
	* @since   2017-05-11 
	*/
	public static boolean checkMulti(){
		return false;
	}
}
