package model.entity;

import java.awt.geom.Rectangle2D;

import control.GameStateManager;

public class Player {

	public final static Rectangle2D 
	player1 = new Rectangle2D.Double(GameStateManager.getWidth()/2-100,GameStateManager.getHeight()-100,100,100),
	player2 = new Rectangle2D.Double(GameStateManager.getWidth()/2,GameStateManager.getHeight()-100,100,100);
	private Rectangle2D ship;
	
	public Player(Rectangle2D ship) {
		super();
		this.ship = ship;
	}

	public Rectangle2D getShip() {
		return ship;
	}

	public void setShip(Rectangle2D ship) {
		this.ship = ship;
	}	
	
	public void setX(double x){
		ship.setFrame(x, ship.getY(), ship.getWidth(), ship.getHeight());
	}
	
	public double getX(){
		return ship.getX();
	}
	
}
