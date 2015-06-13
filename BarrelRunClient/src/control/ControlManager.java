package control;

import java.awt.Toolkit;

import view.GameFrame;

public class ControlManager{

	private GameFrame frame;
	private GameStateManager gsm;
	
	public static int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
	public static int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
	
	public ControlManager(GameFrame frame) {
		this.frame = frame;
		this.gsm = new GameStateManager(this);		
	}
	
	public GameStateManager getGameStateManager(){
		return gsm;
	}
	
	public GameFrame getFrame(){
		return frame;
	}
	
}
