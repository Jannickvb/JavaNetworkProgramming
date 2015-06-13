package model.gamestates;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.IOException;

import control.GameStateManager;

public abstract class GameState {

	public GameStateManager gsm;

	public GameState(GameStateManager gsm) {		
		this.gsm = gsm;
	}
	
	public abstract void init() throws IOException;
	public abstract void draw(Graphics2D g2);
	public abstract void update()throws IOException ;
	public abstract void keyPressed(KeyEvent e);
	public abstract void keyReleased(KeyEvent e);

}
