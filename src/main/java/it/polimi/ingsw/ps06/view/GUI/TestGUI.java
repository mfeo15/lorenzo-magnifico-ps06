package it.polimi.ingsw.ps06.view.GUI;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TestGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton newGameButton = new JButton("Nuova Partita");
	private JLabel l = new JLabel("Menu di prova per Lorenzo il Magnifico");

	public TestGUI() {
		JPanel p = new JPanel();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600, 200);
		
		p.add(l);
		p.add(newGameButton);
		
		this.add(p);
	}
	
	public void addNewControllerListener(ActionListener l) {
		newGameButton.addActionListener(l);
	}
}
