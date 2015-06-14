package model.entity;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import model.Animation;

public class MenuBarrel extends Entity {

	public int size;
	private Animation animation;
	private BufferedImage currentImage;
	
	public MenuBarrel(BufferedImage image,Point2D position, int size) {
		super(image, position);
		animation = new Animation(image, 80, image.getHeight());
		currentImage = animation.giveNext();
		this.size = size;

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Graphics2D g2) {
		g2.drawImage(currentImage, (int) position.getX(),
				(int) position.getY(), size, size, null);
	}

	@Override
	public void update() {
		currentImage = animation.giveNext();
		position.setLocation(position.getX() + 5, position.getY());
	}
	
	public Integer getX(){
		Double x = position.getX();
		return x.intValue();
	}

}
