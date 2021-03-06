package com.kjellvos.twod.inventory;

import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;

public class InventoryItemTarget extends Target{
	public InventoryItemTarget(InventoryItemActor actor) {
		super(actor);
	}

	public boolean drag(Source source, Payload payload, float x, float y, int pointer) {
		return true;
	}

	public void drop(Source source, Payload payload, float x, float y, int pointer) {
	}
}
