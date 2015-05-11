package control;

import view.GameFrame;

public class GameController {
	
	private int width;
	private int height;
	private GameFrame frame;
	public GameController(GameFrame frame){
		this.frame = frame;
	}
	public int getWidth(){
		return frame.getContentPane().getWidth();
	}
	public int getHeight(){
		return frame.getContentPane().getHeight();
	}
}
