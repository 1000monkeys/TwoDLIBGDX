package com.kjellvos.twod.inventory;

import com.kjellvos.twod.TwoD;

public class Shop {
	private int[][] shopInventoryPositions;
	public InventoryItem[] shopItems;
	
	public Shop(int[] itemsForSale){
		shopInventoryPositions = new int[16][2];
		shopInventoryPositions[0][0] = 575;
		shopInventoryPositions[0][1] = 565;
		shopInventoryPositions[1][0] = 670;
		shopInventoryPositions[1][1] = 565;
		shopInventoryPositions[2][0] = 765;
		shopInventoryPositions[2][1] = 565;
		shopInventoryPositions[3][0] = 860;
		shopInventoryPositions[3][1] = 565;
		shopInventoryPositions[4][0] = 575;
		shopInventoryPositions[4][1] = 445;
		shopInventoryPositions[5][0] = 670;
		shopInventoryPositions[5][1] = 445;
		shopInventoryPositions[6][0] = 765;
		shopInventoryPositions[6][1] = 445;
		shopInventoryPositions[7][0] = 860;
		shopInventoryPositions[7][1] = 445;
		shopInventoryPositions[8][0] = 575;
		shopInventoryPositions[8][1] = 325;
		shopInventoryPositions[9][0] = 670;
		shopInventoryPositions[9][1] = 325;
		shopInventoryPositions[10][0] = 765;
		shopInventoryPositions[10][1] = 325;
		shopInventoryPositions[11][0] = 860;
		shopInventoryPositions[11][1] = 325;
		shopInventoryPositions[12][0] = 575;
		shopInventoryPositions[12][1] = 205;
		shopInventoryPositions[13][0] = 670;
		shopInventoryPositions[13][1] = 205;
		shopInventoryPositions[14][0] = 765;
		shopInventoryPositions[14][1] = 205;
		shopInventoryPositions[15][0] = 860;
		shopInventoryPositions[15][1] = 205;

		
		shopItems = new InventoryItem[16];
		
		for (int i = 0; i < shopItems.length; i++) {
			shopItems[i] = new InventoryItem(0, null);
			ShopItemActor actor = new ShopItemActor(shopInventoryPositions[i][0], shopInventoryPositions[i][1], i);
			ShopItemTarget target = new ShopItemTarget(actor);
			ShopItemSource source = new ShopItemSource(actor);
			TwoD.getShopMenu().dnd.addTarget(target);
			TwoD.getShopMenu().dnd.addSource(source);
			TwoD.getShopMenu().stage.addActor(actor);
		}
		
		for (int i = 0; i < itemsForSale.length; i++){
			addToInventory(itemsForSale[i]);
		}
	}
	
	public boolean addToInventory(int itemId){
		for (int i = 0; i < shopItems.length; i++) {
			if (shopItems[i].getItemId() == 0 && shopItems[i].getSprite() == null) {
				shopItems[i].setItemId(itemId);
				shopItems[i].setSprite(TwoD.getItems().itemsSprites[itemId]);
				return true;
			}
		}
		return false;
	}
}
