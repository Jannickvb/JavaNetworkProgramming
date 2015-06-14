package model.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import model.Animation;
import control.ImageHandler;
import control.ImageHandler.ImageType;

public class PlayerIndication extends Entity {

	private int id;
	private final String you = "YOU",enemy = "ENEMY";
	private Animation animation;
	private BufferedImage currentImage;
	private Rectangle2D rectBlue,rectRed;
	
	public PlayerIndication(Point2D position,int id) {
		super(ImageHandler.getImage(ImageType.playerPrev), position);
		animation = new Animation(image,38,image.getHeight());
		currentImage = animation.giveNext();
		this.id = id;
		
		rectBlue = new Rectangle2D.Double(position.getX()-25 , 0, 200, 225);
		rectRed = new Rectangle2D.Double(position.getX() + 200, 0,200,225);
	}

	@Override
	public void init() {
		double x = position.getX();	
		//nu nog de gehele breedte van het scherm
		x -= image.getWidth();
		//nu de breedte vanaf het scherm - dat van het plaatje
		position.setLocation(x, position.getY());
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.setColor(Color.blue);
		g2.fill(rectBlue);
		g2.draw(rectBlue);
		
		g2.drawImage(currentImage,
				 null,
				 (int) position.getX() + 300 - currentImage.getWidth()/2,
				 (int) position.getY()+20);
		
		g2.setColor(Color.red);
		g2.fill(rectRed);
		g2.draw(rectRed);
		g2.drawImage(currentImage,
				 null,
				 (int) position.getX() + 525 - currentImage.getWidth()/2,
				 (int) position.getY()+20);
		g2.setColor(Color.BLACK);

		int x1 = 0,x2 = 0;
		if(id == 0){
			x1 = 300;
			x2 = 525;
		}else{			
			x1 = 525;
			x2 = 300;
		}
		g2.drawString(you,(int)position.getX() - g2.getFontMetrics().stringWidth(you)/2 + x1, currentImage.getHeight()+g2.getFontMetrics().getHeight()+20);
		g2.drawString(enemy,(int)position.getX() - g2.getFontMetrics().stringWidth(enemy)/2 + x2,currentImage.getHeight()+g2.getFontMetrics().getHeight()+20);
		
	}

	@Override
	public void update() {	
		currentImage = animation.giveNext();
	}

}
