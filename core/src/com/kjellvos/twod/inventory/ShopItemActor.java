package com.kjellvos.twod.inventory;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.kjellvos.twod.TwoD;

public class ShopItemActor extends Actor {
	public int positionId;
	
	public ShopItemActor(int x, int y, int positionId) {
		this.setBounds(x-11, y-32, 79, 102);
		this.positionId = positionId;
	}
	
	public void draw(Batch batch, float parentAlpha){
		if (TwoD.getShop().shopItems[positionId].getSprite() != null && TwoD.getShop().shopItems[positionId].getItemId() != 0) {
			batch.draw(TwoD.getItems().itemsSprites[TwoD.getShop().shopItems[positionId].getItemId()], getX(), getY(), 80, 100);
		}
	}
}
