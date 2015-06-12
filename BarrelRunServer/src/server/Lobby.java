package server;

import java.io.IOException;
import java.util.List;

public class Lobby implements Runnable {

	private int id;
	private Player[]players;
	private boolean isRunning;
	
	public Lobby(List<Player> playerList) {		
		id = (int) (Math.random()*1000+1);
		players = new Player[playerList.size()];
		for(int i = 0; i < playerList.size(); i++){
			players[i] = playerList.get(i);
		}
		isRunning = true;
	}

	@Override
	public void run() {
		try{
//		System.out.println("ID: "+id+"\tis running\tPlayer size: "+players.length);
			for(Player p : players){				
					p.toClient.writeUTF("go");
				}
		}catch(IOException e){
			e.printStackTrace();
		}		
		isRunning = false;
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}
	
	
}
