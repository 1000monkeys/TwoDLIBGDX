package com.kjellvos.twod.inventory;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class InventoryItem {
	private int itemId;
	private Sprite sprite;
	
	public InventoryItem(int itemId, Sprite sprite){
		this.itemId = itemId;
		this.sprite = sprite;
	}
	
	public int getItemId(){
		return itemId;
	}
	
	public void setItemId(int itemId){
		this.itemId = itemId;
	}
	
	public Sprite getSprite(){
		return sprite;
	}
	
	public void setSprite(Sprite sprite){
		this.sprite = sprite;
	}
}
