package hu.csega.agents.swing;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;

import javax.swing.JFrame;

public class DisplayFrame extends JFrame {

	public DisplayFrame() {
		super("Agents Display");

		Container contentPane = this.getContentPane();
		contentPane.add(new DisplayCanvas(this));

		this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);

		this.pack();
	}

	public void paintDisplay(Graphics g, int width, int height) {
		g.setColor(Color.WHITE);
		g.drawLine(0, 0, 20, 20);
	}

	private static final long serialVersionUID = 1L;
}
