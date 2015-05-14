package model.gameStates;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
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
	private ImageController imageControl = ImageController.instance;
	public PlayState(GameStateManager gsm) {
		super(gsm);
		this.gsm = gsm;
		pf = new PlayField(gsm.gameControl.getWidth(),gsm.gameControl.getHeight());
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
		g2.translate(width/2 - pf.width/2, height/2 -pf.height/2);
		pf.drawGrid(g2);
		players.get(0).draw(g2);
		g2.drawImage(imageControl.getImage(0),0,0,20,20,null);
	}

	@Override
	public void update() {
		
		updatePlayField();
	}
	/**
	 * Deze methode moet nog verplaats worden naar PlayField denk ik.
	 * Wordt gebruikt om de positie tiles van de Player en het bereik van de Player te updaten.
	 */
	public void updatePlayField(){
		for(ArrayList<PlayerTile> row: pf.coordinates)
		{
			for(PlayerTile column: row)
			{
				column.setUsable(false);
				column.setUsedByPlayer(false);
			}
		}
		for(Player player: players)
		{
			if(player.isActive())
			{
				int x = player.getPlayFieldX();
				int y = player.getPlayFieldY();
				pf.coordinates.get(x).get(y).setUsedByPlayer(true);
				pf.coordinates.get(x+1).get(y).setUsable(true);
				pf.coordinates.get(x-1).get(y).setUsable(true);
				pf.coordinates.get(x).get(y+1).setUsable(true);
				pf.coordinates.get(x).get(y-1).setUsable(true);
			}
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

}
