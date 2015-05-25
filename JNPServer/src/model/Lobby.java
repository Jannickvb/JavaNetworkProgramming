package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import model.Player;
public class Lobby implements Runnable {
	
	public CopyOnWriteArrayList<Player> players = new CopyOnWriteArrayList<Player>();
	public int active = 0;
	public Lobby(CopyOnWriteArrayList<Player> players) {
		this.players = players;
	}
	
	public void removePlayer(Player player) {
		for(Player p: players) {
			if(p.equals(player)){
				players.remove(p);
			}
		}
	}
	
	@Override
	public void run()
	{
		for (Player p : players) {
			try {
				p.getOutput().writeBytes("gimme stat\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		while (true) {
			for(Player p : players)
			{
				if (!p.isReady()) {
					try {
						p.setRange(p.getInput().readInt());
						Collections.sort(players, new PlayerSpeedComparator());
						p.setReady(true);
						System.out.println("player is ready");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else
				{
					break;
				}
			}
		}
	}
	
	class PlayerSpeedComparator implements Comparator<Player> {

		@Override
		public int compare(Player pl1, Player pl2) {
			int pl1Speed = pl1.getRange();
			int pl2Speed = pl2.getRange();
			
			if(pl1Speed > pl2Speed){
				return -1;
			}
			else if(pl1Speed < pl2Speed){
				return 1;
			}
			return 0;
		}
		
	}
}
