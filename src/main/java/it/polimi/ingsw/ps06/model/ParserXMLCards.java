package it.polimi.ingsw.ps06.model;
import it.polimi.ingsw.ps06.model.Character;

import it.polimi.ingsw.ps06.model.Types.MaterialsKind;
import it.polimi.ingsw.ps06.model.Types.PointsKind;
import it.polimi.ingsw.ps06.model.Types.ColorPalette;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

/**
* Classe per la lettura del file xml delle carte territorio, edificio, impresa e personaggio
*
* @author  ps06
* @version 1.0
* @since   2017-05-28
*/
public class ParserXMLCards {

	private ArrayList<DevelopementCard> cards;
	private String XML_sourceFile;
	
	/**
	* Costruttore della classe
	*
	* @param source		Stringa corrispondente al percorso del file 
	* 
	*/	
	public ParserXMLCards(String source) {
		this.XML_sourceFile = source;
		cards=new ArrayList<DevelopementCard>();
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
    		
    		XML_sourceFile = XML_sourceFile.replaceFirst("resources", "");
    		Document document = builder.parse( getClass().getResourceAsStream(XML_sourceFile) ); 
    		
    		
    		NodeList carte = document.getElementsByTagName("card");
    		DevelopementCard dev=new DevelopementCard();
    		for(int j = 0; j < carte.getLength(); j++){
    			Node node = carte.item(j); 
    			Element carta = (Element)node; 
		        if(carta.getAttribute("kind").equals("territory")) {
		        	Territory a=new Territory();
		        	NodeList attributes=node.getChildNodes();
	    			for(int k = 0; k < attributes.getLength(); k++){
	        			Node nodo = attributes.item(k); 
	        		 setAttributes(nodo,a);
	    			}
	    			dev=(Territory)a;		        	
		        }
		        	
		        else if (carta.getAttribute("kind").equals("building")){
		        	Building a=new Building();
		        	NodeList attributes=node.getChildNodes();
	    			for(int k = 0; k < attributes.getLength(); k++){
	        			Node nodo = attributes.item(k); 
	        			setAttributes(nodo,a);
	    			}
	    			dev=(Building)a;
		        	}
		        	
		        else if(carta.getAttribute("kind").equals("venture")){
		        	Venture a=new Venture();		        	
		        	NodeList attributes=node.getChildNodes();
	    			for(int k = 0; k < attributes.getLength(); k++){
	        			Node nodo = attributes.item(k); 
	        			setAttributes(nodo,a);
	    			}
	    			dev=(Venture)a;
		        	}

		        else if(carta.getAttribute("kind").equals("character")){
		        	Character a=new Character();
		        	NodeList attributes=node.getChildNodes();
	    			for(int k = 0; k < attributes.getLength(); k++){
	        			Node nodo = attributes.item(k); 
	        			setAttributes(nodo,a);
	    			}
	    			dev=(Character)a;
		        }	
	    		cards.add(dev);
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
	
	public DevelopementCard setAttributes(Node a, DevelopementCard d){
		
		if(a.getNodeName().equals("title")){
			d.setTitle(a.getTextContent());
		}
		else if(a.getNodeName().equals("code")){
			int x=Integer.parseInt(a.getTextContent());
			d.setCode(x);
		}
		else if(a.getNodeName().equals("period")){
			int x=Integer.parseInt(a.getTextContent());
			d.setPeriod(x);
		}
		else if(a.getNodeName().equals("cost")){
			if(d instanceof Building){
				((Building) d).setRequirement(setResourceBonus(a));
			}
			if(d instanceof Character){
				((Character) d).setRequirement(setResourceBonus(a));
			}
			if(d instanceof Venture){
				Resources res=setResourceBonus(a);
				((Venture) d).setRequirement(res);
			}
		}
		else if(a.getNodeName().equals("permanent_effect")){
			generatePermanentEffect(d, a);
		}
		else if(a.getNodeName().equals("instant_effects")){
			generateInstantEffect(d, a);
		}
		
		else if(a.getNodeName().equals("production_requirement")){
			if(d instanceof Building){
				int x=Integer.parseInt(a.getTextContent());
				((Building) d).setDiceReq(x);
				}
			if(d instanceof Territory){
				int x=Integer.parseInt(a.getTextContent());
				((Territory) d).setDiceReq(x);
				}
		}
			else if(a.getNodeName().equals("requirement")){
			int x=Integer.parseInt(a.getFirstChild().getTextContent());
			if(d instanceof Venture){
				((Venture) d).setMilRequirement(x);
			}
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
	
	public void generateInstantEffect(DevelopementCard card, Node effetto ){
		Element c=(Element)effetto;
		NodeList d=c.getElementsByTagName("effect");
		for(int k=0; k<d.getLength();k++){
			Node eff1=d.item(k);
			Node eff=eff1.getFirstChild();
			if(eff1.getTextContent().equals("resources")){
					Node bonus=eff.getNextSibling();
					Resources res=new Resources();
					res=setResourceBonus(bonus);
					EffectsResources eff_res=new EffectsResources(res);
					card.setEffect(eff_res);
			}	
			else if(eff1.getTextContent().equals("byCard")){
				Node col=eff.getNextSibling();
				String color = col.getTextContent();
				Node bonus=col.getNextSibling();
				Resources res=new Resources();
				res=setResourceBonus(bonus);
				EffectsResourcesByCard e = new EffectsResourcesByCard(res, ColorPalette.valueOf(color.toUpperCase()));
				card.setEffect(e);
			}
			else if(eff1.getTextContent().equals("byPoints")){
				Node mil=eff.getNextSibling();
				//int x=Integer.parseInt(mil.getNodeValue());
				Node bonus=mil.getNextSibling();
				Resources res=new Resources();
				res=setResourceBonus(bonus);
				EffectsResourcesByPoint ef = new EffectsResourcesByPoint(res, PointsKind.MILITARY_POINTS);
				card.setEffect(ef);
			}
			else if(eff1.getTextContent().equals("privilege")){
				//EffectsPrivilege effec = new EffectPrivilege();
				//card.setEffect(effec);
				//Integer x = Integer.parseInt(effect.getElementsByTagName("amount").item(0).getTextContent());   qtà di privilegi
			}			
			else if(eff1.getTextContent().equals("colorful")){
				//nuovo effeto colorful? EffectsAction
				//Integer x = Integer.parseInt(effect.getElementsByTagName("value").item(0).getTextContent());
			}
			else if(eff1.getTextContent().equals("green")){
				//new EffectsActive qlcs....
				//Integer x1 = Integer.parseInt(effect.getElementsByTagName("value").item(0).getTextContent());
			}
			else if(eff1.getTextContent().equals("yellow")){
				//new EffectsActive qlcs....
				//Integer x2 = Integer.parseInt(effect.getElementsByTagName("value").item(0).getTextContent());
			}
			else if(eff1.getTextContent().equals("blue")){
				//new EffectsActive qlcs....
				//Integer x3 = Integer.parseInt(effect.getElementsByTagName("value").item(0).getTextContent());
			}
			else if(eff1.getTextContent().equals("purple")){
				//new EffectsActive qlcs....
				//Integer x4 = Integer.parseInt(effect.getElementsByTagName("value").item(0).getTextContent());
			}
			else if(eff1.getTextContent().equals("discount")){
				//Effect eff= new EffectsActive();
				//String b=effect.getAttribute("color");
				//Integer y = Integer.parseInt(effect.getElementsByTagName("amount").item(0).getTextContent());
				//settare amount per carta
				//EffectsActive.qualcosa per risorsa da togliere(setResourceBonus(effect);
				//card.setPermEffect(eff);
			}
		}
	}
	
	/**
	* Metodo che genera l'effetto permanente delle carte
	*
	* @param effetto	Nodo contenente l'effetto e i suoi valori da impostare
	* @param card		Carta della quale bisogna impostare l'effetto
	* 
	* @return nothing
	* 
	*/	
	public void generatePermanentEffect(DevelopementCard card, Node effetto){
		Element c=(Element)effetto;
		NodeList d=c.getElementsByTagName("effect");
		for(int k=0; k<d.getLength();k++){
			Node eff1=d.item(k);
			Node eff=eff1.getFirstChild();

			if(eff1.getTextContent().equals("resources")){
				Node bonus=eff.getNextSibling();
				Resources res=new Resources();
				res=setResourceBonus(bonus);
				EffectsResources eff_res=new EffectsResources(res);
				card.setEffect(eff_res);
			}
			else if(eff1.getTextContent().equals("swapResources")){	
				Node input=eff.getFirstChild();
				Resources res=new Resources();
				res=setResourceBonus(input);
				Node bonus=input.getNextSibling();
				Resources res1=new Resources();
				res1=setResourceBonus(bonus);
				EffectsResourcesSwap swap=new EffectsResourcesSwap(res1, res);
				card.setPermEffect(swap);
			}
			else if(eff1.getTextContent().equals("bmMember")){
			//Effect eff= new EffectsActive();
			//String a=effect.getAttribute("color");
			//Integer x = Integer.parseInt(effect.getElementsByTagName("amount").item(0).getTextContent());
			//settare amount per carta
			//card.setPermEffect(eff);
			}
			else if(eff1.getTextContent().equals("bmMember")){
			//Effect eff= new EffectsActive();
			//String b=effect.getAttribute("color");
			//Integer x1 = Integer.parseInt(effect.getElementsByTagName("amount").item(0).getTextContent());
			//settare amount per carta
			//EffectsActive.qualcosa per risorsa da togliere(setResourceBonus(effect);
			//card.setPermEffect(eff);
			}
			else if(eff1.getTextContent().equals("harvest")){
			//Integer x2 = Integer.parseInt(effect.getElementsByTagName("amount").item(0).getTextContent());
			//Effect eff=new EffectsActiveGathering(Observable cosa mettere??, x2);
			}
			else if(eff1.getTextContent().equals("production")){  //produzione e gathering sono uguali?
			//Integer x3 = Integer.parseInt(effect.getElementsByTagName("amount").item(0).getTextContent());
			//Effect eff=new EffectsActiveGathering(Observable cosa mettere??, x3);
			}
			else if(eff1.getTextContent().equals("noEffettoTorri")){
			//new effetto cosa?
			}
		}
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



	
}