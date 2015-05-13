package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

public class PlayField {

	public static final int tileWidth = 25, tileHeight = 25;
	private int row,column,widthInset,heightInset;
	public int width,height;
	public ArrayList<ArrayList<Integer>> coordinates;
	public PlayField(double width, double height){
		row = (int) Math.round(height/tileHeight);
		column = (int) Math.round(height/tileWidth);
		widthInset = (int) width%tileWidth;
		heightInset = (int) height%tileHeight;
		this.width = (int) (height - widthInset);
		this.height = (int) (height - heightInset);
		coordinates = new ArrayList<ArrayList<Integer>>();
		for(int r = 0; r < row+1; r++){
			ArrayList<Integer> row = new ArrayList<Integer>();
			for(int c = 0; c < column+1; c++){
				row.add(c);
			}
			coordinates.add(row);
		}
	}
	
	public int getXCoordinate(int column){
		int x = column * tileWidth;
		return x;
	}
	
	public int getYCoordinate(int row){
		int y = row * tileHeight;
		return y;
	}
	
	public void drawGrid(Graphics2D g2){
		for(int y = 0; y < coordinates.size()-1;y++)
		{
			Rectangle rectY = new Rectangle(0, y*tileHeight, tileHeight, tileHeight);
			g2.draw(rectY);
			for(int x = 0; x < coordinates.get(x).size()-1;x++){
				Rectangle rectX = new Rectangle(x*tileHeight, y*tileHeight, tileHeight, tileHeight);
				g2.draw(rectX);
			}
		}
		for(int r = 0; r < row+1; r++){
			g2.drawLine(0, r*tileHeight, height, r*tileHeight);
		}
		for(int c = 0; c < column+1; c++){
			g2.drawLine(c*tileWidth, 0, c*tileWidth, height);
		}
	}
}
