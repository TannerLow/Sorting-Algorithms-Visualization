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
	JFrame frame; 
	public static boolean done = true;
	Sorter sorter;
	
	public Display(int width, int height, String title,Sorter sorter) {
		createDisplay(width, height, title, sorter);
	}
	//Sets up a simple JFrame with a JPanel for drawing to
	private void createDisplay(int width, int height, String title, Sorter sorter) {
		frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(width, height));
		frame.setResizable(true);
		frame.setVisible(true);
		frame.add(this);
		frame.addKeyListener(this);
		frame.pack();
		this.sorter = sorter;
	}
	//JPanel's drawing component
	public void paintComponent(Graphics g) {
		//Clear screen
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		//Display array
		double width = (double) getWidth() / sorter.getSize();
		double height = (double) (getHeight() - 20) / sorter.getSize();
		for(int i = 0; i < sorter.getSize(); i++) {
			if(i == sorter.getSwapIndex(0) || i == sorter.getSwapIndex(1)) 
				g.setColor(Color.RED);
			else
				g.setColor(Color.WHITE);
			g.fillRect((int) (i * width), getHeight() - (int) (height * sorter.at(i)),
					   (int) width      , (int) (height * sorter.at(i))             );
			g.setColor(Color.BLACK);
			g.drawRect((int) (i * width), getHeight() - (int) (height * sorter.at(i)),
					   (int) width      , (int) (height * sorter.at(i))             );
		}
		//Display information
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.PLAIN, 20));
		g.drawString(sorter.getName() + " (Comparisons: " + sorter.getComparisons() + ", Swaps: " + sorter.getSwaps() + ")", 10, 22);
		g.drawString("Time per swap: " + sorter.getSwapTime() + "ms", 10, 44);
		if(done)
			g.drawString("Press a key to Continue", 600, 22);
	}
	//Refreshes paintComponent to account for changing variables
	public void render() {
		repaint();
	}
	//unused
	public void keyPressed(KeyEvent arg0) {}
	public void keyTyped(KeyEvent arg0) {}
	public void keyReleased(KeyEvent e) {
		//Lets the main function know the user has pressed a key and the program should continue
		Main.waitVar = true;
	}
}