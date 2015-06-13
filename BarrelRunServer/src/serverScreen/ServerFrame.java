package serverScreen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.Timer;

import server.Server;

public class ServerFrame extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4645410015441069848L;

	public JTextArea jta;
	private Timer paint;
	
	public static void main(String[] args){
		ServerFrame server = new ServerFrame();
		new Server(server);
	}
	
	public ServerFrame(){
		super(null);
		JFrame frame = new JFrame("Server");
		frame.setDefaultCloseOperation(3);
		frame.setContentPane(this);
		frame.setSize(400,300);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		jta = new JTextArea();
		JScrollPane sp = new JScrollPane(jta);
		sp.setBounds(0, 0, frame.getContentPane().getWidth(), frame.getContentPane().getHeight());
		add(sp);
		paint = new Timer(1000/5, this);
		paint.start();
		
		frame.addComponentListener(new ComponentAdapter() {
		    public void componentResized(ComponentEvent e) {
		    	sp.setBounds(0, 0, frame.getContentPane().getWidth(), frame.getContentPane().getHeight());
		    }
		});
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		repaint();		
	}
}
