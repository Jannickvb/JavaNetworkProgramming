package barrelRunClientModel;

import java.awt.geom.Rectangle2D;

public class Ship {

	public final static Rectangle2D player1 = new Rectangle2D.Double(),player2 = new Rectangle2D.Double();
	private Rectangle2D ship;
	
	public Ship(Rectangle2D ship) {
		super();
		this.ship = ship;
	}	
}
