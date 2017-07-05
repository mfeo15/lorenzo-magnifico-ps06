package it.polimi.ingsw.ps06.model.cards.developement;

import java.util.ArrayList;

import it.polimi.ingsw.ps06.model.Player;
import it.polimi.ingsw.ps06.model.cards.Card;
import it.polimi.ingsw.ps06.model.effects.Effect;


/**
* Classe per la gestione delle carte sviluppo
* 
* @author ps06
* @version 1.1
* @since 13-05-2017
*/
public class DevelopementCard extends Card {

	protected int color;
	protected int period;
	private ArrayList<Effect> instant_effect;
	

	/**
	 * Costruttore di default della classe
	 */
	public DevelopementCard() {
		this.instant_effect = new ArrayList<Effect>();
	}
	
	/**
	 * Getter per il periodo della carta
	 * 
	 * @return	il periodo associato alla carta
	 */
	public int getPeriod() {
		return period;
	}

	/**
	 * Setter per il periodo della carta
	 * 
	 * @param	period	periodo della carta da settare
	 */
	public void setPeriod(int period) {
		this.period = period;
	}
	
	/**
	 * Getter per la collezione di effetti istantanei
	 * 
	 * @return	tutti gli effetti istantanei
	 */
	public ArrayList<Effect> getInstantEffects() {
		return instant_effect;
	}

	/**Metodo per settare il periodo della carta
	 * 
	 * @param	effects		insieme di effetti da settare alla carta
	 */
	public void setEffect(ArrayList<Effect> effects) {
		this.instant_effect = effects;
	}
	
	/**
	 * Metodo per aggiungere alla collezione di effetti istantanei un gruppo
	 * di nuovi effetti
	 * 
	 * @param	instant_effect	insieme di effetti da aggiungere alla carta
	 */
	public void addNewInstantEffects(ArrayList<Effect> instant_effect) {
		this.instant_effect.addAll(instant_effect);
	}
	
	/**
	 * Metodo per aggiungere alla collezione di effetti istantanei
	 * un singolo nuovo effetto
	 * 
	 * @param	effect	effetto da aggiungere
	 */
	public void addNewEffect(Effect effect) {
		this.instant_effect.add(effect);
	}

	/**
	 * Metodo per attivare gli effetti istantanei associati alla carta
	 * 
	 * @param	player	giocatore su cui attivare l'effetto
	 */
	public void activateIstantEffect(Player player) {
		for( Effect i : instant_effect )
			i.activate(player);
	}
}
