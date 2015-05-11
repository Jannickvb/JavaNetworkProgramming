package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

import control.GameStateManager;

public class GamePanel extends JPanel{
	private GameStateManager gsm;
	public GamePanel(GameStateManager gsm){
		this.setFocusable(true);
		this.requestFocus();
		this.gsm = gsm;
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e){
				gsm.keyPressed(e);
			}
			@Override
			public void keyReleased(KeyEvent e){
				gsm.keyReleased(e);
			}
		});
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		gsm.currentstate.draw(g2);
	}
}
