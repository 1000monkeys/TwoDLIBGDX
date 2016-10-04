package com.kjellvos.twod.components;

import com.badlogic.ashley.core.Component;

public class AttackComponent implements Component{
	public int attack;
	public long timeSinceLastAttack;
	
	public AttackComponent(int attack){
		this.attack = attack;
	}
}
