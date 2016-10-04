package com.kjellvos.twod.entities;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector2;
import com.kjellvos.twod.HealthBar;
import com.kjellvos.twod.TwoD;
import com.kjellvos.twod.components.HealthComponent;
import com.kjellvos.twod.components.SteerableComponent;

public class Monster extends Entity{
	ImmutableArray<Component> components;
	HealthBar healthBar;
	SteerableComponent sc;
	HealthComponent hc;
	
	public Monster(){
		this.healthBar = new HealthBar(this);
	}
	
	public Vector2 getXY(){
		if (sc != null) {
			return sc.getPosition();
		}else{
			return null;
		}
	}
	
	public void initHealthBar(){
		this.components = this.getComponents();		
		sc = (SteerableComponent) components.get(0);
		hc = (HealthComponent) components.get(4);
		TwoD.getMain().stage.addActor(healthBar);
	}

	public int getHealth() {
		return hc.health;
	}

	public float getMaxHealth() {
		return hc.maxHealth;
	}
}
