package view;

import javax.swing.JFrame;

import model.Server;

public class ServerFrame extends JFrame{
	
	public ServerFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800,600);
		setLocationRelativeTo(null);
		setVisible(true);
		
		new Server();
	}
	
}
