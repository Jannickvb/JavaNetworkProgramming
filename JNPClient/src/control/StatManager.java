package control;

import java.io.Serializable;

public class StatManager implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2876694410312964727L;
	private int[] statData;
	private String[] statNames;

	public int currentPoints;
	private final int MAXPOINTS = 10;
	
	public StatManager(String[] statNames){	
		this.statNames = statNames;
		statData = new int[statNames.length];
		currentPoints = MAXPOINTS;
	}
	
	public void raiseStat(int index){
		if(currentPoints > 0){
			statData[index]++;
			currentPoints--;
		}		
	}
	
	public void lowerStat(int index){
		if(statData[index] > 0){
			statData[index]--;			
			currentPoints++;
		}		
	}
	public int[] getStatData() {
		return statData;
	}

	public void setStat(int stat, int amount) {
		statData[stat] = amount;
	}
	
	public String[] getStatNames() {
		return statNames;
	}
	public int getStatAmount(int index){
		return statData[index];
	}
}
