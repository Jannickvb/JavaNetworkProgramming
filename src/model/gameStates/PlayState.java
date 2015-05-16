package model.gameStates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import model.PlayField;
import model.PlayerTile;
import model.player.Player;
import control.GameStateManager;
import control.ImageController;
import control.StatManager;

public class PlayState extends GameState{

	private PlayField pf;
	private GameStateManager gsm;
	private ArrayList<Player> players = new ArrayList<Player>();
	private ImageController imageControl = new ImageController();
	private BufferedImage bg;
	private boolean up,down,left,right;
	private  int turn = 0;
	
	public PlayState(GameStateManager gsm) {
		super(gsm);
		this.gsm = gsm;
		pf = new PlayField(gsm.gameControl.getWidth(),gsm.gameControl.getHeight());
		bg = imageControl.getImage(0);
		test();
		
	}
	/** TEST DATA
	 * Moet op andere manier geimplementeerd worden
	 * De player moet aan een game gekoppeld worden, max 4 spelers per game(denk ik), door de createstate
	 * Het aantal beschikbare games moet opgehaald worden van de Server
	*/
	public void test(){
		String[] temp = {"1","2","3","4"};
		Player ptest = new Player(pf.getXCoordinate(4), pf.getYCoordinate(8), 25, 25,0, pf,new StatManager(temp));
		ptest.setActive(true);
		players.add(ptest);
	}
	@Override
	public void draw(Graphics2D g2) {
		int width = gsm.gameControl.getWidth();
		int height = gsm.gameControl.getHeight();
		g2.translate(width/2,height/2);
//		BufferedImage image = imageControl.getImage(0);//wat doe je hiermee?
		g2.drawImage(bg,-1920/2,-1080/2,1920,1080,null);
		g2.translate(-pf.width/2, -pf.height/2);		
		pf.drawGrid(g2);
		players.get(0).draw(g2);
		//komt niet op het scher,
//		g2.setFont(new Font("Comic sans",Font.BOLD,20));
//		g2.setPaint(Color.RED);
//		g2.drawString("Turn: "+turn, 0, 0);
	}

	@Override
	public void update() {
		
		updatePlayField();
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
				pf.coordinates.get(x).get(y).setUsedByPlayer(true);
				
				if(right){
					pf.coordinates.get(x+1).get(y).setSelected(true);
				}else{
					pf.coordinates.get(x+1).get(y).setUsable(true);
				}
				
				if(left){
					pf.coordinates.get(x-1).get(y).setSelected(true);
				}else{
					pf.coordinates.get(x-1).get(y).setUsable(true);
				}
				
				if(down){
					pf.coordinates.get(x).get(y+1).setSelected(true);
				}else{
					pf.coordinates.get(x).get(y+1).setUsable(true);
				}
				
				if(up){
					pf.coordinates.get(x).get(y-1).setSelected(true);
				}else{
					pf.coordinates.get(x).get(y-1).setUsable(true);
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
		case KeyEvent.VK_Q:
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
				
				if(pf.coordinates.get(x+1).get(y).isSelected())
					selectedTile = pf.coordinates.get(x+1).get(y);
				
				if(pf.coordinates.get(x-1).get(y).isSelected())
					selectedTile = pf.coordinates.get(x-1).get(y);
				
				if(pf.coordinates.get(x).get(y+1).isSelected())
					selectedTile = pf.coordinates.get(x).get(y+1);
				
				if(pf.coordinates.get(x).get(y-1).isSelected())
					selectedTile = pf.coordinates.get(x).get(y-1);
				
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

}
