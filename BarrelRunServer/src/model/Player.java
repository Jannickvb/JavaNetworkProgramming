package model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class Player {

	public DataInputStream fromClient;
	public DataOutputStream toClient;
	
	public Player(InputStream fromClient, OutputStream toClient) {
		super();
		this.fromClient = new DataInputStream(fromClient);
		this.toClient = new DataOutputStream(toClient);
	}	
}
