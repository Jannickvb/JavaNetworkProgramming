package barrelRunClientControl;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import barrelRunClientModel.BarrelRunClient;
import barrelRunClientModel.gameState.EndState;
import barrelRunClientModel.gameState.GameState;
import barrelRunClientModel.gameState.LoadingState;
import barrelRunClientModel.gameState.MenuState;
import barrelRunClientModel.gameState.PlayState;

public class GameStateManager {

	private JFrame frame;
	private List<GameState> gameStates;
	public GameState currentState;
	public BarrelRunClient client;
	
	public GameStateManager(JFrame frame) {
		this.frame = frame;
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
		currentState.init();
	}
	
	public int getWidth(){
		return frame.getContentPane().getWidth();
	}
	
	public int getHeight(){
		return frame.getContentPane().getHeight();
	}
	
	public void makeClient(){
		client = new BarrelRunClient();
	}
}
