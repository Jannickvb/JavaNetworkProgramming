package model;

import java.awt.Graphics2D;

public class PlayField {

	public static final int tileWidth = 25, tileHeight = 25;
	private int row,column,width,height,widthInit,heightInit;
	
	public PlayField(double width, double height){			
		row = (int) Math.round(height/tileHeight);
		column = (int) Math.round(width/tileWidth);
		widthInit = (int) width%tileWidth;
		heightInit = (int) height%tileHeight;
		this.width = (int) (width - widthInit);
		this.height = (int) (height - heightInit);
	}
	
	public void drawGrid(Graphics2D g2){	
		for(int r = 0; r < row+1; r++){
			g2.drawLine(0, r*tileHeight, width, r*tileHeight);		
		}
		
		for(int c = 0; c < column+1; c++){
			g2.drawLine(c*tileWidth, 0, c*tileWidth, height);	
		}
		
	}
}
