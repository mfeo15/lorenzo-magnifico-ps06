package it.polimi.ingsw.ps06.view;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import it.polimi.ingsw.ps06.model.events.EventClose;
import it.polimi.ingsw.ps06.model.events.StoryBoard2Board;
import it.polimi.ingsw.ps06.model.events.StoryBoard2Room;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class MenuGUI extends Observable implements Menu {
	
	private Image backgroundImage;
	private JButton play;
	private JButton exit;
	private int width;
	private int height;
	private JFrame f;
	
     
	private JFXPanel fxPanel = new JFXPanel();
	 
	@Override
	public void show() throws IOException{
		
		f = new JFrame();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
 
		double ratio= (screenSize.getWidth()/screenSize.getHeight());
		
		width = (int)((screenSize.getWidth()*70/100)*(1.377 / ratio));
		height = (int)(screenSize.getHeight()*70/100);
    	
		
		//Caricamento e resize delle immagini
        BufferedImage image1 = ImageIO.read(new File("resources/cover4.png"));
        BufferedImage image2 = ImageIO.read(new File("resources/cover7.png"));
        BufferedImage image3 = ImageIO.read(new File("resources/cover6.png"));
            
        BufferedImage cover1 = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        BufferedImage cover2 = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        BufferedImage cover3 = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics g1 = cover1.createGraphics();
        g1.drawImage(image1, 0, 0, width, height, null);
        g1.dispose();
        
        Graphics g2 = cover2.createGraphics();
        g2.drawImage(image2, 0, 0, width, height, null);
        g2.dispose();
        
        Graphics g3 = cover3.createGraphics();
        g3.drawImage(image3, 0, 0, width, height, null);
        g3.dispose();
        
        JLabel label = new JLabel(new ImageIcon(cover1)); 
        
        
      //Caricamento suoni del gioco
        String hoverSound = "resources/menuhover.wav";
		Media hit = new Media(new File(hoverSound).toURI().toString());
		
		String selectSound = "resources/menuselect.wav";
		Media hit2 = new Media(new File(selectSound).toURI().toString());
		
		String exitSound = "resources/exit.wav";
		Media music1 = new Media(new File(exitSound).toURI().toString());

		
		/*
		String music = "resources/menuopen.mp3";
		Media music2 = new Media(new File(music).toURI().toString());
		AudioClip mediaPlayer4 = new AudioClip(music2.getSource());
		mediaPlayer4.play(); 
		*/
    	
        
		//Inizializzazione dei componenti
		play = new JButton();
		play.setLocation(width*40/100,height*55/100);
		play.setSize(width*20/100,width*20/100);
        
		play.setOpaque(false);
		play.setContentAreaFilled(false);
		play.setBorderPainted(false);
        
        f.add(play);
        
        play.addMouseListener(new MouseAdapter()
        {
            public void mouseEntered(MouseEvent evt)
            {
            	
            	label.setIcon(new ImageIcon(cover2));
				MediaPlayer mediaPlayer = new MediaPlayer(hit);
				mediaPlayer.play();
					
            }
            public void mouseExited(MouseEvent evt)
            {

            	label.setIcon(new ImageIcon(cover1));
				MediaPlayer mediaPlayer = new MediaPlayer(hit);
				mediaPlayer.play();
            	
            }
            public void mousePressed(MouseEvent evt)
            {
                	
               	MediaPlayer mediaPlayer2 = new MediaPlayer(hit2);
               	mediaPlayer2.play();
               	startGame();
				f.dispose();
					
            }});
        
        exit = new JButton();
        exit.setLocation(width*95/100,0);
        exit.setSize(width*5/100,width*5/100);
        
        exit.setOpaque(false);
        exit.setContentAreaFilled(false);
        exit.setBorderPainted(false);
        
        f.add(exit);
        
        exit.addMouseListener(new MouseAdapter()
        {
            public void mouseEntered(MouseEvent evt)
            {
				     		
            	label.setIcon(new ImageIcon(cover3));
            	MediaPlayer mediaPlayer = new MediaPlayer(hit);
				mediaPlayer.play();

            }
            public void mouseExited(MouseEvent evt)
            {

            	label.setIcon(new ImageIcon(cover1));
            	MediaPlayer mediaPlayer = new MediaPlayer(hit);
				mediaPlayer.play();
					
            }
            public void mousePressed(MouseEvent evt)
            {
            	MediaPlayer mediaPlayer3 = new MediaPlayer(music1);
        		mediaPlayer3.play();
        		notifyExit();
                f.dispose();
                
                
            }
    	});
        
        
        f.getContentPane().add(label);
        f.setUndecorated(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(width, height);

        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setVisible(true);   
 
    }   
        
    
	
    @Override
	public void addNewObserver(Observer o) {
    	addObserver(o);
		
	}



	@Override
	public void startGame() {
		setChanged();
		StoryBoard2Room storyBoard;
		storyBoard = new StoryBoard2Room(new RoomGUI());
		notifyObservers(storyBoard);
		
	}


	@Override
	public void notifyExit() {
		setChanged();
		EventClose close = new EventClose();
		notifyObservers(close);
		
	}

	public MenuGUI() {
		
	}
	

	public static void main(String[] args) throws IOException
    {   
        MenuGUI a = new MenuGUI();  
        a.show();
    } 
    
 }  

