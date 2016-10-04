package com.kjellvos.twod.inventory;

import com.kjellvos.twod.TwoD;

public class Inventory {
	private int[][] inventoryPositions;
	public InventoryItem[] items;
	
	public Inventory(){
		inventoryPositions = new int[16][2];
		inventoryPositions[0][0] = 75;
		inventoryPositions[0][1] = 565;
		inventoryPositions[1][0] = 170;
		inventoryPositions[1][1] = 565;
		inventoryPositions[2][0] = 265;
		inventoryPositions[2][1] = 565;
		inventoryPositions[3][0] = 360;
		inventoryPositions[3][1] = 565;
		inventoryPositions[4][0] = 75;
		inventoryPositions[4][1] = 445;
		inventoryPositions[5][0] = 170;
		inventoryPositions[5][1] = 445;
		inventoryPositions[6][0] = 265;
		inventoryPositions[6][1] = 445;
		inventoryPositions[7][0] = 360;
		inventoryPositions[7][1] = 445;
		inventoryPositions[8][0] = 75;
		inventoryPositions[8][1] = 325;
		inventoryPositions[9][0] = 170;
		inventoryPositions[9][1] = 325;
		inventoryPositions[10][0] = 265;
		inventoryPositions[10][1] = 325;
		inventoryPositions[11][0] = 360;
		inventoryPositions[11][1] = 325;
		inventoryPositions[12][0] = 75;
		inventoryPositions[12][1] = 205;
		inventoryPositions[13][0] = 170;
		inventoryPositions[13][1] = 205;
		inventoryPositions[14][0] = 265;
		inventoryPositions[14][1] = 205;
		inventoryPositions[15][0] = 360;
		inventoryPositions[15][1] = 205;
		
		items = new InventoryItem[16];
		
		for (int i = 0; i < items.length; i++) {
			items[i] = new InventoryItem(0, null);
			InventoryItemActor actor = new InventoryItemActor(inventoryPositions[i][0], inventoryPositions[i][1], i);
			InventoryItemTarget target = new InventoryItemTarget(actor);
			InventoryItemSource source = new InventoryItemSource(actor);
			TwoD.getMenu().dnd.addTarget(target);
			TwoD.getMenu().dnd.addSource(source);
			TwoD.getMenu().stage.addActor(actor);
			actor = new InventoryItemActor(inventoryPositions[i][0], inventoryPositions[i][1], i);
			target = new InventoryItemTarget(actor);
			source = new InventoryItemSource(actor);
			TwoD.getShopMenu().dnd.addTarget(target);
			TwoD.getShopMenu().dnd.addSource(source);
			TwoD.getShopMenu().stage.addActor(actor);
		}
		
		for (int i = 1; i < 8; i++) {
			addToInventory(i);
		}
	}
	
	public boolean addToInventory(int itemId){
		for (int i = 0; i < items.length; i++) {
			if (items[i].getItemId() == 0 && items[i].getSprite() == null) {
				items[i].setItemId(itemId);
				items[i].setSprite(TwoD.getItems().itemsSprites[itemId]);
				return true;
			}
		}
		return false;
	}
}
