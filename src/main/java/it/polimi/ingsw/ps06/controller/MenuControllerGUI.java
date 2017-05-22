package it.polimi.ingsw.ps06.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import it.polimi.ingsw.ps06.view.GUI.TestGUI;

public class MenuControllerGUI implements MenuController, ActionListener {
	
	private TestGUI theView;
	
	public MenuControllerGUI(TestGUI theView) {
		this.theView = theView;
		theView.addNewControllerListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	@Override
	public void init() {
		theView.setVisible(true);
	}
}