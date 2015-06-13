package model.entity;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public abstract class Entity{

	protected BufferedImage image;
	protected Point2D position;
	protected double rotation = 0;
	protected AffineTransform tx;
	
	public Entity(BufferedImage image, Point2D position) {		
		this.image = image;
		this.position = position;
	}
	
	public Entity(BufferedImage image, Point2D position, double rotation) {		
		this.image = image;
		this.position = position;
		this.rotation = rotation;
	}
	
	public abstract void init();
	public abstract void draw(Graphics2D g2);
	public void update(){
		tx = new AffineTransform();
		tx.rotate(Math.toRadians(rotation),position.getX() + image.getWidth()/2,position.getY() + image.getHeight()/2);
		tx.translate(position.getX(), position.getY());
	}
}
