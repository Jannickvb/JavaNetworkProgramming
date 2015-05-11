package model.gameStates;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import control.GameStateManager;

public abstract class GameState {
	protected GameStateManager gsm;
	public GameState(GameStateManager gsm){
		this.gsm = gsm;
	}
	public abstract void draw(Graphics2D g);
	public abstract void update();
	public abstract void keyPressed(KeyEvent e);
	public abstract void keyReleased(KeyEvent e);
}
