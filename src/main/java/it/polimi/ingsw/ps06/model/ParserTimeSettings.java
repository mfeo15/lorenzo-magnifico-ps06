package it.polimi.ingsw.ps06.model;

	import javax.xml.parsers.DocumentBuilder;
	import javax.xml.parsers.DocumentBuilderFactory;
	
	import it.polimi.ingsw.ps06.model.TimeSettings;
	
	import org.w3c.dom.Document;
	import org.w3c.dom.Element;
	import org.w3c.dom.Node;
	import org.w3c.dom.NodeList;


	/**
	* Classe per la lettura del file xml dei settaggi del tempo
	*
	* @author  ps06
	* @version 1.1
	* @since   2017-06-16
	*/
	
	@SuppressWarnings("unused")
	public class ParserTimeSettings {
		
		private String XML_sourceFile;
		private int server;
		private int action;
		private int players;
		private TimeSettings sett;
		
		/**
		* Costruttore della classe
		*
		* @param source		Stringa corrispondente al percorso del file 
		* 
		*/	
		
		public ParserTimeSettings(String source) {
			this.XML_sourceFile = source;
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
	    		
	    		
	    		Node settings = document.getFirstChild();
	    		NodeList values = settings.getChildNodes();
	    		for(int j=0; j<values.getLength(); j++){
	    			Node current=values.item(j);
	    			if(current.getNodeName().equals("timeoutserver")){
	    				server=Integer.parseInt(current.getTextContent());
	    			}
	    			if(current.getNodeName().equals("timeoutaction")){
	    				action=Integer.parseInt(current.getTextContent());
	    			}
	    			if(current.getNodeName().equals("minnumplayers")){
	    				players=Integer.parseInt(current.getTextContent());
	    			}
	    			
	    		}
	    		sett = new TimeSettings(server, action, players);
			}
			catch(Exception e){
				e.printStackTrace();
			}
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
		
		
		public static void main(String[] args){
			ParserTimeSettings c = new ParserTimeSettings("resources/XML/TimeSettings.xml");
			TimeSettings d= new TimeSettings();
			d = c.getSettings();
			System.out.println(d.getTimeoutServer()+"   "+d.getTimeoutaction()+"   "+d.getMinPlayers());
		}
}
