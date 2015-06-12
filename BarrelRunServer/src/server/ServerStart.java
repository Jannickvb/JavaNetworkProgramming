package server;

import serverScreen.ServerScreen;

public class ServerStart {

	public static void main(String[] args){
		ServerScreen server = new ServerScreen();
		new Server(server);
	}
}
