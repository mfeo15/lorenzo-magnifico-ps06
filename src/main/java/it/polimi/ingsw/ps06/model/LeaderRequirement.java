package it.polimi.ingsw.ps06.model;

import java.util.EnumMap;
import java.util.Iterator;
import java.util.Set;

import it.polimi.ingsw.ps06.model.Types.MaterialsKind;
import it.polimi.ingsw.ps06.model.Types.PointsKind;
import it.polimi.ingsw.ps06.model.Types.CardType;

/**
 * Classe per la modelizzazione dei requirement del leader (materiale e punteggi e carte)
 *
 * @author  ps06
 * @version 1.0
 * @since   2017-06-15
 * 
 */
public class LeaderRequirement {

	private EnumMap<MaterialsKind, Integer> materials;
	private EnumMap<PointsKind, Integer> points;
	private EnumMap<CardType, Integer> cards;
	private int anyCard;

	/**
	 * Costruttore di default della classe. Inizializzazione delle Mappe dipendente dalla classe Enum di definizione
	 */
	public LeaderRequirement () 
	{
		anyCard = 0;
		materials = new EnumMap<MaterialsKind, Integer>(MaterialsKind.class);
		points = new EnumMap<PointsKind, Integer>(PointsKind.class);
		cards = new EnumMap<CardType, Integer>(CardType.class);

		for (MaterialsKind kind : MaterialsKind.values()) 	materials.put(kind, 0);
		for (PointsKind kind : PointsKind.values()) 		points.put(kind, 0);
		for (CardType kind : CardType.values()) 			cards.put(kind, 0);
	}

	/**
	 * Costruttore della classe.
	 * 
	 * @param	kind	tipo di materiale da inizializzare
	 * @param	value	valore dell'inizializzazione
	 * 
	 * @see				it.polimi.ingsw.ps06.model.Types
	 */
	public LeaderRequirement (MaterialsKind kind, int value) 
	{
		this();
		materials.put(kind, value);
	}

	/**
	 * Costruttore della classe.
	 * 
	 * @param	kind	tipo di punteggio da inizializzare
	 * @param	value	valore dell'inizializzazione
	 * 
	 * @see				it.polimi.ingsw.ps06.model.Types
	 */
	public LeaderRequirement (PointsKind kind, int value) 
	{
		this();
		points.put(kind, value);
	}

	/**
	 * Costruttore della classe.
	 * 
	 * @param	kind	tipo di carta da inizializzare
	 * @param	value	valore dell'inizializzazione
	 * 
	 * @see				it.polimi.ingsw.ps06.model.Types
	 */
	public LeaderRequirement (CardType kind, int value) 
	{
		this();
		cards.put(kind, value);
	}

	/**
	 * Imposta un valore ad una certa risorsa di tipo materiale
	 *
	 * @param 	kind		tipo di materiale a cui applicare l'operazione
	 * @param	value		valore da impostare al materiale
	 */
	public void setResourceValue(MaterialsKind kind, int value) {
		materials.put(kind, value);
	}

	/**
	 * Imposta un valore ad una certa risorsa di tipo punteggio
	 *
	 * @param 	kind		tipo di punteggio a cui applicare l'operazione
	 * @param	value		valore da impostare al punteggio
	 */
	public void setResourceValue(PointsKind kind, int value) {
		points.put(kind, value);
	}

	/**
	 * Imposta un valore ad una certa risorsa di tipo carta
	 *
	 * @param 	kind		tipo di carta a cui applicare l'operazione
	 * @param	value		valore da impostare alla carta
	 */
	public void setResourceValue(CardType kind, int value) {
		cards.put(kind, value);
	}

	/**
	 * Setter per il valore del requisito di numero di carte totali. Il valore viene automaticamente messo
	 * nullo (a zero) se il parametro è negativo. Un valore non postivo non ha significato in questo contesto
	 *
	 * @param	amount	quantità di carte da avere
	 */
	public void setAnyCardReq(int amount) {
		if (amount < 0)
			this.anyCard = 0;
		else
			this.anyCard = amount;
	}

	/**
	 * Getter per il valore del requisito di numero di carte totali
	 * 
	 * @return	quantità di carte da avere
	 */
	public int getAnyCardReq() {
		return anyCard;
	}
	
	/**
	 *	Pulisce l'intero requirement
	 */
	public void clearResources() {
		Set<MaterialsKind> materialsSet = materials.keySet();
		for(MaterialsKind currentMaterial : materialsSet) setResourceValue(currentMaterial, 0);

		Set<PointsKind> pointsSet = points.keySet();
		for(PointsKind currentPoint : pointsSet) setResourceValue(currentPoint, 0);

		Set<CardType> cardsSet = cards.keySet();
		for(CardType currentCard : cardsSet) setResourceValue(currentCard, 0);
		
		anyCard = 0;
	}

	/**
	 * Somma un valore ad una certa risorsa di tipo materiale (non distruttivo)
	 *
	 * @param 	kind			tipo di materiale a cui applicare l'operazione
	 * @param	increaseValue	valore da impostare al materiale
	 */
	public void increaseResourceValue(MaterialsKind kind, int increaseValue) {
		int currentValue = materials.get(kind);
		materials.put(kind, currentValue + increaseValue);
	}

	/**
	 * Somma un valore ad una certa risorsa di tipo punteggio (non distruttivo)
	 *
	 * @param 	kind			tipo di punteggio a cui applicare l'operazione
	 * @param	increaseValue	valore da impostare al punteggio
	 */
	public void increaseResourceValue(PointsKind kind, int increaseValue) {
		int currentValue = points.get(kind);
		points.put(kind, currentValue + increaseValue);
	}

	/**
	 * Somma un valore ad una certa risorsa di tipo carta (non distruttivo)
	 *
	 * @param 	kind			ripo di carta a cui applicare l'operazione
	 * @param	increaseValue	valore da impostare alla carta
	 */
	public void increaseResourceValue(CardType kind, int increaseValue) {
		int currentValue = cards.get(kind);
		cards.put(kind, currentValue + increaseValue);
	}

	/**
	 * Ottieni il valore corrente di una certa risorsa di tipo materiale
	 *
	 * @param 	kind	tipo di materiale interessato
	 * 
	 * @return 			valore del materiale 
	 */
	public int getResourceValue(MaterialsKind kind) {
		return (materials.get(kind));
	}

	/**
	 * Ottieni il valore corrente di una certa risorsa di tipo punteggio
	 *
	 * @param 	kind	tipo di punteggio interessato
	 * 
	 * @return 			valore del punteggio 
	 */
	public int getResourceValue(PointsKind kind) {
		return (points.get(kind));
	}

	/**
	 * Ottieni il valore corrente di una certa risorsa di tipo carta
	 *
	 * @param 	kind	tipo di carta interessato
	 * 
	 * @return 			valore della carta 
	 */
	public int getResourceValue(CardType kind) {
		return (cards.get(kind));
	}


	/**
	 * Somma il requisito corrente con un secondo requisito, settando ogni singolo campo opportunatamente 
	 *
	 * @param 	r	requisito da sommare
	 */
	public void add(LeaderRequirement r) {

		Set<MaterialsKind> materialsSet = materials.keySet();
		for(MaterialsKind currentMaterial : materialsSet) increaseResourceValue(currentMaterial, r.getResourceValue(currentMaterial));

		Set<PointsKind> pointsSet = points.keySet();
		for(PointsKind currentPoint : pointsSet) increaseResourceValue(currentPoint, r.getResourceValue(currentPoint));

		Set<CardType> cardsSet = cards.keySet();
		for(CardType currentType : cardsSet) increaseResourceValue(currentType, r.getResourceValue(currentType));
	}

	/**
	 * Verifica se il requisito corrente contiene una quantità di materiali, punteggi, carte
	 * uguale o maggiore rispetto ad un requisito di controllo
	 *
	 * @param 	r		requisito di controllo per il confronto
	 * 
	 * @return 	true	se ogni parametro del requisito corrente è almeno uguale alla requsiito confronto. 
	 */
	public boolean isBiggerThan(LeaderRequirement r) {
		
		boolean flagMaterials = true;
		boolean flagPoints = true;
		boolean flagCards = true;
		boolean flagAnyCard = true;

		Iterator<MaterialsKind> materialsIterator = materials.keySet().iterator();
		while(materialsIterator.hasNext() && flagMaterials==true) 
		{
			MaterialsKind currentMaterial = materialsIterator.next();	
			if ( getResourceValue(currentMaterial) < r.getResourceValue(currentMaterial) ) flagMaterials = false;
		}

		Iterator<PointsKind> pointsIterator = points.keySet().iterator();
		while(pointsIterator.hasNext() && flagPoints==true)
		{
			PointsKind currentPoint = pointsIterator.next();	
			if ( getResourceValue(currentPoint) < r.getResourceValue(currentPoint) ) flagPoints = false;
		}

		Iterator<CardType> cardsIterator = cards.keySet().iterator();
		while(cardsIterator.hasNext() && flagCards==true)
		{
			CardType currentCard = cardsIterator.next();	
			if ( getResourceValue(currentCard) < r.getResourceValue(currentCard) ) flagCards = false;
		}
		
		if (this.getAnyCardReq() < r.getAnyCardReq())
			flagAnyCard = false;

		return (flagMaterials && flagPoints && flagCards && flagAnyCard);
	}	

	@Override
	public String toString() {
		String s = "Coin:" + materials.get(MaterialsKind.COIN) 
		+ " Wood:" + materials.get(MaterialsKind.WOOD)
		+ " Stone:" + materials.get(MaterialsKind.STONE)
		+ " Servant:" + materials.get(MaterialsKind.SERVANT)
		+ " Faith:" + points.get(PointsKind.FAITH_POINTS)
		+ " Military:" + points.get(PointsKind.MILITARY_POINTS)
		+ " Victory:" + points.get(PointsKind.VICTORY_POINTS) 
		+ "\n"
		+ "Territory:" + cards.get(CardType.TERRITORY)
		+ " Building:" + cards.get(CardType.BUILDING)
		+ " Character:" + cards.get(CardType.CHARACTER)
		+ " Venture:" + cards.get(CardType.VENTURE)
		+ "\n"
		+ "AnyCard:" + this.anyCard;

		return s;
	}
}
