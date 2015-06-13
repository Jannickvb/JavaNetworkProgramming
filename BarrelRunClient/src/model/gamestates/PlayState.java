package model.gamestates;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.entity.Player;
import model.entity.Barrel;
import control.ControlManager;
import control.GameStateManager.StateType;
import control.ImageHandler;
import control.ImageHandler.ImageType;

public class PlayState extends GameState {

	private Player player1,player2;
	private int id;	
	private boolean left,right;
	private List<Barrel> rocks;
	
	public PlayState(ControlManager cm) {
		super(cm);	
		rocks = new ArrayList<Barrel>();		
	}
	
	@Override
	public void init(){
		try{
//		System.out.println(gsm.client.fromServer.readInt());
			id = cm.getGameStateManager().client.fromServer.readInt();
		}catch(IOException e){
			e.printStackTrace();
		}
		int frameWidth = cm.getGameStateManager().getWidth();
		int frameHeight = cm.getGameStateManager().getHeight();
		Point2D p1pos = new Point2D.Double(frameWidth/4, frameHeight - 103);
		Point2D p2pos = new Point2D.Double((frameWidth/4) * 3, frameHeight - 103);
		if(id == 0){
			player1 = new Player(ImageHandler.getImage(ImageType.player1),p1pos);
			player2 = new Player(ImageHandler.getImage(ImageType.player2),p2pos);
		}else if(id == 1){
			player1 = new Player(ImageHandler.getImage(ImageType.player2),p2pos);
			player2 = new Player(ImageHandler.getImage(ImageType.player1),p1pos);
		}
		
	}	
	
	@Override
	public void draw(Graphics2D g2) {	
//		g2.setColor(Color.GREEN);
//		g2.drawLine(cm.getGameStateManager().getWidth()/2, 0, cm.getGameStateManager().getWidth()/2, cm.getGameStateManager().getHeight());
		if(player1 != null){		
			player2.draw(g2);
			player1.draw(g2);
			
		}
		
		drawRocks(g2,0);
	}

	/**
	 * Recursive method to draw all rocks.
	 * @param g2 Graphics2D object
	 * @param i Index indicator
	 */
	
	public void drawRocks(Graphics2D g2, int i){
		int index = i;
		if(i<rocks.size()){
			rocks.get(index).draw(g2);
			index++;
			drawRocks(g2,index);
		}else{
			return;
		}
	}
	
	@Override
	public void update(){
		
		refreshData();
		
		//update je player
		if(right && !left){
			player1.setX(player1.getX()+5);
		}
		if(!right && left){
			player1.setX(player1.getX()-5);
		}
		
		checkBarrelBounds();
		checkPlayerCollision();
		
		player1.update();
		player2.update();
	}

	public void refreshData(){
		try{
			//schrijf je eigen positie weg
			cm.getGameStateManager().client.toServer.writeDouble(player1.getX());
			
			//haal de positie van de andere op
			player2.setX(cm.getGameStateManager().client.fromServer.readDouble());
			
			String[]barrelPosition = cm.getGameStateManager().client.fromServer.readUTF().split(":");
			if(rocks.size() < 20){
				rocks.add(	new Barrel(ImageHandler.getImage(ImageType.barrel), 
							new Point2D.Double(Double.parseDouble(barrelPosition[0]),
											   Double.parseDouble(barrelPosition[1]))));
			}
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void checkPlayerCollision(){
		for(Barrel barrel:rocks)
			if(player1.containsPoint(barrel)){
				//temp
				 cm.getGameStateManager().setState(StateType.end);
			}
	}
	
	public void checkBarrelBounds(){
		Iterator<Barrel> barrelIt = rocks.iterator();
		while(barrelIt.hasNext()){
			Barrel Barrel = barrelIt.next();
			if(Barrel.getPosition().getY()>cm.getGameStateManager().getHeight()){
				barrelIt.remove();
			}else{
				Barrel.update();
			}
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {		
		switch(e.getKeyCode()){
		case KeyEvent.VK_RIGHT:
			right = true;
			break;
		case KeyEvent.VK_LEFT:
			left = true;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {		
		switch(e.getKeyCode()){
		case KeyEvent.VK_RIGHT:
			right = false;
			break;
		case KeyEvent.VK_LEFT:
			left = false;
			break;
		}
	}

	
}
