package server;

import java.io.IOException;
import java.util.List;

public class Lobby implements Runnable {
	
	private Player[]players;
	private boolean isRunning = true;
	
	public Lobby(List<Player> playerList) {				
		players = new Player[playerList.size()];
		for(int i = 0; i < playerList.size(); i++){
			players[i] = playerList.get(i);
		}		
	}

	@Override
	public void run() {
		try{
//		System.out.println("ID: "+id+"\tis running\tPlayer size: "+players.length);
			for(int i = 0; i < players.length; i++){				
					players[i].toClient.writeUTF("go");
					players[i].toClient.writeInt(i);
			}			
			double player1X,player2X,xStone,yStone,xBounds = 1920,yBounds = 128;
			String stonePosition = "";
			while(isRunning){
				//haal de coordinaten op vanuit de clients
				player1X = players[0].fromClient.readDouble();
				player2X = players[1].fromClient.readDouble();
				xStone = (Math.random()*xBounds)+1;
				yStone = (Math.random()*yBounds)+1;
//				System.out.println("Player1: "+player1X+"\tPlayer2: "+player2X);
				//schrijf de coordinaten naar de ander speler toe
				players[0].toClient.writeDouble(player2X);
				players[1].toClient.writeDouble(player1X);
				//maak de cooridinaten voor een nieuwe stone
				stonePosition = xStone+":"+yStone;
				players[0].toClient.writeUTF(stonePosition);
				players[1].toClient.writeUTF(stonePosition);
			}
		}catch(IOException e){
			e.printStackTrace();
		}	
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}
	
	
}
