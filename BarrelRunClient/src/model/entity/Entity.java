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
	protected double rotation = 0;
	protected AffineTransform tx;
	protected Rectangle2D border;
	
	public Entity(BufferedImage image, Point2D position) {		
		this.image = image;
		this.position = position;
		tx = new AffineTransform();
	}
	
	public Entity(BufferedImage image, Point2D position, double rotation) {		
		this.image = image;
		this.position = position;
		this.rotation = rotation;
	}
	
	public void init(){
		border = new Rectangle2D.Double(0,0,image.getWidth(),image.getHeight());
	}
	
	//dit gebeurt voor de player en voor de rock, dus hier zetten anders krijg je dubbele code
	public void draw(Graphics2D g2){
		g2.draw(getBorder());
	}
	
	public void update(){
		tx = new AffineTransform();
		tx.rotate(Math.toRadians(rotation),position.getX() + image.getWidth()/2,position.getY() + image.getHeight()/2);
		tx.translate(position.getX(), position.getY());		
	}
	
	public Shape getBorder(){
		System.out.println(border);
		return tx.createTransformedShape(border);
	}
}
