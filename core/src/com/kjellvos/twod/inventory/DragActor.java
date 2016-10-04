package com.kjellvos.twod.inventory;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class DragActor extends Actor {
	Sprite sprite;
	
	public DragActor(Sprite sprite) {
		this.sprite = sprite;
	}

	public void draw(Batch batch, float parentAlpha){
		batch.draw(sprite, getX(), getY());
	}
}
