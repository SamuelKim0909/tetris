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

public class Frame implements MouseListener {
	
	ArrayList<Block> blocks = new ArrayList<Block>();
	boolean[][] grid = new boolean[9][9];
	int score = 0;
	boolean[] rowClear = new boolean[9];
	boolean[] colClear = new boolean[9];
	
	
	public static void main(String[] args) {
		
		Frame f = new Frame();
		
		
	}
	
	public void paint(Graphics g) {
		
		for (int i = 0; i < blocks.size(); i++) {
			blocks.get(i).paint(g);
		}
	}
	
	public Frame() {
		JFrame f = new JFrame();
		f.setSize(450, 450);
		f.setBackground(Color.GRAY);
		f.setResizable(false);
		f.addMouseListener(this);
		
		if (blocks.size()==0) {
			
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
