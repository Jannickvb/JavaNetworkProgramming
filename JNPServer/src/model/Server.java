package model;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
	            ObjectInputStream inputFromClient = new ObjectInputStream(
	                    socket.getInputStream());
	            ObjectOutputStream outputToClient = new ObjectOutputStream(
	                    socket.getOutputStream());
	            
	            try {
					if(inputFromClient.readObject().toString().equals("player")){
						players.add(new Player((Player) inputFromClient.readObject()));
						System.out.println("added player");
					}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            
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
