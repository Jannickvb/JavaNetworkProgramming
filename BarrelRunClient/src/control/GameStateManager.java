package control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import model.BarrelRunClient;
import model.gamestates.EndState;
import model.gamestates.GameState;
import model.gamestates.LoadingState;
import model.gamestates.MenuState;
import model.gamestates.PlayState;
import view.GameFrame;

public class GameStateManager {

	private static GameFrame frame;
	private List<GameState> gameStates;
	public GameState currentState;
	public BarrelRunClient client;
	private ControlManager cm;
	
	public GameStateManager(ControlManager cm) {
		this.cm = cm;
		frame = cm.getFrame();
		gameStates = new ArrayList<GameState>();
		gameStates.add(new MenuState(this));
		gameStates.add(new LoadingState(this));
		gameStates.add(new PlayState(this));
		gameStates.add(new EndState(this));
		setState(StateType.menu);
	}
	
	public enum StateType{
		menu,wait,play,end;
	}
	
	public void setState(StateType type){
		currentState = gameStates.get(type.ordinal());
		try {
			currentState.init();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static int getWidth(){
		return frame.getContentPane().getWidth();
	}
	
	public static int getHeight(){
		return frame.getContentPane().getHeight();
	}
	
	public void createClient(){
		client = new BarrelRunClient();
	}	
}
