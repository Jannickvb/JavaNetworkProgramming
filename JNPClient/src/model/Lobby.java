package model;

import java.util.ArrayList;

public class Lobby {
	
	private ArrayList<Player> players = new ArrayList<>();
	
	public Lobby(ArrayList<Player> players){
		this.players = players;
	}
	
	public Lobby(Lobby lobby){
		this.players = lobby.getPlayers();
	}
	
	public ArrayList<Player> getPlayers(){
		return players;
	}
}
