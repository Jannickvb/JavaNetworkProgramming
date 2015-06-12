package barrelRunClientModel.gameState;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import barrelRunClientControl.GameStateManager;

public class LoadingState extends GameState {

	private String loadingString = "Waiting for other player";
	public LoadingState(GameStateManager gsm) {
		super(gsm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.drawString(loadingString, gsm.getWidth()/2-g2.getFontMetrics().stringWidth(loadingString)/2, gsm.getHeight()/2);
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
		gsm.makeClient();		
	}

}
