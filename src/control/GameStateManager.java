package control;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import model.GameState;
import model.Level1State;
import model.MenuState;
import model.ScoreState;

public class GameStateManager {
	private int index;
	private GameController gameControl;
	public GameState currentstate;
	private ArrayList<GameState> states = new ArrayList<GameState>();
	public GameStateManager(GameController gameControl){
		this.gameControl = gameControl;
		states.add(new MenuState(this));
		states.add(new ScoreState(this));
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
	
	public void keyPressed(KeyEvent e){
		currentstate.keyPressed(e);
		System.out.println("keypressed: " + e.getKeyCode());
	}
	
	public void keyReleased(KeyEvent e){
		currentstate.keyReleased(e);
		System.out.println("keyreleased: " + e.getKeyCode());
	}
}
