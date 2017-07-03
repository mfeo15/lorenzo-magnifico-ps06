package it.polimi.ingsw.ps06.model;

import java.util.EnumMap;
import java.util.Iterator;
import java.util.Set;

import it.polimi.ingsw.ps06.model.Types.MaterialsKind;
import it.polimi.ingsw.ps06.model.Types.PointsKind;
import it.polimi.ingsw.ps06.model.Types.CardType;

/**
* Classe per la modelizzazione dei requirement del leader (materiale & punteggi & carte)
*
* @author  ps06
* @version 1.0
* @since   2017-06-15
* 
**/

public class LeaderRequirement {
		
		private EnumMap<MaterialsKind, Integer> materials;
		private EnumMap<PointsKind, Integer> points;
		private EnumMap<CardType, Integer> cards;
		private int anyCard;
		
		/**
		* Costruttore della classe. Inizializzazione delle Mappe dipendente dalla classe Enum di definizione
		*
		*/
		public LeaderRequirement () {
			
			anyCard=0;
			materials = new EnumMap<MaterialsKind, Integer>(MaterialsKind.class);
			points = new EnumMap<PointsKind, Integer>(PointsKind.class);
			cards = new EnumMap<CardType, Integer>(CardType.class);

			
			for (MaterialsKind kind : MaterialsKind.values()) 	materials.put(kind, 0);
			for (PointsKind kind : PointsKind.values()) 		points.put(kind, 0);
			for (CardType kind : CardType.values()) 			cards.put(kind, 0);

		}
		
		public LeaderRequirement (MaterialsKind kind, int value) {
			
			this();
			materials.put(kind, value);
		}
		
		public LeaderRequirement (PointsKind kind, int value) {
			
			this();
			points.put(kind, value);
		}
		
		public LeaderRequirement (CardType kind, int value) {
			
			this();
			cards.put(kind, value);
		}
		
		/**
		* Imposta un valore ad una certa risorsa di tipo materiale
		*
		* @param 	kind		Tipo di materiale a cui applicare l'operazione
		* @param	value		Valore da impostare al materiale
		* 
		* @return 	Nothing
		*/
		public void setResourceValue(MaterialsKind kind, int value) {
			materials.put(kind, value);
		}
		
		/**
		* Imposta un valore ad una certa risorsa di tipo punteggio
		*
		* @param 	kind		Tipo di punteggio a cui applicare l'operazione
		* @param	value		Valore da impostare al punteggio
		* 
		* @return 	Nothing
		*/
		
		public void setResourceValue(PointsKind kind, int value) {
			points.put(kind, value);
		}
		
		/**
		* Imposta un valore ad una certa risorsa di tipo carta
		*
		* @param 	kind		Tipo di carta a cui applicare l'operazione
		* @param	value		Valore da impostare alla carta
		* 
		* @return 	Nothing
		*/
		
		public void setResourceValue(CardType kind, int value) {
			cards.put(kind, value);
		}
		
		/**
		* Imposta il valore del requisito di numero di carte totali
		*
		* @param 	x		quantità di carte da avere
		* 
		* @return 	Nothing
		*/
		
		public void setAnyCardReq(int x){
			this.anyCard=x;
			return;
		}
		
		/**
		* Restituisce il valore del requisito di numero di carte totali
		*
		* 
		* @return 	anyCard		quantità di carte da avere
		*/
		
		public int getAnyCardReq(int x){
			return anyCard;
		}
		/**
		*	Pulisce tutte le risorse
		*
		* @return 	Nothing
		*/
		public void clearResources() {
			Set<MaterialsKind> materialsSet = materials.keySet();
	        for(MaterialsKind currentMaterial : materialsSet) setResourceValue(currentMaterial, 0);
	        
	        Set<PointsKind> pointsSet = points.keySet();
	        for(PointsKind currentPoint : pointsSet) setResourceValue(currentPoint, 0);
	        

	        Set<CardType> cardsSet = cards.keySet();
	        for(CardType currentCard : cardsSet) setResourceValue(currentCard, 0);
	        								
		}
		
		/**
		* Somma un valore ad una certa risorsa di tipo materiale (non distruttivo)
		*
		* @param 	kind			Tipo di materiale a cui applicare l'operazione
		* @param	increaseValue	Valore da impostare al materiale
		* 
		* @return 	Nothing
		*/
		public void increaseResourceValue(MaterialsKind kind, int increaseValue) {
			int currentValue = materials.get(kind);
			materials.put(kind, currentValue + increaseValue);
		}
		
		/**
		* Somma un valore ad una certa risorsa di tipo punteggio (non distruttivo)
		*
		* @param 	kind			Tipo di punteggio a cui applicare l'operazione
		* @param	increaseValue	Valore da impostare al punteggio
		* 
		* @return 	Nothing
		*/
		public void increaseResourceValue(PointsKind kind, int increaseValue) {
			int currentValue = points.get(kind);
			points.put(kind, currentValue + increaseValue);
		}
		
		/**
		* Somma un valore ad una certa risorsa di tipo carta (non distruttivo)
		*
		* @param 	kind			Tipo di carta a cui applicare l'operazione
		* @param	increaseValue	Valore da impostare alla carta
		* 
		* @return 	Nothing
		*/
		public void increaseResourceValue(CardType kind, int increaseValue) {
			int currentValue = cards.get(kind);
			cards.put(kind, currentValue + increaseValue);
		}
		
		/**
		* Ottieni il valore corrente di una certa risorsa di tipo materiale
		*
		* @param 	kind			Tipo di materiale interessato
		* 
		* @return 	valore della risorsa 
		*/
		public int getResourceValue(MaterialsKind kind) {
			return (materials.get(kind));
		}
		
		/**
		* Ottieni il valore corrente di una certa risorsa di tipo punteggio
		*
		* @param 	kind			Tipo di punteggio interessato
		* 
		* @return 	valore della risorsa 
		*/
		public int getResourceValue(PointsKind kind) {
			return (points.get(kind));
		}
		
		/**
		* Ottieni il valore corrente di una certa risorsa di tipo carta
		*
		* @param 	kind			Tipo di carta interessato
		* 
		* @return 	valore della risorsa 
		*/
		public int getResourceValue(CardType kind) {
			return (cards.get(kind));
		}
	
		
		/**
		* Somma la risorsa corrente con una seconda risorsa, settando correttamente
		* ogni singolo campo opportunatamente 
		*
		* @param 	r	Risorsa da sommare
		* 
		* @return 	Nothing. 
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
		* Verifica se la risorsa corrente contiene una quantità di materiali e punteggi
		* uguale o maggiore rispetto ad una risorsa di controllo
		*
		* @param 	r		Risorsa di controllo per il confronto
		* 
		* @return 	true	se ogni risorsa corrente è almeno uguale alla risorsa confronto. 
		*/
		public boolean isBiggerThan(LeaderRequirement r) {
			
			boolean flag = true;
			
			Iterator<MaterialsKind> materialsIterator = materials.keySet().iterator();
	        while(materialsIterator.hasNext() && flag==true) 
	        {
	        	MaterialsKind currentMaterial = materialsIterator.next();	
	        	if ( getResourceValue(currentMaterial) < r.getResourceValue(currentMaterial) ) flag = false;
	        }

	        Iterator<PointsKind> pointsIterator = points.keySet().iterator();
	        while(pointsIterator.hasNext() && flag==true)
	        {
	        	PointsKind currentPoint = pointsIterator.next();	
	        	if ( getResourceValue(currentPoint) < r.getResourceValue(currentPoint) ) flag = false;
	        }
	        
	        Iterator<CardType> cardsIterator = cards.keySet().iterator();
	        while(cardsIterator.hasNext() && flag==true)
	        {
	        	CardType currentCard = cardsIterator.next();	
	        	if ( getResourceValue(currentCard) < r.getResourceValue(currentCard) ) flag = false;
	        }
			
	        return flag;
		}	
		
		
}
