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
	 * @param	source		stringa corrispondente al percorso del file 
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
	 * Metodo per impostare una carta con tutti gli attributi presenti nel suo nodo
	 *
	 * @param	attributes		insieme di nodi rappresentanti i vari attributi della carta
	 * @param	card			carta della quale si devono impostare gli attributi
	 */
	private void setAttributes(NodeList attributes, Leader card) {

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

				if ( current_attribute.getNodeName().equals("effect") ) 
				{
					card.setOncePerRoundEffect( ((Element) current_attribute).getAttribute("type").equals("turno") );
					card.setPermEffect( parseEffectNode(current_attribute) );
				}
			}
		}
	}


	/**
	 * Metodo per la costruzione di un LeaderRequirement partendo da un Nodo i cui figli
	 * rappresentino una definizione dei vari materials, points, cards, ecc
	 * 
	 * @param 	n	nodo radice del LeaderRequirement
	 * 
	 * @return		LeaderRequirement impostato coi valori del file XML
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
	 * Metodo per generare l'insieme di effetti associati ad un Nodo radice
	 *
	 * @param	effetto		nodo radice i cui figli sono una definizione di vari effetti
	 * 
	 * @return 				insieme di effetti costruiti dal file XML
	 */
	public ArrayList<Effect> parseEffectNode(Node effetto) {

		ArrayList<Effect> effects_collection = new ArrayList<Effect>();

		Effect e = null;
		Node kind = ((Element) effetto).getElementsByTagName("kind").item(0);

		switch ( kind.getTextContent() ) {
		case "resources" :
			Node bonus_res_effect = ((Element) effetto).getElementsByTagName("bonus").item(0);
			e = new EffectsResources( parseResourceNode(bonus_res_effect) );
			break;

		case "bmMember" :
			String member = ((Element) effetto).getElementsByTagName("member").item(0).getTextContent();
			int value =  Integer.parseInt( ((Element) effetto).getElementsByTagName("value").item(0).getTextContent() );
			e = new EffectsBonusMalusMember( value, parseMember(member) );
			break;
			
		case "bmMemberFixed" :

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
		return effects_collection;
	}

	/**
	 * Metodo per associazione biunivoca tra una stringa del colore del member
	 * ed il relativo elemento di ColorPalette
	 * 
	 * @param	member	stringa con il nome del colore del member
	 * 
	 * @return			<p>elemento di ColorPalette relativo alla stringa passata</p>
	 * 					<p>null nel caso in cui la stringa non rappresentasse un dato noto</p>
	 * 
	 * @see				it.polimi.ingsw.ps06.model.Types
	 */
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
	 * Getter della collezione di carte costruite dal file XML
	 * 
	 * @return	insieme di carte costruite   
	 */
	public ArrayList<Leader> getCards(){
		return leaders;
	}
}