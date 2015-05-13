package model.player;

import control.StatManager;

public class Player {

	private int[] stats;
	private String[] statNames;
	private StatManager sm;
	public Player(StatManager sm) {	
		this.sm = sm;
		this.statNames = sm.getStatNames();
		this.stats = sm.getStatData();
	}

	public int getStat(int i){
		return stats[i];
	}
	public String getStatName(int i){
		return statNames[i];
	}
	public StatManager getStatManager(){
		return sm;
	}
}
