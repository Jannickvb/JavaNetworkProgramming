package model.entity;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import control.ImageHandler;
import control.ImageHandler.ImageType;
import model.Animation;
import model.MyGeneric;

public class MenuBarrel extends Entity {

	public int size;
	public Ellipse2D bounds;
	public Point2D position;
	private Animation animation;
	private BufferedImage currentImage;

	public MenuBarrel(Point2D position, int size) {
		super(ImageHandler.getImage(ImageType.barrel_side), position);
		animation = new Animation(image, 80, image.getHeight());
		currentImage = animation.giveNext();
		this.size = size;
		bounds = new Ellipse2D.Double(position.getX(), position.getY(), size,
				size);
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Graphics2D g2) {
		g2.draw(bounds);
//		g2.drawImage(currentImage, (int) position.getX(),
//				(int) position.getY(), size, size, null);
	}

	@Override
	public void update() {
		currentImage = animation.giveNext();
		position.setLocation(position.getX() + 5, position.getY());
	}

}
