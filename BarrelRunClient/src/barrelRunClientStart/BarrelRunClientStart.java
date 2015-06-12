package barrelRunClientStart;

import javax.swing.JFrame;

import barrelRunClientControl.BarrelRunClientControl;
import barrelRunClientControl.GameStateManager;
import barrelRunClientModel.BarrelRunClientModel;
import barrelRunClientView.BarrelRunClientView;

public class BarrelRunClientStart {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Barrel run");
		frame.setDefaultCloseOperation(3);		
		GameStateManager gsm = new GameStateManager(frame);
		BarrelRunClientView view = new BarrelRunClientView(gsm);
		BarrelRunClientModel model = new BarrelRunClientModel(gsm);		
		BarrelRunClientControl control = new BarrelRunClientControl(gsm, model, view);
		
		frame.addKeyListener(control);
		frame.setContentPane(view);
		frame.setSize(800, 800);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
