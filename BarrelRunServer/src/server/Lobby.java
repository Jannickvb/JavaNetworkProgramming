package server;

import java.io.IOException;
import java.util.List;

public class Lobby implements Runnable {
	
	private Player[] players;
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
			
			double player1X,player2X,barrelX,barrelY,boundWidth = 800-103,boundHeight = 128;
			String barrelPosition = "";
			while(isRunning){
				boolean statusp1 = players[0].fromClient.readBoolean();
				boolean statusp2 = players[1].fromClient.readBoolean();
				int id;				
				if(statusp1 || statusp2)
				{
					players[0].toClient.writeBoolean(true);
					players[1].toClient.writeBoolean(true);
					if(statusp1)
					{
						id = players[0].fromClient.readInt();
					}
					else
					{
						id = players[1].fromClient.readInt();
					}
					players[0].toClient.writeInt(id);
					players[1].toClient.writeInt(id);
					isRunning = false;
				}else{
					players[0].toClient.writeBoolean(false);
					players[1].toClient.writeBoolean(false);
				}
				
				//haal de coordinaten op vanuit de clients
				player1X = players[0].fromClient.readDouble();
				player2X = players[1].fromClient.readDouble();
				
				barrelX = (Math.random()*boundWidth)+1;
				barrelY = (Math.random()*boundHeight)+1;

//				System.out.println("Player1: "+player1X+"\tPlayer2: "+player2X);
				//schrijf de coordinaten naar de ander speler toe
				players[0].toClient.writeDouble(player2X);
				players[1].toClient.writeDouble(player1X);
				//maak de cooridinaten voor een nieuwe barrel
				barrelPosition = barrelX+":"+barrelY;
				players[0].toClient.writeUTF(barrelPosition);
				players[1].toClient.writeUTF(barrelPosition);
				
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
