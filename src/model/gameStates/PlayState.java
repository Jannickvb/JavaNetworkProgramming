package model.gameStates;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import model.PlayField;
import model.PlayerTile;
import model.player.Player;
import control.GameStateManager;
import control.ImageController;
import control.ImageController.ImageType;
import control.StatManager;

public class PlayState extends GameState{

	private PlayField pf;
	private GameStateManager gsm;
	private ArrayList<Player> players = new ArrayList<Player>();
	private BufferedImage bg;
	private boolean up,down,left,right;
	private  int turn = 0,currentPlayer = 0;
	
	public PlayState(GameStateManager gsm) {
		super(gsm);
		this.gsm = gsm;
		pf = new PlayField(gsm.gameControl.getWidth(),gsm.gameControl.getHeight());
		bg = ImageController.getImage(ImageType.menubg);
		test();
		
	}
	/** TEST DATA
	 * Moet op andere manier geimplementeerd worden
	 * De player moet aan een game gekoppeld worden, max 4 spelers per game(denk ik), door de createstate
	 * Het aantal beschikbare games moet opgehaald worden van de Server
	*/
	public void test(){
		String[] temp = {"1","2","3","4"};
		StatManager p1stats = new StatManager(temp);
		p1stats.setStat(2, 20);
		Player ptest = new Player(pf.getXCoordinate(4), pf.getYCoordinate(8), 25, 25,0, pf,p1stats);
		StatManager p2stats = new StatManager(temp);
		p2stats.setStat(2, 1);
		Player ptest2 = new Player(pf.getXCoordinate(2), pf.getYCoordinate(12), 25, 25,0, pf,p2stats);
		StatManager p3stats = new StatManager(temp);
		p3stats.setStat(2, 23);
		Player ptest3 = new Player(pf.getXCoordinate(8), pf.getYCoordinate(9), 25, 25,0, pf,p3stats);
		StatManager p4stats = new StatManager(temp);
		p4stats.setStat(2, 0);
		Player ptest4 = new Player(pf.getXCoordinate(11), pf.getYCoordinate(10), 25, 25,0, pf,p4stats);
		players.add(ptest);
		players.add(ptest2);
		players.add(ptest3);
		players.add(ptest4);
		Collections.sort(players, new PlayerSpeedComparator());
		players.get(0).setActive(true);
		System.out.println(players);
	}
	
	@Override
	public void draw(Graphics2D g2) {
		int width = gsm.gameControl.getWidth();
		int height = gsm.gameControl.getHeight();
		g2.translate(width/2,height/2);
		g2.drawImage(bg,-1920/2,-1080/2,1920,1080,null);
		g2.translate(-pf.width/2, -pf.height/2);		
		pf.drawGrid(g2);
		g2.setStroke(new BasicStroke(1));
		for(Player p: players)
			p.draw(g2);
	}

	@Override
	public void update() {
		updatePlayField();
	}
	
	public ArrayList<Player> getPlayers(){
		return players;
	}
	
	/**
	 * Deze methode moet nog verplaats worden naar PlayField denk ik.
	 * Wordt gebruikt om de positie tiles van de Player en het bereik van de Player te updaten.
	 * 
	 * Maar als je deze verplaatst naar playfield, moet daar de lijst met players komen.
	 * Volgens mijn is de playstate juist de plek die de spelers moet bevatten 
	 * en is de playfield alleen wat je ziet waar ze zijn.
	 */
	public void updatePlayField(){
		for(ArrayList<PlayerTile> row: pf.coordinates)
		{
			for(PlayerTile column: row)
			{
				column.setUsable(false);
				column.setUsedByPlayer(false);
				column.setSelected(false);
			}
		}
		for(Player player: players)
		{
			if(player.isActive())
			{
				int x = player.getPlayFieldX();
				int y = player.getPlayFieldY();
				if(pf.getTile(x, y)!=null)
					pf.getTile(x, y).setUsedByPlayer(true);
				if(pf.getTile(x+1, y)!=null)
				if(right){
					pf.getTile(x+1,y).setSelected(true);
				}else{
					pf.getTile(x+1,y).setUsable(true);
				}
				if(pf.getTile(x-1, y)!=null)
				if(left){
					pf.getTile(x-1,y).setSelected(true);
				}else{
					pf.getTile(x-1,y).setUsable(true);
				}
				if(pf.getTile(x, y+1)!=null)
				if(down){
					pf.getTile(x,y+1).setSelected(true);
				}else{
					pf.getTile(x,y+1).setUsable(true);
				}
				if(pf.getTile(x, y-1)!=null)
				if(up){
					pf.getTile(x,y-1).setSelected(true);
				}else{
					pf.getTile(x,y-1).setUsable(true);
				}
			}
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){
		case KeyEvent.VK_UP:
			up = true;
			down = false;
			left = false;
			right = false;
			break;
		case KeyEvent.VK_DOWN:
			up = false;
			down = true;
			left = false;
			right = false;
			break;
		case KeyEvent.VK_LEFT:
			up = false;
			down = false;
			left = true;
			right = false;
			break;
		case KeyEvent.VK_RIGHT:
			up = false;
			down = false;
			left = false;
			right = true;
			break;
		case KeyEvent.VK_ENTER:
			nextTurn();
			break;
		}
		
	}
	
	/**
	 * kijkt welke tile geselecteerd is.
	 * en zet daarna de speler daarnaar toe.
	 */
	private void nextTurn() {
		turn++;
		for(Player p : players){
			
			if(p.isActive()){
				int x = p.getPlayFieldX();
				int y = p.getPlayFieldY();
				PlayerTile selectedTile = null;
				if(pf.getTile(x+1, y)!=null)
				if(pf.getTile(x+1,y).isSelected())
					selectedTile = pf.getTile(x+1,y);
				if(pf.getTile(x-1, y)!=null)
				if(pf.getTile(x-1,y).isSelected())
					selectedTile = pf.getTile(x-1,y);
				if(pf.getTile(x, y+1)!=null)
				if(pf.getTile(x,y+1).isSelected())
					selectedTile = pf.getTile(x,y+1);
				if(pf.getTile(x, y-1)!=null)
				if(pf.getTile(x,y-1).isSelected())
					selectedTile = pf.getTile(x,y-1);
				
				if(selectedTile != null){
					p.setX((int) selectedTile.getMinX());
					p.setY((int) selectedTile.getMinY());
				}
				
			}
		}
		up = false;
		down = false;
		right = false;
		left = false;
		System.out.println("Turn: "+turn);
	}
	@Override
	public void keyReleased(KeyEvent e) {		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	class PlayerSpeedComparator implements Comparator<Player> {

		@Override
		public int compare(Player pl1, Player pl2) {
			int pl1Speed = pl1.range;
			int pl2Speed = pl2.range;
			
			if(pl1Speed > pl2Speed){
				return 1;
			}
			else if(pl1Speed < pl2Speed){
				return -1;
			}
			return 0;
		}
		
	}
}
