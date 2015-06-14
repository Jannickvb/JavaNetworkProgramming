package server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import serverScreen.ServerFrame;

public class Server {	
	
	private List<Player> playerList;
	private List<Lobby> lobbys;	
	
	public Server(ServerFrame serverFrame){	
		ServerSocket server = null;
		playerList = new ArrayList<Player>();
		lobbys = new ArrayList<Lobby>();
		serverFrame.button.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				serverFrame.jta.append("\nLobby size at: "+lobbys.size());				
			}
		});
		try {
			 server = new ServerSocket(8000);
			 serverFrame.jta.append("Server started at: "+new Date());			 
			 serverFrame.jta.append("\nAdress: "+InetAddress.getLocalHost());
			 while(true){
				 Socket socket = server.accept();
				 serverFrame.jta.append("\nClient is connected"+socket.getLocalAddress());
				 playerList.add(new Player(socket.getInputStream(),socket.getOutputStream()));				 
				
				 if(playerList.size() == 2){					 
					 Lobby lobby = new Lobby(playerList);
					 Thread thread = new Thread(lobby);
					 thread.start();					 
					 lobbys.add(lobby);	
					 String[] huidigeTijd = new Date().toString().split("CEST");
					 serverFrame.jta.append("\nEen nieuwe server is toegevoegd op "+huidigeTijd[0]+huidigeTijd[1]);
					 playerList.clear();				
				 }				 
				 
				Iterator<Lobby> lobbyIterator = lobbys.iterator();
				while(lobbyIterator.hasNext()){					
					if(!lobbyIterator.next().isRunning()){
						lobbyIterator.remove();
					}
				}
			 }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
}
