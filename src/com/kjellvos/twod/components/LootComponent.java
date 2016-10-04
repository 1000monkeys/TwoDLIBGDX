package com.kjellvos.twod.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class LootComponent implements Component {
	public Sprite sprite;
	
	public int amountOfMoney = 100;
	
	public LootComponent(Sprite sprite){
		this.sprite = sprite;
	}
	
	public LootComponent(Sprite sprite, int amountOfMoney){
		this.amountOfMoney = amountOfMoney;
	}
}
