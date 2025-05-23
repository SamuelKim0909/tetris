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
	
	//font and music variables
	Font myFont = new Font("Courier", Font.BOLD, 40);
//	SimpleAudioPlayer backgroundMusic = new SimpleAudioPlayer("sound.wav", false);
//	SimpleAudioPlayer backgroundMusic2 = new SimpleAudioPlayer("sound2.wav", false);
//	Music soundBang = new Music("bang.wav", false);
//	Music soundHaha = new Music("haha.wav", false);
	
	//boolean variables to keep track of which keys were found
	
	//frame width/height
	static int width = 850;
	static int height = 750;
	
	ArrayList<Block> blocks = new ArrayList<Block>();
	boolean[][] grid = new boolean[9][9];
	int score = 0;
	boolean[] rowClear = new boolean[9];
	boolean[] colClear = new boolean[9];
	
	Block draggingBlock = null;
	int offsetX, offsetY;

	public void paint(Graphics g) {
		super.paintComponent(g);
		g.drawRect(200, 1, 450, 450);
		//paint the objects that you have
		for (int i = 0; i < blocks.size(); i++) {
			blocks.get(i).paint(g);
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
			for (int i = 0; i < 3; i++) {
				int num = (int) (Math.random()*7);
				if (num==0) {
					blocks.add(new Square(250*i+50, 470, 100, 100));
				}
				else if (num==1) {
					blocks.add(new RightL(250*i+50, 470, 100, 150));
				}
				else if (num==2 ) {
					blocks.add(new LeftL(250*i+50, 470, 100, 150));
				}
				else if (num==3) {
					blocks.add(new Line(250*i+50, 470, 50, 200));
				}
				else if (num==4) {
					blocks.add(new T(250*i+50, 470, 150, 100));
				}
				else if (num==5) {
					blocks.add(new LeftZ(250*i+50, 470, 150, 100));
				}
				else if (num==6) {
					blocks.add(new RightZ(250*i+50, 470, 150, 100));
				}
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
	
	@Override
	public void mouseReleased(MouseEvent e) {
	    draggingBlock = null;
	}
	
	public void mousePressed(MouseEvent e) {
	    int mx = e.getX();
	    int my = e.getY();

	    for (Block b : blocks) {
	        Rectangle r = new Rectangle(b.x, b.y, b.width, b.height);
	        if (r.contains(mx, my)) {
	            draggingBlock = b;
	            offsetX = mx - b.x;
	            offsetY = my - b.y;
	            break;
	        }
	    }
	}

	public void mouseDragged(MouseEvent e) {
	    if (draggingBlock != null) {
	        draggingBlock.x = e.getX() - offsetX;
	        draggingBlock.y = e.getY() - offsetY;
	        repaint();
	    }
	}
	@Override
	public void mouseMoved(MouseEvent e) {}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
	}

	
	}
