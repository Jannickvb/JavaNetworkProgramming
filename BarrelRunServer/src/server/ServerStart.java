package server;

import serverScreen.ServerFrame;

public class ServerStart {

	public static void main(String[] args){
		ServerFrame server = new ServerFrame();
		new Server(server);
	}
}
