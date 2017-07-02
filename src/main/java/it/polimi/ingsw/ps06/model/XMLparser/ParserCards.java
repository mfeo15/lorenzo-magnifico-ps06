package it.polimi.ingsw.ps06.model.XMLparser;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import it.polimi.ingsw.ps06.model.Resources;
import it.polimi.ingsw.ps06.model.Types.ActionCategory;
import it.polimi.ingsw.ps06.model.Types.ColorPalette;
import it.polimi.ingsw.ps06.model.Types.MaterialsKind;
import it.polimi.ingsw.ps06.model.Types.PointsKind;
import it.polimi.ingsw.ps06.model.cards.Building;
import it.polimi.ingsw.ps06.model.cards.Character;
import it.polimi.ingsw.ps06.model.cards.DevelopementCard;
import it.polimi.ingsw.ps06.model.cards.Territory;
import it.polimi.ingsw.ps06.model.cards.Venture;
import it.polimi.ingsw.ps06.model.effects.Effect;
import it.polimi.ingsw.ps06.model.effects.EffectsBonusMalusActions;
import it.polimi.ingsw.ps06.model.effects.EffectsBonusMalusNoTowersEffects;
import it.polimi.ingsw.ps06.model.effects.EffectsResources;
import it.polimi.ingsw.ps06.model.effects.EffectsResourcesByCard;
import it.polimi.ingsw.ps06.model.effects.EffectsResourcesByPoint;
import it.polimi.ingsw.ps06.model.effects.EffectsResourcesSwap;

/**
* Classe per la lettura del file xml delle carte territorio, edificio, impresa e personaggio
*
* @author  ps06
* @version 1.0
* @since   2017-05-28
*/
public class ParserCards extends XMLParser {

	private ArrayList<DevelopementCard> cards;
	
	/**
	* Costruttore della classe
	*
	* @param source		Stringa corrispondente al percorso del file 
	* 
	*/	
	public ParserCards(String source) {
		super(source);
		cards = new ArrayList<DevelopementCard>();
		
		parse( buildDocument() );
	}

	@Override
	protected void parse(Document d) {

		Node deck = d.getFirstChild();
		NodeList carte = ((Element) deck).getElementsByTagName("card");

		for(int j = 0; j < carte.getLength(); j++) 
		{
			DevelopementCard dev = null;
			Node carta_attuale = carte.item(j); 

			if (carta_attuale.getNodeType() == Node.ELEMENT_NODE) 
			{
				if( ((Element) carta_attuale).getAttribute("kind").equals("territory") )
					dev = new Territory();

				if ( ((Element) carta_attuale).getAttribute("kind").equals("building") )
					dev = new Building();

				if( ((Element) carta_attuale).getAttribute("kind").equals("venture") )
					dev = new Venture();		        	

				if( ((Element) carta_attuale).getAttribute("kind").equals("character") )
					dev = new Character();

				
				if (dev != null) 
				{
					NodeList attributes = carta_attuale.getChildNodes();
					setAttributes(attributes, dev);
					
					cards.add(dev);
				}
			}
		}
	}
	
	/**
	* Metodo che da un nodo che corrisponde ad un attributo, lo imposta sulla carta
	*
	* @param a		Nodo contenente l'attributo da inserire nella carta
	* @param d		Carta della quale si devono impostare gli attributi
	* 
	* @return d		Carta impostata
	* 
	*/	
	public void setAttributes(NodeList attributes, DevelopementCard card) {

		for(int j=0; j < attributes.getLength(); j++) 
		{
			Node current_attribute = attributes.item(j);

			if (current_attribute.getNodeType() == Node.ELEMENT_NODE) 
			{
				if ( current_attribute.getNodeName().equals("title") )
					card.setTitle( current_attribute.getFirstChild().getNodeValue() );

				if ( current_attribute.getNodeName().equals("code") ) 
					card.setCode( Integer.parseInt( current_attribute.getFirstChild().getNodeValue() ));

				if ( current_attribute.getNodeName().equals("period") )
					card.setPeriod( Integer.parseInt( current_attribute.getFirstChild().getNodeValue() ));

				if ( current_attribute.getNodeName().equals("cost") ) 
				{
					if (card instanceof Building)
						((Building) card).setRequirement( parseResourceNode(current_attribute) );

					if (card instanceof Character) 
						((Character) card).setRequirement( parseResourceNode(current_attribute) );

					if (card instanceof Venture) 
						((Venture) card).setRequirement( parseResourceNode(current_attribute) );
				}

				if ( current_attribute.getNodeName().equals("permanent_effect") )
					card.setPermEffect( parseEffectNode(current_attribute) );

				if ( current_attribute.getNodeName().equals("instant_effects") )
					card.setEffect( parseEffectNode(current_attribute) );


				if ( current_attribute.getNodeName().equals("harvest_requirement")) {
					if (card instanceof Territory) 
						((Territory) card).setDiceReq( Integer.parseInt( current_attribute.getFirstChild().getNodeValue()) );

					if ( current_attribute.getNodeName().equals("production_requirement"))
						if (card instanceof Building) 
							((Building) card).setDiceReq( Integer.parseInt( current_attribute.getFirstChild().getNodeValue()) );
				}
			}
		}
	}
	
	private ColorPalette parseColorCard(String color) {
		
		if (color.equals("verde")) 	return ColorPalette.CARD_GREEN;
		if (color.equals("giallo")) return ColorPalette.CARD_YELLOW;
		if (color.equals("blu")) 	return ColorPalette.CARD_BLUE;
		if (color.equals("viola")) 	return ColorPalette.CARD_PURPLE;
		
		return null;
	}
	
	private ActionCategory parseAction(String action) {
		
		if (action.equals("verde")) 	return ActionCategory.TOWER_GREEN;
		if (action.equals("giallo")) 	return ActionCategory.TOWER_YELLOW;
		if (action.equals("blu")) 		return ActionCategory.TOWER_BLUE;
		if (action.equals("viola")) 	return ActionCategory.TOWER_PURPLE;
		
		if (action.equals("raccolto")) 		return ActionCategory.HARVEST;
		if (action.equals("produzione")) 	return ActionCategory.PRODUCTION;
		
		return null;
	}
	
	/**
	* Metodo che genera l'effetto delle carte
	*
	* @param effetto	Nodo contenente l'effetto e i suoi valori da impostare
	* @param card		Carta della quale bisogna impostare l'effetto
	* 
	* @return nothing
	* 
	*/	
	public ArrayList<Effect> parseEffectNode(Node effetto) {

		ArrayList<Effect> effects_collection = new ArrayList<Effect>();
		NodeList effects = ((Element) effetto).getElementsByTagName("effect");

		for(int k = 0; k < effects.getLength(); k++) {
			Node current_effect = effects.item(k);

			if (current_effect.getNodeType() == Node.ELEMENT_NODE) 
			{	
				Effect e = null;
				Node kind = ((Element) current_effect).getElementsByTagName("kind").item(0);
				
				switch ( kind.getTextContent() ) {
				case "resources" :
					Node bonus_res_effect = ((Element) effetto).getElementsByTagName("bonus").item(0);
					e = new EffectsResources( parseResourceNode(bonus_res_effect) );
					break;
					
				case "byCard" :	
					String color = ((Element) current_effect).getElementsByTagName("color").item(0).getTextContent();
					Node bonus_byCard_effect = ((Element) current_effect).getElementsByTagName("bonus").item(0);
					e = new EffectsResourcesByCard( parseResourceNode(bonus_byCard_effect) , parseColorCard(color) );
				break;
					
				case "byPoints" :	
					int weight =  Integer.parseInt( ((Element) current_effect).getElementsByTagName("military").item(0).getTextContent() );
					Node bonus_byPoints_effect = ((Element) current_effect).getElementsByTagName("bonus").item(0);
					e = new EffectsResourcesByPoint(parseResourceNode(bonus_byPoints_effect), PointsKind.MILITARY_POINTS, weight);
					break;
					
				case "swapResources" :	
					Node bonus_swap_effect = ((Element) current_effect).getElementsByTagName("bonus").item(0);
					Node input_swap_effect = ((Element) current_effect).getElementsByTagName("input").item(0);
					e = new EffectsResourcesSwap( parseResourceNode(bonus_swap_effect), parseResourceNode(input_swap_effect) );
					break;
					
				case "bmAction" :	
					
					String action = ((Element) current_effect).getElementsByTagName("action").item(0).getTextContent();
					int amount =  Integer.parseInt( ((Element) current_effect).getElementsByTagName("amount").item(0).getTextContent() );
					e = new EffectsBonusMalusActions( amount, parseAction(action) );
					break;
					
				case "noEffettoTorri" :	
					e = new EffectsBonusMalusNoTowersEffects();
					break;
					
				default :
				}

				effects_collection.add(e);
			}
		}
		return effects_collection;
	}
	 

	/**
	* Metodo che ritorna il mazzo di carte
	* 
	* @return cards   
	* 
	*/	 
	
	public ArrayList<DevelopementCard> getCards(){
		return cards;
	}

	public static void main(String[] args){

		ParserCards c = new ParserCards("resources/XML/DevelopementCards.xml");
		ArrayList<DevelopementCard> a = new  ArrayList<DevelopementCard>();
		a = c.getCards();
	}

	
}