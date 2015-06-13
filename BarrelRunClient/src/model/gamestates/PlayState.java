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
import model.entity.Rock;
import control.ControlManager;
import control.ImageHandler;
import control.ImageHandler.ImageType;

public class PlayState extends GameState {

	private Player player1,player2;
	private int id;	
	private boolean left,right;
	private List<Rock> rocks;
	private Line2D bottomLine;
	private Stroke s;
	
	public PlayState(ControlManager cm) {
		super(cm);	
		rocks = new ArrayList<Rock>();		
		s = new BasicStroke(10);
	}

	@Override
	public void draw(Graphics2D g2) {	
//		g2.setColor(Color.GREEN);
//		g2.drawLine(cm.getGameStateManager().getWidth()/2, 0, cm.getGameStateManager().getWidth()/2, cm.getGameStateManager().getHeight());
		if(player1 != null){		
			player2.draw(g2);
			player1.draw(g2);
			
		}
		for(Rock r : rocks){
			r.draw(g2);
		}
		g2.setStroke(s);
		g2.draw(bottomLine);
	}

	@Override
	public void update(){
		bottomLine = new Line2D.Double(0,cm.getGameStateManager().getHeight(),
				cm.getGameStateManager().getWidth(),cm.getGameStateManager().getHeight());
		try{
			//schrijf je eigen positie weg
			
	//		System.out.println("ID: "+id+"\tPlayer1: "+player1.getShip());
			cm.getGameStateManager().client.toServer.writeDouble(player1.getX());
			
			//haal de positie van de andere op
			
	//		System.out.println("ID: "+id+"\tPlayer2 positie: "+gsm.client.fromServer.readDouble());			
			player2.setX(cm.getGameStateManager().client.fromServer.readDouble());
			
			String[]stonePosition = cm.getGameStateManager().client.fromServer.readUTF().split(":");
			if(rocks.size() < 20){
				rocks.add(	new Rock(ImageHandler.getImage(ImageType.stone), 
							new Point2D.Double(Double.parseDouble(stonePosition[0]), Double.parseDouble(stonePosition[1]))));
			}
			
		}catch(IOException e){
			e.printStackTrace();
		}
		
		//update je player
		if(right && !left){
			player1.setX(player1.getX()+5);
		}
		if(!right && left){
			player1.setX(player1.getX()-5);
		}
		Iterator<Rock> rockIterator = rocks.iterator();
		while(rockIterator.hasNext()){
			Rock rock = rockIterator.next();
			if(rock.getBorder().getBounds2D().intersectsLine(bottomLine)){
				rockIterator.remove();
			}else{
				rock.update();
			}
		}
		player1.update();
		player2.update();
		
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
}
