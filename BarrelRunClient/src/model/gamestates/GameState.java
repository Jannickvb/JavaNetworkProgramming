package model.gamestates;

import java.awt.event.KeyEvent;

import model.Constants;
import control.GameStateManager;

public abstract class GameState implements Constants{

	public GameStateManager gsm;

	public GameState(GameStateManager gsm) {		
		this.gsm = gsm;
	}
	
	public abstract void keyPressed(KeyEvent e);
	public abstract void keyReleased(KeyEvent e);

}
