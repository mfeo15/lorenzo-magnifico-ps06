package it.polimi.ingsw.ps06.model.XMLparser;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import it.polimi.ingsw.ps06.model.Resources;
import it.polimi.ingsw.ps06.model.Types.MaterialsKind;
import it.polimi.ingsw.ps06.model.Types.PointsKind;

public abstract class XMLParser {
	
	private String XML_sourceFile;
	
	public XMLParser(String source) {
		this.XML_sourceFile = source;
	}

	/**
	* Metodo per costruire il parser
	*
	* @param none
	* @return nothing 
	* 
	*/	
	protected Document buildDocument(){
		try{
			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = documentFactory.newDocumentBuilder(); 

			XML_sourceFile = XML_sourceFile.replaceFirst("resources", "");
			return builder.parse( getClass().getResourceAsStream(XML_sourceFile) );
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	protected abstract void parse(Document d);
	
	protected Resources parseResourceNode(Node n) {

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
}
