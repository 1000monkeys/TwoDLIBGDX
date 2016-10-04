package com.kjellvos.twod.components;

import com.badlogic.ashley.core.Component;

public class MoneyComponent implements Component{
	public int amountOfMoney;
	
	public MoneyComponent(int amountOfMoney) {
		this.amountOfMoney = amountOfMoney;
	}
}
