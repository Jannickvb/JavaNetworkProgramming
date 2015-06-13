package model.gamestates;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.IOException;

import control.ControlManager;
import control.GameStateManager.StateType;

public class LoadingState extends GameState {

	private String loadingString = "Waiting for other player";
	
	public LoadingState(ControlManager cm) {
		super(cm);		
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.drawString(loadingString, cm.getGameStateManager().getWidth()/2-g2.getFontMetrics().stringWidth(loadingString)/2, 
									cm.getGameStateManager().getHeight()/2);
	}

	@Override
	public void update(){
		try {
			if(cm.getGameStateManager().client.fromServer.readUTF().equals("go")){
				cm.getGameStateManager().setState(StateType.play);
			}
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void init() {
		cm.getGameStateManager().createClient();
	}

}
