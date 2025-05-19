package tetris;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Frame implements KeyListener {
	
	ArrayList<Block> blocks = new ArrayList<Block>();
	
	public static void main(String[] args) {
		
		Frame f = new Frame();
		
		
	}
	
	public Frame() {
		JFrame f = new JFrame();
		f.setSize(400, 800);
		f.setBackground(Color.GRAY);
		f.addKeyListener(this);
		f.setVisible(true);
		if (blocks.size()==0) {
			int num = (int) (Math.random()*7);
			if (num==0) {
				blocks.add(new Square(360, 40, 80, 80));
			}
			else if (num==1) {
				blocks.add(new RightL(360, 40, 80, 120));
			}
			else if (num==2 ) {
				blocks.add(new LeftL(360, 40, 80, 120));
			}
			else if (num==3) {
				blocks.add(new Line(360, 40, 40, 160));
			}
			else if (num==4) {
				blocks.add(new T(360, 40, 120, 80));
			}
			else if (num==5) {
				blocks.add(new LeftZ(360, 40, 120, 80));
			}
			else if (num==6) {
				blocks.add(new RightZ(360, 40, 120, 80));
			}
		}
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public void paint(Graphics g) {
		
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
