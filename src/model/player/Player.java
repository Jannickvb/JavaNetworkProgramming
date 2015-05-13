package model.player;

public class Player {

	private int stamina,strength,dexterity,intelligence;

	public Player(int stamina, int strength, int dexterity, int intelligence) {		
		this.stamina = stamina;
		this.strength = strength;
		this.dexterity = dexterity;
		this.intelligence = intelligence;
	}

	public int getStamina() {
		return stamina;
	}

	public void setStamina(int stamina) {
		this.stamina = stamina;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getDexterity() {
		return dexterity;
	}

	public void setDexterity(int dexterity) {
		this.dexterity = dexterity;
	}

	public int getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}
	
	
	
}
