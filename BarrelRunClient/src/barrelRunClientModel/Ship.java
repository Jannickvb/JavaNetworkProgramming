package barrelRunClientModel;

import java.awt.geom.Rectangle2D;

import barrelRunClientControl.GameStateManager;

public class Ship {

	public final static Rectangle2D 
	player1 = new Rectangle2D.Double(GameStateManager.getWidth()/2-100,GameStateManager.getHeight()-100,100,100),
	player2 = new Rectangle2D.Double(GameStateManager.getWidth()/2+100,GameStateManager.getHeight()-100,100,100);
	private Rectangle2D ship;
	
	public Ship(Rectangle2D ship) {
		super();
		this.ship = ship;
	}

	public Rectangle2D getShip() {
		return ship;
	}

	public void setShip(Rectangle2D ship) {
		this.ship = ship;
	}	
	
	
}
