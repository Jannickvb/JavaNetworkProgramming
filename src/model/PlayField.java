package model;

import java.awt.Graphics2D;

public class PlayField {

	public static final int tileWidth = 25, tileHeight = 25;
	private int row,colom,width,height;
	
	public PlayField(int width, int height){
		this.width = width;
		this.height = height;
		row = width/tileWidth;
		colom = height / tileHeight;	
		System.out.println(row+" - "+colom);
	}
	
	public void drawGrid(Graphics2D g2){	
		for(int r = 0; r < row+1; r++){
			g2.drawLine(0,r*tileHeight,width,r*tileHeight);			
		}
		
		for(int c = 0; c < colom+1; c++){
			g2.drawLine(c*tileWidth, 0, c*tileWidth, height);			
		}
		
	}
}
