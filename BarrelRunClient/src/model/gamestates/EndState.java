package model.gamestates;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import control.ControlManager;
import control.GameStateManager.StateType;
import control.ImageHandler;

public class EndState extends GameState {

	private PlayState playState;
	private String winStatus;
	private BufferedImage bg;
	
	public EndState(ControlManager cm) {
		super(cm);
		bg = ImageHandler.getScaledImage(ImageHandler.getImage(ImageHandler.ImageType.wijzeIndiaan));
		playState = (PlayState) cm.getGameStateManager().getState(StateType.play);
		if(playState.win){
			winStatus = "YOU WON";
		}else{
			winStatus = "OMG NOOB";
		}
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.drawImage(bg,0,0,null);
		drawCenteredText("GAME OVER",cm.getGameStateManager().getHeight()/5,g2);		
		drawCenteredText(winStatus,cm.getGameStateManager().getHeight()/4,g2);
		drawCenteredText("Press enter to go back to the Menu", cm.getGameStateManager().getHeight()/3, g2);
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
		switch(e.getKeyCode()){
		case KeyEvent.VK_ENTER:
			cm.getGameStateManager().reset();
			cm.getGameStateManager().setState(StateType.menu);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void init() {
		
	}

}
