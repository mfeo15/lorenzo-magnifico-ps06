package it.polimi.ingsw.ps06.model.cards;

import java.util.ArrayList;

import java.util.Observable;

import it.polimi.ingsw.ps06.model.Player;
import it.polimi.ingsw.ps06.model.effects.Effect;

/**
 * Classe astratta per definire le carte
 * 
 * @author ps06
 * @version 1.1
 * @since 13-05-2017
 */
public abstract class Card extends Observable {

	protected int code;
	protected String title;
	protected ArrayList<Effect> effect;

	
	/**
	 * Costruttore di default della classe
	 */
	public Card() {
		effect = new ArrayList<Effect>();
	}

	/**
	 * Setter per il codice identificativo della carta
	 * 
	 * @param	code	codice da settare
	 */
	public void setCode(int code){
		this.code = code;
	}
	
	/**
	 * Getter il codice univoco della carta
	 * 
	 * @return	codice associato alla carta
	 */
	public int getCode(){
		return code;
	}

	/**
	 * Setter per il titolo della carta
	 * 
	 * @param	title	titolo da settare
	 */
	public void setTitle(String title){
		this.title = title;
	}
	
	/**
	 * Getter per il titolo della carta
	 * 
	 * @return	il titolo della carta
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Setter per la collezione di effetti permanenti associati alla carta
	 * 
	 * @param	effects		insieme di effetti da settare
	 */
	public void setPermEffect(ArrayList<Effect> effects) {
		this.effect.addAll(effects);
	}

	/**
	 * Metodo per attivare ogni effetto permanente associato alla carta
	 * 
	 * @param	player	giocatore su cui attivare l'effetto
	 */
	public void activateEffect(Player player)
	{
		for(Effect i : effect)
			if (i != null) i.activate(player);
	}
}
