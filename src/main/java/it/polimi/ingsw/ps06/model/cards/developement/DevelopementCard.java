package it.polimi.ingsw.ps06.model.cards.developement;

import java.util.ArrayList;

import it.polimi.ingsw.ps06.model.Player;
import it.polimi.ingsw.ps06.model.cards.Card;
import it.polimi.ingsw.ps06.model.effects.Effect;


/**
* classe per la gestione delle carte sviluppo
* 
* @author ps06
* @version 1.1
* @since 13-05-2017
*/

public class DevelopementCard extends Card {

	protected int color;
	protected int period;
	public ArrayList<Effect> instant_effect;  // <== TO CHANGE ASAP!!!

	
	public DevelopementCard() {
		this.instant_effect = new ArrayList<Effect>();
	}
	
	/**Metodo per settare il colore della carta	 
	 * 
	 *@param	color	colore della carta
	 *@return	nothing
	 */
	public void setColor(int color) {
		this.color = color;
	}


	/**Metodo per settare il periodo della carta
	 * 
	 * @param	code	periodo della carta
	 * @return	nothing
	 */

	public void setPeriod(int period) {
		this.period = period;
	}

	/**Metodo per settare il periodo della carta
	 * 
	 * @param	effect	effetto da aggiungere
	 * @return	nothing
	 */
	public void setEffect(Effect effect) {
		this.instant_effect.add(effect);
	}

	/**Metodo per settare il periodo della carta
	 * 
	 * @param	effect	effetto da aggiungere
	 * @return	nothing
	 */
	public void setEffect(ArrayList<Effect> effects) {
		this.instant_effect.addAll(effects);
	}

	/**Metodo per attivare gli effetti istantanei
	 * 
	 * @param	player	giocatore su cui attivare l'effetto
	 * @return	nothing
	 */
	public void activateIstantEffect(Player player) {
		for( Effect i : instant_effect )
			i.activate(player);
	}

	/**Metodo che restituisce il colore della carta
	 * 
	 * @param	none
	 * @return	color	colore della carta
	 */

	public int getColor() {
		return color;
	}

	/**Metodo che restituisce il periodo della carta
	 * 
	 * @param	none
	 * @return	period	periodo della carta
	 */

	public int getPeriod() {
		return period;
	}
}
