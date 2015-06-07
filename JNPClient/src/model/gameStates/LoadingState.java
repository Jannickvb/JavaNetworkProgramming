package model.gameStates;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.IOException;

import control.GameStateManager;

public class LoadingState extends GameState{
	
	public LoadingState(GameStateManager gsm) {
		super(gsm);

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		if(this.gsm.gameControl.gameReady){
			gsm.initPlayState();
			try {
				gsm.select(3);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
