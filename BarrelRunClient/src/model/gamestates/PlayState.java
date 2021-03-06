package model.gamestates;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.entity.Barrel;
import model.entity.Player;
import model.entity.PlayerIndication;
import control.ControlManager;
import control.GameStateManager.StateType;
import control.ImageHandler;
import control.ImageHandler.ImageType;

public class PlayState extends GameState {

	private PlayerIndication playerIndication;
	private Player player1,player2;
	private int id;	
	private boolean left,right;
	public boolean win;
	private List<Barrel> rocks;
	private BufferedImage bg;
	
	public PlayState(ControlManager cm) {
		super(cm);
		bg = ImageHandler.getScaledImage(ImageHandler.getImage(ImageHandler.ImageType.mooizand));
	}
	
	@Override
	public void init(){
		left = false;
		right = false;
		int frameWidth = cm.getGameStateManager().getWidth();
		int frameHeight = cm.getGameStateManager().getHeight();
		rocks = new ArrayList<Barrel>();	
		try{
//		System.out.println(gsm.client.fromServer.readInt());
			id = cm.getGameStateManager().client.fromServer.readInt();
			cm.getGameStateManager().client.toServer.writeInt(frameWidth);
			cm.getGameStateManager().client.toServer.writeInt(frameHeight);
		}catch(IOException e){
			e.printStackTrace();
		}
		
		Point2D p1pos = new Point2D.Double(frameWidth/4, frameHeight - 103);
		Point2D p2pos = new Point2D.Double((frameWidth/4) * 3, frameHeight - 103);
		if(id == 0){
			player1 = new Player(ImageHandler.getImage(ImageType.player1),p1pos);
			player2 = new Player(ImageHandler.getImage(ImageType.player2),p2pos);
		}else if(id == 1){
			player1 = new Player(ImageHandler.getImage(ImageType.player2),p2pos);
			player2 = new Player(ImageHandler.getImage(ImageType.player1),p1pos);
		}
		playerIndication = new PlayerIndication(new Point2D.Double(cm.getGameStateManager().getWidth()-600, 0), id);
		playerIndication.init();
	}	
	
	@Override
	public void draw(Graphics2D g2) {	
		g2.drawImage(bg, 0, 0, null);
		if(player1 != null){		
			player2.draw(g2);
			player1.draw(g2);
			
		}
		
		playerIndication.draw(g2);
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
		
		//update je player
		//TEMP: player movement limited to screen bounds
		if(right && !left)
		{
			if(player1.getX() < cm.getGameStateManager().getWidth() - player1.getBounds().getWidth())
				player1.setX(player1.getX()+8);
		}
		if(!right && left)
		{
			if(player1.getX() > 0)
				player1.setX(player1.getX()-8);
		}
		
		checkBarrelBounds();
		try{
			checkPlayerCollision();		
			refreshData();
		}catch(IOException e){
			System.err.println("no connection with server");
		}
		player1.update();
		player2.update();
		playerIndication.update();
	}

	public void refreshData() throws IOException{
		if(cm.getGameStateManager().client.fromServer.readBoolean())
		{
			int pID = cm.getGameStateManager().client.fromServer.readInt();
			if(pID != id)
				win = true;
			else{
				win = false;
			}
			cm.getGameStateManager().gameOver();
			cm.getGameStateManager().setState(StateType.end);
		}
		
		//schrijf je eigen positie weg
		cm.getGameStateManager().client.toServer.writeDouble(player1.getX());
		
		//haal de positie van de andere op
		player2.setX(cm.getGameStateManager().client.fromServer.readDouble());
		
		String[]barrelPosition = cm.getGameStateManager().client.fromServer.readUTF().split(":");
		
		if(rocks.size() < 10)
		{
			rocks.add(	new Barrel(ImageHandler.getImage(ImageType.barrel), 
						new Point2D.Double(Double.parseDouble(barrelPosition[0]),
										   Double.parseDouble(barrelPosition[1]))));
		}
	}
	
	public void checkPlayerCollision() throws IOException{
		cm.getGameStateManager().client.toServer.writeBoolean(false);
		for(Barrel barrel:rocks)
			if(player1.containsPoint(barrel)){
				cm.getGameStateManager().client.toServer.writeBoolean(true);
				cm.getGameStateManager().client.toServer.writeInt(id);
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
