package tetris;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Block {

	boolean old = false;
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

//	public void setNumber(int num) {
//		number = num;
//	}
//	
//	public int getNumber() {
//		return number;
//	}

	public boolean cant(int[][] grid, String type) {
		boolean result = false;
		if (type.equals("LeftL")) {
			for (int i = 0; i < grid.length - 2; i++) {
				for (int j = 0; j < grid[i].length - 1; j++) {
					if ((grid[i][j] == 1 || grid[i][j] == 3) && (grid[i + 1][j] == 1 || grid[i + 1][j] == 3)
							&& (grid[i + 2][j] == 1 || grid[i + 2][j] == 3)
							&& (grid[i + 2][j + 1] == 1 || grid[i + 2][j + 1] == 3)) {
						result = true;
					}
				}
			}
		} else if (type.equals("LeftZ")) {
			for (int i = 0; i < grid.length - 1; i++) {
				for (int j = 0; j < grid[i].length - 2; j++) {
					if ((grid[i][j + 1] == 1 || grid[i][j + 1] == 3) 
					&& (grid[i][j + 2] == 1 || grid[i][j + 2] == 3)
					&& (grid[i + 1][j + 1] == 1 || grid[i + 1][j + 1] == 3)
					&& (grid[i + 1][j] == 1 || grid[i + 1][j] == 3)) {
						result = true;
					}
				}
			}
		} else if (type.equals("Line")) {
			for (int i = 0; i < grid.length - 3; i++) {
				for (int j = 0; j < grid[i].length; j++) {
					if ((grid[i][j] == 1 || grid[i][j] == 3) 
							&& (grid[i + 1][j] == 1 || grid[i + 1][j] == 3)
							&& (grid[i + 2][j] == 1 || grid[i + 2][j] == 3)
							&& (grid[i + 3][j] == 1 || grid[i + 3][j] == 3)) {
						result = true;
					}
				}
			}
		} else if (type.equals("RightL")) {
			for (int i = 0; i < grid.length - 2; i++) {
				for (int j = 0; j < grid[i].length - 1; j++) {
					if ((grid[i][j + 1] == 1 || grid[i][j + 1] == 3)
							&& (grid[i + 1][j + 1] == 1 || grid[i + 1][j + 1] == 3)
							&& (grid[i + 2][j + 1] == 1 || grid[i + 2][j + 1] == 3)
							&& (grid[i + 2][j] == 1 || grid[i + 2][j] == 3)) {
						result = true;
					}
				}
			}
			
		} else if (type.equals("RightZ")) {
			for (int i = 0; i < grid.length - 1; i++) {
				for (int j = 0; j < grid[i].length - 2; j++) {
					if ((grid[i][j] == 1 || grid[i][j] == 3) 
							&& (grid[i][j + 1] == 1 || grid[i][j + 1] == 3)
							&& (grid[i + 1][j + 1] == 1 || grid[i + 1][j + 1] == 3)
							&& (grid[i + 1][j + 2] == 1 || grid[i + 1][j + 2] == 3)) {
						result = true;
					}
				}
			}
		} else if (type.equals("Square")) {
			for (int i = 0; i < grid.length - 1; i++) {
				for (int j = 0; j < grid[i].length - 1; j++) {
					if ((grid[i][j + 1] == 1 || grid[i][j + 1] == 3) 
							&& (grid[i][j] == 1 || grid[i][j] == 3)
							&& (grid[i + 1][j + 1] == 1 || grid[i + 1][j + 1] == 3)
							&& (grid[i + 1][j] == 1 || grid[i + 1][j] == 3)) {
						result = true;
					}
				}
			}
		} else if (type.equals("T")) {
			for (int i = 0; i < grid.length - 1; i++) {
				for (int j = 0; j < grid[i].length - 3; j++) {
					if ((grid[i][j] == 1 || grid[i][j] == 3) 
							&& (grid[i][j + 1] == 1 || grid[i][j + 1] == 3)
							&& (grid[i][j + 2] == 1 || grid[i][j + 2] == 3)
							&& (grid[i + 1][j + 1] == 1 || grid[i + 1][j + 1] == 3)) {
						result = true;
					}
				}
			}
		}
		return result;
	}

	public boolean available(int x, int y, int[][] grid, String type) {
		int col = (x + 1) / 50;
		int row = (y + 1) / 50;
		if (type.equals("LeftL")) {
			if (col + 1 > 8 || row + 2 > 8) {
				return false;
			} else if (grid[row][col] == 2 || grid[row + 1][col] == 2 || grid[row + 2][col] == 2
					|| grid[row + 2][col + 1] == 2) {
				return false;
			}
		} else if (type.equals("LeftZ")) {
			if (col + 2 > 8 || row + 1 > 8) {
				return false;
			} else if (grid[row][col + 1] == 2 || grid[row][col + 2] == 2 || grid[row + 1][col] == 2
					|| grid[row + 1][col + 1] == 2) {
				return false;
			}
		} else if (type.equals("Line")) {
			if (col > 8 || row + 3 > 8) {
				return false;
			} else if (grid[row][col] == 2 || grid[row + 1][col] == 2 || grid[row + 2][col] == 2
					|| grid[row + 3][col] == 2) {
				return false;
			}
		} else if (type.equals("RightL")) {
			if (col + 1 > 8 || row + 2 > 8) {
				return false;
			} else if (grid[row][col + 1] == 2 || grid[row + 1][col + 1] == 2 || grid[row + 2][col + 1] == 2
					|| grid[row + 2][col] == 2) {
				return false;
			}
		} else if (type.equals("RightZ")) {
			if (col + 2 > 8 || row + 1 > 8) {
				return false;
			} else if (grid[row][col] == 2 || grid[row][col + 1] == 2 || grid[row + 1][col + 1] == 2
					|| grid[row + 1][col + 2] == 2) {
				return false;
			}
		} else if (type.equals("Square")) {
			if (col + 1 > 8 || row + 1 > 8) {
				return false;
			} else if (grid[row][col + 1] == 2 || grid[row + 1][col + 1] == 2 || grid[row + 1][col] == 2
					|| grid[row][col] == 2) {
				return false;
			}
		} else if (type.equals("T")) {
			if (col + 2 > 8 || row + 1 > 8) {
				return false;
			} else if (grid[row][col] == 2 || grid[row][col + 1] == 2 || grid[row][col + 2] == 2
					|| grid[row + 1][col + 1] == 2) {
				return false;
			}
		}
		return true;
	}

	public int[][] coordinate(int x, int y, int[][] grid, String type) {
		int col = (x + 1) / 50;
		int row = (y + 1) / 50;
		if (type.equals("LeftL")) {
			grid[row][col] = 2;
			grid[row + 1][col] = 2;
			grid[row + 2][col] = 2;
			grid[row + 2][col + 1] = 2;
		} else if (type.equals("LeftZ")) {
			grid[row][col + 1] = 2;
			grid[row][col + 2] = 2;
			grid[row + 1][col] = 2;
			grid[row + 1][col + 1] = 2;
		} else if (type.equals("Line")) {
			grid[row][col] = 2;
			grid[row + 1][col] = 2;
			grid[row + 2][col] = 2;
			grid[row + 3][col] = 2;
		} else if (type.equals("RightL")) {
			grid[row][col + 1] = 2;
			grid[row + 1][col + 1] = 2;
			grid[row + 2][col + 1] = 2;
			grid[row + 2][col] = 2;
		} else if (type.equals("RightZ")) {
			grid[row][col] = 2;
			grid[row][col + 1] = 2;
			grid[row + 1][col + 1] = 2;
			grid[row + 1][col + 2] = 2;
		} else if (type.equals("Square")) {
			grid[row][col + 1] = 2;
			grid[row + 1][col + 1] = 2;
			grid[row + 1][col] = 2;
			grid[row][col] = 2;
		} else if (type.equals("T")) {
			grid[row][col] = 2;
			grid[row][col + 1] = 2;
			grid[row][col + 2] = 2;
			grid[row + 1][col + 1] = 2;
		}
		System.out.println(toString(grid));
		return grid;
	}

	public String toString(int[][] grid) {
		String result = "";
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				String answer = "";
				if (grid[i][j] == 1) {
					answer = "1";
				} else if (grid[i][j] == 2) {
					answer = "2";
				} else if (grid[i][j] == 3) {
					answer = "3";
				}
				result += " [" + answer + "]";
			}
			result += "\n";
		}
		return result;
	}

	public void paint(Graphics g) {
		// TODO Auto-generated method stub

	}

}
