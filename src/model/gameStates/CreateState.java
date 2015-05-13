package model.gameStates;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import control.GameStateManager;
import control.StatManager;

public class CreateState extends GameState{

	GameStateManager gsm;
	private String[] stats = {"Stamina","Strength","Dexterity","Intelligence"};
	private int index = 0;
	private StatManager sm;
	
	public CreateState(GameStateManager gsm) {
		super(gsm);
		this.gsm = gsm;
		sm = new StatManager(stats, 10);
	}

	@Override
	public void init() {
<<<<<<< HEAD
		points = 0;
=======
		
		
>>>>>>> 10fa47b0d3ccb77965aa9db67eb5f2906efe0a28
	}
	
	@Override
	public void draw(Graphics2D g) {
<<<<<<< HEAD
		for(int i = 0; i < stats.length; i++){
			int sWidth = g.getFontMetrics().stringWidth(stats[i]);
			g.drawString(stats[i], gsm.gameControl.getWidth()/2 - 200, 100 + (50*i));
		}
=======
		int x = gsm.gameControl.getWidth()/2;
		int y = 0;
		int width = 0;
		int size = 10;
		g.drawString("Currentpoints: "+sm.currentPoints, 10, 16);		
		
		for(int i = 0; i < stats.length; i++){
			if(i == index){
				g.setPaint(Color.RED);
			}else{
				g.setPaint(Color.BLACK);
			}
			y = 50*i+70;
			width = g.getFontMetrics().stringWidth(stats[i])+x;			
			g.drawString(stats[i], x,y);			
			for(int j = 0; j < sm.getStatAmount(i); j++){				
				g.fillRect(width+(j*size*2)+size/2, y-8, size, size);
			}
		}
		
>>>>>>> 10fa47b0d3ccb77965aa9db67eb5f2906efe0a28
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {		
		switch(e.getKeyCode()){
		
		case KeyEvent.VK_ESCAPE:
			gsm.select(0);
			break;
		case KeyEvent.VK_UP:
			up();
			break;
		case KeyEvent.VK_DOWN:
			down();
			break;
		case KeyEvent.VK_RIGHT:
			sm.raiseStat(index);
			break;
		case KeyEvent.VK_LEFT:
			sm.lowerStat(index);
			break;
		}
		if(e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			gsm.select(4);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private void up(){
		index--;
		if(index == -1) {
			index = stats.length-1;
		}
	}
	
	private void down(){
		index++;
		if(index == stats.length) {
			index = 0;
		}
	}
	
}
