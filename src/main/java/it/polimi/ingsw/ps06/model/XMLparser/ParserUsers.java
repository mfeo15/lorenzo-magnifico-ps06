package it.polimi.ingsw.ps06.model.XMLparser;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import it.polimi.ingsw.ps06.model.TimeSettings;
import it.polimi.ingsw.ps06.model.cards.ExcommunicationTile;
import it.polimi.ingsw.ps06.networking.User;


/**
 * Classe per la lettura del file xml dei client user
 *
 * @author  ps06
 * @version 1.1
 * @since   2017-07-04
 */
public class ParserUsers extends XMLParser {
	
	private ArrayList<User> users;

	/**
	 * Costruttore della classe
	 *
	 * @param	source	Stringa corrispondente al percorso del file 
	 * 
	 */	
	public ParserUsers(String source) {
		super(source);
		this.users = new ArrayList<User>();
		
		parse( buildDocument() );
	}
	
	public void parse(Document d) {
		
		Node root = d.getFirstChild();
		NodeList usersList = ((Element) root).getElementsByTagName("user");
		
		for(int j=0; j<usersList.getLength(); j++) 
		{	
			User u = new User();
			Node current = usersList.item(j);
			
			if (current.getNodeType() == Node.ELEMENT_NODE) 
			{
				NodeList attributes = current.getChildNodes();
				setAttributes(attributes, u);

				users.add(u);
			}
		}
	}
	
	private void setAttributes(NodeList attributes, User user) 
	{
		for(int j=0; j < attributes.getLength(); j++) 
		{
			Node current_attribute = attributes.item(j);

			if (current_attribute.getNodeType() == Node.ELEMENT_NODE) 
			{
				if ( current_attribute.getNodeName().equals("username") ) 
					user.setUsername( current_attribute.getFirstChild().getNodeValue() );
				
				if ( current_attribute.getNodeName().equals("password") ) 
					user.setPassword( current_attribute.getFirstChild().getNodeValue() );
				
				if ( current_attribute.getNodeName().equals("game_counter") ) 
					user.setGameCounter( Integer.parseInt( current_attribute.getFirstChild().getNodeValue() ));
				
				if ( current_attribute.getNodeName().equals("win_counter") ) 
					user.setWinCounder( Integer.parseInt( current_attribute.getFirstChild().getNodeValue() ));
				
				if ( current_attribute.getNodeName().equals("second_place_counter") ) 
					user.setSecondPlaceCounter( Integer.parseInt( current_attribute.getFirstChild().getNodeValue() ));
				
				if ( current_attribute.getNodeName().equals("max_score2") ) 
					user.setMaxScore( Integer.parseInt( current_attribute.getFirstChild().getNodeValue() ));
			}
		}
	}

	/**
	 *Metodo che restituisce tutti gli users
	 *
	 * @return	users caricati da file 
	 **/
	public ArrayList<User> getUsers(){
		return users;
	}
	
	public boolean contains(String username) {
		
		boolean flag = false;
		for (User u : users)
			if (u.getUsername().equals(username)) flag = true;
		
		return flag;
	}
	
	public User getUser(String username) {
		
		for (User u : users)
			if (u.getUsername().equals(username)) return u;
		
		return null;
	}
}
