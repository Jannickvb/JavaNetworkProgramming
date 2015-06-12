package server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import serverScreen.ServerScreen;

public class Server {	
	
	private List<Player> playerList;
	private List<Lobby> lobbys;	
	
	public Server(ServerScreen ss){		
		ServerSocket server = null;
		playerList = new ArrayList<Player>();
		lobbys = new ArrayList<Lobby>();		
		try {
			 server = new ServerSocket(8000);
			 ss.jta.append("Server started at: "+new Date());			 
			 ss.jta.append("\nAdress: "+InetAddress.getLocalHost());
			 while(true){
				 Socket socket = server.accept();
				 ss.jta.append("\nClient is connected"+socket.getLocalAddress());
				 playerList.add(new Player(socket.getInputStream(),socket.getOutputStream()));
				 
				 if(playerList.size() == 2){
					 Lobby lobby = new Lobby(playerList);
					
					 lobbys.add(lobby);					 
					 playerList.clear();					 
					
				 }
				 if(!lobbys.isEmpty()){
					Iterator<Lobby> lobbyIterator = lobbys.iterator();
					while(lobbyIterator.hasNext()){					
						if(!lobbyIterator.next().timer.isRunning()){
							lobbyIterator.remove();
						}
					}
				 }
			 }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
