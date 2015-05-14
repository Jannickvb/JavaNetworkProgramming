package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

import control.GameStateManager;

public class GamePanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1186553953937526703L;
	private GameStateManager gsm;
	
	public GamePanel(GameStateManager gsm){
		this.setFocusable(true);
		this.requestFocus();
		this.gsm = gsm;
		Timer paintTimer = new Timer(1000/60, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				repaint();
			}
		});
		Timer updateTimer = new Timer(1000/20, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gsm.currentstate.update();
			}
		});
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e){
				gsm.currentstate.keyPressed(e);
			}
			@Override
			public void keyReleased(KeyEvent e){
				gsm.currentstate.keyReleased(e);
			}
		});
		paintTimer.start();
		updateTimer.start();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		gsm.currentstate.draw(g2);
	}
}
