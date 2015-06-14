package control;

import java.util.ArrayList;
import java.util.List;

import model.ClientConnect;
import model.gamestates.EndState;
import model.gamestates.GameState;
import model.gamestates.LoadingState;
import model.gamestates.MenuState;
import model.gamestates.PlayState;
import view.GameFrame;

public class GameStateManager {

	private GameFrame frame;
	private List<GameState> gameStates;
	public GameState currentState;
	public ClientConnect client;
	private ControlManager cm;
	
	public GameStateManager(ControlManager cm) {
		this.cm = cm;
		frame = cm.getFrame();
		gameStates = new ArrayList<GameState>();
		addGameStates();
		setState(StateType.menu);
	}
	
	public void addGameStates(){
		gameStates.add(new MenuState(cm));
		gameStates.add(new LoadingState(cm));
		gameStates.add(new PlayState(cm));
	}
	
	public enum StateType{
		menu,wait,play,end;
	}
	
	public void setState(StateType type){
		currentState = gameStates.get(type.ordinal());		
		currentState.init();			
	}
	
	public GameState getState(StateType type) throws IndexOutOfBoundsException{
		return gameStates.get(type.ordinal());
	}
	
	public int getWidth(){
		return frame.getContentPane().getWidth();
	}
	
	public int getHeight(){
		return frame.getContentPane().getHeight();
	}
	
	public void gameOver(){
		gameStates.add(new EndState(cm));
	}
	
	public void createClient(){
		client = new ClientConnect();
	}	
}
