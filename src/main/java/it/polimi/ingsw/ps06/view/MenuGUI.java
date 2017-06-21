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
		JLabel cover1 = ImageHandler.setImage("resources/cover4.png", 100, 100, width, height);
		JLabel cover2 = ImageHandler.setImage("resources/cover7.png", 100, 100, width, height);
		JLabel cover3 = ImageHandler.setImage("resources/cover6.png", 100, 100, width, height);
        
      //Caricamento suoni del gioco
        String hoverSound = "/menuhover.wav";
        String mediaURL = getClass().getResource(hoverSound).toExternalForm();
		Media hit = new Media(mediaURL);
		
		String selectSound = "/menuselect.wav";
        String mediaURL2 = getClass().getResource(selectSound).toExternalForm();
		Media hit2 = new Media(mediaURL2);
		
		String exitSound = "/exit.wav";
        String mediaURL3 = getClass().getResource(selectSound).toExternalForm();
		Media music1 = new Media(mediaURL3);
		
		JLabel label = new JLabel(cover1.getIcon());
    	
        
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
            	
            	label.setIcon(cover2.getIcon());
				MediaPlayer mediaPlayer = new MediaPlayer(hit);
				mediaPlayer.play();
					
            }
            public void mouseExited(MouseEvent evt)
            {

            	label.setIcon(cover1.getIcon());
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
				     		
            	label.setIcon(cover3.getIcon());
            	MediaPlayer mediaPlayer = new MediaPlayer(hit);
				mediaPlayer.play();

            }
            public void mouseExited(MouseEvent evt)
            {

            	label.setIcon(cover1.getIcon());
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

