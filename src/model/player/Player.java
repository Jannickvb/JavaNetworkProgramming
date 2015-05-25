package model.player;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import model.Entity;
import model.PlayField;
import control.StatManager;

public class Player extends Entity{

	private int[] stats;
	private String[] statNames;
	private StatManager sm;
	private PlayField pf;
	private int x,y;
	public int attack, health, range, mana;
	private boolean isActive;
	public Player(int x,int y,int width,int height,int imageID,PlayField pf ,StatManager sm){
		super(x,y,width,height,imageID);
		this.pf = pf;
		this.sm = sm;
		this.x = x;
		this.y = y;
		this.statNames = sm.getStatNames();
		this.stats = sm.getStatData();
		init();
	}
	
	@Override
	public void init() {
		attack = stats[1] * 20;
		health = stats[0] * 10 + 100;
		range = stats[2] + 1;
		mana = stats[3] * 10 + 100;
	}

	@Override
	public void draw(Graphics2D g) {
		g.draw(new Ellipse2D.Double(x, y, width, height));
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getPlayFieldX(){
		int pfX = x / PlayField.tileWidth;
		return pfX;
	}
	
	public int getPlayFieldY(){
		int pfY = y / PlayField.tileHeight;
		return pfY;
	}
	
	public boolean isActive() {
		return isActive;
	}
	
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	public int getStat(int i) {
		return stats[i];
	}
	public String getStatName(int i) {
		return statNames[i];
	}
	public StatManager getStatManager() {
		return sm;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	

}
