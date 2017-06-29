package it.polimi.ingsw.ps06.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import it.polimi.ingsw.ps06.model.events.EventClose;
import it.polimi.ingsw.ps06.model.events.RoomHasLoaded;
import it.polimi.ingsw.ps06.model.events.StoryBoard2Board;
import it.polimi.ingsw.ps06.networking.messages.MessageGameStart;
import it.polimi.ingsw.ps06.networking.messages.MessageUser;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class RoomGUI extends Observable implements Room {
	private JButton exit;
	private JFrame f;
	private JTextField username, stat1, stat2, stat3, stat4, logged;
	private JTextField[] player = new JTextField[5];
	private JPasswordField password;
	private JButton login, start;
	private Font font,font2;
	private int width;
	private int height;
	private AudioClip mediaPlayer4;
	private Media hit2;
		
	@Override
	public void show() throws IOException
	{
		try {
		    UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
		 } catch (Exception e) { e.printStackTrace();}
		
		exit = new JButton();
		f = new JFrame();
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		double ratio= (screenSize.getWidth()/screenSize.getHeight());
		
		width = (int)((screenSize.getWidth()*70/100)*(1.500678 / ratio));
		height = (int)(screenSize.getHeight()*70/100);
		
		font = new Font("Lucida Handwriting",Font.PLAIN,(int)(12*(screenSize.getHeight()/1080)));
		font2 = new Font("Lucida Handwriting",Font.PLAIN,(int)(18*(screenSize.getHeight()/1080)));
	    
		
		//Caricamento e resize delle immagini
		JLabel stanza = ImageHandler.setImage("resources/stanza2.jpg", 100, 100, width, height);
		
        //Caricamento suoni del gioco

		String hoverSound = "/menuhover.wav";
        String mediaURL = getClass().getResource(hoverSound).toExternalForm();
		Media hit = new Media(mediaURL);
		
		String selectSound = "/menuselect.wav";
        String mediaURL2 = getClass().getResource(selectSound).toExternalForm();
		hit2 = new Media(mediaURL2);
		
		String exitSound = "/exit.wav";
        String mediaURL3 = getClass().getResource(exitSound).toExternalForm();
		Media music1 = new Media(mediaURL3);
		
		String menu = "/music1.mp3";
        String mediaURL4 = getClass().getResource(menu).toExternalForm();
		Media menuMusic = new Media(mediaURL4);

		
		mediaPlayer4 = new AudioClip(menuMusic.getSource());
		mediaPlayer4.setVolume(0.3);
       	mediaPlayer4.play();
	        
		//Inizializzazione dei componenti
	    exit = new JButton();
	    exit.setLocation(width*95/100,0);
	    exit.setSize(width*5/100,width*5/100);
	    exit.setOpaque(false);
	    exit.setContentAreaFilled(false);
	    exit.setFocusPainted(false);
	    exit.setBorderPainted(false);
	    exit.setIcon((ImageHandler.setImageScreen("resources/button.png",2,(int)(2*ratio),width,height)).getIcon());
        f.add(exit);
       
        
        username = new JTextField();
        username.setLocation(width*29/100,(int)(height*16.2/100));
        username.setSize(width*13/100,(int)(height*3.5/100));
        username.setOpaque(false);
        username.setFont(font);
        f.add(username);
        
        password = new JPasswordField();
        password.setLocation(width*29/100,(int)(height*23.6/100));
        password.setSize(width*13/100,(int)(height*3.5/100));
        password.setOpaque(false);
        f.add(password);
        
        
        stat1 = new JTextField();
        stat1.setLocation(width*28/100,height*64/100);
        stat1.setSize(width*6/100, height*3/100);
        stat1.setOpaque(false);
        stat1.setEditable(false);
        stat1.setBorder(null);
        stat1.setFont(font);
        f.add(stat1);
        
        stat2 = new JTextField();
        stat2.setLocation(width*28/100,(int)(height*70.6/100));
        stat2.setSize(width*6/100, height*3/100);
        stat2.setOpaque(false);
        stat2.setEditable(false);
        stat2.setBorder(null);
        stat2.setFont(font);
        f.add(stat2);
        
        stat3 = new JTextField();
        stat3.setLocation(width*28/100,(int)(height*77.1/100));
        stat3.setSize(width*6/100, height*3/100);
        stat3.setOpaque(false);
        stat3.setEditable(false);
        stat3.setBorder(null);
        stat3.setFont(font);
        f.add(stat3);
        
        stat4 = new JTextField();
        stat4.setLocation(width*28/100,(int)(height*83.6/100));
        stat4.setSize(width*6/100, height*3/100);
        stat4.setOpaque(false);
        stat4.setEditable(false);
        stat4.setBorder(null);
        stat4.setFont(font);
        f.add(stat4);
        
        logged = new JTextField("");
        logged.setLocation(width*3/100,height*42/100);
        logged.setSize(width*30/100,height*4/100);
        logged.setOpaque(false);
        logged.setEditable(false);
        logged.setBorder(null);
        logged.setFont(font2);
        logged.setForeground(Color.BLACK);
        f.add(logged);
        
        player[0] = new JTextField();
        player[0].setLocation(width*59/100,height*29/100);
        player[0].setSize(width*30/100,height*4/100);
        player[0].setOpaque(false);
        player[0].setEditable(false);
        // player[0].setBorder(null);
        player[0].setFont(font2);
        player[0].setForeground(Color.BLACK);
        f.add( player[0]);
        
        player[1] = new JTextField();
        player[1].setLocation(width*59/100,height*35/100);
        player[1].setSize(width*30/100,height*4/100);
        player[1].setOpaque(false);
        player[1].setEditable(false);
        //player[1].setBorder(null);
        player[1].setFont(font2);
        player[1].setForeground(Color.BLACK);
        f.add(player[1]);
        
        player[2] = new JTextField();
        player[2].setLocation(width*59/100,height*41/100);
        player[2].setSize(width*30/100,height*4/100);
        player[2].setOpaque(false);
        player[2].setEditable(false);
        //player[2].setBorder(null);
        player[2].setFont(font2);
        player[2].setForeground(Color.BLACK);
        f.add(player[2]);
        
        player[3] = new JTextField();
        player[3].setLocation(width*59/100,height*47/100);
        player[3].setSize(width*30/100,height*4/100);
        player[3].setOpaque(false);
        player[3].setEditable(false);
        //player[3].setBorder(null);
        player[3].setFont(font2);
        player[3].setForeground(Color.BLACK);
        f.add(player[3]);
        
        player[4] = new JTextField();
        player[4].setLocation(width*59/100,height*53/100);
        player[4].setSize(width*30/100,height*4/100);
        player[4].setOpaque(false);
        player[4].setEditable(false);
        //player[4].setBorder(null);
        player[4].setFont(font2);
        player[4].setForeground(Color.BLACK);
        f.add(player[4]);
        
        login = new JButton("Login");
        login.setLocation(width*31/100,height*32/100);
        login.setSize(width*8/100,width*4/100);
        login.setOpaque(false);
        login.setContentAreaFilled(false);
        login.setFocusPainted(false);
        login.setMargin(new Insets(0,0,0,5));
        login.setForeground(Color.BLACK);
        login.setFont(font2);
        f.add(login);
        
        /*	Work in Progress, please be patient
        ready = new JButton("Pronto");
        ready.setLocation(width*66/100,height*77/100);
        ready.setSize(width*8/100,width*4/100);
        ready.setOpaque(false);
        ready.setContentAreaFilled(false);
        ready.setFocusPainted(false);
        ready.setMargin(new Insets(0,0,0,5));
        ready.setForeground(Color.BLACK);
        ready.setFont(font2);
        f.add(ready);
        */
        
        start = new JButton("Avvia");
        start.setLocation(width*71/100,height*65/100);
        start.setSize(width*8/100,width*4/100);
        start.setOpaque(false);
        start.setContentAreaFilled(false);
        start.setFocusPainted(false);
        start.setMargin(new Insets(0,0,0,5));
        start.setForeground(Color.BLACK);
        start.setFont(font2);
        start.setEnabled(false);
        f.add(start);
        
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
        		notifyExit();
                f.dispose();

            }
            
        });
        
        login.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent evt)
            {
            	MediaPlayer mediaPlayer3 = new MediaPlayer(hit2);
        		mediaPlayer3.play();
        		giveCredentials(username.getText(),String.valueOf(password.getPassword()));
        		if(checkLogin()){logged.setText("Welcome: "+username.getText());}
        		
            }
            
        });
        
	    f.getContentPane().add(stanza);
        f.setUndecorated(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
     
        
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setVisible(true);   
        
        hasLoaded();
	}

	
	@Override
	public void setPlayer(String name, int index) {
		
		player[index].setText(name);
		
		if(index==1 && name!=null){
			MouseListener[] mouseListeners= start.getMouseListeners();
	  		for (MouseListener mouseListener : mouseListeners) {
	  			start.removeMouseListener(mouseListener);
	  		}
		}
		
		if(index==1 && name!=null){
			
			start.setEnabled(true);
			
			start.addMouseListener(new MouseAdapter()
	        {
	            public void mousePressed(MouseEvent evt)
	            {
	            	MediaPlayer mediaPlayer3 = new MediaPlayer(hit2);
	        		mediaPlayer3.play();
	        		startGame();
	        		mediaPlayer4.stop();
					
	            }
	            
	        });
			
		}

		if(index==1 && name.equals("")){
			
			start.setEnabled(false);
			
			for( MouseListener al : start.getMouseListeners() ) {
        	 	start.removeMouseListener( al );
        	}
		}
	}

	@Override
	public void giveCredentials(String username, String password) {
		setChanged();
		MessageUser userMessage;
		
		userMessage = new MessageUser(username);
		notifyObservers(userMessage);
		
	}

	@Override
	public boolean checkLogin() {
		return true;
		
	}

	@Override
	public void startGame() {
		setChanged();		
		MessageGameStart gameStart;
		gameStart = new MessageGameStart();
		notifyObservers(gameStart);
			
	}
	
	@Override
	public void hasStarted(){
		
		mediaPlayer4.stop();
		f.dispose();
		setChanged();
		StoryBoard2Board storyBoard;
		storyBoard = new StoryBoard2Board(new BoardGUI());
		notifyObservers(storyBoard);
	}
	


	@Override
	public void clearPlayers() {
		
		for(int j=0; j<5; j++){
			player[j].setText("");
		}
		
	}


	@Override
	public void addNewObserver(Observer o) {
		addObserver(o);
		
	}
	

	@Override
	public void notifyExit() {
		setChanged();
		EventClose close = new EventClose();
		notifyObservers(close);
		
	}


	@Override
	public void hasLoaded() {
		setChanged();
		RoomHasLoaded roomLoaded = new RoomHasLoaded();
		notifyObservers(roomLoaded);
	}
	
		
}
