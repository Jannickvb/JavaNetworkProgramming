package view;

import java.awt.Dimension;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JFrame;

import control.GameController;
import control.GameStateManager;

public class GameFrame extends JFrame{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = -6399886456682347905L;

	public GameFrame(){
		super("Java network programming");
		setPreferredSize(new Dimension(800,600));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setContentPane(new GamePanel(new GameStateManager(new GameController(this))));
		setVisible(true);
	}
}
