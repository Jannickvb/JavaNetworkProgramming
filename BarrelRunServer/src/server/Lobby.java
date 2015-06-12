package server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Timer;

public class Lobby implements ActionListener {

	private int id;
	private List<Player> players;
	public Timer timer;	
	
	public Lobby(List<Player> playerList) {
		id = (int) (Math.random()*1000+1);
		this.players = playerList;
		timer = new Timer(1000/30, this);
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		//game logic	
		System.out.println("ID: "+id+"\tis running\tPlayer size: "+players.size());
	}

}
