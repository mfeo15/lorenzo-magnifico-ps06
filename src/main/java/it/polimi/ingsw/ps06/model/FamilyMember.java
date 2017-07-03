package it.polimi.ingsw.ps06.model;

import static it.polimi.ingsw.ps06.model.Types.ColorPalette;

/**
* Classe rappresentativa dei famigliari associati ad un giocatore
*
* @author  ps06
* @version 1.0
* @since   2017-05-10 
*/
public class FamilyMember {
	
	private ColorPalette rappresentativeDiceColor;
	private int value;
	
	private Player ownerPlayer;
	
	/**
	* Costruttore di default della classe. Il member viene inizializzato come neutro
	* 
	*/
	public FamilyMember(Player p)
	{
		this.ownerPlayer = p;
		this.rappresentativeDiceColor = ColorPalette.UNCOLORED;
		this.value = 0;
	}
	
	/**
	 * Costruttore della classe
	 * @param 	rappresentativeDice 	colore del dado associato al member in creazione. I valori accettabili provengono
	 * 									dal ColorPalette della classe Types
	 * 
	 * @see		it.polimi.ingsw.model.Types
	 */
	public FamilyMember(Player p, ColorPalette rappresentativeDice)
	{
		this(p);
		this.rappresentativeDiceColor = rappresentativeDice;
	}
	
	/**
	 * Setter per il valore del member
	 * @param 	value		nuovo valore da settare
	 */
	public void setValue(int value) {
		this.value = value;
	}
	
	/**
	 * Getter per il valore del member
	 * @return 	valore attuale del member (considerando possibili BonusMalus)
	 */
	public int getValue() 
	{	
		if ( ownerPlayer.getBonusMalusCollection().getBonusMalus(rappresentativeDiceColor) != null )
			return (value + ownerPlayer.getBonusMalusCollection().getBonusMalus(rappresentativeDiceColor).getValue()); 
		
		return (value);
	}
	
	/**
	 * Setter per il colore di dado associato al member
	 * @param	rappresentativeDiceColor	colore di dado da associare al member
	 * 
	 * @see 	it.polimi.ingsw.model.Types
	 */
	public void setColor(ColorPalette rappresentativeDiceColor) {
		this.rappresentativeDiceColor = rappresentativeDiceColor;
	}
	
	/**
	 * Getter per il colore di dado associato al member
	 * @return		colore di dado associato al member
	 */
	public ColorPalette getColor() {
		return rappresentativeDiceColor;
	}
	
	/**
	 * Metodo che associa una stringa al colore attuale del dado associato
	 * @return		stringa rappresentante il colore del dado associato al familiare
	 */
	public String getColorString() {
		
		switch ( rappresentativeDiceColor ) {
		case DICE_BLACK: 	return "Nero";
		case DICE_ORANGE: 	return "Arancione";
		case DICE_WHITE: 	return "Bianco";
		case UNCOLORED: 	return "Neutro";
		default:
			return null;
		}
	}
	
	/**
	 * Getter per il giocatore possessore del Member di questa classe
	 * @return		il giocatore possessore
	 */
	public Player getPlayer() {
		return ownerPlayer;
	}
	
	/**
	 * Getter per il giocatore possessore del Member di questa classe
	 * @return		il giocatore possessore, solamente se il member è associato ad un dado
	 * 				null nel caso di membro neutro
	 */
	public Player getFakePlayer() {
		
		if(rappresentativeDiceColor==ColorPalette.UNCOLORED) return null;
		else return ownerPlayer;
	}
}
