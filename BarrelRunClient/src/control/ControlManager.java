package control;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.Timer;

import model.ClientConnect;
import model.ClientConnect;
import view.GameFrame;
import view.GamePanel;

public class ControlManager{

	private GameFrame frame;
	private GameStateManager gsm;
	
	public static int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
	public static int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
	
	public ControlManager(GameFrame frame) {
		this.frame = frame;
		this.gsm = new GameStateManager(this);
		if(gsm == null){
			System.out.println("ControlManager aids");
		}
	}
	
	public GameStateManager getGameStateManager(){
		return gsm;
	}
	
	public GameFrame getFrame(){
		return frame;
	}
	
}
