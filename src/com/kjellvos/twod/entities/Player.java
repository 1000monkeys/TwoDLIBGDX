package com.kjellvos.twod.entities;

import com.badlogic.ashley.core.Entity;
import com.kjellvos.twod.TwoD;

public class Player extends Entity {
	private int money = 0;
	private long timeSinceLastAttack;
	
	public int getDefense(){
		int defense = 1;
		if (TwoD.getGear().gearItems[0].getItemId() == 3){
			defense += 1;
		}
		if (TwoD.getGear().gearItems[2].getItemId() == 2){
			defense += 2;
		}
		if (TwoD.getGear().gearItems[4].getItemId() == 4) {
			defense += 1;
		}
		if (TwoD.getGear().gearItems[5].getItemId() == 5) {
			defense += 1;
		}
		if (TwoD.getGear().gearItems[6].getItemId() == 7) {
			defense += 1;
		}
		return defense;
	}
	
	public int getAttack(){
		int attack = 1;
		if (TwoD.getGear().gearItems[1].getItemId() == 1) {
			attack += 4;
		}
		if (TwoD.getGear().gearItems[3].getItemId() == 6) {
			attack += 1;
		}
		return attack;
	}

	public int getMoney(){
		return money;
	}
	
	public void setMoney(int money){
		this.money = money;
	}

	public long getTimeSinceLastAttack() {
		return timeSinceLastAttack;
	}
	
	public void setTimeSinceLastAttack(long timeSinceLastAttack){
		this.timeSinceLastAttack = timeSinceLastAttack;
	}
}
