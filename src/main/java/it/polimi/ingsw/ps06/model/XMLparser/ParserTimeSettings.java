package it.polimi.ingsw.ps06.model.XMLparser;

import it.polimi.ingsw.ps06.model.TimeSettings;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * Classe per la lettura del file xml dei settaggi del tempo
 *
 * @author  ps06
 * @version 1.1
 * @since   2017-06-16
 */
public class ParserTimeSettings extends XMLParser {
	private TimeSettings sett;

	/**
	 * Costruttore della classe
	 *
	 * @param source		Stringa corrispondente al percorso del file 
	 * 
	 */	

	public ParserTimeSettings(String source) {
		super(source);
		
		parse( buildDocument() );
	}
	
	public void parse(Document d) {
		Node settings = d.getFirstChild();
		NodeList values = settings.getChildNodes();
		
		int timeoutServer = -1;
		int timeoutAction = -1;
		
		for(int j=0; j<values.getLength(); j++) {
			
			Node current=values.item(j);
			
			if(current.getNodeName().equals("timeoutserver"))
				timeoutServer = Integer.parseInt(current.getTextContent());
			
			if(current.getNodeName().equals("timeoutaction"))
				timeoutAction = Integer.parseInt(current.getTextContent());
			
		}
		
		if ( (timeoutServer > 0) && (timeoutAction > 0) )
			sett = new TimeSettings(timeoutServer, timeoutAction);
	}

	/**
	 *Metodo che restituisce i settaggi
	 *
	 * @return sett	i settaggi 
	 * 
	 **/

	public TimeSettings getSettings(){
		return sett;
	}
}
