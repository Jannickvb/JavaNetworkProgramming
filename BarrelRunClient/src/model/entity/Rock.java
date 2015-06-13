package model.entity;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class Rock extends Entity {

	public Rock(BufferedImage image, Point2D position) {
		super(image, position);
		init();
	}

	@Override
	public void update() {
		position.setLocation(position.getX(), position.getY()+3);
		super.update();
	}
}
