package it.polimi.ingsw.ps06.view;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import sun.awt.image.ToolkitImage;

public class ImageHandler {


	public static JLabel setImage(String s,double xMod, double yMod, int width, int height) throws IOException{

		s =s.replaceFirst("resources", "");
		BufferedImage image = ImageIO.read( ImageHandler.class.getResourceAsStream(s) );

		BufferedImage board = new BufferedImage((int)(width*xMod/100), (int)(height*yMod/100), BufferedImage.TYPE_INT_ARGB);

		Graphics2D g = board.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
		g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_LCD_CONTRAST, 100);
		g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		g.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,RenderingHints.VALUE_STROKE_PURE); 
		g.drawImage(image, 0, 0, (int)(width*xMod/100), (int)(height*yMod/100), null);
		g.dispose();

		JLabel label = new JLabel(new ImageIcon(board)); 

		return label;

	}

	public static JLabel setImageScreen(String s,int xMod, int yMod, int width, int height) throws IOException{

		//BufferedImage image = ImageIO.read(new File(s)); 

		s =s.replaceFirst("resources", "");
		BufferedImage image = ImageIO.read( ImageHandler.class.getResourceAsStream(s) );

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		BufferedImage board = new BufferedImage((int)(screenSize.getWidth())*xMod/100, (int)(screenSize.getHeight())*yMod/100, BufferedImage.TYPE_INT_ARGB);

		Graphics2D g = board.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
		g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_LCD_CONTRAST, 100);
		g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		g.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,RenderingHints.VALUE_STROKE_PURE);   
		g.drawImage(image, 0, 0, (int)(screenSize.getWidth())*xMod/100, (int)(screenSize.getHeight())*yMod/100, null);
		g.dispose();

		JLabel label = new JLabel(new ImageIcon(board)); 

		return label;

	}



}
