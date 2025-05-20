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
	double scaleWidth = 1;
	double scaleHeight = 1;
	Rectangle box;
	
	public Block(int x, int y, int width, int height) {

		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		box = new Rectangle(x, y, width, height);
	}
	
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen


	}
	
}
