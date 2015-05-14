package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class PlayField {

	public static final int tileWidth = 25, tileHeight = 25;
	private int row,column,widthInset,heightInset;
	public int width,height;
	public ArrayList<ArrayList<PlayerTile>> coordinates;
	public PlayField(double width, double height){
		row = (int) Math.round(height/tileHeight);
		column = (int) Math.round(height/tileWidth);
		widthInset = (int) width%tileWidth;
		heightInset = (int) height%tileHeight;
		this.width = (int) (height - widthInset);
		this.height = (int) (height - heightInset);
		coordinates = new ArrayList<ArrayList<PlayerTile>>();
		for(int r = 0; r < row; r++){
			ArrayList<PlayerTile> row = new ArrayList<PlayerTile>();
			for(int c = 0; c < column; c++){
				row.add(new PlayerTile(r*tileHeight, c*tileHeight, tileHeight, tileHeight));
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
		for(ArrayList<PlayerTile> r: coordinates)
		{
			for(PlayerTile tile: r){
				if(tile.isUsable())
					g2.setColor(Color.red);
				else if(tile.isUsedByPlayer())
					g2.setColor(Color.blue);
				else
					g2.setColor(Color.cyan);
				g2.draw(tile);
				g2.fill(tile);
			}
		}
		g2.setColor(Color.black);
		g2.setStroke(new BasicStroke(2));
		for(int r = 0; r < row+1; r++){
			g2.drawLine(0, r*tileHeight, height, r*tileHeight);
		}
		for(int c = 0; c < column+1; c++){
			g2.drawLine(c*tileWidth, 0, c*tileWidth, height);
		}
	}
}
