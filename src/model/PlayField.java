package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class PlayField {

	public static final int tileWidth = 32, tileHeight = 32;
	private int row,column,widthInset,heightInset;
	public int width,height;
	public ArrayList<ArrayList<PlayerTile>> coordinates;
	public PlayField(double width, double height){
//		row = (int) Math.round(height/tileHeight);
//		column = (int) Math.round(height/tileWidth);
		row = 17;
		column = 17;
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
	/**
	 * @param column
	 * @return Integer
	 * Returns x-coordinate of the tile the player is on.
	 */
	public int getXCoordinate(int column){
		int x = column * tileWidth;
		return x;
	}
	/**
	 * @param column
	 * @return Integer
	 * Returns y-coordinate of the tile the player is on.
	 */
	public int getYCoordinate(int row){
		int y = row * tileHeight;
		return y;
	}
	/**
	 * @param Graphics2D g2
	 * Draws the playfield
	 */
	public void drawGrid(Graphics2D g2){
		g2.setStroke(new BasicStroke(0));
		for(ArrayList<PlayerTile> r: coordinates)
		{
			for(PlayerTile tile: r){
				if(tile.isUsable())
					g2.setColor(new Color(1f,0f,0f,0.2f));//transparant rood
				else if(tile.isUsedByPlayer())
					g2.setColor(new Color(0.8f,0.8f,0.8f,0.8f));//licht grijs
				else if(tile.isSelected())
					g2.setColor(new Color(1f,0f,0f,0.4f));//minder transparant rood
				else
					g2.setColor(new Color(0.1f,0.1f,0.1f,0.5f));//doorzichtig grijs
				g2.draw(tile);
				g2.fill(tile);
			}
		}
		g2.setColor(Color.black);
		final float dash[] = {5.0f};
		g2.setStroke(new BasicStroke(1.0f,
                BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_MITER,
                10.0f, dash, 0.0f));
		for(int r = 0; r < row+1; r++){
			g2.drawLine(0, r*tileHeight, height, r*tileHeight);
		}
		for(int c = 0; c < column+1; c++){
			g2.drawLine(c*tileWidth, 0, c*tileWidth, height);
		}
	}
}
