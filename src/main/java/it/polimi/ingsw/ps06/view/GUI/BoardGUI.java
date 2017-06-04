package it.polimi.ingsw.ps06.view.GUI;

// for Container
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
// for WindowAdapter
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.TransferHandler;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;



@SuppressWarnings("restriction")
public class BoardGUI extends JFrame implements Board {
	
	private int width, escWidth;
	private int height, escHeight;
	private double screenWidth, screenHeight;
    private JLayeredPane lp = new JLayeredPane();
    private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();
	private CardLayout cl = new CardLayout();
	private JLabel leaderBack;
	
	private JButton scrollTowers = new JButton();
	private JButton scrollOthers = new JButton();
	
	private JTextField playerInfo;
	private JTextField roundInfo;
	private JTextField resourcesInfo;
	private JTextField timerInfo = new JTextField("00");
	private JTextField actionsLog = new JTextField();
	
	private JButton escMenu1;
	private JButton escMenu2;
	private Timer timer = createTimer(1000);
	
	private int phase=1;
	private boolean change=true;
	private Direction direction;
	
	private double distance;
	private int runTime = 1000;
    private long startTime = -1;
    private double smooth;
    private boolean ok=true;
    
    private String playerName;
    
    private Font fontBIG, fontMEDIUM, fontSMALL, fontbig;
    private JDesktopPane desktop;
    private JFrame desktopFrame;
    
	JButton[] leaders = new JButton[4];
    
    private JLabel membersLabel[] = new JLabel[4];
    private JButton[] members = new JButton[4];
    private double ratio;
    
    
    private String player="";
    private String playerColor="G";
    private int blackValue;
    private int orangeValue;
    private int whiteValue;
    private int playerNumber;
    private String roundPlayer;
    private int ex1;
    private int ex2;
    private int ex3;
    private int harvestIndex=1, productionIndex=1, councilIndex=0;
    private boolean check = true;
    private int code1, code2, code3, code4;
    private int coinV, woodV, stoneV, servantV;
    int y;
    
    private JFrame personalView;
    
    PersonalViewGUI view=null;
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
		
		
		setBoard();
		
		JFrame escFrame = new JFrame();
		
		desktopFrame = new JFrame();
		
		enableOSXFullscreen(desktopFrame);
		requestOSXFullscreen(desktopFrame);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		ratio= (screenSize.getWidth()/screenSize.getHeight());
		
		screenWidth = screenSize.getWidth();
		screenHeight = screenSize.getHeight();
		
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
		
		fontMEDIUM = new Font("Lucida Handwriting",Font.PLAIN,(int)(15*(screenSize.getHeight()/1080)) );
		fontMEDIUM = new Font("Lucida Handwriting",Font.PLAIN,(int)(25*(screenSize.getHeight()/1080)) );
		fontBIG = new Font("Lucida Handwriting",Font.PLAIN,(int)(40*(screenSize.getHeight()/1080)) );
		fontbig = new Font("Lucida Handwriting",Font.PLAIN,(int)(33*(screenSize.getHeight()/1080)) );
		
		//Caricamento e resize delle immagini
		BufferedImage image1 = ImageIO.read(new File("resources/board1.jpg")); 
		BufferedImage image2 = ImageIO.read(new File("resources/board2.jpg")); 
		BufferedImage image3 = ImageIO.read(new File("resources/stanzaVuota.png")); 
		BufferedImage image4 = ImageIO.read(new File("resources/desktop.jpg")); 
		
		BufferedImage board1 = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		BufferedImage board2 = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		BufferedImage board3 = new BufferedImage(escWidth, escHeight, BufferedImage.TYPE_INT_ARGB);
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
        
        
        JLabel board1Label = new JLabel(new ImageIcon(board1)); 
        JLabel board2Label = new JLabel(new ImageIcon(board2));
        JLabel board3Label = new JLabel(new ImageIcon(board3));
        JLabel pbLabel = new JLabel(new ImageIcon(pbImage));
        
        
        membersLabel[0] = setImage("resources/member/"+playerColor+"N.png",5,7);
        membersLabel[1] = setImage("resources/member/"+playerColor+"A.png",5,7);
        membersLabel[2] = setImage("resources/member/"+playerColor+"B.png",5,7);
        membersLabel[3] = setImage("resources/member/"+playerColor+"E.png",5,7);
        
        
        JLabel dicesLabel[] = new JLabel[3];
        dicesLabel[0] = setImage("resources/dice/N"+blackValue+".png",6,8);
        dicesLabel[1] = setImage("resources/dice/B"+whiteValue+".png",6,8);
        dicesLabel[2] = setImage("resources/dice/A"+orangeValue+".png",6,8);
        
        JLabel playersLabel[] = new JLabel[5];
        
        playersLabel[0] = setImage("resources/player/avatar1.jpg",7,9);
        playersLabel[1] = setImage("resources/player/avatar2.jpg",7,9);
        playersLabel[2] = setImage("resources/player/avatar3.jpg",7,9);
        playersLabel[3] = setImage("resources/player/avatar4.jpg",7,9);
        playersLabel[4] = setImage("resources/player/avatar5.jpg",7,9);
        
        JLabel excommunicationsLabel[] = new JLabel[5];
        
        excommunicationsLabel[0] = setImage("resources/excomm/"+ex1+".png",9,23);
        excommunicationsLabel[1] = setImage("resources/excomm/"+ex2+".png",9,22);
        excommunicationsLabel[2] = setImage("resources/excomm/"+ex3+".png",9,23);
        
        JLabel marketCover1 = setImage("resources/cover/cover1.png",10,12);
        JLabel marketCover2 = setImage("resources/cover/cover3.png",10,12);
        JLabel productionCover = setImage("resources/cover/cover2.png",25,13);
        JLabel harvestCover = setImage("resources/cover/cover4.png",25,15);
        
        JLabel leadersLabel[] = new JLabel[4];
        
        leadersLabel[0] = new JLabel();
        leadersLabel[1] = new JLabel();
        leadersLabel[2] = new JLabel();
        leadersLabel[3] = new JLabel();
        
        leadersLabel[0] = setImageScreen("resources/leader/leader"+code1+".jpg",9,(int)(13.23*ratio));
        leadersLabel[1] = setImageScreen("resources/leader/leader"+code2+".jpg",9,(int)(13.23*ratio));
        leadersLabel[2] = setImageScreen("resources/leader/leader"+code3+".jpg",9,(int)(13.23*ratio));
        leadersLabel[3] = setImageScreen("resources/leader/leader"+code4+".jpg",9,(int)(13.23*ratio));
        
        JLabel leadersLabelFade[] = new JLabel[4];
        
        leadersLabelFade[0] = new JLabel();
        leadersLabelFade[1] = new JLabel();
        leadersLabelFade[2] = new JLabel();
        leadersLabelFade[3] = new JLabel();
        
        leadersLabelFade[0] = setImageScreen("resources/leader/leader"+code1+"fade.png",9,(int)(13.23*ratio));
        leadersLabelFade[1] = setImageScreen("resources/leader/leader"+code2+"fade.png",9,(int)(13.23*ratio));
        leadersLabelFade[2] = setImageScreen("resources/leader/leader"+code3+"fade.png",9,(int)(13.23*ratio));
        leadersLabelFade[3] = setImageScreen("resources/leader/leader"+code4+"fade.png",9,(int)(13.23*ratio));
        
        leaderBack = new JLabel();
        leaderBack = setImageScreen("resources/leader/leaderBack.jpg", 9,(int)(13.23*ratio));
        
        
        
        //JLabel stone = setImage("resources/"+color+",3,3);
        		
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
		
		playerInfo = new JTextField("Turno del giocatore: "+player);
		playerInfo.setLocation((int)(screenSize.getWidth()*3/100),0);
		playerInfo.setSize((int)screenSize.getWidth()*60/100,(int)screenSize.getHeight()*6/100);
		playerInfo.setOpaque(false);
		playerInfo.setEditable(false);
		playerInfo.setBorder(null);
		playerInfo.setFont(fontBIG);
		playerInfo.setForeground(Color.BLACK);
		
		int roundNumber=1;
		int periodNumber=1;
		
		roundInfo = new JTextField("Turno: "+roundNumber+"  Periodo: "+periodNumber);
		roundInfo.setLocation((int)screenSize.getWidth()*60/100,0);
		roundInfo.setSize((int)screenSize.getWidth()*29/100,(int)screenSize.getHeight()*6/100);
		roundInfo.setOpaque(false);
		roundInfo.setEditable(false);
		roundInfo.setBorder(null);
		roundInfo.setFont(fontBIG);
		roundInfo.setForeground(Color.BLACK);
		
		timerInfo = new JTextField();
		timerInfo.setLocation((int)screenSize.getWidth()*89/100,0);
		timerInfo.setSize((int)screenSize.getWidth()*11/100,(int)screenSize.getHeight()*6/100);
		timerInfo.setOpaque(false);
		timerInfo.setEditable(false);
		timerInfo.setBorder(null);
		timerInfo.setFont(fontbig);
		timerInfo.setForeground(Color.BLACK);
		timerInfo.setHorizontalAlignment(JTextField.CENTER);
		
		resourcesInfo = new JTextField(coinV+" Coin   "+woodV+" Wood   "+stoneV+" Wood   "+servantV+" Servant");
		resourcesInfo.setLocation((int)screenSize.getWidth()*30/100,(int)screenSize.getHeight()*96/100);
		resourcesInfo.setSize((int)screenSize.getWidth()*40/100,(int)screenSize.getHeight()*4/100);
		resourcesInfo.setOpaque(false);
		resourcesInfo.setEditable(false);
		resourcesInfo.setBorder(null);
		resourcesInfo.setFont(fontMEDIUM);
		resourcesInfo.setForeground(Color.BLACK);
		resourcesInfo.setHorizontalAlignment(JTextField.CENTER);
		
		actionsLog.setLocation((int)screenSize.getWidth()*25/100,(int)(screenSize.getHeight()*86/100));
		actionsLog.setSize((int)screenSize.getWidth()*50/100,(int)screenSize.getHeight()*4/100);
		actionsLog.setOpaque(false);
		actionsLog.setEditable(false);
		actionsLog.setBorder(null);
		actionsLog.setFont(fontSMALL);
		actionsLog.setForeground(Color.BLACK);
		actionsLog.setHorizontalAlignment(JTextField.CENTER);
		
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
            	            }}, 3000 );}
            });

        
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
            	            }}, 3000 );}
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
        
        //Impostazioni dei bottoni di gioco
    	
    	JButton[] cards = new JButton[16];
    	JButton[] markets = new JButton[4];
    	JButton[] productions = new JButton[9];
    	JButton[] harvests = new JButton[9];
    	JButton[] players = new JButton[5];
    	JButton[] placements = new JButton[16];
    	
    	JButton[] council = new JButton[1];
    	JButton[] production = new JButton[1];
    	JButton[] harvest = new JButton[1];
    	
    	JButton[] councils = new JButton[16];
    	JButton[] dices = new JButton[3];
    	JButton[] orders = new JButton[5];
    	JButton[] playersInfo = new JButton[5];
    	JButton[] excommunications = new JButton[3];
    	JButton[] excommStones = new JButton[12];
        
        
    	members = initializeButtons(members);
        cards = initializeButtons(cards);
        markets = initializeButtons(markets);
        productions = initializeButtons(productions);
        harvests = initializeButtons(harvests);
        players = initializeButtons(players);
        placements = initializeButtons(placements);
        council = initializeButtons(council);
        harvest = initializeButtons(harvest);
        production = initializeButtons(production);
        
        
        councils = initializeButtons(councils);
        dices = initializeButtons(dices);
        orders = initializeButtons(orders);
        playersInfo = initializeButtons(playersInfo);
        excommunications = initializeButtons(excommunications);
        excommStones = initializeButtons(excommStones);
        leaders = initializeButtons(leaders);
        
        
        cards = locateCards(cards);
        members = locateMembers(members);
        dices = locateDices(dices);
        harvests = locateHarvests(harvests);
        productions = locateProductions(productions);
        players = locatePlayers(players);
        placements = locatePlacements(placements);
        
        councils = locateCouncils(councils);
        orders = locateOrders(orders);
        playersInfo = locatePlayersInfo(playersInfo);
        excommunications = locateExcommunications(excommunications);
        excommStones = locateExcommStones(excommStones);
        leaders = locateLeaders(leaders);
        
        
        markets[0].setLocation((int)(width*58/100),(int)(height*61/100));
		markets[0].setSize(width*7/100,height*9/100);
		
		markets[1].setLocation((int)(width*68/100),(int)(height*61/100));
		markets[1].setSize(width*7/100,height*9/100);
		
		markets[2].setLocation((int)(width*76/100),(int)(height*62/100));
		markets[2].setSize(width*11/100,height*14/100);
		
		markets[3].setLocation((int)(width*83.3/100),(int)(height*71.6/100));
		markets[3].setSize((int)(width*11.2/100),(int)(height*15.6/100));  
		
        council[0].setLocation((int)(width*49/100),(int)(height*7/100));
        council[0].setSize(width*29/100,height*15/100);
        council = set(council);

		production[0].setLocation((int)(width*14.5/100),(int)(height*62.5/100));
		production[0].setSize((int)(width*24.3/100),(int)(height*13.5/100));
		production = set(production);
		
		harvest[0].setLocation((int)(width*14.5/100),(int)(height*81/100));
		harvest[0].setSize(width*24/100,height*13/100);
		harvest = set(harvest);

	
        members = set(members);
        markets = set(markets);
        players = set(players);
        productions = set(productions);
        harvests = set(harvests);
        placements = set(placements);
        
        cards = setLabels(cards);
        councils = setLabels(councils);
        dices = setLabels(dices);
        orders = setLabels(orders);
        playersInfo = setLabels(playersInfo);
		excommunications = setLabels(excommunications);
		excommStones = setLabels(excommStones);
		leaders = setLabels(leaders);
        
        members = fillButtons(members,membersLabel);
        dices = fillLabels(dices,dicesLabel);
        excommunications = fillLabels(excommunications, excommunicationsLabel);
        players = fillButtons(players,playersLabel);
        leaders = fillLeaders(leaders,leadersLabel,leadersLabelFade);
        
        
        
        switch(playerNumber){
        
        case 2:
        	players[2].setEnabled(false);
        	
        	harvest[0].setDisabledIcon(harvestCover.getIcon());
        	harvest[0].setIcon(harvestCover.getIcon());
        	harvest[0].setBorderPainted(false);
        	harvest[0].setEnabled(false);
        	
        	production[0].setDisabledIcon(productionCover.getIcon());
        	production[0].setIcon(productionCover.getIcon());
        	production[0].setBorderPainted(false);
        	production[0].setEnabled(false);
        	
        case 3:
        	players[3].setEnabled(false);
        	
        	markets[2].setDisabledIcon(marketCover1.getIcon());
        	markets[2].setIcon(marketCover1.getIcon());
        	markets[2].setBorderPainted(false);
        	markets[2].setEnabled(false);
        	
        	markets[3].setDisabledIcon(marketCover2.getIcon());
        	markets[3].setIcon(marketCover2.getIcon());
        	markets[3].setBorderPainted(false);
        	markets[3].setEnabled(false);
       
        case 4:
        	players[4].setEnabled(false);
        
        case 5:
        	break;
        
        default: 
        	break;
        }
        
        others.add(production[0]);
        others.add(harvest[0]);
        others.add(council[0]);
        
        for(int j=0; j<excommStones.length;j++){ others.add(excommStones[j]); }
        for(int j=0; j<members.length;j++){ desktop.add(members[j]); }
        for(int j=0; j<cards.length;j++){ towers.add(cards[j]); }
        for(int j=0; j<markets.length;j++){ others.add(markets[j]); }
        for(int j=0; j<productions.length;j++){ others.add(productions[j]); }
        for(int j=0; j<harvests.length;j++){ others.add(harvests[j]); }
        for(int j=0; j<councils.length;j++){ others.add(councils[j]); }
        for(int j=0; j<players.length;j++){ desktop.add(players[j]); }
        for(int j=0; j<placements.length;j++){ towers.add(placements[j]); }
             
        for(int j=0; j<dices.length;j++){ others.add(dices[j]); }
        for(int j=0; j<orders.length;j++){ others.add(orders[j]); }
        for(int j=0; j<playersInfo.length;j++){ desktop.add(playersInfo[j]); }
        for(int j=0; j<excommunications.length;j++){ others.add(excommunications[j]); }
        for(int j=0; j<leaders.length;j++){ 
        	desktop.add(leaders[j]); 
        	leaders[j].addMouseListener(new PopClickListener(j));
        }
        
        

        for(y=0; y<players.length;y++)
        {
        	if(players[y].isEnabled()){
	        	players[y].addMouseListener(new MouseAdapter()
	        	{        		
		            public void mousePressed(MouseEvent evt)
		            {
		            	MediaPlayer mediaPlayer3 = new MediaPlayer(hit2);
		        		mediaPlayer3.play();
		        		if(view!=null) view.close();
						try {view = new PersonalViewGUI(y);} catch (IOException e) {e.printStackTrace();}}
	            
	        	});
        	}
        }
        
        
        for(int j=0; j<4;j++){
	        if(members[j].isEnabled()) members[j].setTransferHandler(new ValueExportTransferHandler(Integer.toString(j)));
	
	        members[j].addMouseMotionListener(new MouseAdapter() {
	            @Override
	            public void mouseDragged(MouseEvent e) {
	                JButton button = (JButton) e.getSource();
	                TransferHandler handle = button.getTransferHandler();
	                handle.exportAsDrag(button, e, TransferHandler.COPY);
	            }
	        });
	        
	        if(markets[j].isEnabled()){markets[j].setTransferHandler(new ValueImportTransferHandler());}
        }
        
        if(production[0].isEnabled()){production[0].setTransferHandler(new ValueImportTransferHandler());}
        if(productions[0].isEnabled()){productions[0].setTransferHandler(new ValueImportTransferHandler());}
        if(harvest[0].isEnabled()){harvest[0].setTransferHandler(new ValueImportTransferHandler());}
        if(harvests[0].isEnabled()){harvests[0].setTransferHandler(new ValueImportTransferHandler());}
        if(council[0].isEnabled()){council[0].setTransferHandler(new ValueImportTransferHandler());}
        
        for(int j=0; j<16; j++){
        	if(placements[j].isEnabled()){placements[j].setTransferHandler(new ValueImportTransferHandler());}
        }

        
        java.util.Timer timer = new java.util.Timer();
        timer.schedule(new Repeat(harvest,harvests,1), 0, 5000);
        timer.schedule(new Repeat(production,productions,2), 0, 5000);
        timer.schedule(new Repeat(council,councils,3), 0, 5000);
       
         
        
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
   	 	escFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
         
        desktop.add(actionsLog);
        desktop.add(others);
        desktop.add(towers);
        //desktop.add(personalBoard);
	    desktop.setVisible(true);
	    
	    desktopFrame.add(timerInfo);
	    desktopFrame.add(resourcesInfo);
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
	private void centerJIF(JInternalFrame jif) {
	    
		Dimension desktopSize = desktopFrame.getSize();
	    Dimension jInternalFrameSize = jif.getSize();
	    int width = (desktopSize.width - jInternalFrameSize.width) / 2;
	    //int height = ((desktopSize.height - jInternalFrameSize.height) / 2)-desktopSize.height*8/100;
	    int height=desktopSize.height*12/100;
	    jif.setLocation(width, height);
	    jif.setVisible(true);
	    
	}
	
	private int transfer(JButton[] btns, JButton[] lbs, int currentIndex){
		
		if(btns[0].isEnabled()){
		
			if(btns[0].getIcon()!=null)
	        	{
					lbs[currentIndex].setIcon(btns[0].getIcon());
	        		btns[0].setIcon(null);
	        		lbs[currentIndex].setEnabled(true);
	        		currentIndex++;
	        	}
		}
			return currentIndex;
		
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
	        btn.setBorderPainted(false);
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
	
		
	private JButton[] locateCards(JButton[] btns){
		double x=3.2;
		double y;
		int i=0;
		
		//Ciclo 16 volte diviso per colonne ogni colonna 4 posti
		for(int j=0;j<=3;j++){
			y=5;
			for(int z=0;z<=3;z++){
				
				btns[i].setLocation((int)(width*x/100),(int)(height*y/100));
				btns[i].setSize(width*12/100,height*22/100);
				y=y+24.3;
				i++;
			}	
			x=x+21.9;		
		}
		return btns;
	}
	
	private JButton[] locatePlacements(JButton[] btns){
		double x=15.7;
		double y;
		int i=0;
		
		//Ciclo 16 volte diviso per colonne ogni colonna 4 posti
		for(int j=0;j<=3;j++){
			y=12;
			for(int z=0;z<=3;z++){
				
				btns[i].setLocation((int)(width*x/100),(int)(height*y/100));
				btns[i].setSize((int)(width*7.9/100),height*9/100);
				y=y+24.8;
				i++;
			}	
			x=x+21.9;		
		}
		return btns;
	}
	
	
	private JButton[] locateMembers(JButton[] btns){
		double x=7;
		double y=100;
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		for(int j=0;j<2;j++){
			btns[j].setLocation((int)(width*x/100),(int)(height*y/100));
			btns[j].setSize((int)width*6/100,(int)width*6/100);
			x=x+8;		
		}
		
		x=7;
		y=110;
		
		for(int j=2;j<4;j++){
			btns[j].setLocation((int)(width*x/100),(int)(height*y/100));
			btns[j].setSize((int)width*6/100,(int)width*6/100);
			x=x+8;		
		}
		return btns;
	}
	
	private JButton[] locateExcommStones(JButton[] btns){
		double x=19.3;
		double y=22;
		int i=0;
		
		for(int z=0;z<3;z++){
		if(z==1){y=y+1.5;}
			for(int j=0;j<2;j++){
				btns[i].setLocation((int)(width*x/100),(int)(height*y/100));
				btns[i].setSize(width*2/100,height*2/100);
				i=i+1;
				x=x+2;		
			}
			
			x=x-4;;
			y=y+2;
			
			for(int j=2;j<4;j++){
				btns[i].setLocation((int)(width*x/100),(int)(height*y/100));
				btns[i].setSize(width*2/100,height*2/100);
				i=i+1;
				x=x+2;		
			}
			if(z==1){y=y-1.5;}
			x=x+4.7;
			y=y-2;
		}
		
		return btns;
	}
	
	private JButton[] locateCouncils(JButton[] tfs){
		double x=49;
		double y=7;
		int j;
		
		for(j=0;j<tfs.length/2;j++){
			tfs[j].setLocation((int)(width*x/100),(int)(height*y/100));
			tfs[j].setSize(width*6/100,height*7/100);
			x=x+4;		
		}
		
		x=49;
		y=14;
		
		for(j=tfs.length/2; j<tfs.length;j++){
			tfs[j].setLocation((int)(width*x/100),(int)(height*y/100));
			tfs[j].setSize(width*6/100,height*7/100);
			x=x+4;	
		}
		return tfs;
	}
	
	private JButton[] locateDices(JButton[] tfs){
		double x=55.2;
		double y=84.6;
		
		for(int j=0;j<tfs.length;j++){
			tfs[j].setLocation((int)(width*x/100),(int)(height*y/100));
			tfs[j].setSize(width*8/100,height*10/100);
			x=x+10.6;		
		}
		return tfs;
	}
	
	private JButton[] locateLeaders(JButton[] btns){
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double screenWidth = screenSize.getWidth();
		double screenHeight = screenSize.getHeight();
		
		double y=7.5;
		double x=86;
		
		for(int j=0;j<4;j++){
			btns[j].setLocation((int)(screenWidth*x/100),(int)(screenHeight*y/100));
			btns[j].setSize((int)(screenWidth*8.2/100),(int)(screenHeight*12.05*ratio/100));
			y=y+12.45*ratio;
		}
		
		return btns;
	}
	
	
	
	private JButton[] locateOrders(JButton[] tfs){
		double x=84;
		double y=0.5;
		
		for(int j=0;j<tfs.length;j++){
			tfs[j].setLocation((int)(width*x/100),(int)(height*y/100));
			tfs[j].setSize(width*6/100,height*8/100);
			y=y+8;		
		}
		return tfs;
	}
	
	private JButton[] locateProductions(JButton[] btns){
		boolean first=true;
		double x=15;
		double y=65.8;
		
		if(first){
			btns[0].setLocation((int)(width*3.5/100),(int)(height*65/100));
			btns[0].setSize(width*7/100,height*9/100);
		}
		
		for(int j=1;j<btns.length;j++){
			btns[j].setLocation((int)(width*x/100),(int)(height*y/100));
			btns[j].setSize(width*5/100,height*7/100);
			btns[j].setEnabled(false);
			btns[j].setBorderPainted(false);
			x=x+3;		
		}
		return btns;
	}
	
	private JButton[] locateHarvests(JButton[] btns){
		boolean first=true;
		double x=15;
		double y=84;
		
		if(first){
			btns[0].setLocation((int)(width*3.5/100),(int)(height*83/100));
			btns[0].setSize(width*7/100,height*9/100);
		}
		
		for(int j=1;j<btns.length;j++){
			btns[j].setLocation((int)(width*x/100),(int)(height*y/100));
			btns[j].setSize(width*5/100,height*7/100);
			btns[j].setEnabled(false);
			btns[j].setBorderPainted(false);
			x=x+3;		
		}
		return btns;
	}
	
	private JButton[] locatePlayers(JButton[] btns){
		double x=7;
		double y=14;
		
		
		for(int j=0;j<btns.length;j++){
			btns[j].setLocation((int)(width*x/100),(int)(height*y/100));
			btns[j].setSize(width*7/100,height*9/100);
			y=y+16;		
		}
		return btns;
	}
	
	private JButton[] locateExcommunications(JButton[] tfs){
		
		double x=17.3;
		double y=16;
		
		
		for(int j=0;j<tfs.length;j++){
			tfs[j].setLocation((int)(width*x/100),(int)(height*y/100));
			tfs[j].setSize((int)(width*8.6/100),height*25/100);
			x=x+8.6;		
		}
		y=y+2;
		x=25.9;
		tfs[1].setLocation((int)(width*x/100),(int)(height*y/100));
		return tfs;
	}
	
	
	private JButton[] locatePlayersInfo(JButton[] tfs){
		double x=1;
		double y=24;
		
		
		for(int j=0;j<tfs.length;j++){
			tfs[j].setLocation((int)(width*x/100),(int)(height*y/100));
			tfs[j].setSize(width*20/100,height*3/100);
			y=y+16;	
		}
		return tfs;
	}
	
	
	private JButton[] fillButtons(JButton[] btns, JLabel[] lbs){
		
		for (int j=0;j<btns.length;j++) {
			btns[j].setDisabledIcon( btns[j].getIcon() );
			btns[j].setIcon(lbs[j].getIcon());
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
	
	private JButton[] fillLeaders(JButton[] btns, JLabel[] lbs, JLabel[] lbs2){
		
		for (int j=0;j<btns.length;j++) {
			
			btns[j].setIcon(lbs[j].getIcon());
			btns[j].setDisabledIcon( lbs2[j].getIcon() );
		}
		
		return btns;
	}
	
	private void enable(JButton...btns){
		for (JButton btn : btns) {
	        btn.setEnabled(true);
	    }
	}
	
	private void disable(JButton...btns){
		for (JButton btn : btns) {
	        btn.setEnabled(false);
	    }
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
	
	private JLabel setImageScreen(String s,int xMod, int yMod) throws IOException{
		
		BufferedImage image = ImageIO.read(new File(s)); 
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		BufferedImage board = new BufferedImage((int)(screenSize.getWidth())*xMod/100, (int)(screenSize.getHeight())*yMod/100, BufferedImage.TYPE_INT_ARGB);
		
		Graphics g = board.createGraphics();
        g.drawImage(image, 0, 0, (int)(screenSize.getWidth())*xMod/100, (int)(screenSize.getHeight())*yMod/100, null);
        g.dispose();
        
        JLabel label = new JLabel(new ImageIcon(board)); 
        
        return label;
		
	}
	
	private void fillStones(int index){
		
	}
	
	public static void main(String[] args) throws IOException
    {   
        new BoardGUI();  
    }   


	public static class ValueExportTransferHandler extends TransferHandler {
	
	    public static final DataFlavor SUPPORTED_DATE_FLAVOR = DataFlavor.stringFlavor;
	    private String value;
	
	    public ValueExportTransferHandler(String value) {
	        this.value = value;
	    }
	
	    public String getValue() {
	        return value;
	    }
	
	    @Override
	    public int getSourceActions(JComponent c) {
	        return DnDConstants.ACTION_COPY_OR_MOVE;
	    }
	
	    @Override
	    protected Transferable createTransferable(JComponent c) {
	        Transferable t = new StringSelection(getValue());
	        return t;
	    }
	
	    @Override
	    protected void exportDone(JComponent source, Transferable data, int action) {
	        super.exportDone(source, data, action);
	    }
	
	}

	public class ValueImportTransferHandler extends TransferHandler {
	
	    public final DataFlavor SUPPORTED_DATE_FLAVOR = DataFlavor.stringFlavor;
	
	    public ValueImportTransferHandler() {
	    }
	
	    @Override
	    public boolean canImport(TransferHandler.TransferSupport support) {
	        return support.isDataFlavorSupported(SUPPORTED_DATE_FLAVOR);
	    }
	
	    @Override
	    public boolean importData(TransferHandler.TransferSupport support) {
	        boolean accept = false;
	        if (canImport(support)) {
	            try {
	                Transferable t = support.getTransferable();
	                Object value = t.getTransferData(SUPPORTED_DATE_FLAVOR);
	                if (value instanceof String) {
	                    Component component = support.getComponent();
	                    //Chiedi per un check 
	                    if (check && component instanceof JButton && component.isEnabled() && ((JButton) component).getIcon()==null && members[Integer.parseInt(value.toString())].isEnabled()) {
	                        
	                    	((JButton) component).setDisabledIcon(membersLabel[Integer.parseInt(value.toString())].getIcon());
	                        ((JButton) component).setIcon(membersLabel[Integer.parseInt(value.toString())].getIcon());
	                        members[Integer.parseInt(value.toString())].setEnabled(false);	                       
	                        //cambia testo ad actionslog somehow
	                        
	                        String hoverSound = "resources/place.wav";
	                		Media hit = new Media(new File(hoverSound).toURI().toString());
	                		MediaPlayer mediaPlayer1 = new MediaPlayer(hit);
	        				mediaPlayer1.play();
	                        
	                        
	                        
	                        accept = true;
	                    }
	                }
	            } catch (Exception exp) {
	                exp.printStackTrace();
	            }
	        }
	        return accept;
	    }
	}
	
	class Repeat extends TimerTask {
		JButton big[];
		JButton small[];
		int index;
		
		public Repeat(JButton[] b1, JButton[] b2, int index){
			this.big=b1;
			this.small=b2;
			this.index=index;
		}
		
		public void setLogText(String s){
			actionsLog.setText(s);
		}
		
	    public void run() {
	    	
	    	switch(index){
	    	case 1:
	    		harvestIndex=transfer(big,small,harvestIndex);
	    		break;
	    	case 2:
	    		productionIndex=transfer(big,small,productionIndex);
	    		break;
	    	case 3:
	    		councilIndex=transfer(big,small,councilIndex);
	    		break;
	    	}
	    }
	}
	
	class desktopPopUp extends JPopupMenu {
	    JMenuItem gioca;
	    JMenuItem scarta;
	    JMenuItem attiva;
	    
	    public desktopPopUp(int value){
	    	
	        gioca = new JMenuItem("Gioca!");
	        
	        gioca.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	              leaders[value].setEnabled(true);
	            }
	          });
	        
	        scarta = new JMenuItem("Scarta!");
	        
	        scarta.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	              
	            	leaders[value].disable();
	            	leaders[value].setIcon(null);
	            	
	            	MouseListener[] mouseListeners = leaders[value].getMouseListeners();
	            	for (MouseListener mouseListener : mouseListeners) {
	            		leaders[value].removeMouseListener(mouseListener);
	            	}
	            	
	            }
	          });
	        
	        attiva = new JMenuItem("Attiva!");
	        
	        attiva.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            
	            	if(leaders[value].isEnabled()){
	            		leaders[value].setIcon(leaderBack.getIcon());
	  	              	leaders[value].setDisabledIcon(leaderBack.getIcon());
	            	}

	            }
	          });
	        
	        add(attiva);
	        add(gioca);
	        add(scarta);
	    }
	}
	
	class PopClickListener extends MouseAdapter {
		int value;
		
		public PopClickListener(int value){
			this.value=value;
		}
		
	    public void mousePressed(MouseEvent e){
	        if (e.isPopupTrigger())
	            doPop(e);
	    }

	    public void mouseReleased(MouseEvent e){
	        if (e.isPopupTrigger())
	            doPop(e);
	    }

	    private void doPop(MouseEvent e){
	        desktopPopUp menu = new desktopPopUp(value);
	        menu.show(e.getComponent(), e.getX(), e.getY());
	    }
	}

	public void startTimer(){
		getTimer().start();
	}
	
    private Timer createTimer(int delay) {
        Timer timer = new Timer(delay, new ActionListener(){
            Time counter = new Time(90);
            public void actionPerformed(ActionEvent e) {
                if (counter.getTime() == 0) {
                    ((Timer)e.getSource()).stop();
                    timerInfo.setText("Times up!");
                } else {
                    timerInfo.setText("" + counter.getTime());
                    counter.decTime();
                }
            }
        });
        timer.setInitialDelay(0);
        return timer;
    }

    private Timer getTimer() {
        return timer;
    }

    static class Time {
        int time = 1000;
        public Time(int time) {
            this.time = time;
        }
        void decTime() {
            time--;
        }
        int getTime() {
            return time;
        }
    }
	
    
    public static void enableOSXFullscreen(Window window) {
        try {
            Class util = Class.forName("com.apple.eawt.FullScreenUtilities");
            Class params[] = new Class[]{Window.class, Boolean.TYPE};
            Method method = util.getMethod("setWindowCanFullScreen", params);
            method.invoke(util, window, true);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public static void requestOSXFullscreen(Window window) {
        try {
            Class appClass = Class.forName("com.apple.eawt.Application");
            Class params[] = new Class[]{};

            Method getApplication = appClass.getMethod("getApplication", params);
            Object application = getApplication.invoke(appClass);
            Method requestToggleFulLScreen = application.getClass().getMethod("requestToggleFullScreen", Window.class);

            requestToggleFulLScreen.invoke(application, window);
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            ex.printStackTrace();
        }
    }
    
	private void setBoard(){
	    
		player = "null";
		playerColor="G";
	    blackValue=1;
	    orangeValue=1;
	    whiteValue=1;
	    playerNumber=2;
	    ex1=5;
	    ex2=9;
	    ex3=17;
	    code1=1;
	    code2=9;
	    code3=18;
	    code4=12;
	    coinV=5;
	    woodV=5;
	    stoneV=5;
	    servantV=5;
	    playerName="Gigi Scarfani";
	    roundPlayer="Gigi Scarfani";
	    
        startTimer();

	}
}
