package barrelRunClientModel.gameState;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.IOException;

import barrelRunClientControl.GameStateManager;
import barrelRunClientModel.Ship;

public class PlayState extends GameState {

	private Ship player1,player2;
	private int id;	
	private boolean left,right;
	
	public PlayState(GameStateManager gsm) {
		super(gsm);		
	}

	@Override
	public void draw(Graphics2D g2) {	
		g2.setColor(Color.GREEN);
		g2.drawLine(GameStateManager.getWidth()/2, 0, GameStateManager.getWidth()/2, GameStateManager.getHeight());
		if(player1 != null){
			if(id == 0){
				g2.setColor(Color.BLUE);
				g2.fill(player1.getShip());
				g2.setColor(Color.RED);
				g2.fill(player2.getShip());
			}else if(id == 1){
				g2.setColor(Color.RED);
				g2.fill(player1.getShip());
				g2.setColor(Color.BLUE);
				g2.fill(player2.getShip());
			}			
		}		
	}

	@Override
	public void update() throws IOException {		
		//schrijf je eigen positie weg
		
//		System.out.println("ID: "+id+"\tPlayer1: "+player1.getShip());
		gsm.client.toServer.writeDouble(player1.getX());
		
		//haal de positie van de andere op
		
//		System.out.println("ID: "+id+"\tPlayer2 positie: "+gsm.client.fromServer.readDouble());
		player2.setX(gsm.client.fromServer.readDouble());
		
		//update je player
		if(right && !left){
			player1.setX(player1.getX()+5);
		}
		if(!right && left){
			player1.setX(player1.getX()-5);
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {		
		switch(e.getKeyCode()){
		case KeyEvent.VK_RIGHT:
			right = true;
			break;
		case KeyEvent.VK_LEFT:
			left = true;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {		
		switch(e.getKeyCode()){
		case KeyEvent.VK_RIGHT:
			right = false;
			break;
		case KeyEvent.VK_LEFT:
			left = false;
			break;
		}
	}

	@Override
	public void init()throws IOException {
//		System.out.println(gsm.client.fromServer.readInt());
		id = gsm.client.fromServer.readInt();
		if(id == 0){
			player1 = new Ship(Ship.player1);
			player2 = new Ship(Ship.player2);
		}else if(id == 1){
			player1 = new Ship(Ship.player2);
			player2 = new Ship(Ship.player1);
		}
	}	
}
