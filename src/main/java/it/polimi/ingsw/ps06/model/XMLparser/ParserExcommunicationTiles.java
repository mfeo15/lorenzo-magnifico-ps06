package it.polimi.ingsw.ps06.model.XMLparser;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import it.polimi.ingsw.ps06.model.Types.ActionCategory;
import it.polimi.ingsw.ps06.model.Types.ColorPalette;
import it.polimi.ingsw.ps06.model.cards.ExcommunicationTile;
import it.polimi.ingsw.ps06.model.cards.leader.Leader;
import it.polimi.ingsw.ps06.model.effects.Effect;
import it.polimi.ingsw.ps06.model.effects.EffectsBonusMalusActions;
import it.polimi.ingsw.ps06.model.effects.EffectsBonusMalusMember;
import it.polimi.ingsw.ps06.model.effects.EffectsBonusMalusNoMarket;

/**
 * Classe per la lettura del file xml delle carte tessere scomunica
 *
 * @author  ps06
 * @version 1.0
 * @since   2017-07-03
 */
public class ParserExcommunicationTiles extends XMLParser {

	private ArrayList<ExcommunicationTile> excommunicationTiles;

	/**
	* Costruttore della classe
	*
	* @param	source		stringa corrispondente al percorso del file 
	*/	
	public ParserExcommunicationTiles(String source) {
		super(source);
		excommunicationTiles = new ArrayList<ExcommunicationTile>();

		parse( buildDocument() );
	}

	@Override
	protected void parse(Document d) {

		Node deck = d.getFirstChild();
		NodeList tessere = ((Element) deck).getElementsByTagName("tile");

		for(int j = 0; j < tessere.getLength(); j++) 
		{
			ExcommunicationTile excomTile = new ExcommunicationTile();
			Node tessera_attuale = tessere.item(j);

			if (tessera_attuale.getNodeType() == Node.ELEMENT_NODE) 
			{
				NodeList attributes = tessera_attuale.getChildNodes();
				setAttributes(attributes, excomTile);

				excommunicationTiles.add(excomTile);
			}
		}
	}

	/**
	* Metodo per impostare una carta con tutti gli attributi presenti nel suo nodo
	*
	* @param	attributes		insieme di nodi rappresentanti i vari attributi della carta
	* @param	tile			tessera della quale si devono impostare gli attributi
	*/
	private void setAttributes(NodeList attributes, ExcommunicationTile tile) {

		for(int j=0; j < attributes.getLength(); j++) 
		{
			Node current_attribute = attributes.item(j);

			if (current_attribute.getNodeType() == Node.ELEMENT_NODE) 
			{
				if ( current_attribute.getNodeName().equals("code") ) 
					tile.setCode( Integer.parseInt( current_attribute.getFirstChild().getNodeValue() ));
				
				if ( current_attribute.getNodeName().equals("period") ) 
					tile.setPeriod( Integer.parseInt( current_attribute.getFirstChild().getNodeValue() ));

				if ( current_attribute.getNodeName().equals("effect") ) 
				{
					tile.setPermEffect( parseEffectNode(current_attribute) );
				}
			}
		}
	}

	/**
	* Metodo per generare l'insieme di effetti associati ad un Nodo radice
	*
	* @param	effetto		nodo radice i cui figli sono una definizione di vari effetti
	* 
	* @return 				insieme di effetti costruiti dal file XML
	*/
	private ArrayList<Effect> parseEffectNode(Node effetto) {

		ArrayList<Effect> effects_collection = new ArrayList<Effect>();
		NodeList effects = ((Element) effetto).getElementsByTagName("effect");

		for(int k = 0; k < effects.getLength(); k++) {
			Node current_effect = effects.item(k);

			if (current_effect.getNodeType() == Node.ELEMENT_NODE) 
			{	
				Effect e = null;
				Node kind = ((Element) current_effect).getElementsByTagName("kind").item(0);

				switch ( kind.getTextContent() ) {
				case "bmResources" :
					/*
					int value =  Integer.parseInt( ((Element) current_effect).getElementsByTagName("value").item(0).getTextContent() );
					e = new EffectsBonusMalusResources(value, );
					
					Node bonus_res_effect = ((Element) effetto).getElementsByTagName("bonus").item(0);
					e = new EffectsResources( parseResourceNode(bonus_res_effect) );
					*/
					break;

				case "bmMember" :
					String member = ((Element) current_effect).getElementsByTagName("member").item(0).getTextContent();
					int value =  Integer.parseInt( ((Element) current_effect).getElementsByTagName("value").item(0).getTextContent() );
					e = new EffectsBonusMalusMember( value, parseMember(member) );
					break;
					
				case "bmAction" :	
					
					String action = ((Element) current_effect).getElementsByTagName("action").item(0).getTextContent();
					int amount =  Integer.parseInt( ((Element) current_effect).getElementsByTagName("amount").item(0).getTextContent() );
					e = new EffectsBonusMalusActions( amount, parseAction(action) );
					break;

				case "noMarket" :
					e = new EffectsBonusMalusNoMarket();
					break;

				default :
				}

				effects_collection.add(e);
			}
		}
		return effects_collection;
	}
	
	/**
	 * Metodo per associazione biunivoca tra una stringa del tipo di azione
	 * ed il relativo elemento di ActionCategory
	 * 
	 * @param	action	stringa con il nome del'azione da associare
	 * 
	 * @return			<p>elemento di ActionCategory relativo alla stringa passata</p>
	 * 					<p>null nel caso in cui la stringa non rappresentasse un dato noto</p>
	 * 
	 * @see				it.polimi.ingsw.ps06.model.Types
	 */
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
	* @return	insieme di tessere costruite   
	*/
	public ArrayList<ExcommunicationTile> getTiles(){
		return excommunicationTiles;
	}
}