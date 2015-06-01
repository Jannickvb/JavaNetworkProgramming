package model.gameStates;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.IOException;

import model.Player;
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
		sm = new StatManager(stats);
	}

	@Override
	public void init() {
				
	}
	
	@Override
	public void draw(Graphics2D g) {
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
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {		
		switch(e.getKeyCode()){
		
		case KeyEvent.VK_ESCAPE:
			try {
				gsm.select(0);
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
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
			if(sm.currentPoints == 0)
			{
				Player player = new Player(3,4,0,sm);
//				gsm.gameControl.addPlayer(player);
				try {
					gsm.gameControl.sentPlayer(player);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				try {
					gsm.select(3);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else
			{
				System.out.println("You didn't use al your points.");
			}
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
