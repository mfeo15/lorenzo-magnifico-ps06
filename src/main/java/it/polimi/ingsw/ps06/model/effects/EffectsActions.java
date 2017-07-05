package it.polimi.ingsw.ps06.model.effects;
import static it.polimi.ingsw.ps06.model.Types.Action;
import static it.polimi.ingsw.ps06.model.Types.ColorPalette;

import it.polimi.ingsw.ps06.model.Actions;
import it.polimi.ingsw.ps06.model.Player;

/**
 * Classe per la gestione di effetti che concedono al giocatre un azione supplementare,
 * che viene ristretta ad un numero limitato di scelte
 *
 * @author  ps06
 * @version 1.0
 * @since   2017-05-11
 */
public class EffectsActions extends Effect 
{
	private Actions action;
	
	/**
	 * Costruttore della classe
	 * 
	 * @param	action	tipo di azione che l'effetto genera
	 * 
	 * @see				it.polimi.ingsw.ps06.model.Types
	 */
	public EffectsActions(Actions action) {
		this.action = action;
	}
	
	@Override
	public void activate(Player p) {	
		action.activate();
	}
}
