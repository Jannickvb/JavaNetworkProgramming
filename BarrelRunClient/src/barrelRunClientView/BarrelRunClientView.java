package barrelRunClientView;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import barrelRunClientControl.GameStateManager;

public class BarrelRunClientView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2790127054190184305L;
	private GameStateManager gsm;
	
	public BarrelRunClientView(GameStateManager gsm) {
		super(null);
		this.gsm = gsm;		
	}
	
	@Override
	protected void paintComponent(Graphics g) {		
		super.paintComponent(g);
		
		gsm.currentState.draw(((Graphics2D)g));
	}
}
