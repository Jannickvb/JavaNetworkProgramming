package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

import control.ControlManager;

@SuppressWarnings("serial")
public class GamePanel extends JPanel{	
	private ControlManager cm;
	private Font font;
	private Timer update,paint;
	
	public GamePanel(ControlManager cm) {		
		setFocusable(true);
		requestFocus(true);
		font = new Font("Verdana", Font.BOLD, 48);
		this.cm = cm;
		
		
		paint = new Timer(1000/60,new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {				
				repaint();
			}
		});
		update = new Timer(1000/30, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {				
				cm.getGameStateManager().currentState.update();								
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
				cm.getGameStateManager().currentState.keyPressed(e);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				cm.getGameStateManager().currentState.keyReleased(e);
			}
		});
	}
	
	@Override
	protected void paintComponent(Graphics g) {		
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Color.WHITE);
		g2.setFont(font);
		cm.getGameStateManager().currentState.draw(g2);
	}

}
