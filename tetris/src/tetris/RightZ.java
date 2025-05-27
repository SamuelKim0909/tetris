package tetris;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class RightZ extends Block{
	
	private Image forward;
	private AffineTransform tx;
	
	double scaleWidth = 0.05;
	double scaleHeight = 0.05;

	public RightZ(int x, int y, int width, int height) {
		super(x, y, width, height);
		forward = getImage("/imgs/"+"rightZ.png");
		tx = AffineTransform.getTranslateInstance(0, 0);
		init(x,y);
		// TODO Auto-generated constructor stub
	}
	@Override
	public String getShape() {
		return "RightZ";
	}
	
	
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
			
		
		
		
		init(x,y);
		g2.drawImage(forward, tx, null);
		

	}
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(scaleWidth, scaleHeight);
	}
	
	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Block.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}
