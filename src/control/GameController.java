package control;

import java.util.ArrayList;

import model.gameStates.PlayState;
import model.player.Player;
import view.GameFrame;

public class GameController {
	
	private int width;
	private int height;
	private GameFrame frame;
	private ArrayList<Player> players = new ArrayList<Player>();
	private ArrayList<PlayState> games = new ArrayList<PlayState>();
	private PlayState activeState;
	public GameController(GameFrame frame){
		this.frame = frame;
	}
	
	public void addPlayer(Player player){
		players.add(player);
	}
	
	public int getWidth(){
		return frame.getContentPane().getWidth();
	}
	public int getHeight(){
		return frame.getContentPane().getHeight();
	}
}
