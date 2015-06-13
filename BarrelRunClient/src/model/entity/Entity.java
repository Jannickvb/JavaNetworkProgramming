package model.entity;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import model.Constants;

public class Entity implements Constants{

	private BufferedImage img,sprite;
	private Point2D position;
	private double rotation = 0;
	private int indexX = 0,indexY = 0, oneSpriteWidth, oneSpriteHeight;
	private AffineTransform tx;
	
	/**
	 * 
	 * @param img, Het complete plaatje voor je entity
	 * @param position, De plaats waar de sprite is op het moment.
	 * @param rotation, De rotatie van de sprite
	 * @param oneSpriteWidth, De breedte van 1 sprite
	 * @param oneSpriteHeight, De hoogte van 1 sprite
	 */
	public Entity(BufferedImage img, Point2D position, double rotation,int oneSpriteWidth,int oneSpriteHeight) {		
		this.img = img;
		this.position = position;
		this.rotation = rotation;
		this.oneSpriteWidth = oneSpriteHeight;
		this.oneSpriteHeight = oneSpriteHeight;
		update();
	}
	
	/**
	 * De rotatie is standaard 0;
	 * @param img, Het complete plaatje van je entity
	 * @param position, De plaats waar de sprite is op het moment.
	 * @param oneSpriteWidth, De breedte van 1 sprite
	 * @param oneSpriteHeight, De hoogte van 1 sprite
	 */
	public Entity(BufferedImage img, Point2D position, int oneSpriteWidth,int oneSpriteHeight) {		
		this.img = img;
		this.position = position;
		this.oneSpriteWidth = oneSpriteWidth;
		this.oneSpriteHeight = oneSpriteHeight;		
		update();
	}



	@Override
	public void init(){
		if(img != null){
			sprite = img.getSubimage(indexX*oneSpriteHeight, indexY * oneSpriteHeight, oneSpriteWidth, oneSpriteHeight);
		}
	}

	@Override
	public void draw(Graphics2D g2) {		
		if(sprite != null){
			g2.drawImage(sprite, tx, null);
		}
	}

	@Override
	public void update(){		
		AffineTransform tx = new AffineTransform();		
		tx.rotate(Math.toRadians(rotation), position.getX()+oneSpriteWidth/2, position.getY()+oneSpriteHeight/2);
		tx.translate(position.getX(), position.getY());
	}	
}
