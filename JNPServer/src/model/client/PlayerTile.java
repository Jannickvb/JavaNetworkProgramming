package model.client;

import java.awt.Rectangle;

public class PlayerTile extends Rectangle{
	private int x,y,width,height;
	private boolean isUsedByPlayer,isUsable,selected;	
	
	public PlayerTile(int x, int y, int width, int height){
		super(x,y,width,height);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public boolean isUsedByPlayer() {
		return isUsedByPlayer;
	}

	public void setUsedByPlayer(boolean isUsedByPlayer) {
		this.isUsedByPlayer = isUsedByPlayer;
	}

	public boolean isUsable() {
		return isUsable;
	}

	public void setUsable(boolean isUsable) {
		this.isUsable = isUsable;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	
	
}

