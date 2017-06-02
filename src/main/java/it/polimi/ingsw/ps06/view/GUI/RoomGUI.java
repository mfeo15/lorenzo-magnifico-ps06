package it.polimi.ingsw.ps06.view.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class RoomGUI extends JFrame {
	private JButton exit;
	private JFrame f;
	private JTextField username, stat1, stat2, stat3, stat4, logged, player1, player2, player3, player4;
	private JPasswordField password;
	private JButton login, start, ready;
	private Font font,font2;
	private int width;
	private int height;
		
	public RoomGUI() throws IOException
	{
		exit = new JButton();
		f = new JFrame();
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		double ratio= (screenSize.getWidth()/screenSize.getHeight());
		
		width = (int)((screenSize.getWidth()*70/100)*(1.5 / ratio));
		height = (int)(screenSize.getHeight()*70/100);
		
		font = new Font("Lucida Handwriting",Font.PLAIN,12);
		font2 = new Font("Lucida Handwriting",Font.PLAIN,(int)(18*(screenSize.getHeight()/1080)));
	    
		
		//Caricamento e resize delle immagini
		BufferedImage image1 = ImageIO.read(new File("resources/stanza2.jpg")); 
		BufferedImage exit1 = ImageIO.read(new File("resources/button.png")); 
		
		BufferedImage stanza1 = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		Graphics g1 = stanza1.createGraphics();
        g1.drawImage(image1, 0, 0, width, height, null);
        g1.dispose();
		
		
        //Caricamento suoni del gioco
        String hoverSound = "resources/menuhover.wav";
		Media hit = new Media(new File(hoverSound).toURI().toString());
		
		String selectSound = "resources/menuselect.wav";
		Media hit2 = new Media(new File(selectSound).toURI().toString());
		
		String exitSound = "resources/exit.wav";
		Media music1 = new Media(new File(exitSound).toURI().toString());
		
		String menu = "resources/music1.mp3";
		Media menuMusic = new Media(new File(menu).toURI().toString());
		
		AudioClip mediaPlayer4 = new AudioClip(menuMusic.getSource());
		mediaPlayer4.setVolume(0.3);
       	mediaPlayer4.play();
		
		JLabel label = new JLabel(new ImageIcon(stanza1)); 
		
	        
		//Inizializzazione dei componenti
	    exit = new JButton();
	    exit.setLocation(width*95/100,0);
	    exit.setSize(width*5/100,width*5/100);
	    exit.setOpaque(false);
	    exit.setContentAreaFilled(false);
	    exit.setFocusPainted(false);
	    exit.setBorderPainted(false);
	    exit.setIcon(new ImageIcon(exit1));
        f.add(exit);
       
        
        username = new JTextField();
        username.setLocation(width*29/100,(int)(height*16.6/100));
        username.setSize(width*13/100,(int)(height*3.5/100));
        username.setOpaque(false);
        username.setFont(font);
        f.add(username);
        
        password = new JPasswordField();
        password.setLocation(width*29/100,(int)(height*24/100));
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
        
        logged = new JTextField("Now logged in:");
        logged.setLocation(width*3/100,height*42/100);
        logged.setSize(width*30/100,height*4/100);
        logged.setOpaque(false);
        logged.setEditable(false);
        logged.setBorder(null);
        logged.setFont(font2);
        logged.setForeground(Color.BLACK);
        f.add(logged);
        
        player1 = new JTextField();
        player1.setLocation(width*59/100,height*29/100);
        player1.setSize(width*30/100,height*4/100);
        player1.setOpaque(false);
        player1.setEditable(false);
        //player1.setBorder(null);
        player1.setFont(font2);
        player1.setForeground(Color.BLACK);
        f.add(player1);
        
        player2 = new JTextField();
        player2.setLocation(width*59/100,height*35/100);
        player2.setSize(width*30/100,height*4/100);
        player2.setOpaque(false);
        player2.setEditable(false);
        //player2.setBorder(null);
        player2.setFont(font2);
        player2.setForeground(Color.BLACK);
        f.add(player2);
        
        player3 = new JTextField();
        player3.setLocation(width*59/100,height*41/100);
        player3.setSize(width*30/100,height*4/100);
        player3.setOpaque(false);
        player3.setEditable(false);
        //player3.setBorder(null);
        player3.setFont(font2);
        player3.setForeground(Color.BLACK);
        f.add(player3);
        
        player4 = new JTextField();
        player4.setLocation(width*59/100,height*47/100);
        player4.setSize(width*30/100,height*4/100);
        player4.setOpaque(false);
        player4.setEditable(false);
        //player4.setBorder(null);
        player4.setFont(font2);
        player4.setForeground(Color.BLACK);
        f.add(player4);
        
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
        
        start = new JButton("Avvia");
        start.setLocation(width*76/100,height*77/100);
        start.setSize(width*8/100,width*4/100);
        start.setOpaque(false);
        start.setContentAreaFilled(false);
        start.setFocusPainted(false);
        start.setMargin(new Insets(0,0,0,5));
        start.setForeground(Color.BLACK);
        start.setFont(font2);
        //start.setEnabled(false);
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
                f.dispose();
                System.exit(0);
            }
            
        });
	    
        start.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent evt)
            {
            	MediaPlayer mediaPlayer3 = new MediaPlayer(hit2);
        		mediaPlayer3.play();
        		try {   new BoardGUI();   } catch (IOException e) {e.printStackTrace();}
        		mediaPlayer4.stop();
				f.dispose();
				
            }
            
        });
        
        ready.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent evt)
            {
            	MediaPlayer mediaPlayer3 = new MediaPlayer(hit2);
        		mediaPlayer3.play();
        		
            }
            
        });
        
        login.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent evt)
            {
            	MediaPlayer mediaPlayer3 = new MediaPlayer(hit2);
        		mediaPlayer3.play();
        		
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
		
}
