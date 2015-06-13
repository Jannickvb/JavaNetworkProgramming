package serverScreen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;

public class ServerFrame extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4645410015441069848L;

	public JTextArea jta;
	private Timer paint;
	
	public ServerFrame(){
		super(null);
		JFrame frame = new JFrame("Server");
		frame.setDefaultCloseOperation(3);
		frame.setContentPane(this);
		frame.setSize(400,300);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		jta = new JTextArea();
		jta.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		add(jta);
		paint = new Timer(1000/60, this);
		paint.start();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		repaint();		
	}
}
