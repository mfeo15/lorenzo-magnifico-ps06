package it.polimi.ingsw.ps06.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import it.polimi.ingsw.ps06.view.GUI.MenuGUI;

public class MenuControllerGUI extends Observable implements MenuController, ActionListener {
	
	private MenuGUI theView;
	
	public MenuControllerGUI(MenuGUI theView) {
		this.theView = theView;
		theView.addNewControllerListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("PRESSED");
	}

	@Override
	public void init() {
		theView.setVisible(true);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
	}
}