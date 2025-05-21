package tetris;

import javax.swing.JFrame;

public class Runner {

	JFrame frame;
	Frame f;
	public Runner() {
		frame = new JFrame();
		f = new Frame();
	}
	
	void setup() {
		frame.add(f);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		Runner game = new Runner();
		game.setup();
	}

}
