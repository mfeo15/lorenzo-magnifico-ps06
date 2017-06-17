package it.polimi.ingsw.ps06.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
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
import javax.swing.JTextField;
import javax.swing.UIManager;

import it.polimi.ingsw.ps06.model.events.EventClose;
import it.polimi.ingsw.ps06.model.events.StoryBoard2BoardAgain;
import it.polimi.ingsw.ps06.model.messages.MessageObtainPersonalBoardStatus;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class PersonalViewGUI extends Observable implements PersonalView {
	private int playerCode;
	private int width, height;
	private JFrame f = new JFrame();
	private JButton exit;
	private JTextField coins, woods, stones, servants, victory, military, faith;
	private Font font;
	private BoardGUI board;
	
    
    private JButton[] territories = new JButton[6];
    private JButton[] buildings = new JButton[6];
	
    private double ratio;
	private int code1, code2, code3, code4;
	private int btCode;
	private Dimension screenSize;

	public PersonalViewGUI(int id, BoardGUI board){
		this.board=board;
		this.playerCode=id;
	}
	
	public int getPlayerCode(){
		return playerCode;
	}
	
	@Override
	public void show() throws IOException{
			
			try {
				UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
			} catch (Exception e) { e.printStackTrace();}
		
			setPersonalView();
			
			exit = new JButton();
			
			screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			
			ratio= (screenSize.getWidth()/screenSize.getHeight());
			
			font = new Font("Lucida Handwriting",Font.PLAIN,(int)(18*(screenSize.getHeight()/1080)));
			
			width = (int)((screenSize.getWidth()*77/100)*(1.349 / ratio));
			height = (int)(screenSize.getHeight()*77/100);
			
			BufferedImage image1 = ImageIO.read(new File("resources/personalView.png")); 
			BufferedImage exit1 = ImageIO.read(new File("resources/button.png")); 
			
			BufferedImage stanza1 = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			
			
			Graphics g1 = stanza1.createGraphics();
	        g1.drawImage(image1, 0, 0, width, height, null);
	        g1.dispose();
	        
	        String hoverSound = "resources/menuhover.wav";
			Media hit = new Media(new File(hoverSound).toURI().toString());
			
			String exitSound = "resources/exit.wav";
			Media music1 = new Media(new File(exitSound).toURI().toString());
			
			JLabel label = new JLabel(new ImageIcon(stanza1)); 
			
			exit = new JButton();
		    exit.setLocation(width*95/100,0);
		    exit.setSize(width*5/100,width*5/100);
		    exit.setOpaque(false);
		    exit.setContentAreaFilled(false);
		    exit.setFocusPainted(false);
		    exit.setBorderPainted(false);
		    exit.setIcon(new ImageIcon(exit1));
	        f.add(exit);
	        
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
	                close();
	            }
	            
	        });
	        
	        coins = new JTextField("10");
	        coins.setLocation((int)(width*11.3/100),(int)(height*87.2/100));
	        coins.setSize(width*4/100,(int)(height*4/100));
	        coins.setOpaque(false);
	        coins.setFont(font);
	        coins.setBorder(javax.swing.BorderFactory.createEmptyBorder());
	        f.add(coins);
	        
	        woods = new JTextField("22");
	        woods.setLocation((int)(width*27.7/100),(int)(height*87.7/100));
	        woods.setSize(width*4/100,(int)(height*4/100));
	        woods.setOpaque(false);
	        woods.setFont(font);
	        woods.setBorder(javax.swing.BorderFactory.createEmptyBorder());
	        f.add(woods);
	        
	        stones = new JTextField("31");
	        stones.setLocation((int)(width*43.3/100),(int)(height*86.5/100));
	        stones.setSize(width*4/100,(int)(height*4/100));
	        stones.setOpaque(false);
	        stones.setFont(font);
	        stones.setBorder(javax.swing.BorderFactory.createEmptyBorder());
	        f.add(stones);
	        
	        servants = new JTextField("44");
	        servants.setLocation((int)(width*58.4/100),(int)(height*92.3/100));
	        servants.setSize(width*4/100,(int)(height*4/100));
	        servants.setOpaque(false);
	        servants.setFont(font);
	        servants.setBorder(javax.swing.BorderFactory.createEmptyBorder());
	        f.add(servants);
	        
	        victory = new JTextField("101");
	        victory.setLocation(width*73/100,(int)(height*85.8/100));
	        victory.setSize(width*4/100,(int)(height*4/100));
	        victory.setOpaque(false);
	        victory.setFont(font);
	        victory.setBorder(javax.swing.BorderFactory.createEmptyBorder());
	        f.add(victory);
	        
	        military = new JTextField("31");
	        military.setLocation((int)(width*81.7/100),(int)(height*85.8/100));
	        military.setSize(width*4/100,(int)(height*4/100));
	        military.setOpaque(false);
	        military.setFont(font);
	        military.setBorder(javax.swing.BorderFactory.createEmptyBorder());
	        f.add(military);
	        
	        faith = new JTextField("5");
	        faith.setLocation((int)(width*90.4/100),(int)(height*85.8/100));
	        faith.setSize(width*4/100,(int)(height*4/100));
	        faith.setOpaque(false);
	        faith.setFont(font);
	        faith.setBorder(javax.swing.BorderFactory.createEmptyBorder());
	        f.add(faith);
	        
	        territories = initializeButtons(territories);
	        buildings = initializeButtons(buildings);

	        territories = setLabels(territories);
	        buildings = setLabels(buildings);
	        
        	territories = locatePersonalCards(territories,false);
        	buildings = locatePersonalCards(buildings,true);
	          
	        
	        JButton[] bonusTile = new JButton[1];
	        bonusTile = initializeButtons(bonusTile);
	        bonusTile=setLabels(bonusTile);
	        bonusTile[0].setLocation((int)(width*0.07/100),(int)(height*4.38/100));
	        bonusTile[0].setSize((int)(width*5.8/100),(int)(height*72.8/100));
	        
	        
	        JLabel bonusTileLabel = new JLabel();       
	        bonusTileLabel = ImageHandler.setImage("resources/tile/pb"+btCode+".png",5.8,73.1,width,height);
	        
	        bonusTile[0].setIcon(bonusTileLabel.getIcon());
			bonusTile[0].setDisabledIcon( bonusTile[0].getIcon() );
			
	        
			for(int j=0; j<territories.length;j++){ f.add(territories[j]); }
			for(int j=0; j<buildings.length;j++){ f.add(buildings[j]); }
			f.add(bonusTile[0]);
			
			
	        f.getContentPane().add(label);
	        
	        if(!(f.isUndecorated())) {f.setUndecorated(true);}
	        
	        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        
	        f.setSize(width, height);
			
	        f.setResizable(false);
	        f.setVisible(true);  
	        
	        f.setLocation((int)((screenSize.getWidth()-f.getWidth())/2), -height);
	        Animations.AnimationPV(f);
	        
	        hasLoaded();
		}
		
		public void setPersonalView(){
			
			code1= 5;
			code2= 7;
			code3= 15;
			code4=19;
			btCode=1;
			
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
		
		private JButton[] locatePersonalCards(JButton[] btns, boolean buildings){
			double x=6.5;
			double y=3;
			
			if(buildings)y=y+40;
			
			for(int j=0;j<btns.length;j++){
				
				btns[j].setLocation((int)(width*x/100),(int)(height*y/100));
				btns[j].setSize((int)(width*15.4/100),height*32/100);
				x=x+15.3;		
			}
			return btns;
		}
		
		public void close(){
			
			//f.setLocation((int)((screenSize.getWidth()-f.getWidth())/2), (int)((screenSize.getHeight()-f.getHeight())/2)- (int)(screenSize.getHeight()/182.4));
			Animations.AnimationPV2(f);
			
			setChanged();
			StoryBoard2BoardAgain sbboard = new StoryBoard2BoardAgain(board);
			notifyObservers(sbboard);	
		}
		
		
		
		@Override
		public void setResources(int coin, int wood, int stone, int servant, int victory, int military, int faith) {
			
			coins.setText(String.valueOf(coin));
			woods.setText(String.valueOf(wood));
			stones.setText(String.valueOf(wood));
			servants.setText(String.valueOf(servant));
			this.victory.setText(String.valueOf(victory));
			this.military.setText(String.valueOf(military));
			this.faith.setText(String.valueOf(faith));
		}

		@Override
		public void setTerritoryCard(int id, int index) {
			try {
				territories[index].setIcon((ImageHandler.setImageScreen("resources/cards/devcards_f_en_c_"+id+".png",9,(int)(13*ratio),width,height)).getIcon());
			} catch (IOException e) {
				e.printStackTrace();}
			
			territories[index].setDisabledIcon( territories[index].getIcon());
		}
		
		@Override
		public void setBuildingCard(int id, int index) {
			try {
				buildings[index].setIcon((ImageHandler.setImageScreen("resources/cards/devcards_f_en_c_"+id+".png",9,(int)(13*ratio),width,height)).getIcon());
			} catch (IOException e) {
				e.printStackTrace();}
			
			buildings[index].setDisabledIcon( buildings[index].getIcon());
			
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
			MessageObtainPersonalBoardStatus obtainPbStatus = new MessageObtainPersonalBoardStatus(playerCode);
			notifyObservers(obtainPbStatus);
		}	
}
