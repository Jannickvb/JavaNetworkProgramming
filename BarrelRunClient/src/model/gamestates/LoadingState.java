package model.gamestates;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.IOException;

import control.ControlManager;
import control.GameStateManager.StateType;

public class LoadingState extends GameState {
	
	public LoadingState(ControlManager cm) {
		super(cm);		
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.drawString("Waiting for other player",
					  cm.getGameStateManager().getWidth()/2-g2.getFontMetrics().stringWidth("Waiting for other player")/2, 
					  cm.getGameStateManager().getHeight()/2);
	}

	@Override
	public void update(){
		try {
			if(cm.getGameStateManager().client.fromServer.readUTF().equals("go")){
				cm.getGameStateManager().setState(StateType.play);
			}
		} catch (Exception e) {			
			System.out.println("Can't connect to server");
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
