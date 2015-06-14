package model.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import model.Animation;

public class Player extends Entity {
	
	private Animation animation;
	private BufferedImage currentImage;
	private Rectangle2D bounds;

	public Player(BufferedImage image, Point2D position) {
		super(image, position);		
		animation = new Animation(image, 52, image.getHeight());
		currentImage = animation.giveNext();
		bounds = new Rectangle2D.Double(position.getX(),
										position.getY(),
										currentImage.getWidth(),
										currentImage.getHeight());
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Graphics2D g2) {
		g2.drawImage(currentImage,
					 null,
					 (int) position.getX(),
					 (int) position.getY());
		g2.setColor(Color.BLACK);
		g2.draw(bounds);
	}

	@Override
	public void update() {
		currentImage = animation.giveNext();
		bounds = new Rectangle2D.Double(position.getX()+5,
										position.getY()+24,
										currentImage.getWidth()-10,
										currentImage.getHeight()-24);
	}

	public double getX() {
		return position.getX();
	}

	public void setX(double x) {
		position.setLocation(x, position.getY());
	}

	public Rectangle2D getBounds() {
		return bounds;
	}
	
	public boolean containsPoint(Barrel barrel) {
		Rectangle2D objectRectangle = barrel.getBounds();
		if(bounds.intersects(objectRectangle)) 
			return true;
		else
			return false;
	}
}
