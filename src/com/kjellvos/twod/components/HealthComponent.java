package com.kjellvos.twod.components;

import com.badlogic.ashley.core.Component;

public class HealthComponent implements Component {
	public int health;
	public int maxHealth;
	
	public HealthComponent(int health){
		this.health = health;
		this.maxHealth = health;
	}
}
