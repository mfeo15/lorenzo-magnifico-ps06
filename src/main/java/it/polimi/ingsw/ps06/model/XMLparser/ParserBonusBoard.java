package it.polimi.ingsw.ps06.model.XMLparser;
import it.polimi.ingsw.ps06.model.Resources;
import it.polimi.ingsw.ps06.model.Types;
import it.polimi.ingsw.ps06.model.Types.Action;
import it.polimi.ingsw.ps06.model.Types.MaterialsKind;
import it.polimi.ingsw.ps06.model.Types.PointsKind;
import it.polimi.ingsw.ps06.model.cards.Building;
import it.polimi.ingsw.ps06.model.cards.Character;
import it.polimi.ingsw.ps06.model.cards.Territory;
import it.polimi.ingsw.ps06.model.cards.Venture;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.EnumMap;

/**
 * Classe per la lettura del file xml delle carte territorio, edificio, impresa e personaggio
 *
 * @author  ps06
 * @version 1.0
 * @since   2017-05-28
 */

public class ParserBonusBoard {

	private ArrayList<Integer> faith_points;
	public EnumMap<Action, Resources> actionSpaces;
	private String XML_sourceFile;

	/**
	 * Costruttore della classe
	 *
	 * @param source		Stringa corrispondente al percorso del file 
	 * 
	 */	
	public ParserBonusBoard(String source) {
		this.XML_sourceFile = source;
		faith_points = new ArrayList<Integer>();
		actionSpaces = new EnumMap<Action, Resources>(Action.class);

		parse( buildDocument() );
	}

	public Resources getBonusRescourcesForActionSpace(Action a) {
		if (! actionSpaces.containsKey(a) )
			return null;

		return actionSpaces.get(a);
	}

	private Resources parseResourceNode(Node n) {

		Resources r = new Resources();
		NodeList resourcesList = n.getChildNodes();

		for (int i = 0; i < resourcesList.getLength(); i++ ) 
		{
			Node currentResource = resourcesList.item(i);
			if (currentResource.getNodeType() == Node.ELEMENT_NODE) 
			{
				int valueResource = Integer.parseInt( currentResource.getFirstChild().getNodeValue() );

				switch (currentResource.getNodeName()) {
				case "coin" : 
					r.setResourceValue(MaterialsKind.COIN, valueResource);
					break;
				case "stone" : 
					r.setResourceValue(MaterialsKind.STONE, valueResource);
					break;
				case "wood" :
					r.setResourceValue(MaterialsKind.WOOD, valueResource);
					break;
				case "servant" : 
					r.setResourceValue(MaterialsKind.SERVANT, valueResource);
					break;
				case "faith" : 
					r.setResourceValue(PointsKind.FAITH_POINTS, valueResource);
					break;
				case "military" :
					r.setResourceValue(PointsKind.MILITARY_POINTS, valueResource);
					break;
				case "victory" :
					r.setResourceValue(PointsKind.VICTORY_POINTS, valueResource);
					break;
				}
			}
		}

		return r;
	}

	private void parserActionSpacesBonus(NodeList bonus_action_zone, Action actionSpace3, Action actionSpace4) {

		for(int j=0; j < bonus_action_zone.getLength(); j++) 
		{
			Node current = bonus_action_zone.item(j);

			if (current.getNodeType() == Node.ELEMENT_NODE) 
			{
				if (current.getNodeName().equals("posto3"))
					actionSpaces.put(actionSpace3, parseResourceNode(current));

				if (current.getNodeName().equals("posto4"))
					actionSpaces.put(actionSpace4, parseResourceNode(current));	
			}
		}
	}

	private void parserActionSpacesBonus(NodeList bonus_action_zone) {

		for(int j=0; j < bonus_action_zone.getLength(); j++) 
		{
			Node current = bonus_action_zone.item(j);

			if (current.getNodeType() == Node.ELEMENT_NODE) 
			{
				if (current.getNodeName().equals("posto1"))
					actionSpaces.put(Action.MARKET_1, parseResourceNode(current));

				if (current.getNodeName().equals("posto2"))
					actionSpaces.put(Action.MARKET_2, parseResourceNode(current));	

				if (current.getNodeName().equals("posto3"))
					actionSpaces.put(Action.MARKET_3, parseResourceNode(current));

				/*
	    			if (current.getNodeName().equals("posto4"))
	    				actionSpaces.put(Action.MARKET_4, parseResourceNode(current));	<== TODO
				 */
			}
		}
	}

	private void parserFaithBonus(NodeList bonus_fede) {

		for(int j=0; j < bonus_fede.getLength(); j++) 
		{
			Node current = bonus_fede.item(j);

			if (current.getNodeType() == Node.ELEMENT_NODE) 
			{
				if (current.getNodeName().startsWith("posto"))
					faith_points.add( Integer.parseInt( current.getFirstChild().getNodeValue() ));
			}
		}
	}

	private void parse(Document d) {

		Node bonuses = d.getFirstChild();

		NodeList bonus_torre_verde = ((Element) bonuses).getElementsByTagName("bonustorreverde").item(0).getChildNodes();
		parserActionSpacesBonus(bonus_torre_verde, Action.TOWER_GREEN_3, Action.TOWER_GREEN_4);


		NodeList bonus_torre_gialla = ((Element) bonuses).getElementsByTagName("bonustorregialla").item(0).getChildNodes();
		parserActionSpacesBonus(bonus_torre_gialla, Action.TOWER_YELLOW_3, Action.TOWER_YELLOW_4);

		NodeList bonus_torre_blu = ((Element) bonuses).getElementsByTagName("bonustorreblu").item(0).getChildNodes();
		parserActionSpacesBonus(bonus_torre_blu, Action.TOWER_BLUE_3, Action.TOWER_BLUE_4);

		NodeList bonus_torre_viola = ((Element) bonuses).getElementsByTagName("bonustorreviola").item(0).getChildNodes();
		parserActionSpacesBonus(bonus_torre_viola, Action.TOWER_PURPLE_3, Action.TOWER_PURPLE_3);

		NodeList bonus_mercato = ((Element) bonuses).getElementsByTagName("bonusmercato").item(0).getChildNodes();
		parserActionSpacesBonus(bonus_mercato);

		NodeList bonus_fede = ((Element) bonuses).getElementsByTagName("bonusfede").item(0).getChildNodes();
		parserFaithBonus(bonus_fede);
	}

	/**
	 *Metodo per costruire il parser
	 *
	 * @param none
	 * @return nothing 
	 * 
	 */	
	public Document buildDocument() 
	{
		try {
			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = documentFactory.newDocumentBuilder(); 

			XML_sourceFile = XML_sourceFile.replaceFirst("resources", "");
			return builder.parse( getClass().getResourceAsStream(XML_sourceFile) ); 
		}
		catch(Exception e){
			e.printStackTrace();
		}

		return null;
	}
}
