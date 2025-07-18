package tetris;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener; 
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Frame extends JPanel implements ActionListener, MouseListener, MouseMotionListener, KeyListener {
	//ints to keep track of the state of the game
	
	//Timer related variables
	int waveTimer = 5; //each wave of enemies is 20s
	long ellapseTime = 0;
	Font timeFont = new Font("Courier", Font.BOLD, 70);
	int blockCount = 0;
	private int originalX, originalY;
	//font and music variables
	Font myFont = new Font("Courier", Font.BOLD, 40);
	boolean check = false;
	int currNum = 0;
	int nextNum = 0;
	final int MENU = 2;
	final int GAME = 0;
	final int END = 1;
	int currentState = MENU;
	
	//frame width/height
	static int width = 850; 
	static int height = 750; 
	
	ArrayList<Block> blocks = new ArrayList<Block>();
	ArrayList<Block> blocksAvailable = new ArrayList<Block>();
	int [][] grid = new int [9][9];
	//true = 1, false = 2, changed = 3;
	int score = 0;
	int lineCleared = 0;
	int rowCleared = 0;
	int colCleared = 0;
	boolean[] rowClear = new boolean[9];
	boolean[] colClear = new boolean[9];
	
	Block draggingBlock = null;
	int offsetX, offsetY;
	
	//covers the area that is available with a gray square
	public void clearing(Graphics g) {
	    for(int i = 0; i<grid.length; i++) {
	    	for(int j = 0; j<grid[i].length; j++) {
    			if(grid[i][j] == 3) {
    				g.setColor(Color.GRAY);
    				g.fillRect(j*50, i * 50, 50, 50); // Use i, not rowIndex
    			}
    		}
	    }
	}
	
	public void paint(Graphics g) {
	    //paints the frame, blocks, scores, and directions
	    super.paintComponent(g);
	    if (currentState == GAME) {
		    g.setColor(Color.GRAY);
		    g.fillRect(0, 0, 850, 750);
		    g.setColor(Color.BLACK);
		    g.drawRect(0, 0, 450, 450);
		    g.setFont(myFont);
		    g.setColor(Color.WHITE);
			g.drawString("Score: " + score+"", 455,40);
			g.drawString("Lines Cleared: " + lineCleared+"", 455,80);
			g.drawString("Rows Cleared: " + rowCleared+"", 455,120);
			g.drawString("Cols Cleared: " + colCleared+"", 455,160);
		    clearing(g);
		    // Paint the blocks first
		    for (int i = 0; i < blocks.size(); i++) {
		    	if(blocks.get(i).old) {
		    		blocks.get(i).paint(g);
		    	}
		    }
		    // Now draw the gray rectangles on top of any empty rows
		    for (int i = 0; i < grid.length; i++) {
		        boolean allFalse = true;
		        for (int j = 0; j < grid[i].length; j++) {
		            if (grid[i][j]==1||grid[i][j] == 3) {
		                allFalse = false;
		                break;
		            }
		        }
		        if (allFalse) {
		        	score+=1000;
		        	rowCleared ++;
		        	lineCleared++;
		        	clearRow(i); // Optional: clear the row if needed
		        }
		    }
		    
		    //checks if any column is fully occupied
		    for (int i = 0; i < grid[0].length; i++) {
		        boolean allFalse = true;
		        for (int j = 0; j < grid.length; j++) {
		            if (grid[j][i]==1||grid[j][i] == 3) {
		                allFalse = false;
		                break;
		            }
		        }
		        if (allFalse) {
		        	score+=1000;
		        	colCleared ++;
		        	lineCleared++;
		            clearCol(i); // Optional: clear the row if needed
		        }
		    }
		    clearing(g);
		    for (int i = 0; i < blocks.size(); i++) {
		    	if(blocks.get(i).old==false) {
		    		blocks.get(i).paint(g);
		    	}
		    }
	
		    //reset
		    boolean next = false;
		    for(int i = 0; i<blockCount; i++) {
		    	if(blocksAvailable.get(i).cant(grid, blocksAvailable.get(i).getShape())) {
		    		next = true;
		    		break;
		    	}
		    }
		    if(next == false) {
		        g.setColor(Color.WHITE);
		    	currentState = END;
		    }
	    }
	    if (currentState == END) {
	    	drawEndState(g);
	    }if (currentState == MENU) {
	    	drawMenuState(g);
	    }

	}
	
	//switches the screen to end page.
	public void drawEndState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.WHITE);
		g.setFont(myFont);
		g.drawString("GAME OVER! Press R to play again.", 50, 50);
		
	}
	
	//switches the screen to the direction page.
	public void drawMenuState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0,  0,  width, height);
		g.setColor(Color.WHITE);
		g.setFont(myFont);
		g.drawString("BLOCK BLAST", 260, 200);
		g.drawString("Press SPACE to play!", 200, 500);
		g.drawString("Press 1 for instructions", 175, 350);
	}

	//clears the row
	public void clearRow(int rowIndex) {
		for (int j = 0; j < grid[rowIndex].length; j++) {
			grid[rowIndex][j] = 3;
		}
		System.out.println("clearRow");
		System.out.println(toString());
	}
	
	//clears the column
	public void clearCol(int colIndex) {
		for (int i = 0; i < grid.length; i++) {
			grid[i][colIndex] = 3;
		}
		System.out.println("clearRow");
		System.out.println(toString());
	}

	
	//main 
	public static void main(String[] arg) {
		Frame f = new Frame();
	}
	
	 
	public Frame() {
		JFrame f = new JFrame("Duck Hunt");
		f.setSize(new Dimension(width, height));
		f.setBackground(Color.white);
		f.add(this);
		f.setResizable(false);
 		f.addMouseListener(this);
 		f.addMouseMotionListener(this);
 		f.addKeyListener(this);
 		
 		if (blocks.size()==0) {
			generate();
		}

 		for(int i = 0; i<grid.length; i++) {
 			for(int j = 0; j<grid[i].length; j++) {
 				grid[i][j] = 1;
 			}
 		}
 		
		Timer t = new Timer(16, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	
	
	//generates three blocks outside of the grid when there are no more blocks to move
	public void generate() {
		int length = 0;
		for (int i = 0; i < 3; i++) {
			int num = (int)(Math.random()*7);
			if (num==0) {
				blocks.add(new Square(length+(10*i), 470, 100, 100));
				System.out.println(blocks);
				blocksAvailable.add(blocks.get(blocks.size()-1));
				System.out.println(blocksAvailable);
				length += 100;
			}
			else if (num==1) {
				blocks.add(new RightL(length+(10*i), 470, 100, 150));
				length += 100;
				System.out.println(blocks);
				blocksAvailable.add(blocks.get(blocks.size()-1));
				System.out.println(blocksAvailable);
			}
			else if (num==2 ) {
				blocks.add(new LeftL(length+(10*i), 470, 100, 150));
				length += 100;
				System.out.println(blocks);
				blocksAvailable.add(blocks.get(blocks.size()-1));
				System.out.println(blocksAvailable);
			}
			else if (num==3) {
				blocks.add(new Line(length+(10*i), 470, 50, 200));
				length += 50;
				System.out.println(blocks);
				blocksAvailable.add(blocks.get(blocks.size()-1));
				System.out.println(blocksAvailable);
			}
			else if (num==4) {
				blocks.add(new T(length+(10*i), 470, 150, 100));
				length += 150;
				System.out.println(blocks);
				blocksAvailable.add(blocks.get(blocks.size()-1));
				System.out.println(blocksAvailable);
			}
			else if (num==5) {
				blocks.add(new LeftZ(length+(10*i), 470, 150, 100));
				length += 150;
				System.out.println(blocks);
				blocksAvailable.add(blocks.get(blocks.size()-1));
				System.out.println(blocksAvailable);
			}
			else if (num==6) {
				blocks.add(new RightZ(length+(10*i), 470, 150, 100));
				length += 150;
				System.out.println(blocks);
				blocksAvailable.add(blocks.get(blocks.size()-1));
				System.out.println(blocksAvailable);
			}
			blockCount++;
		}	
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
	    draggingBlock = null; // optional safety
	}
	
	
	//places and locks the block in the square it is drawn to
	public void mouseReleased(MouseEvent e) {
		int index = 0;
	    if (draggingBlock != null) {
	    	for(int i = 0; i<blocksAvailable.size(); i++) {
	    		if(blocksAvailable.get(i).equals(draggingBlock.getShape())) {
	    			index = i;
	    		}
	    	}
	        System.out.println("Block released at: (" + draggingBlock.x + ", " + draggingBlock.y + ")");
		    System.out.println(draggingBlock.getShape());
		    if(draggingBlock.available(draggingBlock.getX(),draggingBlock.getY(),grid,draggingBlock.getShape())){
		    	draggingBlock.coordinate(draggingBlock.getX(),draggingBlock.getY(),grid,draggingBlock.getShape());	
		    	if(draggingBlock.y<450) {
		        	draggingBlock.draggable = false; // Make it ungrabbable
		        	blockCount--;
		        	blocksAvailable.remove(index);
		        	draggingBlock.old = true;
		        }
		    	draggingBlock = null;
		        if(blockCount==0) {
		        	generate();
		        }

		    }else {
		    	draggingBlock.x = originalX;
	            draggingBlock.y = originalY;
		    }
	    }
	    check = true;
	    System.out.println(blockCount);
	}
	//moves the selected block.
	public void mousePressed(MouseEvent e) {
	    int mx = e.getX();
	    int my = e.getY();

	    for (Block b : blocks) {
	        if (!b.draggable) continue; // Skip if it's ungrabbable

	        Rectangle r = new Rectangle(b.x, b.y, b.width, b.height);
	        if (r.contains(mx, my)) {
	            draggingBlock = b;
	            offsetX = mx - b.x;
	            offsetY = my - b.y;
	            
	            originalX = b.x;
	            originalY = b.y;
	            break;
	        }
	    }
	}

	public void mouseDragged(MouseEvent e) {
	    if (draggingBlock != null) {
	        int mx = e.getX();
	        int my = e.getY();

	        // Snap new position to 50x50 grid
	        draggingBlock.x = ((mx - offsetX) / 50) * 50;
	        draggingBlock.y = ((my - offsetY) / 50) * 50;
	    }
	}
	@Override
	public void mouseMoved(MouseEvent e) {
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
	}
	
	public String toString() {
		String result = "";
		for(int i = 0; i<grid.length; i++) {
			for(int j = 0; j<grid[i].length; j++) {
				String answer = "";
				if(grid[i][j]==1) {
					answer = "1";
				}else if(grid[i][j] ==2){
					answer = "2";
				}else if(grid[i][j] == 3){
					answer = "3";
				}
				result+= " ["+ answer+ "]";
			}
			result+= "\n";
		}
		return result;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getKeyCode());
		
		if (e.getKeyCode()==32) {
			currentState = GAME;
			
		}if (e.getKeyCode() == 49) {
			JOptionPane.showMessageDialog(null, "Click and drag blocks onto the grid. If an entire row or column is filled with blocks, the row/column will be cleared and you will gain points. The goal is to get as many points as possible before becoming unable to place more blocks.");
		}

			currentState = GAME;
			score = 0;
			rowCleared = 0;
			colCleared = 0;
			lineCleared = 0;
			blockCount = 0;
			while(blocks.size()>0) {
				blocks.remove(blocks.size()-1);	
			}
			for(int i = 0; i<grid.length;i++) {
				for(int j = 0; j<grid[i].length; j++) {
					grid[i][j] = 1;
				}
			}
			generate();		
	}
	
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
