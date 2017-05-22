package it.polimi.ingsw.ps06.view.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
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

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class RoomGUI extends JFrame {
	private JButton button;
	private JFrame f;
	private JTextField tf1, tf3, tf4, tf5, tf6, tf7, player1, player2, player3, player4;
	private JPasswordField tf2;
	private JButton b1, b2, b3;
	private Font font,font2;
		
	public RoomGUI() throws IOException
	{
		button = new JButton();
		f = new JFrame();
		
		font = new Font("Lucida Handwriting",Font.PLAIN,12);
		font2 = new Font("Lucida Handwriting",Font.PLAIN,18);
	    
		String path = "resources/stanza2.jpg";
	    File file = new File(path);
	    BufferedImage image = ImageIO.read(file);
	    JLabel label = new JLabel(new ImageIcon(image)); 
	    
	    String bip = "resources/menuhover.wav";
		Media hit = new Media(new File(bip).toURI().toString());
		
		String bip2 = "resources/menuselect.wav";
		Media hit2 = new Media(new File(bip2).toURI().toString());
		
		String exit = "resources/exit.wav";
		Media music1 = new Media(new File(exit).toURI().toString());
		
		
	    
	    
	    button = new JButton();
        button.setLocation(1066,0);
        button.setSize(40,40);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        f.add(button);
        
        tf1 = new JTextField();
        tf1.setLocation(325,121);
        tf1.setSize(135,24);
        tf1.setOpaque(false);
        tf1.setFont(font);
        f.add(tf1);
        
        tf2 = new JPasswordField();
        tf2.setLocation(325,175);
        tf2.setSize(135,24);
        tf2.setOpaque(false);
        f.add(tf2);
        
        
        tf3 = new JTextField();
        tf3.setLocation(300,520);
        tf3.setSize(60,24);
        tf3.setOpaque(false);
        tf3.setEditable(false);
        tf3.setBorder(null);
        tf3.setFont(font);
        f.add(tf3);
        
        tf4 = new JTextField();
        tf4.setLocation(300,568);
        tf4.setSize(60,24);
        tf4.setOpaque(false);
        tf4.setEditable(false);
        tf4.setBorder(null);
        tf4.setFont(font);
        f.add(tf4);
        
        tf5 = new JTextField();
        tf5.setLocation(300,615);
        tf5.setSize(60,24);
        tf5.setOpaque(false);
        tf5.setEditable(false);
        tf5.setBorder(null);
        tf5.setFont(font);
        f.add(tf5);
        
        tf6 = new JTextField();
        tf6.setLocation(300,470);
        tf6.setSize(60,24);
        tf6.setOpaque(false);
        tf6.setEditable(false);
        tf6.setBorder(null);
        tf6.setFont(font);
        f.add(tf6);
        
        tf7 = new JTextField("Now logged in:");
        tf7.setLocation(35,300);
        tf7.setSize(400,30);
        tf7.setOpaque(false);
        tf7.setEditable(false);
        tf7.setBorder(null);
        tf7.setFont(font2);
        tf7.setForeground(Color.BLACK);
        f.add(tf7);
        
        player1 = new JTextField();
        player1.setLocation(650,230);
        player1.setSize(300,30);
        player1.setOpaque(false);
        player1.setEditable(false);
        //player1.setBorder(null);
        player1.setFont(font2);
        player1.setForeground(Color.BLACK);
        f.add(player1);
        
        player2 = new JTextField();
        player2.setLocation(650,280);
        player2.setSize(300,30);
        player2.setOpaque(false);
        player2.setEditable(false);
        //player2.setBorder(null);
        player2.setFont(font2);
        player2.setForeground(Color.BLACK);
        f.add(player2);
        
        player3 = new JTextField();
        player3.setLocation(650,330);
        player3.setSize(300,30);
        player3.setOpaque(false);
        player3.setEditable(false);
        //player3.setBorder(null);
        player3.setFont(font2);
        player3.setForeground(Color.BLACK);
        f.add(player3);
        
        player4 = new JTextField();
        player4.setLocation(650,380);
        player4.setSize(300,30);
        player4.setOpaque(false);
        player4.setEditable(false);
        //player4.setBorder(null);
        player4.setFont(font2);
        player4.setForeground(Color.BLACK);
        f.add(player4);
        
        b1 = new JButton();
        b1.setLocation(342,227);
        b1.setSize(80,40);
        b1.setOpaque(false);
        b1.setContentAreaFilled(false);
        f.add(b1);
        
        b2 = new JButton("Pronto");
        b2.setLocation(720,612);
        b2.setSize(90,50);
        b2.setOpaque(false);
        b2.setContentAreaFilled(false);
        b2.setFocusPainted(false);
        b2.setMargin(new Insets(0,0,0,2));
        b2.setForeground(Color.BLACK);
        b2.setFont(font2);
        f.add(b2);
        
        b3 = new JButton("Avvia");
        b3.setLocation(840,612);
        b3.setSize(90,50);
        b3.setOpaque(false);
        b3.setContentAreaFilled(false);
        b3.setFocusPainted(false);
        b3.setMargin(new Insets(0,0,0,2));
        b3.setForeground(Color.BLACK);
        b3.setFont(font2);
        b3.setEnabled(false);
        f.add(b3);
        
        button.addMouseListener(new MouseAdapter()
        {
            public void mouseEntered(MouseEvent evt)
            {
            	String imageName = "resources/button.png";
            	try {
            		
            		MediaPlayer mediaPlayer = new MediaPlayer(hit);
					mediaPlayer.play();
					button.setIcon( new ImageIcon(ImageIO.read( new File(imageName) ) ) );
			    
            	} catch (IOException e) {e.printStackTrace();}
            }
            
            public void mouseExited(MouseEvent evt)
            {
            	
            	MediaPlayer mediaPlayer = new MediaPlayer(hit);
				mediaPlayer.play();
            	button.setIcon(null);
		        
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
		
}
