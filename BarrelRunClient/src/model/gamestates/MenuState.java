package model.gamestates;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import control.ControlManager;
import control.GameStateManager.StateType;

public class MenuState extends GameState {

	private String startString;	
	
	public MenuState(ControlManager cm) {
		super(cm);		
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.drawString(startString, cm.getGameStateManager().getWidth()/2-g2.getFontMetrics().stringWidth(startString)/2, cm.getGameStateManager().getHeight()/2);

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){
		case KeyEvent.VK_ENTER:
			System.out.println(StateType.wait);
			cm.getGameStateManager().setState(StateType.wait);
			break;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		startString = "Press enter to start";		
	}

}
