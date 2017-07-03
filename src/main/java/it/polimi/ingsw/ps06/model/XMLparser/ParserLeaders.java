package it.polimi.ingsw.ps06.model.XMLparser;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import it.polimi.ingsw.ps06.model.LeaderRequirement;
import it.polimi.ingsw.ps06.model.Types.CardType;
import it.polimi.ingsw.ps06.model.Types.ColorPalette;
import it.polimi.ingsw.ps06.model.Types.MaterialsKind;
import it.polimi.ingsw.ps06.model.Types.PointsKind;
import it.polimi.ingsw.ps06.model.cards.developement.DevelopementCard;
import it.polimi.ingsw.ps06.model.cards.leader.Leader;
import it.polimi.ingsw.ps06.model.effects.Effect;
import it.polimi.ingsw.ps06.model.effects.EffectsBonusMalusDoubleMaterialsFromDevCards;
import it.polimi.ingsw.ps06.model.effects.EffectsBonusMalusMember;
import it.polimi.ingsw.ps06.model.effects.EffectsBonusMalusNoMilitaryRequirement;
import it.polimi.ingsw.ps06.model.effects.EffectsResources;

/**
 * Classe per la lettura del file xml delle carte leader
 *
 * @author  ps06
 * @version 1.0
 * @since   2017-06-05
 */
public class ParserLeaders extends XMLParser {

	private ArrayList<Leader> leaders;

	/**
	 * Costruttore della classe
	 *
	 * @param source		Stringa corrispondente al percorso del file 
	 * 
	 */	
	public ParserLeaders(String source) {
		super(source);
		leaders = new ArrayList<Leader>();

		parse( buildDocument() );
	}

	@Override
	protected void parse(Document d) {

		Node deck = d.getFirstChild();
		NodeList carte = ((Element) deck).getElementsByTagName("card");

		for(int j = 0; j < carte.getLength(); j++) 
		{
			Leader lead = new Leader();
			Node carta_attuale = carte.item(j);

			if (carta_attuale.getNodeType() == Node.ELEMENT_NODE) 
			{
				NodeList attributes = carta_attuale.getChildNodes();
				setAttributes(attributes, lead);

				leaders.add(lead);
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
	public void setAttributes(NodeList attributes, Leader card) {

		for(int j=0; j < attributes.getLength(); j++) 
		{
			Node current_attribute = attributes.item(j);

			if (current_attribute.getNodeType() == Node.ELEMENT_NODE) 
			{
				if ( current_attribute.getNodeName().equals("code") ) 
					card.setCode( Integer.parseInt( current_attribute.getFirstChild().getNodeValue() ));

				if ( current_attribute.getNodeName().equals("title") )
					card.setTitle( current_attribute.getFirstChild().getNodeValue() );

				if ( current_attribute.getNodeName().equals("cost") ) 
					card.setRequirement( parseLeaderRequirementNode(current_attribute) );

				if ( current_attribute.getNodeName().equals("permanent_effect") ) 
				{
					card.setOncePerRoundEffect( ((Element) current_attribute).getAttribute("type").equals("turno") );
					card.setPermEffect( parseEffectNode(current_attribute) );
				}
			}
		}
	}


	/**
	 * Metodo per creare un elemento risorsa quando nel file xml si va a leggere un nodo che imposti delle risorse nelle carte
	 *
	 * @param bonus		Nodo contenente i valori dei vari tipi di risorsa da allocare
	 * @return c			risorsa da impostare nella carta in questione 
	 * 
	 */	
	private LeaderRequirement parseLeaderRequirementNode(Node n) {

		LeaderRequirement lRequire = new LeaderRequirement();
		NodeList requirementList = n.getChildNodes();

		for (int i = 0; i < requirementList.getLength(); i++ ) 
		{
			Node currentRequirement = requirementList.item(i);
			if (currentRequirement.getNodeType() == Node.ELEMENT_NODE) 
			{
				int valueRequirement = Integer.parseInt( currentRequirement.getFirstChild().getNodeValue() );

				switch (currentRequirement.getNodeName()) {
				case "coin" :
					lRequire.setResourceValue(MaterialsKind.COIN, valueRequirement);
					break;
				case "stone" : 
					lRequire.setResourceValue(MaterialsKind.STONE, valueRequirement);
					break;
				case "wood" :
					lRequire.setResourceValue(MaterialsKind.WOOD, valueRequirement);
					break;
				case "servant" : 
					lRequire.setResourceValue(MaterialsKind.SERVANT, valueRequirement);
					break;
				case "faith" : 
					lRequire.setResourceValue(PointsKind.FAITH_POINTS, valueRequirement);
					break;
				case "military" :
					lRequire.setResourceValue(PointsKind.MILITARY_POINTS, valueRequirement);
					break;
				case "victory" :
					lRequire.setResourceValue(PointsKind.VICTORY_POINTS, valueRequirement);
					break;
				case "territory" :
					lRequire.setResourceValue(CardType.TERRITORY, valueRequirement);
					break;
				case "building" :
					lRequire.setResourceValue(CardType.BUILDING, valueRequirement);
					break;
				case "charachter" :
					lRequire.setResourceValue(CardType.CHARACTER, valueRequirement);
					break;
				case "venture" :
					lRequire.setResourceValue(CardType.VENTURE, valueRequirement);
					break;
				case "anyCard" :
					lRequire.setAnyCardReq(valueRequirement);
					break;
				}
			}
		}

		return lRequire;
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

				case "bmMember" :
					String member = ((Element) current_effect).getElementsByTagName("member").item(0).getTextContent();
					int value =  Integer.parseInt( ((Element) current_effect).getElementsByTagName("value").item(0).getTextContent() );
					e = new EffectsBonusMalusMember( value, parseMember(member) );
					break;

				case "noMilitary" :
					e = new EffectsBonusMalusNoMilitaryRequirement();
					break;

				case "noExtraMoney" :

					break;

				case "multi" :

					break;

				case "copia" :

					break;

				case "double" :
					e = new EffectsBonusMalusDoubleMaterialsFromDevCards();
					break;


				default :
				}

				effects_collection.add(e);
			}
		}
		return effects_collection;
	}

	private ArrayList<ColorPalette> parseMember(String member) {

		ArrayList<ColorPalette> a = new ArrayList<ColorPalette>();

		if (member.equals("neutral")) 	
			a.add( ColorPalette.UNCOLORED );

		if (member.equals("black")) 	
			a.add( ColorPalette.DICE_BLACK );

		if (member.equals("white")) 	
			a.add( ColorPalette.DICE_WHITE );

		if (member.equals("orange")) 	
			a.add( ColorPalette.DICE_ORANGE );

		if (member.equals("colorful")) {
			a.add( ColorPalette.DICE_BLACK );
			a.add( ColorPalette.DICE_WHITE );
			a.add( ColorPalette.DICE_ORANGE );
		}

		return a;
	}

	/**
	 * Metodo che ritorna il mazzo di carte
	 * 
	 * @return cards   
	 * 
	 */	 

	public ArrayList<Leader> getCards(){
		return leaders;
	}
	
	public static void main(String[] args){

		ParserLeaders c = new ParserLeaders("resources/XML/Leaders.xml");
		ArrayList<Leader> a = new  ArrayList<Leader>();
		a = c.getCards();
		
		for (Leader l : a) {
			System.out.println("");
			System.out.println("CODE: " + l.getCode());
			System.out.println("TITLE: " + l.getTitle());
			System.out.println("OncePerRound: " + l.isOncePerRoundEffect());
		}
	}
}