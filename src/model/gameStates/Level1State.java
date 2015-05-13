package model.gameStates;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import model.PlayField;
import control.GameStateManager;

public class Level1State extends GameState{

	private PlayField pf;
	private GameStateManager gsm;
	public Level1State(GameStateManager gsm) {
		super(gsm);
		this.gsm = gsm;
		pf = new PlayField(gsm.gameControl.getWidth(),gsm.gameControl.getHeight());
	}

	@Override
	public void draw(Graphics2D g2) {
		int width = gsm.gameControl.getWidth();
		int height = gsm.gameControl.getHeight();
		g2.translate(width/2 - pf.width/2, height/2 -pf.height/2);
		pf.drawGrid(g2);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

}
