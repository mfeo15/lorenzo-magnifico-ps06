package it.polimi.ingsw.ps06.view.GUI;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.awt.*;				// for Container
import java.awt.event.*;			// for WindowAdapter
import javax.swing.*;



@SuppressWarnings("restriction")
public class BoardGUI extends JFrame {
	
	private int width, escWidth;
	private int height, escHeight;
    private JLayeredPane lp = new JLayeredPane();
    private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();
	private CardLayout cl = new CardLayout();
	
	private JButton scrollTowers = new JButton();
	private JButton scrollOthers = new JButton();
	
	private JTextField playerInfo;
	private JTextField roundInfo;
	
	private JButton escMenu1;
	private JButton escMenu2;
	
	private int phase=1;
	private boolean change=true;
	private Direction direction;
	
	private double distance;
	private int runTime = 1000;
    private long startTime = -1;
    private double smooth;
    private boolean ok=true;
    
    private Font fontBIG;
    private JDesktopPane desktop;
    private JFrame desktopFrame;
    
	private JFXPanel fxPanel = new JFXPanel();
    
    private enum Direction {
		LEFT,
		RIGHT,
		BOTTOM,
		TOP;
	}


	public BoardGUI() throws IOException{
		
		try {
		    UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
		 } catch (Exception e) { e.printStackTrace();}
		
		//JFrame towers = new JFrame();
		//JFrame others = new JFrame();
		JFrame escFrame = new JFrame();
		
		desktopFrame = new JFrame();
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double ratio= (screenSize.getWidth()/screenSize.getHeight());
			
		
	    //Due frame interni al desktop per la parte delle torri e la sezione rimanente
	    JInternalFrame towers = new JInternalFrame("frame", false, false, false, false);
	    towers.putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
	    towers.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
	    
	    JInternalFrame others = new JInternalFrame("frame", false, false, false, false);
	    others.putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
	    others.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
	    
	    JInternalFrame personalBoard = new JInternalFrame("frame", false, false, false, false);
	    personalBoard.putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
	    personalBoard.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
	    
	    
	    //Rimuovi la barra per muovere le finestre	    
	    towers.putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
	    towers.setBorder(null);
	    BasicInternalFrameUI bi = (BasicInternalFrameUI)towers.getUI();
	    bi.setNorthPane(null);
	    
	    others.putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
	    others.setBorder(null);
	    BasicInternalFrameUI bi2 = (BasicInternalFrameUI)others.getUI();
	    bi2.setNorthPane(null);
	    
	    personalBoard.putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
	    personalBoard.setBorder(null);
	    BasicInternalFrameUI bi3 = (BasicInternalFrameUI)personalBoard.getUI();
	    bi3.setNorthPane(null);
	    
	    
	    //Scalabilità delle immagini su vari schermi
		width = (int)((screenSize.getWidth()*75/100)*(1.377 / ratio));
		height = (int)(screenSize.getHeight()*75/100);
		
		escWidth=(int)(width*60/100);
        escHeight=(int)(height*80/100);
		
		distance = 9 * ((screenSize.getHeight()/1080)*2);
		smooth = 10 * ((screenSize.getHeight()/1080));
		
		fontBIG = new Font("Lucida Handwriting",Font.PLAIN,(int)(40*(screenSize.getHeight()/1080)) );
		
		//Caricamento e resize delle immagini
		BufferedImage image1 = ImageIO.read(new File("resources/board1.jpg")); 
		BufferedImage image2 = ImageIO.read(new File("resources/board2.jpg")); 
		BufferedImage image3 = ImageIO.read(new File("resources/stanzaVuota.png")); 
		BufferedImage image4 = ImageIO.read(new File("resources/desktop.jpg")); 
		BufferedImage image5 = ImageIO.read(new File("resources/personalboard2.png")); 
		
		BufferedImage board1 = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		BufferedImage board2 = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		BufferedImage board3 = new BufferedImage(escWidth, escHeight, BufferedImage.TYPE_INT_RGB);
		BufferedImage desktopImage = new BufferedImage((int)screenSize.getWidth(), (int)screenSize.getHeight(), BufferedImage.TYPE_INT_RGB);
		BufferedImage pbImage = new BufferedImage( (int)(screenSize.getWidth()), (int)(screenSize.getHeight()*0.18), BufferedImage.TYPE_INT_RGB);
		
		Graphics g1 = board1.createGraphics();
        g1.drawImage(image1, 0, 0, width, height, null);
        g1.dispose();
        
        Graphics g2 = board2.createGraphics();
        g2.drawImage(image2, 0, 0, width, height, null);
        g2.dispose();
        
        Graphics g3 = board3.createGraphics();
        g3.drawImage(image3, 0, 0, escWidth, escHeight, null);
        g3.dispose();
        
        Graphics g4 = desktopImage.createGraphics();
        g4.drawImage(image4, 0, 0, (int)screenSize.getWidth(), (int)screenSize.getHeight(), null);
        g4.dispose();
        
        Graphics g5 = pbImage.createGraphics();
        g5.drawImage(image5, 0, 0, (int)screenSize.getWidth(), (int)(screenSize.getHeight()*0.18), null);
        g5.dispose();
        
        JLabel board1Label = new JLabel(new ImageIcon(board1)); 
        JLabel board2Label = new JLabel(new ImageIcon(board2));
        JLabel board3Label = new JLabel(new ImageIcon(board3));
        JLabel pbLabel = new JLabel(new ImageIcon(pbImage));
        
        
        //Creazione DesktopPane con Background
        desktop = new JDesktopPane(){
			@Override
		    protected void paintComponent(Graphics g) {
		        super.paintComponent(g);
                g.drawImage(desktopImage, 0, 0, null);
		    }};
	    setContentPane(desktop);
        
	    
        //Caricamento suoni del gioco
        String hoverSound = "resources/menuhover.wav";
		Media hit = new Media(new File(hoverSound).toURI().toString());
		
		String selectSound = "resources/menuselect.wav";
		Media hit2 = new Media(new File(selectSound).toURI().toString());
		
		String exitsound = "resources/exit.wav";
		Media exitSound = new Media(new File(exitsound).toURI().toString());
		
		String slidesound = "resources/slide.mp3";
		Media slideSound = new Media(new File(slidesound).toURI().toString());
		
		String switchsound = "resources/effect1.mp3";
		Media switchSound = new Media(new File(switchsound).toURI().toString());
        
          
 
        //Inizializzazione dei componenti
		String player="";
		
		playerInfo = new JTextField("  Turno del giocatore: "+player);
		playerInfo.setLocation(0,0);
		playerInfo.setSize((int)screenSize.getWidth()*70/100,(int)screenSize.getHeight()*6/100);
		playerInfo.setOpaque(false);
		playerInfo.setEditable(false);
		playerInfo.setBorder(null);
		playerInfo.setFont(fontBIG);
		playerInfo.setForeground(Color.BLACK);
		
		int roundNumber=1;
		int periodNumber=1;
		
		roundInfo = new JTextField("Turno: "+roundNumber+"  Periodo: "+periodNumber);
		roundInfo.setLocation((int)screenSize.getWidth()*70/100,0);
		roundInfo.setSize((int)screenSize.getWidth()*30/100,(int)screenSize.getHeight()*6/100);
		roundInfo.setOpaque(false);
		roundInfo.setEditable(false);
		roundInfo.setBorder(null);
		roundInfo.setFont(fontBIG);
		roundInfo.setForeground(Color.BLACK);
		
        scrollOthers.setLocation(width*95/100,height*35/100);
        scrollOthers.setSize(width*5/100,height*30/100);
        scrollOthers.setOpaque(false);
        scrollOthers.setContentAreaFilled(false);
        scrollOthers.setFocusPainted(false);
        //scrollOthers.setBorderPainted(false);
        
        scrollOthers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
            	MediaPlayer mediaPlayer1 = new MediaPlayer(slideSound);
				mediaPlayer1.play();
            	Animation(others ,towers,Direction.RIGHT);
            	
            	scrollTowers.setEnabled(false);
            	scrollOthers.setEnabled(false);
            	
            	new java.util.Timer().schedule( 
            	        new java.util.TimerTask() {
            	            @Override
            	            public void run() {
            	            	scrollTowers.setEnabled(true);
                        		scrollOthers.setEnabled(true);
            	            }
            	        }, 
            	        2000 
            	);
            }
          }
        );

        
        scrollTowers.setLocation(0,height*35/100);
        scrollTowers.setSize(width*5/100,height*30/100);
        scrollTowers.setOpaque(false);
        scrollTowers.setContentAreaFilled(false);
        scrollTowers.setFocusPainted(false);
        //scrollTowers.setBorderPainted(false);
   
        
        scrollTowers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
            	
            	
            	MediaPlayer mediaPlayer2 = new MediaPlayer(slideSound);
				mediaPlayer2.play();
            	Animation(towers ,others,Direction.LEFT);
            	
            	scrollTowers.setEnabled(false);
            	scrollOthers.setEnabled(false);
            	
            	new java.util.Timer().schedule( 
            	        new java.util.TimerTask() {
            	            @Override
            	            public void run() {
            	            	scrollTowers.setEnabled(true);
                        		scrollOthers.setEnabled(true);
            	            }
            	        }, 
            	        2000 
            	);
            }
          });
            
        
        
        escMenu1 = new JButton("Ritorna al gioco");
        escMenu1.setLocation(escWidth*20/100,escHeight*20/100);
        escMenu1.setSize(escWidth*60/100,escHeight*10/100);
        escMenu1.setOpaque(false);
        escMenu1.setContentAreaFilled(false);
        escMenu1.setFocusPainted(false);
        escMenu1.setMargin(new Insets(0,0,0,5));
        escMenu1.setForeground(Color.BLACK);
        escMenu1.setFont(fontBIG);
        escFrame.add(escMenu1);
        
        escMenu2 = new JButton("Esci dal gioco");
        escMenu2.setLocation(escWidth*20/100,escHeight*40/100);
        escMenu2.setSize(escWidth*60/100,escHeight*10/100);
        escMenu2.setOpaque(false);
        escMenu2.setContentAreaFilled(false);
        escMenu2.setFocusPainted(false);
        escMenu2.setMargin(new Insets(0,0,0,5));
        escMenu2.setForeground(Color.BLACK);
        escMenu2.setFont(fontBIG);
        escFrame.add(escMenu2);

        escMenu1.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent evt)
            {
            	MediaPlayer mediaPlayer3 = new MediaPlayer(hit2);
        		mediaPlayer3.play();
				escFrame.dispose();
				
            }
            
        });
        
        escMenu2.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent evt)
            {
            	
            	MediaPlayer mediaPlayer6 = new MediaPlayer(exitSound);
        		mediaPlayer6.play();
        		System.exit(0);
				
            }
            
        });
        
        
        //KeyBinding per tasto ESC
        Action esc = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {

            	MediaPlayer mediaPlayer4 = new MediaPlayer(switchSound);
				mediaPlayer4.play();
            	escFrame.setVisible(true);}};
        
            	
        Action escClose = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {

            	MediaPlayer mediaPlayer5 = new MediaPlayer(switchSound);
				mediaPlayer5.play();
            	escFrame.dispose();}};
            	
        KeyStroke stroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0); 
        
        JPanel towerPanel = (JPanel) towers.getContentPane();
        InputMap inputMap = towerPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(stroke, "OPEN");
        towerPanel.getActionMap().put("OPEN", esc);    
        
        JPanel otherPanel = (JPanel) others.getContentPane();
        InputMap inputMap2 = otherPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap2.put(stroke, "OPEN");
        otherPanel.getActionMap().put("OPEN", esc);
        
        JPanel pbPanel = (JPanel) personalBoard.getContentPane();
        InputMap inputMap4 = pbPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap4.put(stroke, "OPEN");
        pbPanel.getActionMap().put("OPEN", esc);
        
        JPanel escPanel = (JPanel) escFrame.getContentPane();
        InputMap inputMap3 = escPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap3.put(stroke, "OPEN");
        escPanel.getActionMap().put("OPEN", escClose); 
        
        JPanel desktopPanel = (JPanel) desktopFrame.getContentPane();
        InputMap inputMap5 = desktopPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap5.put(stroke, "OPEN");
        desktopPanel.getActionMap().put("OPEN", esc);
        
        
        
        //Inizializzazione dei Frame 
        escFrame.getContentPane().add(board3Label);
    	escFrame.setUndecorated(true);
   	 	escFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   	 	escFrame.setSize(escWidth, escHeight);
   	 	escFrame.setResizable(false);
   	 	escFrame.setLocationRelativeTo(null);
        
        
        towers.add(scrollTowers);
        towers.getContentPane().add(board2Label);
        towers.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        towers.setSize(width, height);
          
        
        others.add(scrollOthers);
        others.getContentPane().add(board1Label);
        others.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        others.setSize(width, height);
        
       
        personalBoard.getContentPane().add(pbLabel);
        personalBoard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        personalBoard.setSize((int)screenSize.getWidth(), (int)(screenSize.getHeight()*18/100));
        personalBoard.setLocation(0, (int)screenSize.getHeight()*82/100);
        
        
        towers.setResizable(false);
        towers.setVisible(true); 
        
        others.setResizable(false);
        others.setVisible(true); 
        
        personalBoard.setResizable(false);
        personalBoard.setVisible(true); 
          
        
        desktop.add(others);
        desktop.add(towers);
        desktop.add(personalBoard);
	    desktop.setVisible(true);

	    
	    desktopFrame.add(playerInfo);
	    desktopFrame.add(roundInfo);
	    desktopFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
	    desktopFrame.getRootPane().putClientProperty("apple.awt.fullscreenable", Boolean.valueOf(true));
	    desktopFrame.add(desktop);
	    desktopFrame.setSize((int)screenSize.getWidth(), (int)screenSize.getHeight());
	    desktopFrame.setLocationRelativeTo(null);
	    desktopFrame.setUndecorated(true);
	    desktopFrame.setVisible(true);
	    
	    centerJIF(others);
	    centerJIF(towers);
	    
	    

	}
	
	
	/**
	 * Animazione tra due frame in base alla direzione scelta
	 * 
	 * @param f1
	 * @param f2
	 * @param direction
	 */
	private void Animation(JInternalFrame f1, JInternalFrame f2, Direction direction){
		
		EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception ex) {
                }

                ok = false;
                
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
                        	f2.toFront();
                        }

                        if(direction==Direction.TOP){
	                        if(phase==1){
	                        	to.x = f1.getX();
	                            to.y = f1.getY() - (int)Math.round(smooth * progress);
	                        }
	                        else{
	                        	to.x = f1.getX();
	                            to.y = f1.getY() + (int)Math.round(smooth * progress);
	                        }
	                        
	                        if(to.y>location2.y) f1.setLocation(f2.getLocation());
	                        else f1.setLocation(to);
                        }
                        
                        if(direction==Direction.RIGHT){
	                        if(phase==1){
	                        	to.x = f1.getX() + (int)Math.round(smooth * progress);
	                            to.y = f1.getY();
	                        }
	                        else{
	                        	to.x = f1.getX() - (int)Math.round(smooth * progress);
	                            to.y = f1.getY();
	                        }
	                        if(to.x<location2.x) f1.setLocation(f2.getLocation());
	                        else f1.setLocation(to);   
	                        
                        }
                        
                        if(direction==Direction.LEFT){
	                        if(phase==1){
	                        	to.x = f1.getX() - (int)Math.round(smooth * progress);
	                            to.y = f1.getY();
	                        }
	                        else{
	                        	to.x = f1.getX() + (int)Math.round(smooth * progress);
	                            to.y = f1.getY();
	                        }
	                        if(to.x>location2.x) f1.setLocation(f2.getLocation());
	                        else f1.setLocation(to);   
                        }
                        
                        if(direction==Direction.BOTTOM){
                        	if(phase==1){
	                        	to.x = f1.getX();
	                            to.y = f1.getY() + (int)Math.round(smooth * progress);
	                        }
	                        else{
	                        	to.x = f1.getX();
	                            to.y = f1.getY() - (int)Math.round(smooth * progress);
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
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		phase=1;
		change=true;	
		distance = 9;
		runTime = 1000;
	    startTime = -1;
	    
    }

	/**
	 * Metodo per centrare un JInternalFrame generico
	 * 
	 * @param jif
	 */
	public void centerJIF(JInternalFrame jif) {
	    
		Dimension desktopSize = desktopFrame.getSize();
	    Dimension jInternalFrameSize = jif.getSize();
	    int width = (desktopSize.width - jInternalFrameSize.width) / 2;
	    //int height = ((desktopSize.height - jInternalFrameSize.height) / 2)-desktopSize.height*8/100;
	    int height=desktopSize.height*6/100;
	    jif.setLocation(width, height);
	    jif.setVisible(true);
	    
	}
	
	public static void main(String[] args) throws IOException
    {   
        new BoardGUI();  
    }   
}
