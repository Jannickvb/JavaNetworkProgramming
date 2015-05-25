package model;

import java.awt.Graphics2D;

public abstract class Entity {
	protected int x,y,height,width,imageID;
	public Entity(int x,int y,int width,int height,int imageID) {
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		this.imageID = imageID;
	}
	public abstract void init();
	public abstract void draw(Graphics2D g);
	public abstract void update();
}
