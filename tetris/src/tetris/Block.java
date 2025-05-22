package tetris;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Block {
	
	int x;
	int y;
	int width;
	int height;
	double scaleWidth = 0.05;
	double scaleHeight = 0.05;
	Rectangle box;
	
	public Block(int x, int y, int width, int height) {

		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		box = new Rectangle(x, y, width, height);
	}

	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
