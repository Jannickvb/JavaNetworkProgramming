package model.entity;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import model.Animation;

public class Rock extends Entity {

	private Animation animation;
	private BufferedImage currentImage;
	public Rock(BufferedImage image, Point2D position) {
		super(image, position);
		animation = new Animation(image, 70, image.getHeight());
		init();
	}

	@Override
	public void draw(Graphics2D g2) {
		super.draw(g2);
		g2.drawImage(currentImage, tx, null);
	};
	
	@Override
	public void update() {
		super.update();
		position.setLocation(position.getX(), position.getY()+3);
		currentImage = animation.giveNext();
	}
}
