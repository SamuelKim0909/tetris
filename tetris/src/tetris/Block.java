package tetris;

import java.awt.Rectangle;

public class Block {

	int x;
	int y;
	int width;
	int height;
	int speed;
	Rectangle collisionBox;
	
	public Block(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		collisionBox = new Rectangle(x, y, width, height);
	}
	
	
}
