package model.gamestates;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.IOException;

import control.ControlManager;
import control.GameStateManager;
import control.GameStateManager.StateType;

public class LoadingState extends GameState {

	private String loadingString = "Waiting for other player";
	private GameStateManager gsm;
	public LoadingState(ControlManager cm) {
		super(cm);
		gsm = cm.getGameStateManager();
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.drawString(loadingString, gsm.getWidth()/2-g2.getFontMetrics().stringWidth(loadingString)/2, gsm.getHeight()/2);
	}

	@Override
	public void update(){
		try {
			if(gsm.client.fromServer.readUTF().equals("go")){
				gsm.setState(StateType.play);
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
		gsm.createClient();
	}

}
