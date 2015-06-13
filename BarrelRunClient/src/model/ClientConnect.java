package model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientConnect {

	private Socket socket;
	public DataInputStream fromServer;
	public DataOutputStream toServer;
	
	public ClientConnect() {
		try {
			socket = new Socket("LocalHost",8000);
			fromServer = new DataInputStream(socket.getInputStream());
			toServer = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
}
