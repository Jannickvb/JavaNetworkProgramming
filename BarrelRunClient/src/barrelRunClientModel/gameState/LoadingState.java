package barrelRunClientModel.gameState;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.IOException;

import barrelRunClientControl.GameStateManager;
import barrelRunClientControl.GameStateManager.StateType;

public class LoadingState extends GameState {

	private String loadingString = "Waiting for other player";
	public LoadingState(GameStateManager gsm) {
		super(gsm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.drawString(loadingString, GameStateManager.getWidth()/2-g2.getFontMetrics().stringWidth(loadingString)/2, GameStateManager.getHeight()/2);
	}

	@Override
	public void update()throws IOException{
		if(gsm.client.fromServer.readUTF().equals("go")){
			gsm.setState(StateType.play);
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

	@Override
	public void init() {
		gsm.makeClient();		
	}

}
