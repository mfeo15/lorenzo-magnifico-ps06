package it.polimi.ingsw.ps06.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImageHandler {
	

	public static JLabel setImage(String s,double xMod, double yMod, int width, int height) throws IOException{
		
		BufferedImage image = ImageIO.read(new File(s)); 
		
		BufferedImage board = new BufferedImage((int)(width*xMod/100), (int)(height*yMod/100), BufferedImage.TYPE_INT_ARGB);
		
		Graphics g = board.createGraphics();
        g.drawImage(image, 0, 0, (int)(width*xMod/100), (int)(height*yMod/100), null);
        g.dispose();
        
        JLabel label = new JLabel(new ImageIcon(board)); 
        
        return label;
		
	}
	
	public static JLabel setImageScreen(String s,int xMod, int yMod, int width, int height) throws IOException{
		
		BufferedImage image = ImageIO.read(new File(s)); 
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		BufferedImage board = new BufferedImage((int)(screenSize.getWidth())*xMod/100, (int)(screenSize.getHeight())*yMod/100, BufferedImage.TYPE_INT_ARGB);
		
		Graphics g = board.createGraphics();
        g.drawImage(image, 0, 0, (int)(screenSize.getWidth())*xMod/100, (int)(screenSize.getHeight())*yMod/100, null);
        g.dispose();
        
        JLabel label = new JLabel(new ImageIcon(board)); 
        
        return label;
		
	}
	
	
	
}
