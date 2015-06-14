package view;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.Timer;

import control.Server;

public class ServerFrame extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4645410015441069848L;

	public JTextArea jta;
	public JButton button;
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
		try {
			Image image = ImageIO.read(ServerFrame.class.getResource("/barrelServer.png"));
			frame.setIconImage(new ImageIcon(image).getImage());
		} catch (IOException e1) {			
			e1.printStackTrace();
		}
		jta = new JTextArea();
		button = new JButton("Show active lobbys");		
		JScrollPane sp = new JScrollPane(jta);
		sp.setBounds(0, 0, frame.getContentPane().getWidth(), frame.getContentPane().getHeight()-25);
		button.setBounds(0, sp.getHeight(), 200, 25);
		add(sp);
		add(button);
		paint = new Timer(1000/5, this);
		paint.start();
		
		frame.addComponentListener(new ComponentAdapter() {
		    public void componentResized(ComponentEvent e) {
		    	sp.setBounds(0, 0, frame.getContentPane().getWidth(), frame.getContentPane().getHeight()-25);
		    	button.setBounds(0, sp.getHeight(), 200, 25);
		    }
		});
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		repaint();		
	}
}
