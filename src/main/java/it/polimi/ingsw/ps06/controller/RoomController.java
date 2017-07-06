package it.polimi.ingsw.ps06.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.xml.bind.DatatypeConverter;

import it.polimi.ingsw.ps06.model.events.Event;
import it.polimi.ingsw.ps06.model.events.EventParser;
import it.polimi.ingsw.ps06.model.events.StoryBoard;
import it.polimi.ingsw.ps06.networking.Client;
import it.polimi.ingsw.ps06.networking.messages.Client2Server;
import it.polimi.ingsw.ps06.networking.messages.EventMessage;
import it.polimi.ingsw.ps06.networking.messages.Message;
import it.polimi.ingsw.ps06.networking.messages.MessageParser;
import it.polimi.ingsw.ps06.networking.messages.MessageUser;
import it.polimi.ingsw.ps06.networking.messages.Server2Client;
import it.polimi.ingsw.ps06.view.Room;

/**
 * Controller associato alla View Room
 *
 * @author  ps06
 * @version 1.1
 * @since   2017-05-23
 */
public class RoomController extends Observable implements Observer {
	
	private Room theView;
	private Client theModel;
	
	/**
	 * Costruttore della classe
	 * 
	 * @param 	model	model associato al controller
	 * @param 	view	view associata al controller
	 */
	public RoomController(Client model, Room view) {
		this.theView = view;
		this.theModel = model;
	}
	
	/**
	 * Metodo per l'aggiunta di un nuovo Observer
	 * 
	 * @param	o	observer da aggiungere
	 */
	public void addNewObserver(Observer o) {
		addObserver(o);
	}
	
	/**
	 * Metodo per la notifica dei messaggi verso gli Observes
	 * 
	 * @param	o	oggetto da notificare
	 */
	public void notifyChangement(Object o) {
		setChanged();
		notifyObservers(o);
	} 

	@Override
	public void update(Observable o, Object arg) {
		
		//Smista le comunicazioni provenienti dal Client
		if ( o.getClass().isInstance(theModel) ) {
			handleMessage( (Message) arg );
			return;
		}
		
		//Smista le comunicazioni provenienti dalla View
		if ( o.getClass().isInstance(theView) ) {
			
			if (arg instanceof Message) {
				handleMessage( (Message) arg );
				return;
			}
			
			if (arg instanceof Event) {
				handleEvent( (Event) arg );
				return;
			}
		}
	}
	
	/**
	 * Metodo per la gestione di un messaggio in/out
	 * 
	 * @param	m	messaggio da gestire
	 */
	private void handleMessage(Message m) {
		
		if (m instanceof MessageUser)
			((MessageUser) m).setPassword( encrypt( ((MessageUser) m).getPassword() ) );
		
		if (m instanceof Server2Client) {
			//This is a normal SERVER -> CLIENT message 
			((Server2Client) m).accept(new MessageParser(theView));
			return;
		}	
		
		if (m instanceof Client2Server) {
			notifyChangement(m);
		}
	}
	
	/**
	 * Metodo per la gestione degli eventi in/out
	 * 
	 * @param	e	evento da gestire
	 */
	private void handleEvent(Event e) {
		
		if ( e instanceof StoryBoard) 
		{
			//Let the controller handle this, it's just a StoryBoard Event (new View)
			EventParser parser = new EventParser();
			((StoryBoard) e).accept(parser);
		} 
		else 
		{
			//Event to let the model handle
			EventMessage m = new EventMessage(e);
			notifyChangement(m);
		}
	}
	
	/**
	 * Metodo per la protezione di un dato a rischio proveniente dalla view.
	 * Viene eseguit una cifratura SHA-224 su elementi della view che richiedono un 
	 * trattamento particolare (le password delle utenze ad esempio)
	 * 
	 * @param	s	stringa da crpitare
	 * 
	 * @return		stringa criptata
	 */
	private String encrypt(String s) 
	{
		String hash = "";
		
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-224");
			messageDigest.update( s.getBytes() );
			byte[] digestedBytes = messageDigest.digest();
			hash = DatatypeConverter.printHexBinary(digestedBytes).toLowerCase();
			
			System.out.println(hash);
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return hash;
	}
}