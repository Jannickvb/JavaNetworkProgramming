package control;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import model.player.Player;
import view.GameFrame;

public class GameController {
	
	private GameFrame frame;
	private ArrayList<Player> players = new ArrayList<Player>();
	private ObjectOutputStream toServer;
//	private DataOutputStream toServer;
	private BufferedReader fromServer;
		
	public GameController(GameFrame frame){
		this.frame = frame;

		try {
		      // Create a socket to connect to the server
		      Socket socket = new Socket("localhost", 8000);

		      // Create an input stream to receive data from the server
		      fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		      System.out.println(fromServer.ready());
		      // Create an output stream to send data to the server
//		      toServer = new DataOutputStream(socket.getOutputStream());
		      toServer = new ObjectOutputStream(socket.getOutputStream());
		    }
		    catch (IOException ex) {System.out.println(ex.toString() + '\n');
		}
	}
	
	public ArrayList<Player> getPlayers(){
		return players;
	}
	
	public void sentPlayer(Player player) throws IOException{
		toServer.writeObject(player);
	}
	
	public void addPlayer(Player player){
		players.add(player);
	}
	
	public int getWidth(){
		return frame.getContentPane().getWidth();
	}
	public int getHeight(){
		return frame.getContentPane().getHeight();
	}
	
	public BufferedReader getFromServer() {
		return fromServer;
	}

	public void setFromServer(BufferedReader fromServer) {
		this.fromServer = fromServer;
	}

	public ObjectOutputStream getToServer() {
		return toServer;
	}

	public void setToServer(ObjectOutputStream toServer) {
		this.toServer = toServer;
	}

}
