package it.polimi.ingsw.ps06.model;
	import it.polimi.ingsw.ps06.model.Character;

	import it.polimi.ingsw.ps06.model.Types.Action;
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

		private ArrayList<Resources> faith_points;
		private EnumMap<Action, Integer> towers;
		private String XML_sourceFile;
		
		/**
		* Costruttore della classe
		*
		* @param source		Stringa corrispondente al percorso del file 
		* 
		*/	
		public ParserBonusBoard(String source) {
			this.XML_sourceFile = source;
			faith_points=new ArrayList<Resources>();
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
	    		
	    		
	    		Node bonuses = document.getFirstChild();
	    		NodeList bonus_type = bonuses.getChildNodes();
	    		for(int j=0; j<bonus_type.getLength(); j++){
	    			Node current=bonus_type.item(j);
	    			if(current.getNodeName().equals("bonustorreverde"))
	    			
	    		}
	    		
	    		
	    		
	    		
	    		
	    		
	    		
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
}
