package model.entity;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import model.Animation;

public class Barrel extends Entity {

	private Animation animation;
	private BufferedImage currentImage;
	int x, y;
	private Rectangle2D bounds;
	private boolean dead = false;
	
	public Barrel(BufferedImage image, Point2D position) {
		super(image, position);
		animation = new Animation(image, 70, image.getHeight());
		currentImage = animation.giveNext();
		bounds = new Rectangle2D.Double(position.getX(), position.getY(),
				currentImage.getWidth(), currentImage.getHeight());
	}

	@Override
	public void init() {}
	
	@Override
	public void draw(Graphics2D g2) {
		g2.drawImage(currentImage, null,
					(int) position.getX(),
					(int) position.getY());
		g2.draw(bounds);
	};

	@Override
	public void update() {
		position.setLocation(position.getX(), position.getY() + 3);
		currentImage = animation.giveNext();
		bounds = new Rectangle2D.Double(position.getX(), position.getY(),
				currentImage.getWidth(), currentImage.getHeight());
	}

	public Point2D getPosition(){
		return position;
	}
	
	public Rectangle2D getBounds() {
		return bounds;
	}

}
