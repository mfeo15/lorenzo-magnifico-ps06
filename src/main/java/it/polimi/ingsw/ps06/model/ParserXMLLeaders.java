package it.polimi.ingsw.ps06.model;
import it.polimi.ingsw.ps06.model.Character;

import it.polimi.ingsw.ps06.model.Types.MaterialsKind;
import it.polimi.ingsw.ps06.model.Types.PointsKind;
import it.polimi.ingsw.ps06.model.Types.CardType;
import it.polimi.ingsw.ps06.model.Types.ColorPalette;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

/**
* Classe per la lettura del file xml delle carte leader
*
* @author  ps06
* @version 1.0
* @since   2017-06-05
*/
public class ParserXMLLeaders {

	private ArrayList<Leader> leaders;
	private String XML_sourceFile;
	
	/**
	* Costruttore della classe
	*
	* @param source		Stringa corrispondente al percorso del file 
	* 
	*/	
	public ParserXMLLeaders(String source) {
		this.XML_sourceFile = source;
		leaders=new ArrayList<Leader>();
		buildDocument();
	}
	
	/**
	*Metodo per costruire il parser
	*
	* @param none
	* @return nothing 
	* 
	*/	
	
	public void buildDocument(){
		try{
			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
    		DocumentBuilder builder = documentFactory.newDocumentBuilder(); 
    		Document document = builder.parse(XML_sourceFile); 
  
    		NodeList carte = document.getElementsByTagName("card");
    		Leader lead=new Leader();
    		for(int j = 0; j < carte.getLength(); j++){
    			Node node = carte.item(j);
		        	NodeList attributes=node.getChildNodes();
	    			for(int k = 0; k < attributes.getLength(); k++){
	        			Node nodo = attributes.item(k); 
	        			lead=setAttributes(nodo,lead);
	    			}
		    		leaders.add(lead);
		        }
		}
		catch(Exception e){
			e.printStackTrace();
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
	
	public Leader setAttributes(Node a, Leader d){
		Leader l= new Leader();
		if(a.getNodeName().equals("title")){
			d.setTitle(a.getTextContent());
		}
		else if(a.getNodeName().equals("code")){
			int x=Integer.parseInt(a.getTextContent());
			d.setCode(x);
		}
		else if(a.getNodeName().equals("cost")){
				l.setRequirement(setLeadResourceBonus(a));
		}
		else if(a.getNodeName().equals("permanent_effect")){
			generatePermanentEffect(d, a);
		}
		return d;
	}
	
	/**
	* Metodo per creare un elemento risorsa quando nel file xml si va a leggere un nodo che imposti delle risorse nelle carte
	*
	* @param bonus		Nodo contenente i valori dei vari tipi di risorsa da allocare
	* @return c			risorsa da impostare nella carta in questione 
	* 
	*/	
	
	public LeaderRequirement setLeadResourceBonus(Node bonus){
		LeaderRequirement c= new LeaderRequirement();
		NodeList bon=bonus.getChildNodes();
		for(int j=0; j<bon.getLength(); j++){
			Node b=bon.item(j);
			String x=b.getNodeName();
			if(x.equals("wood")){
				int z=Integer.parseInt(b.getTextContent());
				c.setResourceValue(MaterialsKind.WOOD, z);
			}
			else if(x.equals("stone")){
				int z=Integer.parseInt(b.getTextContent());
				c.setResourceValue(MaterialsKind.STONE, z);
			}
			else if(x.equals("coin")){
				int z=Integer.parseInt(b.getTextContent());
				c.setResourceValue(MaterialsKind.COIN, z);
			}
			else if(x.equals("servant")){
				int z=Integer.parseInt(b.getTextContent());
				c.setResourceValue(MaterialsKind.SERVANT, z);
			}
			else if(x.equals("faith")){
				int z=Integer.parseInt(b.getTextContent());
				c.setResourceValue(PointsKind.FAITH_POINTS, z);
			}
			else if(x.equals("victory")){
				int z=Integer.parseInt(b.getTextContent());
				c.setResourceValue(PointsKind.VICTORY_POINTS, z);
			}
			else if(x.equals("military")){
				int z=Integer.parseInt(b.getTextContent());
				c.setResourceValue(PointsKind.MILITARY_POINTS, z);
			}
			else if(x.equals("territory")){
				int z=Integer.parseInt(b.getTextContent());
				c.setResourceValue(CardType.TERRITORY, z);
			}
			else if(x.equals("character")){
				int z=Integer.parseInt(b.getTextContent());
				c.setResourceValue(CardType.CHARACTER, z);
			}
			else if(x.equals("building")){
				int z=Integer.parseInt(b.getTextContent());
				c.setResourceValue(CardType.BUILDING, z);
			}
			else if(x.equals("venture")){
				int z=Integer.parseInt(b.getTextContent());
				c.setResourceValue(CardType.VENTURE, z);
			}
			else if(x.equals("anyCard")){
				int z=Integer.parseInt(b.getTextContent());
				c.setAnyCardReq(z);
			}
		}
		return c;		
	}
	/**
	* Metodo per creare un elemento risorsa quando nel file xml si va a leggere un nodo che imposti delle risorse nelle carte
	*
	* @param bonus		Nodo contenente i valori dei vari tipi di risorsa da allocare
	* @return c			risorsa da impostare nella carta in questione 
	* 
	*/	
	
	public Resources setResourceBonus(Node bonus){
		Resources c= new Resources();
		NodeList bon=bonus.getChildNodes();
		for(int j=0; j<bon.getLength(); j++){
			Node b=bon.item(j);
			String x=b.getNodeName();
			if(x.equals("wood")){
				int z=Integer.parseInt(b.getTextContent());
				c.setResourceValue(MaterialsKind.WOOD, z);
			}
			else if(x.equals("stone")){
				int z=Integer.parseInt(b.getTextContent());
				c.setResourceValue(MaterialsKind.STONE, z);
			}
			else if(x.equals("coin")){
				int z=Integer.parseInt(b.getTextContent());
				c.setResourceValue(MaterialsKind.COIN, z);
			}
			else if(x.equals("servant")){
				int z=Integer.parseInt(b.getTextContent());
				c.setResourceValue(MaterialsKind.SERVANT, z);
			}
			else if(x.equals("faith")){
				int z=Integer.parseInt(b.getTextContent());
				c.setResourceValue(PointsKind.FAITH_POINTS, z);
			}
			else if(x.equals("victory")){
				int z=Integer.parseInt(b.getTextContent());
				c.setResourceValue(PointsKind.VICTORY_POINTS, z);
			}
			else if(x.equals("military")){
				int z=Integer.parseInt(b.getTextContent());
				c.setResourceValue(PointsKind.MILITARY_POINTS, z);
			}
		}
		return c;		
	}
	
	/**
	* Metodo che genera l'effetto istantaneo delle carte
	*
	* @param effetto	Nodo contenente l'effetto e i suoi valori da impostare
	* @param card		Carta della quale bisogna impostare l'effetto
	* 
	* @return nothing
	* 
	*/	
	
	/*public void generatePermanentEffect(Leader card, Node effetto ){
		Node d=effetto.getFirstChild();
		Element e=(Element) d;
		if(e.getAttribute("type").equals("turno")){
			NodeList attr=d.getChildNodes();
			for(int j=0; j<attr.getLength(); j++){
				Node c=attr.item(j);
				if(c.getTextContent().equals("harvest")){
					//fai un azione gathering
					int x=Integer.parseInt(c.getne)
				}
			}
		}
		else if(e.getAttribute("type".equals("permanente"))){
			
	
		}
	}*/
		
		/*public static void main(String[] args){
			ParserXMLLeaders c = new ParserXMLLeaders("resources/XML/Leaders.xml");
		}*/
	}