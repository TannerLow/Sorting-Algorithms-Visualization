import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Display extends JPanel {
	JFrame frame;
	
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
		frame.pack();
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		double width = (double) getWidth() / Main.array.length;
		double height = (double) (getHeight() - 20) / Main.array.length;
		for(int i = 0; i < Main.array.length; i++) {
			g.setColor(Color.WHITE);
			g.fillRect((int) (i * width), getHeight() - (int) (height * Main.array[i]),
					   (int) width      , (int) (height * Main.array[i])             );
			g.setColor(Color.BLACK);
			g.drawRect((int) (i * width), getHeight() - (int) (height * Main.array[i]),
					   (int) width      , (int) (height * Main.array[i])             );
		}
//		g.setColor(color);
//		g.fillRect(0, 0, getWidth(), getHeight());
//		//g.setColor(color);
//		g.fillOval(200, 100, 600, 600);
//		g.fillOval(800, 100, 600, 600);
//		g.setFont(new Font("Arial", Font.PLAIN, 12));
//		g.setColor(Color.BLACK);
//		//g.drawString("Sample Text", 350, 400);
//		int y = 400;
//		for(String s : sampleText) {
//			g.drawString(s, 350, y);
//			y += 12;
//		}
//		g.setColor(Color.WHITE);
//		y = 400;
//		for(String s : sampleText) {
//			g.drawString(s, 950, y);
//			y += 12;
//		}
//		//g.drawString("Sample Text", 950, 400);
//		g.setFont(new Font("Arial", Font.PLAIN, 48));
//		g.drawString("Count: " + count, 10, 50);
	}
	public void render() {
		repaint();
	}
}
