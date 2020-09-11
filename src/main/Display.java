package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Display extends JPanel implements KeyListener{

	private JFrame frame;
	private int[] array = null;
	private int swap1, swap2, swaps, comparisons;
	private String algorithmName;
	private long delay;
	private boolean waiting = false;
	private final long WAIT_CONSTANT = 200;
	
	public Display(int width, int height, String title) {
		createDisplay(width, height, title);
	}
	
	private void createDisplay(int width, int height, String title) {
		frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(width, height));
		frame.setResizable(true);
		frame.setVisible(true);
		frame.add(this);
		frame.addKeyListener(this);
		frame.pack();
	}
	
	//Draw on screen
	public void paintComponent(Graphics g) {
		//Clear Screen
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		if(array != null) {
			//Display array
			double width  = (double)   getWidth()       / array.length;
			double height = (double) (getHeight() - 20) / array.length;
			for(int i = 0; i < array.length; i++) {
				if(i == swap1 || i == swap2) 
					g.setColor(Color.RED);
				else
					g.setColor(Color.WHITE);
				g.fillRect((int) (i * width), getHeight() - (int) (height * array[i]),
						   (int) width      , (int) (height * array[i])             );
				g.setColor(Color.BLACK);
				g.drawRect((int) (i * width), getHeight() - (int) (height * array[i]),
						   (int) width      , (int) (height * array[i])             );
			}
			//Display information
			g.setColor(Color.WHITE);
			g.setFont(new Font("Arial", Font.PLAIN, 20));
			g.drawString(algorithmName + " (Comparisons: " + comparisons + ", Swaps: " + swaps + ")", 10, 22);
			g.drawString("Time per swap: " + delay + "ms", 10, 44);
		}
		if(waiting) {
			g.drawString("Press a key to Continue", 600, 22);
		}
	}
	
	//Redraw screen
	public void render(int[] array, int swap1, int swap2, int swaps, int comparisons) {
		this.array = array;
		this.swap1 = swap1;
		this.swap2 = swap2;
		this.swaps = swaps;
		this.comparisons = comparisons;
		repaint();
	}
	
//	public void renderWithPause(int pos1, int pos2, long delay) {
//		try {
//			Thread.sleep(delay);
//		} catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//        }
//		swap1 = pos1;
//		swap2 = pos2;
//		repaint();
//	}
	
	public void render() {
		this.render(null, 0, 0, 0, 0);
	}
	
	public void setAlgorithmInfo(String name, long delay) {
		algorithmName = name;
		this.delay = delay;
	}
	
	public void pause() {
		waiting = true;
		render(array, swap1, swap2, swaps, comparisons);
		do {
			try {
				Thread.sleep(WAIT_CONSTANT);
			} catch (InterruptedException e) {
	            Thread.currentThread().interrupt();
	        }
		}while(waiting);
	}
	
	
	public void keyPressed(KeyEvent arg0) {}
	public void keyTyped(KeyEvent arg0) {}
	public void keyReleased(KeyEvent arg0) {
		waiting = false;
	}
}
