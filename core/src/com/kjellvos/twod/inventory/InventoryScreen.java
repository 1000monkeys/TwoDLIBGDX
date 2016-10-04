package com.kjellvos.twod.inventory;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class InventoryScreen extends Actor{
	private Sprite sprite = new Sprite(new Texture("InventoryAchtergrond.png"));
	
	public InventoryScreen(){
        sprite.setSize(512, 786);
        sprite.setCenterX(256);
        sprite.setCenterY(393);
	}
	
	public void draw(Batch batch, float parentAlpha){
		sprite.draw(batch);
	}
}
