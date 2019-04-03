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
	public static String currentSort = ""; 
	public static boolean done = true;
	
	public Display(int width, int height, String title) {
		createDisplay(width, height, title);
	}
	//Sets up a simple JFrame with a JPanel for drawing to
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
	//JPanel's drawing component
	public void paintComponent(Graphics g) {
		//Clear screen
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		//Display array
		double width = (double) getWidth() / Main.array.length;
		double height = (double) (getHeight() - 20) / Main.array.length;
		for(int i = 0; i < Main.array.length; i++) {
			if(i == Main.swapIndeces[0] || i == Main.swapIndeces[1]) 
				g.setColor(Color.RED);
			else
				g.setColor(Color.WHITE);
			g.fillRect((int) (i * width), getHeight() - (int) (height * Main.array[i]),
					   (int) width      , (int) (height * Main.array[i])             );
			g.setColor(Color.BLACK);
			g.drawRect((int) (i * width), getHeight() - (int) (height * Main.array[i]),
					   (int) width      , (int) (height * Main.array[i])             );
		}
		//Display information
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.PLAIN, 20));
		g.drawString(currentSort + " (Comparisons: " + Main.comparisons + ", Swaps: " + Main.swaps + ")", 10, 22);
		g.drawString("Time per swap: " + Main.swapTime + "ms", 10, 44);
		if(done)
			g.drawString("Press a key to Continue", 600, 22);
	}
	//Refreshes paintComponent to account for changing variables
	public void render() {
		repaint();
	}
	//unused
	public void keyPressed(KeyEvent arg0) {}
	public void keyReleased(KeyEvent e) {
		//Lets the main function know the user has pressed a key and the program should continue
		Main.waitVar++;
	}
	//unused
	public void keyTyped(KeyEvent arg0) {}
}