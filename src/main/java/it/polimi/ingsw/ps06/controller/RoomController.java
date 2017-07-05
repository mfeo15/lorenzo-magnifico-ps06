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

public class RoomController extends Observable implements Observer {
	
	private Room theView;
	private Client theModel;
	
	private ArrayList<String> players;
	
	public RoomController(Client model, Room view) {
		this.theView = view;
		this.theModel = model;
	}
	
	public void addNewObserver(Observer o) {
		addObserver(o);
	}
	
	public void notifyChangement(Object o) {
		setChanged();
		notifyObservers(o);
	} 

	@Override
	public void update(Observable o, Object arg) {
		/*
		if (!( arg instanceof Message))
			return;
		*/
		
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
	
	private String encrypt(String s) {
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