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
	        f.add(exit);
	        
	        exit.addMouseListener(new MouseAdapter()
	        {
	            public void mouseEntered(MouseEvent evt)
	            {

	            	MediaPlayer mediaPlayer = new MediaPlayer(hit);
					mediaPlayer.play();
					exit.setIcon(new ImageIcon(exit1));
				    
	            }
	            
	            public void mouseExited(MouseEvent evt)
	            {
	            	
	            	MediaPlayer mediaPlayer = new MediaPlayer(hit);
					mediaPlayer.play();
	            	exit.setIcon(null);
			        
	            }
	            
	            public void mousePressed(MouseEvent evt)
	            {
	            	MediaPlayer mediaPlayer3 = new MediaPlayer(music1);
	        		mediaPlayer3.play();
	                f.dispose();
	            }
	            
	        });
	        
	        JButton[] leaders = new JButton[4];
	        
	        leaders = initializeButtons(leaders);
	        int x=11;
        	int y=40;
        	
	        for(int j=0; j<4; j++){

	        	leaders[j].setLocation((int)(width*x/100),(int)(height*y/100));
				leaders[j].setSize(width*20/100,height*40/100);
	        	leaders[j].setOpaque(false);
	        	leaders[j].setContentAreaFilled(false);
	        	leaders[j].setFocusPainted(false);
				leaders[j].setEnabled(false);
				//leaders[j].setBorderPainted(false);
				x=x+22;
				
	        }
	        
	        JLabel leadersLabel[] = new JLabel[4];
	        
	        leadersLabel[0] = setImage("resources/leader/leader"+code1+".jpg",20,40);
	        leadersLabel[1] = setImage("resources/leader/leader"+code2+".jpg",20,40);
	        leadersLabel[2] = setImage("resources/leader/leader"+code3+".jpg",20,40);
	        leadersLabel[3] = setImage("resources/leader/leader"+code4+".jpg",20,40);
	        
	        
	        JButton bonusTile = new JButton();
	        bonusTile.setLocation((int)(width*0.5/100),(int)(height*30/100));
	        bonusTile.setSize(width*5/100,(int)(height*69.5/100));
	        bonusTile.setOpaque(false);
	        bonusTile.setContentAreaFilled(false);
	        bonusTile.setFocusPainted(false);
	        bonusTile.setEnabled(false);
			//bonusTile.setBorderPainted(false);
	        
	        JLabel bonusTileLabel = new JLabel();
	        
	        bonusTileLabel = setImage("resources/tile/pb"+btCode+".png",5,68);
	        
	        bonusTile.setIcon(bonusTileLabel.getIcon());
			bonusTile.setDisabledIcon( bonusTile.getIcon() );
			
			fillLabels(leaders,leadersLabel);
	        
			for(int j=0; j<leaders.length;j++){ f.add(leaders[j]); }
			f.add(bonusTile);
			
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
		
		private JButton[] fillLabels(JButton[] btns, JLabel[] lbs){
			
			for (int j=0;j<btns.length;j++) {
				
				btns[j].setIcon(lbs[j].getIcon());
				btns[j].setDisabledIcon( btns[j].getIcon() );
			}
			
			return btns;
		}
		
		private JLabel setImage(String s,int xMod, int yMod) throws IOException{
			
			BufferedImage image = ImageIO.read(new File(s)); 
			
			BufferedImage board = new BufferedImage(width*xMod/100, height*yMod/100, BufferedImage.TYPE_INT_ARGB);
			
			Graphics g = board.createGraphics();
	        g.drawImage(image, 0, 0, width*xMod/100, height*yMod/100, null);
	        g.dispose();
	        
	        JLabel label = new JLabel(new ImageIcon(board)); 
	        
	        return label;
			
		}
}
