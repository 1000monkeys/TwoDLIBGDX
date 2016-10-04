package com.kjellvos.twod.components;

import com.badlogic.ashley.core.Component;

public class ItemComponent implements Component {
	public int itemId;
	
	public ItemComponent(int itemId){
		this.itemId = itemId;
	}
}
