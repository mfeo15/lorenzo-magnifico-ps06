package it.polimi.ingsw.ps06.controller;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps06.Client;
import it.polimi.ingsw.ps06.Message;
import it.polimi.ingsw.ps06.model.events.Event;
import it.polimi.ingsw.ps06.model.events.StoryBoard;
import it.polimi.ingsw.ps06.model.messages.MessageParser;
import it.polimi.ingsw.ps06.view.PersonalView;
import it.polimi.ingsw.ps06.view.Room;

public class PersonalViewController extends Observable implements Observer{
	
private PersonalView theView;
	
	private ArrayList<String> players;
	
	public PersonalViewController(PersonalView personalView) {
		this.theView = personalView;
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
		if (!(arg instanceof Event))
			return;
					
		//Smista le comunicazioni provenienti dalla View
		if ( o.getClass().isInstance(theView) ) {
					
			MessageParser parser = new MessageParser();
			((Event) arg).accept(parser);
		}
	}

}
