package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

import control.ControlManager;
import control.GameStateManager;

@SuppressWarnings("serial")
public class GamePanel extends JPanel{

	private GameStateManager gsm;
	private ControlManager cm;
	
	private Timer update,paint;
	
	public GamePanel(ControlManager cm) {		
		setFocusable(true);
		requestFocus(true);
		
		if(cm == null){
			System.out.println("GamePanel aids");
		}
		
		this.cm = cm;
		this.gsm = this.cm.getGameStateManager();
		
		paint = new Timer(1000/60,new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {				
				repaint();
			}
		});
		update = new Timer(1000/30, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {				
				gsm.currentState.update();								
			}
		});
		this.paint.start();
		this.update.start();
		
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
					System.exit(0);
				}
				gsm.currentState.keyPressed(e);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				gsm.currentState.keyReleased(e);
			}
		});
	}
	
	@Override
	protected void paintComponent(Graphics g) {		
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		gsm.currentState.draw(g2);
	}

}