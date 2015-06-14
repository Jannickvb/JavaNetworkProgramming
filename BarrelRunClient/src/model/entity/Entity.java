package model.entity;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public abstract class Entity{

	protected BufferedImage image;
	protected Point2D position;
	
	public Entity(BufferedImage image, Point2D position) {		
		this.image = image;
		this.position = position;
	}
	public abstract void init();
	public abstract void draw(Graphics2D g2);
	public abstract void update();	
}
