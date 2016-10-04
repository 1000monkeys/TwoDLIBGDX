package com.kjellvos.twod.components;

import com.badlogic.ashley.core.Component;

public class ShopKeeperComponent implements Component {
	public int[] itemsForSale;
	
	public ShopKeeperComponent(int[] itemsForSale){
		this.itemsForSale = itemsForSale;
	}
}
