package model.entity;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import model.Animation;
import control.GameStateManager;

public class Player extends Entity{
	
	private Animation animation;
	private BufferedImage currentImage;
	public Player(BufferedImage image, Point2D position) {
		super(image, position);
		animation = new Animation(image,52,image.getHeight());
	}

	@Override
	public void init() {
		
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.drawImage(currentImage,tx,null);
	}
	
	@Override
	public void update(){
		super.update();
	    currentImage = animation.giveNext();
	}
	
	public double getX(){
		return position.getX();
	}

	public void setX(double x){
		position.setLocation(x, position.getY());
	}
	
}
