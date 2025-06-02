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

public class Frame extends JPanel implements ActionListener, MouseListener, MouseMotionListener {
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
//	SimpleAudioPlayer backgroundMusic = new SimpleAudioPlayer("sound.wav", false);
//	SimpleAudioPlayer backgroundMusic2 = new SimpleAudioPlayer("sound2.wav", false);
//	Music soundBang = new Music("bang.wav", false);
//	Music soundHaha = new Music("haha.wav", false);
	
	//boolean variables to keep track of which keys were found
	
	//frame width/height
	static int width = 850; 
	static int height = 750; 
	
	ArrayList<Block> blocks = new ArrayList<Block>();
	int [][] grid = new int [9][9];
	//true = 1, false = 2, something else = 3;
	int score = 0;
	int lineCleared = 0;
	int rowCleared = 0;
	int colCleared = 0;
	boolean[] rowClear = new boolean[9];
	boolean[] colClear = new boolean[9];
	
	Block draggingBlock = null;
	int offsetX, offsetY;
	
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
	    
	    super.paintComponent(g);
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
//	    System.out.println(toString());
//	    for(int i = 0; i<grid.length; i++) {
//	    	for(int j = 0; j<grid[i].length; j++) {
//	    		if(grid[i][j] == true) {
//	    			g.setColor(Color.GRAY);
//	    			g.fillRect(j*50, i * 50, 50, 50); // Use i, not rowIndex
//	    		}
//	    	}
//	    }
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
	}
	
	public void clearRow(int rowIndex) {
		for (int j = 0; j < grid[rowIndex].length; j++) {
			grid[rowIndex][j] = 3;
		}
		System.out.println("clearRow");
		System.out.println(toString());
	}
	
	public void clearCol(int colIndex) {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (j==colIndex) {
					grid[i][j] = 3;
				}
			}
		}
	}

	
	
	//for kill screen, draw this image
	//reset lives removes the last heart in the list
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
 		
 		if (blocks.size()==0) {
			generate();
		}

 		for(int i = 0; i<grid.length; i++) {
 			for(int j = 0; j<grid[i].length; j++) {
 				grid[i][j] = 1;
 			}
 		}
 		
 		
//		backgroundMusic.play();

		/*
		 * Setup any 1D array here! - create the objects that go in them ;)
		 */
		
//		for (int i = 0; i < row1.length; i++) {
//			//create the object and put in the array at position i!
//			row1[i] = new Imposter(i*170, 430);
//			
//		}
		// creates a new object for each item in array or list
		
		
		//the cursor image must be outside of the src folder
		//you will need to import a couple of classes to make it fully 
		//functional! use eclipse quick-fixes
//		setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
//				new ImageIcon("torch.png").getImage(),
//				new Point(0,0),"custom cursor"));	
		
		
		Timer t = new Timer(16, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	
	public void generate() {
//		currNum = Block.number;
		int length = 0;
//		int number = Block.number;
		for (int i = 0; i < 3; i++) {
			int num = (int)(Math.random()*7);
			if (num==0) {
				blocks.add(new Square(length+(10*i), 470, 100, 100));
				length += 100;
			}
			else if (num==1) {
				blocks.add(new RightL(length+(10*i), 470, 100, 150));
				length += 100;

			}
			else if (num==2 ) {
				blocks.add(new LeftL(length+(10*i), 470, 100, 150));
				length += 100;

			}
			else if (num==3) {
				blocks.add(new Line(length+(10*i), 470, 50, 200));
				length += 50;

			}
			else if (num==4) {
				blocks.add(new T(length+(10*i), 470, 150, 100));
				length += 150;

			}
			else if (num==5) {
				blocks.add(new LeftZ(length+(10*i), 470, 150, 100));
				length += 150;

			}
			else if (num==6) {
				blocks.add(new RightZ(length+(10*i), 470, 150, 100));
				length += 150;
			}
			blockCount++;
//			nextNum = Block.number;

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
	
	public void mouseReleased(MouseEvent e) {
	    if (draggingBlock != null) {
	        System.out.println("Block released at: (" + draggingBlock.x + ", " + draggingBlock.y + ")");
		    System.out.println(draggingBlock.getShape());
		    if(draggingBlock.available(draggingBlock.getX(),draggingBlock.getY(),grid,draggingBlock.getShape())){
		    	draggingBlock.coordinate(draggingBlock.getX(),draggingBlock.getY(),grid,draggingBlock.getShape());	
		    	if(draggingBlock.y<450) {
		        	draggingBlock.draggable = false; // Make it ungrabbable
		        	blockCount--;
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
	}

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
	
}
