package model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;

import model.client.StatManager;

public class Player implements Serializable{
	
	private InetAddress inet;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	private boolean ready;
	private int[] stats;
	private String[] statNames;
	private StatManager sm;
	private int x,y;
	public int attack, health, range, mana;
	private boolean isActive;
	public Player(Player player){
		this.sm = player.sm;
		this.x = player.x;
		this.y = player.y;
	}
	
	public Player(InetAddress inet, ObjectInputStream inputFromClient, ObjectOutputStream outputToClient) {
		this.inet = inet;
		this.input = inputFromClient;
		this.output = outputToClient;
		ready = false;
	}
	public Player(int x,int y,int width,int height,int imageID,StatManager sm){
		this.sm = sm;
		this.x = x;
		this.y = y;
		this.statNames = sm.getStatNames();
		this.stats = sm.getStatData();
		init();
	}
	
	public Player(int x,int y,int imageID,StatManager sm){
		this.sm = sm;
		this.x = x;
		this.y = y;
		this.statNames = sm.getStatNames();
		this.stats = sm.getStatData();
		init();
	}
	
	public void init() {
		attack = stats[1] * 20;
		health = stats[0] * 10 + 100;
		range = stats[2] + 1;
		mana = stats[3] * 10 + 100;
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getPlayFieldX(){
		int pfX = x / 32;
		return pfX;
	}
	
	public int getPlayFieldY(){
		int pfY = y / 32;
		return pfY;
	}
	
	public boolean isActive() {
		return isActive;
	}
	
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	public int getStat(int i) {
		return stats[i];
	}
	public String getStatName(int i) {
		return statNames[i];
	}
	public StatManager getStatManager() {
		return sm;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	

	@Override
	public String toString() {
		return "Player [attack=" + attack + ", health=" + health + ", range="
				+ range + ", mana=" + mana + "]";
	}
	
	public void setRange(int i) {
		this.range = i;
	}

	public InetAddress getInet() {
		return inet;
	}

	public void setInet(InetAddress inet) {
		this.inet = inet;
	}

	public ObjectInputStream getInput() {
		return input;
	}

	public void setInput(ObjectInputStream input) {
		this.input = input;
	}

	public ObjectOutputStream getOutput() {
		return output;
	}

	public void setOutput(ObjectOutputStream output) {
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
