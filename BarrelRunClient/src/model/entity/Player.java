package model.entity;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import model.Animation;
import control.GameStateManager;

public class Player extends Entity{
	
	private Animation animation;
	private int keyFrame;
	public Player(BufferedImage image, Point2D position) {
		super(image, position);
		animation = new Animation(image,52,image.getHeight());
		keyFrame = 0;
	}

	@Override
	public void init() {
		
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.drawImage(image,tx,null);
	}
	
	@Override
	public void update(){
		super.update();
		keyFrame++;
		if(keyFrame%4 == 0){
			image = animation.giveNext();
		}
		if(keyFrame >= 30){
			keyFrame = 0;
		}
	}
	
	public double getX(){
		return position.getX();
	}

	public void setX(double x){
		position.setLocation(x, position.getY());
	}
	
}
