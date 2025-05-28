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
	boolean draggable = true;
	
	public Block(int x, int y, int width, int height) {

		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		box = new Rectangle(x, y, width, height);
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	public String getShape() {
		return "null";
	}
	
	public void move(int x, int y) {
		setX(x);
		setY(y);
	}
	
	public boolean available(int x, int y, boolean[][] grid, String type) {
		int col= (x+1)/50;
		int row= (y+1)/50;
		if(type.equals("LeftL")){
			if(col+1>8|| row+2>8) {
				return false;
			}else if(grid[row][col] == false||
			grid[row+1][col] == false||
			grid[row+2][col] == false||
			grid[row+2][col+1] == false) {
				return false;
			}
		}else if(type.equals("LeftZ")) {
			if(col+2>8|| row+1>8) {
				return false;
			}else if(grid[row][col+1] == false||
			grid[row][col+2] == false||
			grid[row+1][col] == false||
			grid[row+1][col+1] == false) {
				return false;
			}
		}else if(type.equals("Line")) {
			if(col>8|| row+3>8) {
				return false;
			}else if(grid[row][col] == false||
			grid[row+1][col] == false||
			grid[row+2][col] == false||
			grid[row+3][col] == false) {
				return false;
			}
		}else if(type.equals("RightL")) {
			if(col+1>8|| row+2>8) {
				return false;
			}else if(grid[row][col+1] == false||
			grid[row+1][col+1] == false||
			grid[row+2][col+1] == false||
			grid[row+2][col] == false) {
				return false;
			}
		}else if(type.equals("RightZ")) {
			if(col+2>8|| row+1>8) {
				return false;
			}else if(grid[row][col] == false||
			grid[row][col+1] == false||
			grid[row+1][col+1] == false||
			grid[row+1][col+2] == false) {
				return false;
			}
		}else if(type.equals("Square")) {
			if(col+1>8|| row+1>8) {
				return false;
			}else if(grid[row][col+1] == false||
			grid[row+1][col+1] == false||
			grid[row+1][col] == false||
			grid[row][col] == false) {
				return false;
			}
		}else if(type.equals("T")) {
			if(col+2>8|| row+1>8) {
				return false;
			}else if(grid[row][col] == false||
			grid[row][col+1] == false||
			grid[row][col+2] == false||
			grid[row+1][col+1] == false) {
				return false;
			}
		}
		return true;
	}

	public boolean[][] coordinate(int x, int y, boolean[][] grid, String type) {
		int col= (x+1)/50;
		int row= (y+1)/50;
		if(type.equals("LeftL")) {
			grid[row][col] = false;
			grid[row+1][col] = false;
			grid[row+2][col] = false;
			grid[row+2][col+1] = false;
		}else if(type.equals("LeftZ")) {
			grid[row][col+1] = false;
			grid[row][col+2] = false;
			grid[row+1][col] = false;
			grid[row+1][col+1] = false;
		}else if(type.equals("Line")) {
			grid[row][col] = false;
			grid[row+1][col] = false;
			grid[row+2][col] = false;
			grid[row+3][col] = false;
		}else if(type.equals("RightL")) {
			grid[row][col+1] = false;
			grid[row+1][col+1] = false;
			grid[row+2][col+1] = false;
			grid[row+2][col] = false;
		}else if(type.equals("RightZ")) {
			grid[row][col] = false;
			grid[row][col+1] = false;
			grid[row+1][col+1] = false;
			grid[row+1][col+2] = false;
		}else if(type.equals("Square")) {
			grid[row][col+1] = false;
			grid[row+1][col+1] = false;
			grid[row+1][col] = false;
			grid[row][col] = false;
		}else if(type.equals("T")) {
			grid[row][col] = false;
			grid[row][col+1] = false;
			grid[row][col+2] = false;
			grid[row+1][col+1] = false;
		}
		System.out.println(toString(grid));
		return grid;
	}

	public String toString(boolean[][] grid) {
		String result = "";
		for(int i = 0; i<grid.length; i++) {
			for(int j = 0; j<grid[i].length; j++) {
				String answer = "";
				if(grid[i][j]) {
					answer = "T";
				}else {
					answer = "F";
				}
				result+= " ["+ answer+ "]";
			}
			result+= "\n";
		}
		return result;
	}

	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
