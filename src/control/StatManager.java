package control;

public class StatManager {
	
	private int[] statData;
	public int currentPoints;
	private final int MAXPOINTS = 10;
	
	public StatManager(String[] statNames){		
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
	
	public int getStatAmount(int index){
		return statData[index];
	}
}
