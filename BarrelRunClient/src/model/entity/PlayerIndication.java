package model.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import control.ImageHandler;
import control.ImageHandler.ImageType;

public class PlayerIndication extends Entity {

	private Rectangle2D border; 
	private int id;
	private final String you = "YOU",enemy = "ENEMY";
	
	public PlayerIndication(Point2D position,int id) {
		super(ImageHandler.getImage(ImageType.playerI), position);
		this.id = id;		
	}

	@Override
	public void init() {
		double x = position.getX();	
		//nu nog de gehele breedte van het scherm
		x -= image.getWidth();
		//nu de breedte vanaf het scherm - dat van het plaatje
		position.setLocation(x, position.getY());
		border = new Rectangle2D.Double(position.getX(),position.getY(),image.getWidth(),image.getHeight());		
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.drawImage(image,
				 null,
				 (int) position.getX(),
				 (int) position.getY());
		g2.setColor(Color.BLACK);
		g2.draw(border);
		int x1 = (int) border.getX(),x2 = (int) border.getX(),quaterOfPlayerIndicatorImage = image.getWidth()/4;	//één vierde van het plaatje
		if(id == 0){
			x1 += quaterOfPlayerIndicatorImage;
			x2 += quaterOfPlayerIndicatorImage*3;
		}else{			
			x1 += quaterOfPlayerIndicatorImage*3;	
			x2 += quaterOfPlayerIndicatorImage;
		}
		g2.drawString(you, x1-g2.getFontMetrics().stringWidth(you), image.getHeight()+g2.getFontMetrics().getHeight());
		g2.drawString(enemy, x2-g2.getFontMetrics().stringWidth(enemy),image.getHeight()+g2.getFontMetrics().getHeight());
		
	}

	@Override
	public void update() {	
		
	}

}
