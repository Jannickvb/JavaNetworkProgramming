package model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {
	
	public int playeramount;
	public CopyOnWriteArrayList<Player> players = new CopyOnWriteArrayList<Player>();
	public CopyOnWriteArrayList<Lobby> lobbies = new CopyOnWriteArrayList<Lobby>();
	
	public Server() {
		try {
            // Create a server socket
            ServerSocket serverSocket = new ServerSocket(8000);
            System.out.println("Server started at " + new Date() + '\n');

            java.net.InetAddress i = java.net.InetAddress.getLocalHost();
            System.out.println("Server name/ip adres: " + i);                  // name and IP address
            
            // Listen for a connection request
            Socket socket;
            while (true) {
				socket = serverSocket.accept();
				InetAddress adres = socket.getInetAddress();
			
				// Create data input and output streams
	            DataInputStream inputFromClient = new DataInputStream(
	                    socket.getInputStream());
	            DataOutputStream outputToClient = new DataOutputStream(
	                    socket.getOutputStream());
	            
	            
	            players.add(new Player(adres, inputFromClient, outputToClient));
	            
	            if(players.size() == 4)
	            {
	            	Lobby lol = new Lobby(players);
	            	lobbies.add(lol);
	            	Thread lobby = new Thread(lol);
	            	lobby.run();
	            	players.clear();
	            }
	            for(Lobby l: lobbies){
	            	if(l.players.size()==0){
	            		lobbies.remove(l);
	            	}
	            }
            }
            
        }
        catch(IOException ex) {
            System.err.println(ex);
        }
	}
}
