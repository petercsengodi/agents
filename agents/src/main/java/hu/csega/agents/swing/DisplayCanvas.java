package hu.csega.agents.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class DisplayCanvas extends JPanel {

	private DisplayFrame frame;

	private int lastWidth = 0;
	private int lastHeight = 0;
	private BufferedImage image = null;

	public DisplayCanvas(DisplayFrame frame) {
		this.frame = frame;
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(800, 600);
	}

	@Override
	public void paint(Graphics g) {
		int width = this.getWidth();
		int height = this.getHeight();
		if(image == null || width != lastWidth || height != lastHeight) {
			image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
		}

		Graphics2D g2d = (Graphics2D) image.getGraphics();
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, width, height);

		frame.paintDisplay(g2d, width, height);

		g.drawImage(image, 0, 0, null);
	}

	private static final long serialVersionUID = 1L;
}
