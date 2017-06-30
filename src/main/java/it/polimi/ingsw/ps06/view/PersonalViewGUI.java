package it.polimi.ingsw.ps06.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class PersonalViewGUI  {
	private int playerCode;
	private int width, height;
	private JFrame f = new JFrame();
	private JButton exit;
	private JTextField coins, woods, stones, servants, victory, military, faith;
	private Font font;
	private BoardGUI boardView;
	private JLabel bonusTileLabel = new JLabel(); 
	private JButton[] bonusTile = new JButton[1];
    
    private JButton[] territories = new JButton[6];
    private JButton[] buildings = new JButton[6];
	
    private double ratio;
	private int btCode=1;
	private Dimension screenSize;

	public PersonalViewGUI(int id, BoardGUI v){
		this.boardView = v;
		this.playerCode=id;
	}
	
	public int getPlayerCode(){
		return playerCode;
	}
	
	public void show() throws IOException{
			
			try {
				UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
			} catch (Exception e) { e.printStackTrace();}
		
			exit = new JButton();
			
			screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			
			ratio= (screenSize.getWidth()/screenSize.getHeight());
			
			font = new Font("Lucida Handwriting",Font.PLAIN,(int)(18*(screenSize.getHeight()/1080)));
			
			width = (int)((screenSize.getWidth()*77/100)*(1.349 / ratio));
			height = (int)(screenSize.getHeight()*77/100);
			
			JLabel stanza = ImageHandler.setImage("resources/personalView.png", 100, 100, width, height);
	        
			String hoverSound = "/menuhover.wav";
	        String mediaURL = getClass().getResource(hoverSound).toExternalForm();
			Media hit = new Media(mediaURL);
			
			String exitSound = "/exit.wav";
	        String mediaURL3 = getClass().getResource(exitSound).toExternalForm();
			Media music1 = new Media(mediaURL3);
			
			exit = new JButton();
		    exit.setLocation(width*95/100,0);
		    exit.setSize(width*5/100,width*5/100);
		    exit.setOpaque(false);
		    exit.setContentAreaFilled(false);
		    exit.setFocusPainted(false);
		    exit.setBorderPainted(false);
		    exit.setIcon((ImageHandler.setImageScreen("resources/button.png",2,(int)(2*ratio),width,height)).getIcon());
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
	        coins.setEditable(false);
	        coins.setBorder(javax.swing.BorderFactory.createEmptyBorder());
	        f.add(coins);
	        
	        woods = new JTextField("22");
	        woods.setLocation((int)(width*27.7/100),(int)(height*87.7/100));
	        woods.setSize(width*4/100,(int)(height*4/100));
	        woods.setOpaque(false);
	        woods.setFont(font);
	        woods.setEditable(false);
	        woods.setBorder(javax.swing.BorderFactory.createEmptyBorder());
	        f.add(woods);
	        
	        stones = new JTextField("31");
	        stones.setLocation((int)(width*43.3/100),(int)(height*86.5/100));
	        stones.setSize(width*4/100,(int)(height*4/100));
	        stones.setOpaque(false);
	        stones.setFont(font);
	        stones.setEditable(false);
	        stones.setBorder(javax.swing.BorderFactory.createEmptyBorder());
	        f.add(stones);
	        
	        servants = new JTextField("44");
	        servants.setLocation((int)(width*58.4/100),(int)(height*92.3/100));
	        servants.setSize(width*4/100,(int)(height*4/100));
	        servants.setOpaque(false);
	        servants.setFont(font);
	        servants.setEditable(false);
	        servants.setBorder(javax.swing.BorderFactory.createEmptyBorder());
	        f.add(servants);
	        
	        victory = new JTextField("101");
	        victory.setLocation(width*73/100,(int)(height*85.8/100));
	        victory.setSize(width*4/100,(int)(height*4/100));
	        victory.setOpaque(false);
	        victory.setFont(font);
	        victory.setEditable(false);
	        victory.setBorder(javax.swing.BorderFactory.createEmptyBorder());
	        f.add(victory);
	        
	        military = new JTextField("31");
	        military.setLocation((int)(width*81.7/100),(int)(height*85.8/100));
	        military.setSize(width*4/100,(int)(height*4/100));
	        military.setOpaque(false);
	        military.setFont(font);
	        military.setEditable(false);
	        military.setBorder(javax.swing.BorderFactory.createEmptyBorder());
	        f.add(military);
	        
	        faith = new JTextField("5");
	        faith.setLocation((int)(width*90.4/100),(int)(height*85.8/100));
	        faith.setSize(width*4/100,(int)(height*4/100));
	        faith.setOpaque(false);
	        faith.setFont(font);
	        faith.setEditable(false);
	        faith.setBorder(javax.swing.BorderFactory.createEmptyBorder());
	        f.add(faith);
	        
	        territories = initializeButtons(territories);
	        buildings = initializeButtons(buildings);

	        territories = setLabels(territories);
	        buildings = setLabels(buildings);
	        
	        buildings = locatePersonalCards(buildings,false);
        	territories = locatePersonalCards(territories,true);
	        
	        bonusTile = initializeButtons(bonusTile);
	        bonusTile=setLabels(bonusTile);
	        bonusTile[0].setLocation((int)(width*0.07/100),(int)(height*4.38/100));
	        bonusTile[0].setSize((int)(width*5.8/100),(int)(height*72.8/100));
	        
			for(int j=0; j<territories.length;j++){ f.add(territories[j]); }
			for(int j=0; j<buildings.length;j++){ f.add(buildings[j]); }
			f.add(bonusTile[0]);
			
	        f.getContentPane().add(stanza);
	        
	        if(!(f.isUndecorated())) {f.setUndecorated(true);}
	        
	        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        
	        f.setSize(width, height);
			
	        f.setResizable(false);
	        f.setVisible(true);  
	        
	        f.setLocation((int)((screenSize.getWidth()-f.getWidth())/2), -height);
	        Animations.AnimationPV(f);
	        
	        hasLoaded();
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
			}
			
			return btns;
		}
		
		private JButton[] locatePersonalCards(JButton[] btns, boolean buildings){
			double x=7.3;
			double y=9;
			
			if(buildings)y=y+35;
			
			for(int j=0;j<btns.length;j++){
				
				btns[j].setLocation((int)(width*x/100),(int)(height*y/100));
				btns[j].setSize((int)(width*14.3/100),(int)(height*27/100));
				x=x+15.3;		
			}
			return btns;
		}
		
		public void close(){
			
			//f.setLocation((int)((screenSize.getWidth()-f.getWidth())/2), (int)((screenSize.getHeight()-f.getHeight())/2)- (int)(screenSize.getHeight()/182.4));
			Animations.AnimationPV2(f);
				
		}
		
		public void setResources(int coin, int wood, int stone, int servant, int victory, int military, int faith) {
			
			coins.setText(String.valueOf(coin));
			woods.setText(String.valueOf(wood));
			stones.setText(String.valueOf(stone));
			servants.setText(String.valueOf(servant));
			this.victory.setText(String.valueOf(victory));
			this.military.setText(String.valueOf(military));
			this.faith.setText(String.valueOf(faith));
		}


		public void setTerritoryCard(int id, int index) {
			try {
				territories[index].setIcon((ImageHandler.setImage("resources/cards/devcards_f_en_c_"+id+".png",17,(int)( 17.5 * 1.77 ),width,height)).getIcon());
			} catch (IOException e) {
				e.printStackTrace();}
			
			territories[index].setDisabledIcon( territories[index].getIcon());
		}
		
		
		public void setBuildingCard(int id, int index) {
			try {
				buildings[index].setIcon((ImageHandler.setImage("resources/cards/devcards_f_en_c_"+id+".png",17,(int)( 17.5 * 1.77  ),width,height)).getIcon());
			} catch (IOException e) {
				e.printStackTrace();}
			
			buildings[index].setDisabledIcon( buildings[index].getIcon());
			
		}
		
		public void setTileCode(int code) throws IOException{
			this.btCode=code;
			
			bonusTileLabel = ImageHandler.setImage("resources/tile/pb"+btCode+".png",5.8,73.1,width,height);
	        
	        bonusTile[0].setIcon(bonusTileLabel.getIcon());
			bonusTile[0].setDisabledIcon( bonusTile[0].getIcon() );
		}

		public void hasLoaded() {
			
			boardView.hasLoadedPersonalView();
		}
		
}
