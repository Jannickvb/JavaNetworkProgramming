package model.gameStates;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import control.GameStateManager;

public class CreateState extends GameState{

	GameStateManager gsm;
	private String[] stats = {"Stamina","Strength","Dexterity","Intelligence"};
	private int points = 10;
	public CreateState(GameStateManager gsm) {
		super(gsm);
		this.gsm = gsm;
	}

	@Override
	public void init() {
		points = 0;
		
	}
	
	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
		{
			gsm.select(0);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
