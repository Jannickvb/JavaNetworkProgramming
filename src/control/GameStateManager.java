package control;

import java.util.ArrayList;

import model.gameStates.CreateState;
import model.gameStates.GameState;
import model.gameStates.Level1State;
import model.gameStates.MenuState;
import model.gameStates.ScoreState;

public class GameStateManager {
	private int index;
	public GameController gameControl;
	public GameState currentstate;
	private ArrayList<GameState> states = new ArrayList<GameState>();
	public GameStateManager(GameController gameControl){
		this.gameControl = gameControl;
		states.add(new MenuState(this));
		states.add(new ScoreState(this));
		states.add(new CreateState(this));
		states.add(new Level1State(this));
		currentstate = states.get(0);
	}
	
	public void next(){
		index++;
		if(index == states.size()) {
			index = 0;
		}
	}
	
	public void back(){
		index--;
		if(index == -1) {
			index = states.size() - 1;
		}
	}
	
	public void start(){
		currentstate = states.get(2);
	}
	
	public void select(int i){
		if(i >= 0 || i < states.size())
			currentstate = states.get(i);
		else
			System.out.println("invalid state");
	}
}
