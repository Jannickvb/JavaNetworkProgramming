package barrelRunClientControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.Timer;

import barrelRunClientModel.BarrelRunClient;
import barrelRunClientModel.BarrelRunClientModel;
import barrelRunClientView.BarrelRunClientView;

public class BarrelRunClientControl implements KeyListener{

	private GameStateManager gsm;
	private BarrelRunClientModel model;
	private BarrelRunClientView view;
	private Timer update,paint;	
	
	public BarrelRunClientControl(GameStateManager gsm, BarrelRunClientModel model,BarrelRunClientView view) {
		this.gsm = gsm;
		paint = new Timer(1000/60,new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {				
				view.repaint();
			}
		});
		update = new Timer(1000/30, new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					gsm.currentState.update();
				} catch (IOException e) {					
					e.printStackTrace();
				}
			}
		});
		paint.start();
		update.start();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		gsm.currentState.keyPressed(e);		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		gsm.currentState.keyReleased(e);		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}	
}
