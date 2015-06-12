package barrelRunClientModel.gameState;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.IOException;

import barrelRunClientControl.GameStateManager;
import barrelRunClientModel.Ship;

public class PlayState extends GameState {

	private Ship ship;
	
	public PlayState(GameStateManager gsm) {
		super(gsm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.setColor(Color.RED);
		g2.fillRect(gsm.getWidth()/2-25, gsm.getHeight()/2-25, 50, 50);

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
	public void init()throws IOException {
		System.out.println(gsm.client.fromServer.readInt());
		
	}

}
