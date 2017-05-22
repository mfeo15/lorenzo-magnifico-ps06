package it.polimi.ingsw.ps06.view.GUI;


import javafx.scene.media.AudioClip;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.beans.EventHandler;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class MenuGUI extends JFrame {
	
	private Image backgroundImage;
	private JButton button;
	private JButton button2;
	private JFrame f;
     
	private JFXPanel fxPanel = new JFXPanel();
	 
	public MenuGUI() throws IOException{
		
		f = new JFrame();
    	
    	String path = "resources/cover4.png";
        File file = new File(path);
        BufferedImage image = ImageIO.read(file);
        JLabel label = new JLabel(new ImageIcon(image)); 
        
        String bip = "resources/menuhover.wav";
		Media hit = new Media(new File(bip).toURI().toString());
		
		String bip2 = "resources/menuselect.wav";
		Media hit2 = new Media(new File(bip2).toURI().toString());
		
		String exit = "resources/exit.wav";
		Media music1 = new Media(new File(exit).toURI().toString());
		
		/*
		String music = "resources/menuopen.mp3";
		Media music2 = new Media(new File(music).toURI().toString());
		AudioClip mediaPlayer4 = new AudioClip(music2.getSource());
		mediaPlayer4.play(); 
		*/
    	
        
    	button = new JButton();
        button.setLocation(410,422);
        button.setSize(200,200);
        
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        
        f.add(button);
        
        button.addMouseListener(new MouseAdapter()
        {
            public void mouseEntered(MouseEvent evt)
            {
            	String imageName = "resources/cover7.png";
            	try {
            		
            		
					label.setIcon( new ImageIcon(ImageIO.read( new File(imageName) ) ) );
					MediaPlayer mediaPlayer = new MediaPlayer(hit);
					mediaPlayer.play();
					
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            public void mouseExited(MouseEvent evt)
            {
            	String imageName = "resources/cover4.png";
            	try {
            		
            		
					label.setIcon( new ImageIcon(ImageIO.read( new File(imageName) ) ) );
					MediaPlayer mediaPlayer = new MediaPlayer(hit);
					mediaPlayer.play();
					
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	
            }
            public void mousePressed(MouseEvent evt)
            {
                try {
                	
                	MediaPlayer mediaPlayer2 = new MediaPlayer(hit2);
                	mediaPlayer2.play();
                	new RoomGUI();
					f.dispose();
					
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
                
            });
        
        button2 = new JButton();
        button2.setLocation(960,0);
        button2.setSize(55,55);
        
        button2.setOpaque(false);
        button2.setContentAreaFilled(false);
        button2.setBorderPainted(false);
        
        f.add(button2);
        
        button2.addMouseListener(new MouseAdapter()
        {
            public void mouseEntered(MouseEvent evt)
            {
            	String imageName = "resources/cover6.png";
            	try {
					
            		
            		label.setIcon( new ImageIcon(ImageIO.read( new File(imageName) ) ) );
            		MediaPlayer mediaPlayer = new MediaPlayer(hit);
					mediaPlayer.play();
					
					
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
            public void mouseExited(MouseEvent evt)
            {
            	String imageName = "resources/cover4.png";
            	try {
					
            		
            		label.setIcon( new ImageIcon(ImageIO.read( new File(imageName) ) ) );
            		MediaPlayer mediaPlayer = new MediaPlayer(hit);
					mediaPlayer.play();
					
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            public void mousePressed(MouseEvent evt)
            {
            	MediaPlayer mediaPlayer3 = new MediaPlayer(music1);
        		mediaPlayer3.play();
                f.dispose();
                System.exit(0);
                
            }
    	});
        
        
        f.getContentPane().add(label);
        f.setUndecorated(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();

        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setVisible(true);   
 
    }   
        
    
    public static void main(String[] args) throws IOException
    {   
        new MenuGUI();  
    }   
    
 }  

