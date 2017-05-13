package it.polimi.ingsw.ps06.model;
import static it.polimi.ingsw.ps06.model.Types.Action;
import static it.polimi.ingsw.ps06.model.Types.ColorPalette;

/**
* Classe per la gestione di effetti che concedono al giocatre un azione supplementare, che viene ristretta ad un numero limitato di scelte
*
* @author  ps06
* @version 1.0
* @since   2017-05-11
*/

public class ActionsEffects extends Effect {
	Player player;
	
	/**
	* Metodo per l'utilizzo dell'azione supplementare concessa
	*
	* @param 	player			Giocatore a cui concedere l'azione
	* @param	possibilites	Array di azioni possibili
	* @return 					Nothing
	*/
	public void doRestrictedAction(Player player, int value, ColorPalette color){
		this.player=player;
		player.doAction(value, color);
	}

}
