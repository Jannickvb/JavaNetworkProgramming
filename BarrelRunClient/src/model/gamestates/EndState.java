package model.gamestates;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import control.ControlManager;
import control.GameStateManager.StateType;

public class EndState extends GameState {

	PlayState playState;
	String winStatus;
	public EndState(ControlManager cm) {
		super(cm);
		playState = (PlayState) cm.getGameStateManager().getState(StateType.play);
		if(playState.win){
			winStatus = "YOU WON";
		}else{
			winStatus = "OMG NOOB";
		}
	}

	@Override
	public void draw(Graphics2D g2) {
		drawCenteredText("GAME OVER",300,g2);		
		drawCenteredText(winStatus,400,g2);
	}

	public void drawCenteredText(String text,int y, Graphics2D g2){
		int stringWidth = g2.getFontMetrics().stringWidth(text);
		g2.drawString(text, cm.getGameStateManager().getWidth()/2-stringWidth/2, y);
	}
	
	@Override
	public void update() {

	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void init() {
		
	}

}
