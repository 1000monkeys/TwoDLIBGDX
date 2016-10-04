package com.kjellvos.twod.inventory;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.kjellvos.twod.TwoD;

public class GearItemActor extends Actor{
	public int positionId;
	
	public GearItemActor(int x, int y, int positionId) {
		this.setBounds(x-11, y-32, 79, 102);
		this.positionId = positionId;
	}
	
	public void draw(Batch batch, float parentAlpha){
		if (TwoD.getGear().gearItems[positionId].getSprite() != null && TwoD.getGear().gearItems[positionId].getItemId() != 0) {
			batch.draw(TwoD.getItems().itemsSprites[TwoD.getGear().gearItems[positionId].getItemId()], getX(), getY(), 80, 100);
		}
	}
}
