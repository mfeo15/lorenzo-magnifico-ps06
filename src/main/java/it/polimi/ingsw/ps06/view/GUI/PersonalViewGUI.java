package it.polimi.ingsw.ps06.view.GUI;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class PersonalViewGUI {
	private int playerCode;
	private int width, height;
	private JFrame f;
	private JButton exit;
	
	private int code1, code2, code3, code4;
	private int btCode;

		public PersonalViewGUI(int code){
			this.playerCode=code;
			
		}
		
		public PersonalViewGUI() throws IOException{
			
			setPersonalView();
			
			exit = new JButton();
			f = new JFrame();
			
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			
			double ratio= (screenSize.getWidth()/screenSize.getHeight());
			
			width = (int)((screenSize.getWidth()*70/100)*(1.5 / ratio));
			height = (int)(screenSize.getHeight()*70/100);
			
			BufferedImage image1 = ImageIO.read(new File("resources/personalView.png")); 
			BufferedImage exit1 = ImageIO.read(new File("resources/button.png")); 
			
			BufferedImage stanza1 = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			
			Graphics g1 = stanza1.createGraphics();
	        g1.drawImage(image1, 0, 0, width, height, null);
	        g1.dispose();
	        
	        String hoverSound = "resources/menuhover.wav";
			Media hit = new Media(new File(hoverSound).toURI().toString());
			
			String selectSound = "resources/menuselect.wav";
			Media hit2 = new Media(new File(selectSound).toURI().toString());
			
			String exitSound = "resources/exit.wav";
			Media music1 = new Media(new File(exitSound).toURI().toString());
			
			JLabel label = new JLabel(new ImageIcon(stanza1)); 
			
			exit = new JButton();
		    exit.setLocation(width*95/100,0);
		    exit.setSize(width*5/100,width*5/100);
		    exit.setOpaque(false);
		    exit.setContentAreaFilled(false);
		    exit.setFocusPainted(false);
		    exit.setBorderPainted(false);
		    exit.setIcon(new ImageIcon(exit1));
	        f.add(exit);
	        
	        exit.addMouseListener(new MouseAdapter()
	        {
	            public void mouseEntered(MouseEvent evt)
	            {

	            	MediaPlayer mediaPlayer = new MediaPlayer(hit);
					mediaPlayer.play();  
	            }
	            
	            public void mouseExited(MouseEvent evt)
	            {
	            	
	            	MediaPlayer mediaPlayer = new MediaPlayer(hit);
					mediaPlayer.play();
			        
	            }
	            
	            public void mousePressed(MouseEvent evt)
	            {
	            	MediaPlayer mediaPlayer3 = new MediaPlayer(music1);
	        		mediaPlayer3.play();
	                f.dispose();
	            }
	            
	        });
	        
	        JButton[] territories = new JButton[6];
	        JButton[] buildings = new JButton[6];
	        
	        territories = initializeButtons(territories);
	        buildings = initializeButtons(buildings);

	        territories = setLabels(territories);
	        buildings = setLabels(buildings);
	        
        	territories = locatePersonalCards(territories,false);
        	buildings = locatePersonalCards(buildings,true);
	          
	        
	        JButton[] bonusTile = new JButton[1];
	        bonusTile = initializeButtons(bonusTile);
	        bonusTile=setLabels(bonusTile);
	        bonusTile[0].setLocation((int)(width*0.2/100),(int)(height*0.3/100));
	        bonusTile[0].setSize((int)(width*8/100),(int)(height*99.6/100));
	        
	        
	        JLabel bonusTileLabel = new JLabel();       
	        bonusTileLabel = setImage("resources/tile/pb"+btCode+".png",8,100);
	        
	        bonusTile[0].setIcon(bonusTileLabel.getIcon());
			bonusTile[0].setDisabledIcon( bonusTile[0].getIcon() );
			
	        
			for(int j=0; j<territories.length;j++){ f.add(territories[j]); }
			for(int j=0; j<buildings.length;j++){ f.add(buildings[j]); }
			f.add(bonusTile[0]);
			
	        f.getContentPane().add(label);
	        f.setUndecorated(true);
	        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        f.pack();
	     
	        
	        f.setResizable(false);
	        f.setLocationRelativeTo(null);
	        f.setVisible(true);  
	        
		}
		
		public void setPersonalView(){
			
			code1= 5;
			code2= 7;
			code3= 15;
			code4=19;
			btCode=1;
			
		}
		
		private JButton[] initializeButtons(JButton...btns){
			
			for (int j=0;j<btns.length;j++) {
		        btns[j]=new JButton();
		    }
			return btns;
		}
		
		
		private JButton[] setLabels(JButton[] btns){
			
			for (JButton btn : btns) {
				
				btn.setOpaque(false);
				btn.setContentAreaFilled(false);
				btn.setFocusPainted(false);
				btn.setEnabled(false);
		        //btn.setBorderPainted(false);
			}
			
			return btns;
		}
		
		private JButton[] set(JButton[] btns){
			
			for (JButton btn : btns) {
				
				btn.setOpaque(false);
				btn.setContentAreaFilled(false);
				btn.setFocusPainted(false);
		        //btn.setBorderPainted(false);
			}
			
			return btns;
		}
		
		private JButton[] locatePersonalCards(JButton[] btns, boolean buildings){
			double x=9;
			double y=3;
			
			if(buildings)y=y+40;
			
			for(int j=0;j<btns.length;j++){
				
				btns[j].setLocation((int)(width*x/100),(int)(height*y/100));
				btns[j].setSize(width*15/100,height*32/100);
				x=x+15;		
			}
			return btns;
		}
		
		private JLabel setImage(String s,double xMod, double yMod) throws IOException{
			
			BufferedImage image = ImageIO.read(new File(s)); 
			
			BufferedImage board = new BufferedImage((int)(width*xMod/100), (int)(height*yMod/100), BufferedImage.TYPE_INT_ARGB);
			
			Graphics g = board.createGraphics();
	        g.drawImage(image, 0, 0, (int)(width*xMod/100), (int)(height*yMod/100), null);
	        g.dispose();
	        
	        JLabel label = new JLabel(new ImageIcon(board)); 
	        
	        return label;
			
		}
}
