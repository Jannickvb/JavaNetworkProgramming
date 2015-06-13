package model.gamestates;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.IOException;

import control.GameStateManager;
import model.Ship;

public class PlayState extends GameState {

	private Ship ship;
	private int id;
	
	public PlayState(GameStateManager gsm) {
		super(gsm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics2D g2) {		
		if(ship != null){
			if(id == 0){
				g2.setColor(Color.BLUE);
			}else if(id == 1){
				g2.setColor(Color.RED);
			}
			g2.fill(ship.getShip());
		}
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
//		System.out.println(gsm.client.fromServer.readInt());
		id = gsm.client.fromServer.readInt();
		if(id == 0){
			ship = new Ship(Ship.player1);
		}else if(id == 1){
			ship = new Ship(Ship.player2);
		}
	}	
}
