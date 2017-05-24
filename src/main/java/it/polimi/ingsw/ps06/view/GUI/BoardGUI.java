package it.polimi.ingsw.ps06.view.GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.UIManager;

import javafx.scene.media.MediaPlayer;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class BoardGUI extends JFrame {
	
	private int width;
	private int height;
    private JLayeredPane lp = new JLayeredPane();
    private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();
	private CardLayout cl = new CardLayout();
	
	private JButton scrollTowers = new JButton();
	private JButton scrollOthers = new JButton();
	
	private int phase=1;
	private boolean change=true;
	private Direction direction;
	
	private int distance = 9;
	private int runTime = 700;
    private long startTime = -1;
    
    private enum Direction {
		LEFT,
		RIGHT,
		BOTTOM,
		TOP;
	}


	public BoardGUI() throws IOException{
		
		JFrame towers = new JFrame();
		JFrame others = new JFrame();
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		width = (int)(screenSize.getWidth()*75/100*0.75);
		height = (int)(screenSize.getHeight()*75/100);
		
		BufferedImage image1 = ImageIO.read(new File("resources/board1.jpg")); 
		BufferedImage image2 = ImageIO.read(new File("resources/board2.jpg")); 
		
		BufferedImage board1 = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		BufferedImage board2 = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		Graphics g1 = board1.createGraphics();
        g1.drawImage(image1, 0, 0, width, height, null);
        g1.dispose();
        
        Graphics g2 = board2.createGraphics();
        g2.drawImage(image2, 0, 0, width, height, null);
        g2.dispose();
        
        JLabel board1Label = new JLabel(new ImageIcon(board1)); 
        JLabel board2Label = new JLabel(new ImageIcon(board2));
       
        scrollOthers.setLocation(width*35/100,0);
        scrollOthers.setSize(width*30/100,height*7/100);
        scrollOthers.setOpaque(false);
        scrollOthers.setContentAreaFilled(false);
        scrollOthers.setFocusPainted(false);
        //scrollOthers.setBorderPainted(false);
   
        scrollOthers.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent evt)
            {
            	Animation(towers ,others,Direction.TOP);
            }
        });
        
        
        scrollTowers.setLocation(width*35/100,height*93/100);
        scrollTowers.setSize(width*30/100,height*7/100);
        scrollTowers.setOpaque(false);
        scrollTowers.setContentAreaFilled(false);
        scrollTowers.setFocusPainted(false);
        //scrollTowers.setBorderPainted(false);
   
        scrollTowers.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent evt)
            {
            	
            	Animation(others ,towers,Direction.BOTTOM);
            }
        });
        
        
        towers.add(scrollTowers);
        towers.getContentPane().add(board2Label);
        towers.setUndecorated(true);
        towers.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        towers.setSize(width, height);
         
        
        others.add(scrollOthers);
        others.getContentPane().add(board1Label);
        others.setUndecorated(true);
        others.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        others.setSize(width, height);
        
        towers.setResizable(false);
        towers.setLocationRelativeTo(null);
        towers.setVisible(true); 
        
        others.setResizable(false);
        others.setLocationRelativeTo(null);
        others.setVisible(true); 
        
        
	}
	
	private void Animation(JFrame f1, JFrame f2, Direction direction){
		
		EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception ex) {
                }


                Timer timer = new Timer(40, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        if (startTime < 0) {
                            startTime = System.currentTimeMillis();
                        }

                        long now = System.currentTimeMillis();
                        long dif = now - startTime;
                        
                        if (dif > runTime && phase ==1) {
                        	dif=0;
                        	startTime=now;
                        	phase=2;
                        }
                         
                        if (dif > runTime && phase ==2) {
                        	dif=runTime; 
                        	((Timer)e.getSource()).stop();
                        }
                        
                        double progress = ((double)dif / (double)runTime)*distance;

                        Point location = f1.getLocation();
                        Point to = new Point(location);
                        
                        Point location2 = f2.getLocation();
                        
                        if(phase==2 && change){
                        	change=false;
                        	f1.setVisible(true);
                        }

                        if(direction==Direction.TOP){
	                        if(phase==1){
	                        	to.x = f1.getX();
	                            to.y = f1.getY() - (int)Math.round(10 * progress);
	                        }
	                        else{
	                        	to.x = f1.getX();
	                            to.y = f1.getY() + (int)Math.round(10 * progress);
	                        }
	                        
	                        if(to.y>location2.y) f1.setLocation(f2.getLocation());
	                        else f1.setLocation(to);
                        }
                        
                        if(direction==Direction.RIGHT){
	                        if(phase==1){
	                        	to.x = f1.getX() + (int)Math.round(10 * progress);
	                            to.y = f1.getY();
	                        }
	                        else{
	                        	to.x = f1.getX() - (int)Math.round(10 * progress);
	                            to.y = f1.getY();
	                        }
	                        if(to.x<location2.x) f1.setLocation(f2.getLocation());
	                        else f1.setLocation(to);   
                        }
                        
                        if(direction==Direction.BOTTOM){
                        	if(phase==1){
	                        	to.x = f1.getX();
	                            to.y = f1.getY() + (int)Math.round(10 * progress);
	                        }
	                        else{
	                        	to.x = f1.getX();
	                            to.y = f1.getY() - (int)Math.round(10 * progress);
	                        }
	                        
	                        if(to.y<location2.y) f1.setLocation(f2.getLocation());
	                        else f1.setLocation(to);
                        }
                        
                        //f2.setLocation(to);
                    }
                });
                timer.setRepeats(true);
                timer.setCoalesce(true);
                timer.start();
                
            }
            
        });
		
		phase=1;
		change=true;	
		distance = 9;
		runTime = 700;
	    startTime = -1;
    }

	
	public static void main(String[] args) throws IOException
    {   
        new BoardGUI();  
    }   
}
