package model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;

public class Player {
	
	private InetAddress inet;
	private DataInputStream input;
	private DataOutputStream output;
	private int range,position;
	private boolean ready;
	
	public Player(InetAddress inet, DataInputStream input, DataOutputStream output) {
		this.inet = inet;
		this.input = input;
		this.output = output;
		ready = false;
	}
	
	public void setRange(int i) {
		this.range = i;
	}

	public void setPosition(int i) {
		this.position = i;
	}
	
	public int getPosition() {
		return position;
	}
	
	public InetAddress getInet() {
		return inet;
	}

	public void setInet(InetAddress inet) {
		this.inet = inet;
	}

	public DataInputStream getInput() {
		return input;
	}

	public void setInput(DataInputStream input) {
		this.input = input;
	}

	public DataOutputStream getOutput() {
		return output;
	}

	public void setOutput(DataOutputStream output) {
		this.output = output;
	}

	public int getRange() {
		return range;
	}

	public boolean isReady() {
		return ready;
	}

	public void setReady(boolean ready) {
		this.ready = ready;
	}
}
