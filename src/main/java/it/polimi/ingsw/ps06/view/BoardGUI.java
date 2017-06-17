package it.polimi.ingsw.ps06.view;

// for Container
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
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
import java.util.Observable;
import java.util.Observer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
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

import it.polimi.ingsw.ps06.model.Types.ColorPalette;
import it.polimi.ingsw.ps06.model.events.BoardHasLoaded;
import it.polimi.ingsw.ps06.model.events.EventClose;
import it.polimi.ingsw.ps06.model.events.EventLeaderActivated;
import it.polimi.ingsw.ps06.model.events.EventLeaderDiscarded;
import it.polimi.ingsw.ps06.model.events.EventLeaderPlayed;
import it.polimi.ingsw.ps06.model.events.EventMemberPlaced;
import it.polimi.ingsw.ps06.model.events.StoryBoard2PersonalView;
import it.polimi.ingsw.ps06.model.events.StoryBoard2Room;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


@SuppressWarnings("restriction")
public class BoardGUI extends Observable implements Board {
	
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
	
	private Direction direction;
    
    private String playerName;
    
    private Font fontBIG, fontMEDIUM, fontSMALL, fontbig;
    private JDesktopPane desktop;
    private JFrame desktopFrame;
    
    private JButton[] cards = new JButton[16];
    private JButton[] markets = new JButton[4];
    private JButton[] productions = new JButton[9];
    private JButton[] harvests = new JButton[9];
    private JButton[] players = new JButton[5];
    private JButton[] placements = new JButton[16];
	
    private JButton[] production = new JButton[1];
    private JButton[] harvest = new JButton[1];
	
    private JButton[] councils = new JButton[16];
    private JButton[] dices = new JButton[3];
    private JButton[] orders = new JButton[5];
    private JTextField[] playersInfo = new JTextField[5];
    private JButton[] excommunications = new JButton[3];
    private JButton[] excommStones = new JButton[12];
	
    private JButton[] privileges = new JButton[5];
    private JButton[] council = new JButton[1];
	private JButton[] leaders = new JButton[4];
	private int[] cardsCodes = new int[16];
    
    private JLabel membersLabel[] = new JLabel[4];
    private JButton[] members = new JButton[4];
    private double ratio;
    
    private JLabel dicesLabel[] = new JLabel[3];
    private JLabel excommunicationsLabel[] = new JLabel[5];
    private JLabel playersLabel[] = new JLabel[5];
    private JLabel leadersLabel[] = new JLabel[4];
    private JLabel leadersLabelFade[] = new JLabel[4];
    
    private JLabel marketCover1;
    private JLabel marketCover2;
    private JLabel productionCover;
    private JLabel harvestCover;
    
    private String[] playersName = new String[5]; 
    
    private JInternalFrame towers;
    private JInternalFrame others;
    
    private boolean allowed = true;
    private boolean[] member = new boolean[4];
    private int playerID=0;
    private String player="";
    private String playerColor="G";
    private int blackValue;
    private int orangeValue;
    private int whiteValue;
    private int playerNumber;
    private int roundNumber;
    private int periodNumber;
    private int ex1, ex2, ex3;
    private int harvestIndex=1, productionIndex=1, councilIndex=0;
    private int lead1, lead2, lead3, lead4;
    private int coinV, woodV, stoneV, servantV;
    private int y;
    private int usedMember;
    
    PersonalViewGUI view= new PersonalViewGUI(0,this);
	private JFXPanel fxPanel = new JFXPanel();
	
    public enum Direction {
		LEFT,
		RIGHT,
		BOTTOM,
		TOP;
	}

    @Override
	public void show() throws IOException{
		
		try {
		    UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
		 } catch (Exception e) { e.printStackTrace();}
		
		setBoard();	
		
		JFrame escFrame = new JFrame();
		
		desktopFrame = new JFrame();
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		ratio= (screenSize.getWidth()/screenSize.getHeight());
		
		screenWidth = screenSize.getWidth();
		screenHeight = screenSize.getHeight();
		
	    //Due frame interni al desktop per la parte delle torri e la sezione rimanente
		towers = new JInternalFrame("frame", false, false, false, false);
	    towers.putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
	    towers.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
	    
	    others = new JInternalFrame("frame", false, false, false, false);
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
	    
	    //Scalabilità delle immagini su vari schermi
		width = (int)((screenSize.getWidth()*75/100)*(1.377 / ratio));
		height = (int)(screenSize.getHeight()*75/100);
		
		escWidth=(int)(width*60/100);
        escHeight=(int)(height*80/100);
	
        fontSMALL = new Font("Lucida Handwriting",Font.PLAIN,(int)(20*(screenSize.getHeight()/1080)) );
		fontMEDIUM = new Font("Lucida Handwriting",Font.PLAIN,(int)(25*(screenSize.getHeight()/1080)) );
		fontBIG = new Font("Lucida Handwriting",Font.PLAIN,(int)(40*(screenSize.getHeight()/1080)) );
		fontbig = new Font("Lucida Handwriting",Font.PLAIN,(int)(33*(screenSize.getHeight()/1080)) );
		
		//Caricamento e resize delle immagini
		BufferedImage image1 = ImageIO.read(new File("resources/board1.jpg")); 
		BufferedImage image2 = ImageIO.read(new File("resources/board2.jpg")); 
		BufferedImage image3 = ImageIO.read(new File("resources/stanzaVuota.png")); 
		BufferedImage image4 = ImageIO.read(new File("resources/desktop.jpg")); 
		
		BufferedImage board1 = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		BufferedImage board2 = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		BufferedImage board3 = new BufferedImage(escWidth, escHeight, BufferedImage.TYPE_INT_ARGB);
		BufferedImage desktopImage = new BufferedImage((int)screenSize.getWidth(), (int)screenSize.getHeight(), BufferedImage.TYPE_INT_ARGB);
	
		
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
        
        getResources();
        
        playersLabel[0] = ImageHandler.setImage("resources/player/avatar1.jpg",7,9,width,height);
        playersLabel[1] = ImageHandler.setImage("resources/player/avatar2.jpg",7,9,width,height);
        playersLabel[2] = ImageHandler.setImage("resources/player/avatar3.jpg",7,9,width,height);
        playersLabel[3] = ImageHandler.setImage("resources/player/avatar4.jpg",7,9,width,height);
        playersLabel[4] = ImageHandler.setImage("resources/player/avatar5.jpg",7,9,width,height);
        
        excommunicationsLabel[0] = ImageHandler.setImage("resources/excomm/"+ex1+".png",9,23,width,height);
        excommunicationsLabel[1] = ImageHandler.setImage("resources/excomm/"+ex2+".png",9,22,width,height);
        excommunicationsLabel[2] = ImageHandler.setImage("resources/excomm/"+ex3+".png",9,23,width,height);
        
        marketCover1 = ImageHandler.setImage("resources/cover/cover1.png",10,12,width,height);
        marketCover2 = ImageHandler.setImage("resources/cover/cover3.png",10,12,width,height);
        productionCover = ImageHandler.setImage("resources/cover/cover2.png",25,13,width,height);
        harvestCover = ImageHandler.setImage("resources/cover/cover4.png",25,15,width,height);
        
        leaderBack = new JLabel();
        leaderBack = ImageHandler.setImageScreen("resources/leader/leaderBack.jpg", 9,(int)(13.23*ratio),width,height);
        
        
        //JLabel stone = ImageHandler.setImage("resources/"+color+",3,3,width,height);
        		
        //Creazione DesktopPane con Background
        desktop = new JDesktopPane(){
			@Override
		    protected void paintComponent(Graphics g) {
		        super.paintComponent(g);
                g.drawImage(desktopImage, 0, 0, null);
		    }};
        
	    
        //Caricamento suoni del gioco
        //String hoverSound = "resources/menuhover.wav";
		//Media hit = new Media(new File(hoverSound).toURI().toString());
		
		String selectSound = "resources/menuselect.wav";
		Media hit2 = new Media(new File(selectSound).toURI().toString());
		
		String exitsound = "resources/exit.wav";
		Media exitSound = new Media(new File(exitsound).toURI().toString());
		
		String slidesound = "resources/slide.mp3";
		Media slideSound = new Media(new File(slidesound).toURI().toString());
		
		String switchsound = "resources/effect1.mp3";
		Media switchSound = new Media(new File(switchsound).toURI().toString());
        
        //Inizializzazione dei componenti
		
		playerInfo = new JTextField();
		playerInfo.setLocation((int)(screenSize.getWidth()*3/100),0);
		playerInfo.setSize((int)screenSize.getWidth()*60/100,(int)screenSize.getHeight()*6/100);
		playerInfo.setOpaque(false);
		playerInfo.setEditable(false);
		playerInfo.setBorder(null);
		playerInfo.setFont(fontBIG);
		playerInfo.setForeground(Color.BLACK);
		
		roundInfo = new JTextField();
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
		actionsLog.setSize((int)screenSize.getWidth()*50/100,(int)screenSize.getHeight()*5/100);
		actionsLog.setOpaque(false);
		actionsLog.setEditable(false);
		actionsLog.setBorder(null);
		actionsLog.setFont(fontSMALL);
		actionsLog.setForeground(Color.BLACK);
		actionsLog.setHorizontalAlignment(JTextField.CENTER);
		
        scrollOthers.setLocation(width*96/100,height*45/100);
        scrollOthers.setSize(width*4/100,height*10/100);
        scrollOthers.setOpaque(false);
        scrollOthers.setContentAreaFilled(false);
        scrollOthers.setFocusPainted(false);
        scrollOthers.setDisabledIcon( scrollOthers.getIcon() );
        scrollOthers.setIcon(ImageHandler.setImage("resources/backRight.png",(int)3.7,(int)(3*ratio),width,height).getIcon());
        //scrollOthers.setBorderPainted(false);
        
        scrollOthers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
            	MediaPlayer mediaPlayer1 = new MediaPlayer(slideSound);
				mediaPlayer1.play();
            	Animations.Animation(others ,towers,Direction.RIGHT);
            	
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

        
        scrollTowers.setLocation(0,height*45/100);
        scrollTowers.setSize(width*4/100,height*10/100);
        scrollTowers.setOpaque(false);
        scrollTowers.setContentAreaFilled(false);
        scrollTowers.setFocusPainted(false);
        scrollTowers.setDisabledIcon( scrollTowers.getIcon() );
        scrollTowers.setIcon(ImageHandler.setImage("resources/backLeft.png",(int)3.7,(int)(3*ratio),width,height).getIcon());
   
        
        scrollTowers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
            	
            	
            	MediaPlayer mediaPlayer2 = new MediaPlayer(slideSound);
				mediaPlayer2.play();
            	Animations.Animation(towers ,others,Direction.LEFT);
            	
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
        		notifyExit();
            }
            
        });
        
        //Impostazioni dei bottoni di gioco        
        
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
        playersInfo = initialize(playersInfo);
        
        
        councils = initializeButtons(councils);
        dices = initializeButtons(dices);
        orders = initializeButtons(orders);
        excommunications = initializeButtons(excommunications);
        excommStones = initializeButtons(excommStones);
        privileges = initializeButtons(privileges);
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
        privileges = locatePrivileges(privileges);
        leaders = locateLeaders(leaders);
        
        for(int j=0; j<members.length; j++){members[j].setDisabledIcon( members[j].getIcon() );}
        
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
        privileges = set(privileges);
        
        cards = setLabels(cards);
        councils = setLabels(councils);
        dices = setLabels(dices);
        orders = setLabels(orders);
		excommunications = setLabels(excommunications);
		excommStones = setLabels(excommStones);
		leaders = setLabels(leaders);
		
		playersInfo = setFont(playersInfo);
        
        dices = fillLabels(dices,dicesLabel);
        excommunications = fillLabels(excommunications, excommunicationsLabel);
        for(int j=0; j<players.length; j++){players[j].setDisabledIcon( players[j].getIcon() );}
        players = fillButtons(players,playersLabel);
        leaders = fillLeaders(leaders,leadersLabel,leadersLabelFade);
        cards = fillCards(cards);
        
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
        for(int j=0; j<privileges.length;j++){ others.add(privileges[j]); }
        for(int j=0; j<leaders.length;j++){ 
        	desktop.add(leaders[j]); 
        	leaders[j].addMouseListener(new PopClickListener(j));
        }
        
        for(int j=0; j<privileges.length;j++){
        	privileges[j].setBorderPainted(false);
        	privileges[j].setEnabled(false);
        }
        
        
        for(int j=0; j<privileges.length; j++){
        	privileges[j].addMouseListener(new MouseAdapter()
            {
                public void mousePressed(MouseEvent evt)
                {
                	for(int j=0; j<privileges.length;j++){
                    	privileges[j].setBorderPainted(false);
                    	privileges[j].setEnabled(false);
                    }	
                }
            });
        }

        if(players[0].isEnabled()){
	       	players[0].addMouseListener(new MouseAdapter()
	       	{        		
	       		public void mousePressed(MouseEvent evt)
		        {
		          	MediaPlayer mediaPlayer3 = new MediaPlayer(hit2);
		      		mediaPlayer3.play();
		       		view.close();
		       		startGame(0);
	            }
        	});
       	}
        
        if(players[1].isEnabled()){
	       	players[1].addMouseListener(new MouseAdapter()
	       	{        		
	       		public void mousePressed(MouseEvent evt)
		        {
		          	MediaPlayer mediaPlayer3 = new MediaPlayer(hit2);
		      		mediaPlayer3.play();
		       		view.close();
		       		startGame(1);
	            }
        	});
       	}
        
        if(players[2].isEnabled()){
	       	players[2].addMouseListener(new MouseAdapter()
	       	{        		
	       		public void mousePressed(MouseEvent evt)
		        {
		          	MediaPlayer mediaPlayer3 = new MediaPlayer(hit2);
		      		mediaPlayer3.play();
		       		view.close();
		       		startGame(2);
	            }
        	});
       	}
        
        if(players[3].isEnabled()){
	       	players[3].addMouseListener(new MouseAdapter()
	       	{        		
	       		public void mousePressed(MouseEvent evt)
		        {
		          	MediaPlayer mediaPlayer3 = new MediaPlayer(hit2);
		      		mediaPlayer3.play();
		       		view.close();
		       		startGame(3);
	            }
        	});
       	}
        
        if(players[4].isEnabled()){
	       	players[4].addMouseListener(new MouseAdapter()
	       	{        		
	       		public void mousePressed(MouseEvent evt)
		        {
		          	MediaPlayer mediaPlayer3 = new MediaPlayer(hit2);
		      		mediaPlayer3.play();
		       		view.close();
		       		startGame(4);
	            }
        	});
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
        
        //setBoardPlayers();
        
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
   	 	
        towers.setResizable(false);
        towers.setVisible(true); 
        
        others.setResizable(false);
        others.setVisible(true); 
        
        personalBoard.setResizable(false);
        personalBoard.setVisible(true); 
         
        desktop.add(actionsLog);
        desktop.add(others);
        desktop.add(towers);
	    desktop.setVisible(true);
	    
		setRound();
	    
	    desktopFrame.add(timerInfo);
	    desktopFrame.add(resourcesInfo);
	    desktopFrame.add(playerInfo);
	    desktopFrame.add(roundInfo);
	    desktopFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
	    desktopFrame.getRootPane().putClientProperty("apple.awt.fullscreenable", Boolean.valueOf(true));
	    desktopFrame.add(desktop);

	    desktopFrame.setLocationRelativeTo(null);
	    desktopFrame.setUndecorated(true);
	    desktopFrame.setVisible(true);
	    
	    enableOSXFullscreen(desktopFrame);
		requestOSXFullscreen(desktopFrame);

		desktopFrame.setSize((int)screenSize.getWidth(), (int)screenSize.getHeight());
		
	    centerJIF(others);
	    centerJIF(towers);
	    
	    scrollTowers.setEnabled(false);
	    scrollOthers.setEnabled(false);
	    
	    new java.util.Timer().schedule( 
    	        new java.util.TimerTask() {
    	            @Override
    	            public void run() {
    	            	scrollTowers.setEnabled(true);
                		scrollOthers.setEnabled(true);
    	            }}, 4000 );
	    
		Animations.startAnimation(towers, others, Direction.RIGHT,towers.getX(),towers.getY() );
	    
	    try {
		    UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
		 } catch (Exception e) { e.printStackTrace();}
	    
	    hasLoaded();
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
	
	private JTextField[] initialize(JTextField...btns){
		
		for (int j=0;j<btns.length;j++) {
	        btns[j]=new JTextField();
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
				btns[i].setSize(width*12/100,(int)(height*21.7/100));
				y=y+24;
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
			
			x=x-4;
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
			first=false;
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
			first=false;
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
	
	private JButton[] locatePrivileges(JButton[] btns){
		double x=58.5;
		double y=25;
		
		for(int j=0;j<btns.length;j++){
			btns[j].setLocation((int)(width*x/100),(int)(height*y/100));
			btns[j].setSize((int)(width*3/100),(int)(height*4.5/100));
			x=x+3.25;		
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
	
	
	private JTextField[] locatePlayersInfo(JTextField[] tfs){
		double x=0.5;
		double y=24;
		
		
		for(int j=0;j<tfs.length;j++){
			tfs[j].setLocation((int)(width*x/100),(int)(height*y/100));
			tfs[j].setSize(width*20/100,height*4/100);
			y=y+16;	
		}
		return tfs;
	}
	
	
	private JButton[] fillButtons(JButton[] btns, JLabel[] lbs){
		
		for (int j=0;j<btns.length;j++) {
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
	
	private JButton[] fillCards(JButton[] btns) throws IOException{
		
		for (int j=0;j<btns.length;j++) {
			
			btns[j].setIcon((ImageHandler.setImage("resources/cards/devcards_f_en_c_"+(cardsCodes[j]+1)+".png",14,(int)( 14.5 * ratio * (1.77 /ratio) ),width,height)).getIcon());
			btns[j].setDisabledIcon( btns[j].getIcon());
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
	
	private void fillStones(int index){
		
	}
	
	public static void main(String[] args) throws IOException
    {   
        (new BoardGUI()).show();  
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
	                                 
	                    if (component instanceof JButton && component.isEnabled() && ((JButton) component).getIcon()==null && members[Integer.parseInt(value.toString())].isEnabled()) {
	                        
		                    notifyAction(identifySpot(component),Integer.parseInt(value.toString()));
	                    	
		                    usedMember= Integer.parseInt(value.toString());
		                    
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
	    
	    public desktopPopUp(int value, boolean allowed){
	    	
	    	if(allowed){
		        gioca = new JMenuItem("Gioca!");
		        
		        gioca.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e) {
		              leaders[value].setEnabled(true);
		            }
	          });
		        
	    	}
	    		
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
	        
	    	if(allowed){
	    		
		        attiva = new JMenuItem("Attiva!");
		        
		        attiva.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e) {
		            
		            	if(leaders[value].isEnabled()){
		            		leaders[value].setIcon(leaderBack.getIcon());
		  	              	leaders[value].setDisabledIcon(leaderBack.getIcon());
		            	}
	
		            }
		          });
	    	}
	        
	        if(allowed) add(attiva);
	        if(allowed) add(gioca);
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
	        desktopPopUp menu = new desktopPopUp(value, allowed);
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
                    timerInfo.setText("" + Integer.toString(counter.getTime()));
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
    
    public it.polimi.ingsw.ps06.model.Types.Action identifySpot(Component c){
    	
    	if( ( (JButton) c) == council[0] ) return it.polimi.ingsw.ps06.model.Types.Action.COUNCIL_SPACE;
    	if( ( (JButton) c) == harvests[0] ) return it.polimi.ingsw.ps06.model.Types.Action.HARVEST_1;
    	if( ( (JButton) c) == harvest[0] ) return it.polimi.ingsw.ps06.model.Types.Action.HARVEST_2;
    	if( ( (JButton) c) == productions[0] ) return it.polimi.ingsw.ps06.model.Types.Action.PRODUCTION_1;
    	if( ( (JButton) c) == production[0] ) return it.polimi.ingsw.ps06.model.Types.Action.PRODUCTION_2;
    	
    	if( ( (JButton) c) == markets[0] ) return it.polimi.ingsw.ps06.model.Types.Action.MARKET_1;
    	if( ( (JButton) c) == markets[1] ) return it.polimi.ingsw.ps06.model.Types.Action.MARKET_2;
    	if( ( (JButton) c) == markets[2] ) return it.polimi.ingsw.ps06.model.Types.Action.MARKET_3;
    	if( ( (JButton) c) == markets[3] ) return it.polimi.ingsw.ps06.model.Types.Action.MARKET_4;
    	
    	if( ( (JButton) c) == placements[0] ) return it.polimi.ingsw.ps06.model.Types.Action.TOWER_GREEN_1;
    	if( ( (JButton) c) == placements[1] ) return it.polimi.ingsw.ps06.model.Types.Action.TOWER_GREEN_2;
    	if( ( (JButton) c) == placements[2] ) return it.polimi.ingsw.ps06.model.Types.Action.TOWER_GREEN_3;
    	if( ( (JButton) c) == placements[3] ) return it.polimi.ingsw.ps06.model.Types.Action.TOWER_GREEN_4;
    	
    	if( ( (JButton) c) == placements[4] ) return it.polimi.ingsw.ps06.model.Types.Action.TOWER_BLUE_1;
    	if( ( (JButton) c) == placements[5] ) return it.polimi.ingsw.ps06.model.Types.Action.TOWER_BLUE_2;
    	if( ( (JButton) c) == placements[6] ) return it.polimi.ingsw.ps06.model.Types.Action.TOWER_BLUE_3;
    	if( ( (JButton) c) == placements[7] ) return it.polimi.ingsw.ps06.model.Types.Action.TOWER_BLUE_4;
    	
    	if( ( (JButton) c) == placements[8] ) return it.polimi.ingsw.ps06.model.Types.Action.TOWER_YELLOW_1;
    	if( ( (JButton) c) == placements[9] ) return it.polimi.ingsw.ps06.model.Types.Action.TOWER_YELLOW_2;
    	if( ( (JButton) c) == placements[10] ) return it.polimi.ingsw.ps06.model.Types.Action.TOWER_YELLOW_3;
    	if( ( (JButton) c) == placements[11] ) return it.polimi.ingsw.ps06.model.Types.Action.TOWER_YELLOW_4;
    	
    	if( ( (JButton) c) == placements[12] ) return it.polimi.ingsw.ps06.model.Types.Action.TOWER_PURPLE_1;
    	if( ( (JButton) c) == placements[13] ) return it.polimi.ingsw.ps06.model.Types.Action.TOWER_PURPLE_2;
    	if( ( (JButton) c) == placements[14] ) return it.polimi.ingsw.ps06.model.Types.Action.TOWER_PURPLE_3;
    	if( ( (JButton) c) == placements[15] ) return it.polimi.ingsw.ps06.model.Types.Action.TOWER_PURPLE_4;
    	
    	return null;
    	
    }
    
    public JButton identifyComponent(it.polimi.ingsw.ps06.model.Types.Action chosenAction){
    	
    	switch(chosenAction){
    	case COUNCIL_SPACE:
    		return council[0];
    	case HARVEST_1:
    		return harvests[0];
    	case HARVEST_2:
    		return harvest[0];
    	case PRODUCTION_1:
    		return productions[0];
    	case PRODUCTION_2:
    		return production[0];
    		
    	case MARKET_1:
    		return markets[0];
    	case MARKET_2:
    		return markets[1];
    	case MARKET_3:
    		return markets[2];
    	case MARKET_4:
    		return markets[3];
    		
    	case TOWER_GREEN_1:
    		return placements[0];
    	case TOWER_GREEN_2:
    		return placements[1];
    	case TOWER_GREEN_3:
    		return placements[2];
    	case TOWER_GREEN_4:
    		return placements[3];
    		
    	case TOWER_BLUE_1:
    		return placements[4];
    	case TOWER_BLUE_2:
    		return placements[5];
    	case TOWER_BLUE_3:
    		return placements[6];
    	case TOWER_BLUE_4:
    		return placements[7];
    		
    	case TOWER_YELLOW_1:
    		return placements[8];
    	case TOWER_YELLOW_2:
    		return placements[9];
    	case TOWER_YELLOW_3:
    		return placements[10];
    	case TOWER_YELLOW_4:
    		return placements[11];

    	case TOWER_PURPLE_1:
    		return placements[12];
    	case TOWER_PURPLE_2:
    		return placements[13];
    	case TOWER_PURPLE_3:
    		return placements[14];
    	case TOWER_PURPLE_4:
    		return placements[15];
    		
    	default:
    		return null;
    		
    		
    	}
    }
    
	@Override
	public void setHarvestIndex(int value) {
		this.harvestIndex=value;
		
	}


	@Override
	public void setProductionIndex(int value) {
		this.productionIndex=value;
		
	}


	@Override
	public void setCouncilIndex(int value) {
		this.councilIndex=value;
		
	}


	@Override
	public void setPeriodRound(int period, int round) {
		this.periodNumber=period;
		this.roundNumber=period;
		
		roundInfo.setText("Turno: "+roundNumber+"  Periodo: "+periodNumber);
		
		startTimer();
		setRound();
		
	}

	

	@Override
	public void setPlayerNumber(int number) {
		this.playerNumber=number;
		
		setBoardPlayers();
	}


	@Override
	public void setPlayerColor(String s) {
		this.playerColor=s;
		
	}


	@Override
	public void setPlayersNames(String s, int index) {
		playersName[index]=s;
		playersInfo[index].setText(s);
		
	}


	@Override
	public void setCurrentPlayerID(int id) {
		
		playerInfo.setText("Turno del giocatore: "+playersName[id]);
		
		if(id != playerID){
			blockedStatus();
		}
		
		if(id == playerID){
			allowedStatus();
		}
		
	}



	@Override
	public void setDices(int black, int white, int orange) {
		this.blackValue=black;
		this.whiteValue=white;
		this.orangeValue=orange;
		
		try {
			refresh();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}


	@Override
	public void setExcommunicationTiles(int code1, int code2, int code3) {
		this.ex1=code1;
		this.ex2=code2;
		this.ex3=code3;
		
	}


	@Override
	public void activateLeaders() {
		leaders = fillLeaders(leaders,leadersLabel,leadersLabelFade);
	}


	@Override
	public void setLeaders(int code1, int code2, int code3, int code4){
		this.lead1=code1;
		this.lead2=code2;
		this.lead3=code3;
		this.lead4=code4;
		
		try {
			refresh();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void setPersonalResources(int coin, int wood, int stone, int servant) {
		
		this.coinV=coin;
		this.woodV=wood;
		this.stoneV=wood;
		this.servantV=servant;
		
		resourcesInfo.setText(coinV+" Coin   "+woodV+" Wood   "+stoneV+" Wood   "+servantV+" Servant");
		
	}

	

	@Override
	public void addMember(it.polimi.ingsw.ps06.model.Types.Action chosenAction, ColorPalette color, int playerIndex) throws IOException {
		
		String pColor="";
		String fullPColor="";
		String mColor="";
		String fullMColor="";
		
		switch(color){
		case DICE_BLACK:
			mColor="N";
			fullMColor="Nero";
			break;
		case DICE_WHITE:
			mColor="B";
			fullMColor="Bianco";
			break;
		case DICE_ORANGE:
			mColor="A";
			fullMColor="Arancione";
			break;
		case UNCOLORED:
			mColor="E";
			fullMColor="Neutro";
			break;
		default:
			break;
		}
		
		switch(playerIndex){
		case 0:
			pColor="R";
			fullPColor="Rosso";
			break;
		case 1:
			pColor="V";
			fullPColor="Verde";
			break;
		case 2:
			pColor="B";
			fullPColor="Blu";
			break;
		case 3:
			pColor="G";
			fullPColor="Giallo";
			break;
		default:
			break;
		}
		
		JButton btn = identifyComponent(chosenAction);
		
		btn.setIcon((ImageHandler.setImage("resources/member/"+pColor+mColor+".png",5,7,width,height)).getIcon());
		
		if((btn)==council[0]){
    		for(int j=0; j<privileges.length; j++){
    			privileges[j].setEnabled(true);
    			privileges[j].setBorderPainted(true);
    		}
    	}
    	
        String hoverSound = "resources/place.wav";
		Media hit = new Media(new File(hoverSound).toURI().toString());
		MediaPlayer mediaPlayer1 = new MediaPlayer(hit);
		mediaPlayer1.play();
		
		if(playerIndex==playerID){
			member[usedMember] = false;
			members[usedMember].setEnabled(false);
			
		}
		
		actionsLog.setText("Il giocatore "+fullPColor+" ha piazzato il familiare "+fullMColor+"!");
	}
	
	@Override
	public void notifyExit() {
		setChanged();
		EventClose close = new EventClose();
		notifyObservers(close);
		
	}


	@Override
	public void notifyAction(it.polimi.ingsw.ps06.model.Types.Action chosenAction, int memberIndex) {
		
		setChanged();
		EventMemberPlaced memberPlaced;
		
		switch(memberIndex){
		case 0:
			memberPlaced = new EventMemberPlaced(chosenAction, ColorPalette.DICE_BLACK);
			break;
		case 1:
			memberPlaced = new EventMemberPlaced(chosenAction, ColorPalette.DICE_WHITE);
			break;
		case 2:
			memberPlaced = new EventMemberPlaced(chosenAction, ColorPalette.DICE_ORANGE);
			break;
		case 3:
			memberPlaced = new EventMemberPlaced(chosenAction, ColorPalette.UNCOLORED);
			break;
		default:
			memberPlaced=null;
		}
		
		notifyObservers(memberPlaced);
		
	}


	@Override
	public void notifyLeaderDiscard(int index) {
		setChanged();
		EventLeaderDiscarded leaderDiscarded;
		leaderDiscarded = new EventLeaderDiscarded(index);
		notifyObservers(leaderDiscarded);
		
	}

	

	@Override
	public void notifyLeaderActivation(int index) {
		setChanged();
		EventLeaderActivated leaderActivated;
		leaderActivated = new EventLeaderActivated(index);
		notifyObservers(leaderActivated);
		
	}


	@Override
	public void notifyLeaderPlacement(int index) {
		setChanged();
		EventLeaderPlayed leaderPlayed;
		leaderPlayed = new EventLeaderPlayed(index);
		notifyObservers(leaderPlayed);
		
	}

	
	@Override
	public void addNewObserver(Observer o) {
		addObserver(o);
		
	}


	@Override
	public void notifyTimesUp() {
		// TODO Auto-generated method stub
		
	}

	
	
	@Override
	public void startGame(int index) {
		view = new PersonalViewGUI(index, this);
		setChanged();
		StoryBoard2PersonalView storyBoard;
		storyBoard = new StoryBoard2PersonalView(view);
		notifyObservers(storyBoard);
	}


	@Override
	public void setCards(int[] cards) {
		this.cardsCodes=cards;
		
		try {
			refresh();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Override
	public void setOwnerPlayerIndex(int index) {
		playerID=index;
		
		switch (index){
		case 0:
			playerColor="R";
			break;
		case 1:
			playerColor="V";
			break;
		case 2:
			playerColor="B";
			break;
		case 3:
			playerColor="G";
			break;
		}
		
		try {
			refresh();
		} catch (IOException e) {
			e.printStackTrace();}
		
	}

	public void refresh() throws IOException{
		getResources();
		
        members = fillButtons(members,membersLabel);
        dices = fillLabels(dices,dicesLabel);
        leaders = fillLeaders(leaders,leadersLabel,leadersLabelFade);
        cards = fillCards(cards);
	}
	
	public void getResources() throws IOException{

        
        dicesLabel[0] = ImageHandler.setImage("resources/dice/N"+blackValue+".png",6,8,width,height);
        dicesLabel[1] = ImageHandler.setImage("resources/dice/B"+whiteValue+".png",6,8,width,height);
        dicesLabel[2] = ImageHandler.setImage("resources/dice/A"+orangeValue+".png",6,8,width,height);
		
        leadersLabel[0] = new JLabel();
        leadersLabel[1] = new JLabel();
        leadersLabel[2] = new JLabel();
        leadersLabel[3] = new JLabel();
        
        leadersLabel[0] = ImageHandler.setImageScreen("resources/leader/leader"+lead1+".jpg",9,(int)(13.23*ratio),width,height);
        leadersLabel[1] = ImageHandler.setImageScreen("resources/leader/leader"+lead2+".jpg",9,(int)(13.23*ratio),width,height);
        leadersLabel[2] = ImageHandler.setImageScreen("resources/leader/leader"+lead3+".jpg",9,(int)(13.23*ratio),width,height);
        leadersLabel[3] = ImageHandler.setImageScreen("resources/leader/leader"+lead4+".jpg",9,(int)(13.23*ratio),width,height);
        
        leadersLabelFade[0] = new JLabel();
        leadersLabelFade[1] = new JLabel();
        leadersLabelFade[2] = new JLabel();
        leadersLabelFade[3] = new JLabel();
        
        leadersLabelFade[0] = ImageHandler.setImageScreen("resources/leader/leader"+lead1+"fade.png",9,(int)(13.23*ratio),width,height);
        leadersLabelFade[1] = ImageHandler.setImageScreen("resources/leader/leader"+lead2+"fade.png",9,(int)(13.23*ratio),width,height);
        leadersLabelFade[2] = ImageHandler.setImageScreen("resources/leader/leader"+lead3+"fade.png",9,(int)(13.23*ratio),width,height);
        leadersLabelFade[3] = ImageHandler.setImageScreen("resources/leader/leader"+lead4+"fade.png",9,(int)(13.23*ratio),width,height);
        
        membersLabel[0] = ImageHandler.setImage("resources/member/"+playerColor+"N.png",5,7,width,height);
        membersLabel[1] = ImageHandler.setImage("resources/member/"+playerColor+"B.png",5,7,width,height);
        membersLabel[2] = ImageHandler.setImage("resources/member/"+playerColor+"A.png",5,7,width,height);
        membersLabel[3] = ImageHandler.setImage("resources/member/"+playerColor+"E.png",5,7,width,height);
        
	}

	private JTextField[] setFont(JTextField[] btns){
		
		for (JTextField btn : btns) {
			btn.setOpaque(false);
			btn.setEditable(false);
			btn.setBorder(null);
			btn.setFont(fontMEDIUM);
			btn.setForeground(Color.BLACK);
			btn.setHorizontalAlignment(JTextField.CENTER);
		}
		
		return btns;
	}
	
	private void setBoardPlayers(){
		
		System.out.println("BOARD SETUP PLAYERS: " + playerNumber);
		
		switch(playerNumber){
		
        case 2:
        	players[2].setEnabled(false);
        	
        	for( MouseListener al : players[2].getMouseListeners() ) {
        	 	players[2].removeMouseListener( al );
        	}
        	
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
        	
        	for( MouseListener al : players[3].getMouseListeners() ) {
        	 	players[3].removeMouseListener( al );
        	}
        	
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
        	
        	for( MouseListener al : players[4].getMouseListeners() ) {
        	 	players[4].removeMouseListener( al );
        	}
        
        default: 
        	break;
        }
		
	}
	
	private void setBoard(){
	   		
		player = "null";
	    blackValue=1;
	    orangeValue=1;
	    whiteValue=1;
		playerColor="G";
	    playerNumber=2;
	    ex1=5;
	    ex2=9;
	    ex3=17;
	    lead1=1;
	    lead2=9;
	    lead3=18;
	    lead4=12;
	    coinV=5;
	    woodV=5;
	    stoneV=5;
	    servantV=5;
	    playerName="Gigi Scarfani";
	    roundNumber=1;
	    periodNumber=1;
	    
	    
	    for(int j=0;j<cardsCodes.length;j++){cardsCodes[j]=j;}
	    
        startTimer();

	}
	
	private void blockedStatus(){
		
		
		allowed=false;
		
		for (int j=0; j<members.length ; j++){
			members[j].setEnabled(false);
		}
	}
	
	private void allowedStatus(){
		
		allowed=true;
		
		if(member[0]) members[0].setEnabled(true);
		if(member[1]) members[1].setEnabled(true);
		if(member[2]) members[2].setEnabled(true);
		if(member[3]) members[3].setEnabled(true);
	}
	
	
	
	@Override
	public void setRound() {

		for (int j=0; j<member.length ; j++){
			member[j] = true;
		}
		
		for(int j=0; j<placements.length ; j++){placements[j].setIcon(null);}
		for(int j=0; j<councils.length ; j++){councils[j].setIcon(null);}
		for(int j=0; j<markets.length ; j++){markets[j].setIcon(null);}
		for(int j=0; j<harvests.length ; j++){harvests[j].setIcon(null);}
		for(int j=0; j<productions.length ; j++){productions[j].setIcon(null);}
		
		setBoardPlayers();
		
	}
	
	@Override
	public void showErrorLog(String s) {
		actionsLog.setText(s);
		
	}

	@Override
	public void hasLoaded() {
		setChanged();
		BoardHasLoaded roomLoaded = new BoardHasLoaded();
		notifyObservers(roomLoaded);
	}
}
