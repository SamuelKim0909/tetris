package tetris;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame extends JPanel implements MouseListener {
	
	ArrayList<Block> blocks = new ArrayList<Block>();
	boolean[][] grid = new boolean[9][9];
	int score = 0;
	boolean[] rowClear = new boolean[9];
	boolean[] colClear = new boolean[9];
	
	public void paint(Graphics g) {
		super.paintComponent(g);
//		g.fillRect(50, 50, 50, 50);
		for (int i = 0; i < blocks.size(); i++) {
			blocks.get(i).paint(g);
		}
	}
	
	public Frame() {
		JFrame f = new JFrame();
		f.setSize(450, 650);
		f.setBackground(Color.GRAY);
		
		f.setResizable(false);
		f.addMouseListener(this);
		if (blocks.size()==0) {
			for (int i = 0; i < 3; i++) {
				int num = (int) (Math.random()*7);
				if (num==0) {
					blocks.add(new Square(50*(i+2), 470, 80, 80));
				}
				else if (num==1) {
					blocks.add(new RightL(50*(i+2), 470, 80, 120));
				}
				else if (num==2 ) {
					blocks.add(new LeftL(50*(i+2), 470, 80, 120));
				}
				else if (num==3) {
					blocks.add(new Line(50*(i+2), 470, 40, 160));
				}
				else if (num==4) {
					blocks.add(new T(50*(i+2), 470, 120, 80));
				}
				else if (num==5) {
					blocks.add(new LeftZ(50*(i+2), 470, 120, 80));
				}
				else if (num==6) {
					blocks.add(new RightZ(50*(i+2), 470, 120, 80));
				}
			}
			
		}
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
