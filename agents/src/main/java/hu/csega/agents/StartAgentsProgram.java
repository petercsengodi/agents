package hu.csega.agents;

import javax.swing.JFrame;

import hu.csega.agents.swing.DisplayFrame;

public class StartAgentsProgram {

	public static void main(String[] args) throws Exception {
		DisplayFrame frame = new DisplayFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
